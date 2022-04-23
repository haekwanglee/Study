//graph list
#include <iostream>
using namespace std;

struct NODE 
{ 
	int v;  //연결된 노드 정보
	//int value; //가중치가 있는 경우 사용.
	NODE* prev; 
}a[100];

int heap_cnt;
NODE* myalloc(void) 
{ 
	return &a[heap_cnt++]; 
}
NODE* mem[10];

int main(void) 
{ 
	freopen("graph_input.txt", "r", stdin);
	int node, edge, from, to;
	cin >> node >> edge;
	//초기화
	heap_cnt = 0;
	for (int i = 0; i < node; i++) 
		mem[i] = NULL;
	
	NODE* n;
	//간선 정보 입력
	for (int e = 0; e < edge; e++)
	{ 
		cin >> from >> to;
		n = myalloc();
		n->v = to;  //연결된 노드를 저장
		//n->value = input_value;  // 가중치 저장.
		n->prev = mem[from];  //from 의 Single linked list 에 저장
		mem[from] = n; 
	}

	//모든 map 정보를 표시.
	cout << "Print all map info " << endl;
	for (int i = 1; i <= node; i++)
	{ 
		cout << "from " << i << " to ";
		for (NODE* iter = mem[i]; iter != NULL; iter = iter->prev) 
			cout << iter->v << " " << endl; 
	}
	cout << endl << endl;

	//1번에 연결된 노드 검색.
	cout << "Searching edge from 1 to  ";
	for (NODE* iter = mem[1]; iter != NULL; iter = iter->prev)  // table[1] 의 Single linked list 에서 검색 
	{
		cout << iter->v << " , "; 
	}
	cout << endl; 
} 