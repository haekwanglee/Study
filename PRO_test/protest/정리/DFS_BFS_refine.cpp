#include <iostream>
using namespace std;
///////////////////
int data[5][5] = 
	{
		{0 ,0 ,0 ,0 ,1},
		{0 ,1, 1 ,1 ,0},
		{0 ,1 ,0 ,0 ,0},
		{0 ,1 ,0 ,1 ,1},
		{1 ,0 ,0 ,1, 1},
	};
///////////////////

//왼, 아래, 오른, 위
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, 1, 0, -1};
int current = 1; // visit map?

struct NODE
{
	int x;
	int y;
	NODE* prev;
	NODE* next;
}node[25];

int idx = 0;
NODE* myalloc()
{
	node[idx].x = 0;
	node[idx].y = 0;
	node[idx].next = NULL;
	node[idx].prev = NULL;
	return &node[idx++];
}

struct QUEUE
{
	NODE* head;
	NODE* tail;
	int count;
};

struct STACK
{
	NODE* top;
	int count;
};


void BFS(int x, int y)
{
	NODE* start = myalloc();
	start->x = x;
	start->y = y;

	QUEUE queue;
	queue.count = 0;
	queue.head = start;
	queue.tail = start;
	queue.tail->prev = NULL;
	queue.count += 1;

	current++;

	int nx, ny = 0;
	while(queue.count != 0)
	{
		
		for(int i=0; i<4; i++)
		{
			nx = x + dx[i];
			ny = y + dy[i];

			if(ny >=0 && ny <5 && nx >=0 && nx <5 && 
				data[ny][nx] == 1)
			{
				NODE* node = myalloc();
				node->x = nx;
				node->y = ny;
				queue.tail->prev = node;
				queue.tail = node;
				queue.tail->prev = NULL;
				queue.count += 1;
			}
		}

		//pop and check
		if(queue.head)
		{
			x = queue.head->x;
			y = queue.head->y;
			queue.count -= 1;
			queue.head = queue.head->prev;
			data[y][x] = current;
		}
	}	
}



void DFS(int x, int y)
{
	NODE* start = myalloc();
	start->x = x;
	start->y = y;

	STACK stack;
	stack.count = 0;
	stack.top = start;
	stack.top->prev = NULL;
	stack.top->next = NULL;
	stack.count += 1;

	current++;

	int nx, ny = 0;
	while(stack.count != 0)
	{		
		for(int i=0; i<4; i++)
		{
			nx = x + dx[i];
			ny = y + dy[i];

			if(ny >=0 && ny <5 && nx >=0 && nx <5 && 
				data[ny][nx] == 1)
			{
				NODE* node = myalloc();
				node->x = nx;
				node->y = ny;
				node->next = stack.top;
				stack.top->prev = node;
				stack.top = node;
				stack.top->prev = NULL;
				stack.count += 1;
			}
		}

		//pop and check
		if(stack.top)
		{
			x = stack.top->x;
			y = stack.top->y;
			stack.count -= 1;
			stack.top = stack.top->next;
			if(stack.top)
			{
				stack.top->prev = NULL;
			}
			data[y][x] = current;
		}
	}	
}

int main()
{
	

	
	for(int i = 0; i<5; i++)
	{
		for(int j = 0; j<5; j++)
		{
			if(data[i][j] == 1)
			{
				BFS(j,i);
				//DFS(j,i);
			}
		}
	}

	return 0;
}