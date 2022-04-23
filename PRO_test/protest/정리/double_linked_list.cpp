#include <iostream>
using namespace std;
int arr_idx = 0;
struct NODE 
{ 
	int v;
	NODE* prev;  //for single linked list
	NODE* next;  //for double linked list 
} a[20000];

NODE* myalloc(void) 
{ 
	return &a[arr_idx++]; 
}

NODE* pTail;  //더블 링크드 리스트의 끝. (싱글 링크드 리스트의 pList의 역활) 
NODE* pHead;  //더블 링크드 리스트의 시작.	
//oid print_node(void);

int main(void) 
{ 
	arr_idx = 0;  //사용할 배열 초기화
	pHead = myalloc();  //Head 노드를 미리 만들어둠.
	pTail = myalloc();  //Tail 노드를 미리 만들어둠.
	pTail->prev = pHead;  //pHead <-> pTail 을 연결
	pHead->next = pTail;
	
	// 노드 추가..
	NODE* p;
	for (int i = 1; i < 10; i++)
	{ 
		p = myalloc();
		p->v = i;

		//prev 연결.
		p->prev = pTail->prev; 
		pTail->prev = p;		
		
		//next 연결. 
		p->next = p->prev->next;  //이전노드가 가르키던걸 내가 가르키고.
		p->prev->next = p;        //이전 노드는 나를 가르키게 만듬. 
	}
	//print_node();
	//HEAD 부터 검색해서 노드(5) 삭제
	for (NODE * iter = pHead->next; iter != pTail; iter = iter->next)  //HEAD 에서 모든 노드를 따라가면서.. 
	{
		if (iter->v == 5)  //노드(5)를 찾으면
		{ 
			iter->prev->next = iter->next;  //이전 노드의 다음(next)은 내가 가르키던 다음(next)을 
			iter->next->prev = iter->prev;  //다음 노드의 이전(next)은 내가 가르키던 이전(prev)을
			cout << "Delete 5 node ... " << endl << endl; 
		} 
	}
	//print_node();

	return 0;

}