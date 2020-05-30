#include "graph.h"
#include <limits.h>
#include <stdlib.h>
#include "dijgraph.h"
#define INFINITY INT_MAX


Queue createQueue(unsigned size){
	Queue queue=(Queue)malloc(sizeof(Queue));
	queue->size=size;
	queue->array=(Node *)calloc(size,sizeof(Node *));
	return queue;
}

void enqueueAll(Queue queue,Node*array){
	for (unsigned i=0;i<queue->size;i++){
		queue->array[i]=array[i];
	}
}



void dijkstra(Graph graph,Node startingNode, Node endingNode){
	int *dist=calloc(graph->nbrVertices, sizeof(int));
	Node *previous=calloc(graph->nbrVertices, sizeof(*previous));
	Queue queue=createQueue(graph->nbrVertices);
	//initialization
	for (int i=0;i<graph->nbrVertices;i++){
		dist[i]=INFINITY;
		previous[i]=NULL;
	}
	dist[startingNode->id]=0;
	enqueueAll(queue,graph->vertices);
	while (queue->size>0){
		//get the smallest distance
        int min_dist=INFINITY;
        int index_min_in_queue=-1;
        for (unsigned i=0;i<queue->size;i++){
            if (dist[queue->array[i]->id]<min_dist){
                min_dist=dist[queue->array[i]->id];
                index_min_in_queue=i;
            }
        }
        //get u
        Node min=queue->array[index_min_in_queue];
        //remove u from the Queue
        Node temp=queue->array[queue->size-1];
        queue->array[queue->size-1]=min;
        queue->array[index_min_in_queue]=temp;
        queue->size=queue->size-1;

		list=graph->adjacencyList[min->id];
		while(list->next != NULL){

			Node next=list->next->current;

			alt=min_dist+getWeight(graph,min,next);

			for(unsigned i;i<queue->size;i++){
				if(queue->array[i]==next){
					if(alt<dist[next->id]){
						dist[next->id]=alt;
						previous[next->id]=min;
					}
				}
			}

			list->next=list->next->current->next;

		}



	}

	int main(){
		return 0;
	}

}

/*
1:	function Dijkstra(Graph, source):
2:		for each vertex v in Graph:	// Initialization
3:		dist[v] := infinity	// initial distance from source to vertex v is set to infinite
4:		previous[v] := undefined	// Previous node in optimal path from source
5:		dist[source] := 0	// Distance from source to source
6:		Q := the set of all nodes in Graph	// all nodes in the graph are unoptimized - thus are in Q
7:		while Q is not empty:	// main loop
8:			u := node in Q with smallest dist[ ]
9:			remove u from Q
10:			for each neighbor v of u:	// where v has not yet been removed from Q.
11:				alt := dist[u] + dist_between(u, v)
12:				if alt < dist[v]	// Relax (u,v)
13:					dist[v] := alt
14:					previous[v] := u
15:		return previous[ ]
*/