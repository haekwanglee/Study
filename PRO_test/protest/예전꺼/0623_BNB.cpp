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

struct BNB
{
	int x;
	int y;
	int bid;
	int price;
	int rating;	
	BNB* next;
}bnb[20];

int idx = 0;
BNB* myalloc()
{
	bnb[idx].x = 0;
	bnb[idx].y = 0;
	bnb[idx].bid = 0;
	bnb[idx].price = 0;
	bnb[idx].rating = 0;
	bnb[idx].next = NULL;
	return &bnb[idx++];
}


//추가할때마다 비교해서 우선순위 확정
struct BNBMAP
{
	BNB* head;
	int count;
};
BNBMAP bnbmap[10][10] = {0,};


void addBNB(int x, int y, int bid, int price, int rating)
{
	BNB* b = myalloc();
	b->x = x;
	b->y = y;
	b->bid = bid;
	b->price = price;
	b->rating = rating;
	b->next = NULL;

	BNB** curPtr = &(bnbmap[y][x].head);
	BNB* tempPtr = NULL;
	while(1)
	{
		if((*curPtr) == NULL)
		{
			(*curPtr) = b;
			bnbmap[y][x].count += 1;
			break;
		}
		if((*curPtr)->price > price)
		{
			tempPtr = (*curPtr);
			(*curPtr) = b;
			(*curPtr)->next = tempPtr;
			bnbmap[y][x].count += 1;
			break;
		}
		curPtr = &((*curPtr)->next);
		//(*curPtr) = (*curPtr)->next;
	}	
}



//10개까지만 한계가 있다면 우선순위를 위해 heap 사용?
struct BNBMAP_heap
{
	BNB* heap[11]; //1부터 시작
	int count;
};
BNBMAP_heap bnbmap_h[10][10] = {0,};

BNB* temp = NULL;
void addBNB_h(int x, int y, int bid, int price, int rating)
{
	BNB* b = myalloc();
	b->x = x;
	b->y = y;
	b->bid = bid;
	b->price = price;
	b->rating = rating;
	b->next = NULL;
	
	if(bnbmap_h[y][x].count == 0)
	{
		bnbmap_h[y][x].heap[1] = b;
		bnbmap_h[y][x].count +=1;
		return;
	}

	int child = bnbmap_h[y][x].count+1; 
	int parent = child/2;
	bnbmap_h[y][x].heap[child] = b;
	while( bnbmap_h[y][x].heap[parent] && child >1 && bnbmap_h[y][x].heap[child]->price > bnbmap_h[y][x].heap[parent]->price )
	{
		temp = bnbmap_h[y][x].heap[parent];
		bnbmap_h[y][x].heap[parent] = bnbmap_h[y][x].heap[child];
		bnbmap_h[y][x].heap[child] = temp;

		child = parent;
		parent = child /2;

		bnbmap_h[y][x].count += 1;
	}
}


int main()
{
	/*
	addBNB(0,0,0,500,0);
	addBNB(0,0,0,700,0);
	addBNB(0,0,0,800,0);
	addBNB(0,0,0,600,0);
	*/

	addBNB_h(0,0,0,500,0);
	addBNB_h(0,0,0,700,0);
	addBNB_h(0,0,0,800,0);
	addBNB_h(0,0,0,600,0);
	return 0;
}