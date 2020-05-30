#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BSIZE 80

int main()
{
	char filename[] = "airports.csv";
	char buffer[BSIZE];
	FILE *f;
	char *field;
	int id;
	int actualId;
	char *name;
	double latitude;
	double longitude;
	/* on ouvre le fichier csv */
	f = fopen(filename,"r");
	if( f == NULL)
	{
		printf("Impossible d'ouvrir le fichier '%s'\n",filename);
		exit(1);
	}

	
	/* le fichier contient 5 champs dans l'ordre suivant:
	   id,actualId,name,latitude,longitude*/
	while(fgets(buffer,BSIZE,f))
	{
		/* lecture id */
		field=strtok(buffer,",");
		id=atoi(field);
		/* lecture actualId */
		field=strtok(NULL,",");
		actualId=atoi(field);
	
		/* lecture name */
		field=strtok(NULL,",");
		name=atoi(field);
		/* lecture latitude */
		field=strtok(NULL,",");
		latitude=atof(field);
		/*  lecture longitude */
		field=strtok(NULL,",");
		longitude=atof(field);
		/*  
		printf("%d %d %s %f %f \n",
				id,
				actualId,
				name,
				latitude,
				long);*/
	}

	/* close file */
	fclose(f);

	return(0);
}
