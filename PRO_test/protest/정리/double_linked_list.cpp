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

NODE* pTail;  //���� ��ũ�� ����Ʈ�� ��. (�̱� ��ũ�� ����Ʈ�� pList�� ��Ȱ) 
NODE* pHead;  //���� ��ũ�� ����Ʈ�� ����.	
//oid print_node(void);

int main(void) 
{ 
	arr_idx = 0;  //����� �迭 �ʱ�ȭ
	pHead = myalloc();  //Head ��带 �̸� ������.
	pTail = myalloc();  //Tail ��带 �̸� ������.
	pTail->prev = pHead;  //pHead <-> pTail �� ����
	pHead->next = pTail;
	
	// ��� �߰�..
	NODE* p;
	for (int i = 1; i < 10; i++)
	{ 
		p = myalloc();
		p->v = i;

		//prev ����.
		p->prev = pTail->prev; 
		pTail->prev = p;		
		
		//next ����. 
		p->next = p->prev->next;  //������尡 ����Ű���� ���� ����Ű��.
		p->prev->next = p;        //���� ���� ���� ����Ű�� ����. 
	}
	//print_node();
	//HEAD ���� �˻��ؼ� ���(5) ����
	for (NODE * iter = pHead->next; iter != pTail; iter = iter->next)  //HEAD ���� ��� ��带 ���󰡸鼭.. 
	{
		if (iter->v == 5)  //���(5)�� ã����
		{ 
			iter->prev->next = iter->next;  //���� ����� ����(next)�� ���� ����Ű�� ����(next)�� 
			iter->next->prev = iter->prev;  //���� ����� ����(next)�� ���� ����Ű�� ����(prev)��
			cout << "Delete 5 node ... " << endl << endl; 
		} 
	}
	//print_node();

	return 0;

}