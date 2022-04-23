#include <iostream>
using namespace std;
#define MAX_TABLE 10
int arr_idx = 0;
struct NODE { 
	char data[7];
	NODE* next;  //�̱� ����Ʈ�� ���� �߰�. 
} a[20000];

NODE* myalloc(void) 
{ 
	return &a[arr_idx++]; 
}
NODE* hTable[MAX_TABLE];
unsigned long myhash(const char *str) 
{ 
	unsigned long hash = 5381;
	int c;
	while (c = *str++)
	{ 
		hash = (((hash << 5) + hash) + c) % MAX_TABLE; 
	}
	return hash % MAX_TABLE; 
}

int main(void) 
{ 
	freopen("input.txt", "r", stdin);
	//	freopen("output.txt", "w", stdout);
	//for (int i = 0; i < 100; i++)
	//{
	//	for (int j = 0; j < 6; j++)
	//	cout << (char)(rand() % 26 + 'a');
	//	cout << endl;
	//}
	arr_idx = 0;  //����� �迭 �ʱ�ȭ
	for (int i = 0; i < MAX_TABLE; i++)
	{
		hTable[i] = nullptr;
	}
	NODE *p;
	int key;
	char in[7];
	int test_case;
	cin >> test_case;
	//Hash table �߰�
	for (int T = 0; T < test_case; T++)
	{ 
		cin >> in;  //���ڿ��� �Է� �޽��ϴ�.
		key = (int)myhash(in);  //���ڿ��� �������� key �� �����մϴ�.
		cout << "Add to Hash table " << key << " : " << in << endl;
		p = myalloc();  //������ ������ alloc �մϴ�.
		strncpy(p->data, in, 7);  //�Է¹��� ���ڿ��� �����մϴ�.
		p->next = hTable[key];  //Hash table �� �����մϴ�. ���� key�� ��Ƽ� ����Ʈ�� ���߿� �˻��Ϸ���.
		hTable[key] = p;
		//�߰��� ��� ��� Ȯ��
		for (int _tKey = 0; _tKey < MAX_TABLE; _tKey++)
		{ cout << "Hash table( " << _tKey << " ) :";
			for (NODE * pp = hTable[_tKey]; pp != NULL; pp = pp->next)
			{ cout << " " << pp->data; }
			cout << endl; }
		cout << endl << endl; }
	//Hash table �˻�
	char search[7] = "vrvipy";
	cout << "Searching : " << search << endl;
	int k = (int)myhash(search);  // hash key ����
	cout << "Hash table key : " << k << endl;
	//hash table [k] ���� �˻�
	for (NODE * pp = hTable[k]; pp != NULL; pp = pp->next)
	{ 
		if (!strncmp(search, pp->data, 6)) cout << "FOUND : " << pp->data << endl << endl; 
	}
	NODE **del = &hTable[k];  //���� ����� ��ġ�� ������ ������ ����
	for (NODE * iter = hTable[k]; iter != nullptr; iter = iter->next)  //hTable[k]���� ��� �˻� 
	{ 
		if (!strncmp(search, iter->data, 6)) 
		{   
			//������ �����͸� ã����
			cout << "FOUND & DEL : " << iter->data << endl << endl;
			*del = iter->next;   //���� ��忡 ����� next ��带 ���� ��忡 ���� 
		}
		del = &iter->next;  //���� ��带 ���� 
	}
	//���� Ȯ��
	cout << "Check delete";
	for (NODE *iter = hTable[k]; iter != nullptr; iter = iter->next)
	{ if (!strncmp(search, iter->data, 6)) cout << "FOUND : " << iter->data << endl << endl; } } 
    
    *input 
    9 
    1 2
    1 3 
    1 4 
    2 5 
    2 6 
    6 7 
    4 8 
    4 9 
    4 10 
		*/
		/////////////////////////////////////////////////////////////////////////////////////////////
