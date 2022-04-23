//배열 동적할당 흉내
#include <iostream>
using namespace std;

/*
매 case 가 시작할 때마다 arr_idx = 0 으로 초기화만으로 충분합니다.
당연히 NODE 구조체에 변수를 추가하여 여러가지 사항을 만들어서 사용하실 수도 있습니다.
가장 많이 사용하는 single linked list 는 아래와 같이 사용 할 수 있습니다.
*/


int arr_idx = 0;
struct NODE 
{
    int v;
    NODE* prev;  //싱글 리스트를 위해 추가. 
}a[10000];

NODE* myalloc(void)
{
    return &a[arr_idx++];
}

void main(void) 
{ 
	NODE* pList = NULL; // 싱글 링크드 리스트의 시작
    NODE* p;
    arr_idx = 0;  // 배열 초기화
    //첫번째 노드(1) 추가
    p = myalloc();
    p->v = 1;
    p->prev = pList;
    pList = p;
    //두번째 노드(2) 추가
    p = myalloc();
    p->v = 2;
    p->prev = pList;
    pList = p;
    //추가된 노드 확인
    for(NODE* pp = pList; pp != NULL; pp = pp->prev)
    { 
		cout << pp->v << " "; 
	}
}


/*
struct NODE
{
	int v;
	NODE* next;
	NODE* prev;
};
struct NODE a[100];

int idx = 0;
NODE* myalloc(void)
{
	return &a[idx++];
}

void main(void)
{
	idx = 0;
	NODE* pList = NULL;	
	NODE* p;
	
	for(int i = 0; i<5; i++)
	{
		p  = myalloc();	
		p->v = i;
		p->prev = pList;	
		p->next = NULL;		
		if(p->prev != NULL) p->prev->next = p;
		pList = p;
	}
	
	while(1)
	{
		cout << pList->v << endl;
		if(pList->prev == NULL)
		{
			break;
		}
		pList = pList->prev;
	}	
	while(1)
	{
		cout << pList->v << endl;
		if(pList->next == NULL)
		{
			break;
		}
		pList = pList->next;
	}

	return;
}
*/
