#include <iostream>
using namespace std;
#define MAX_TABLE 10
int arr_idx = 0;
struct NODE { 
	char data[7];
	NODE* next;  //싱글 리스트를 위해 추가. 
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
	arr_idx = 0;  //사용할 배열 초기화
	for (int i = 0; i < MAX_TABLE; i++)
	{
		hTable[i] = nullptr;
	}
	NODE *p;
	int key;
	char in[7];
	int test_case;
	cin >> test_case;
	//Hash table 추가
	for (int T = 0; T < test_case; T++)
	{ 
		cin >> in;  //문자열을 입력 받습니다.
		key = (int)myhash(in);  //문자열을 바탕으로 key 를 생성합니다.
		cout << "Add to Hash table " << key << " : " << in << endl;
		p = myalloc();  //저장할 공간을 alloc 합니다.
		strncpy(p->data, in, 7);  //입력받은 문자열을 복사합니다.
		p->next = hTable[key];  //Hash table 에 저장합니다. 같은 key로 모아서 리스트를 나중에 검색하려고.
		hTable[key] = p;
		//추가된 모든 노드 확인
		for (int _tKey = 0; _tKey < MAX_TABLE; _tKey++)
		{ cout << "Hash table( " << _tKey << " ) :";
			for (NODE * pp = hTable[_tKey]; pp != NULL; pp = pp->next)
			{ cout << " " << pp->data; }
			cout << endl; }
		cout << endl << endl; }
	//Hash table 검색
	char search[7] = "vrvipy";
	cout << "Searching : " << search << endl;
	int k = (int)myhash(search);  // hash key 생성
	cout << "Hash table key : " << k << endl;
	//hash table [k] 에서 검색
	for (NODE * pp = hTable[k]; pp != NULL; pp = pp->next)
	{ 
		if (!strncmp(search, pp->data, 6)) cout << "FOUND : " << pp->data << endl << endl; 
	}
	NODE **del = &hTable[k];  //이전 노드의 위치를 저장할 포인터 변수
	for (NODE * iter = hTable[k]; iter != nullptr; iter = iter->next)  //hTable[k]에서 모두 검색 
	{ 
		if (!strncmp(search, iter->data, 6)) 
		{   
			//삭제할 데이터를 찾으면
			cout << "FOUND & DEL : " << iter->data << endl << endl;
			*del = iter->next;   //현재 노드에 연결된 next 노드를 다음 노드에 저장 
		}
		del = &iter->next;  //이전 노드를 변경 
	}
	//삭제 확인
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
