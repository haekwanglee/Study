//�迭 �����Ҵ� �䳻
#include <iostream>
using namespace std;

/*
�� case �� ������ ������ arr_idx = 0 ���� �ʱ�ȭ������ ����մϴ�.
�翬�� NODE ����ü�� ������ �߰��Ͽ� �������� ������ ���� ����Ͻ� ���� �ֽ��ϴ�.
���� ���� ����ϴ� single linked list �� �Ʒ��� ���� ��� �� �� �ֽ��ϴ�.
*/


int arr_idx = 0;
struct NODE 
{
    int v;
    NODE* prev;  //�̱� ����Ʈ�� ���� �߰�. 
}a[10000];

NODE* myalloc(void)
{
    return &a[arr_idx++];
}

void main(void) 
{ 
	NODE* pList = NULL; // �̱� ��ũ�� ����Ʈ�� ����
    NODE* p;
    arr_idx = 0;  // �迭 �ʱ�ȭ
    //ù��° ���(1) �߰�
    p = myalloc();
    p->v = 1;
    p->prev = pList;
    pList = p;
    //�ι�° ���(2) �߰�
    p = myalloc();
    p->v = 2;
    p->prev = pList;
    pList = p;
    //�߰��� ��� Ȯ��
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
