#include <stdio.h>
#include <stdlib.h>

#include "../CJSON/cJSON.h"

char* read_file(const char* filename)
{
	FILE* f = fopen(filename, "rb");
	if (!f) {
		return NULL;
	}

	fseek(f, 0, SEEK_END);
	int size = ftell(f);
	char* buff = NULL;
	if (size > 0) {
		buff = (char*)malloc(size + 1);
		if (buff) {
			fseek(f, 0, SEEK_SET);
			fread(buff, size, 1, f);
			buff[size] = '\0';
		}
	}
	fclose(f);

	return buff;
}

int main()
{
	char* route_json = read_file("../routes.json");
	if (!route_json) {
		puts("Could not read 'routes.json'. Check if the file exists in the current path");
		return -1;
	}
	cJSON *elem;
	cJSON *name;
	cJSON *root = cJSON_Parse(route_json);
	int n = cJSON_GetArraySize(root);
	for (int i = 0; i < n; i++) {
		elem = cJSON_GetArrayItem(root, i);
		name = cJSON_GetObjectItem(elem, "name");
		printf("%s\n", name->valuestring);
	}

	return 0;
}