//graph list
#include <iostream>
using namespace std;

struct NODE 
{ 
	int v;  //����� ��� ����
	//int value; //����ġ�� �ִ� ��� ���.
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
	//�ʱ�ȭ
	heap_cnt = 0;
	for (int i = 0; i < node; i++) 
		mem[i] = NULL;
	
	NODE* n;
	//���� ���� �Է�
	for (int e = 0; e < edge; e++)
	{ 
		cin >> from >> to;
		n = myalloc();
		n->v = to;  //����� ��带 ����
		//n->value = input_value;  // ����ġ ����.
		n->prev = mem[from];  //from �� Single linked list �� ����
		mem[from] = n; 
	}

	//��� map ������ ǥ��.
	cout << "Print all map info " << endl;
	for (int i = 1; i <= node; i++)
	{ 
		cout << "from " << i << " to ";
		for (NODE* iter = mem[i]; iter != NULL; iter = iter->prev) 
			cout << iter->v << " " << endl; 
	}
	cout << endl << endl;

	//1���� ����� ��� �˻�.
	cout << "Searching edge from 1 to  ";
	for (NODE* iter = mem[1]; iter != NULL; iter = iter->prev)  // table[1] �� Single linked list ���� �˻� 
	{
		cout << iter->v << " , "; 
	}
	cout << endl; 
} 