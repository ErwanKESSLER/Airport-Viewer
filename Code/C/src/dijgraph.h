#ifndef PPII_DIJGRAPH_H
#define PPII_DIJGRAPH_H
struct _Queue{
	Node * array;
	unsigned size;
};
typedef struct _Queue *Queue;
void dijkstra(Graph graph,Node startingNode, Node endingNode);
#endif //PPII_DIJGRAPH_H
