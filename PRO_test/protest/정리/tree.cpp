#include <iostream>
using namespace std;
void traversing_tree(int node);
#define MAX_NODE 11
int arr_idx = 0;
struct NODE { 
	int id;    //id 번호
	NODE * prev;  //싱글 리스트를 위해 추가. 
} a[20000];
NODE* myalloc(void) 
{ 
	return &a[arr_idx++]; 
}
int parent_info[MAX_NODE]; // => NODE  타입이면 될거 같은디? 특정 index의 parent에 바로 접근할수 있는 배열 아이디어.
NODE* child_info[MAX_NODE]; //특정 index의 child들을 SLL로 묶어놓는 아이디어, NODE* child_info[0] <= 0번 NODE의 child접근하는 head정보.


//아님 각 NODE마다 parent. 포인터갖고있기, prev로 되어있지만 child로 명명하는게 직관적?

int main(void) 
{ 
	freopen("input.txt", "r", stdin);
	//freopen("output.txt", "w", stdout);

	arr_idx = 0;  //사용할 힙 배열 초기화
	for (int i = 0; i < MAX_NODE; i++)  //tree 정보 초기화
	{ 
		child_info[i] = NULL;
		parent_info[i] = -1; 
	}

	NODE* p;
	int parent, child;
	int test_case;
	cin >> test_case;
	//tree 정보 추가
	for (int T = 0; T < test_case; T++)
	{ 
		cin >> parent >> child;  //부모 - 자식간의 연결정보
		parent_info[child] = parent;  //부모 정보를 child에 저장
		p = myalloc();  //저장할 공간을 alloc 합니다.
		p->id = child;
		p->prev = child_info[parent];  //parent 의 linked list 에 child 정보를 저장합니다. 
		child_info[parent] = p;
		//추가된 모든 노드 확인
		cout << "Tree info" << endl;
		for (int tNode = 0; tNode < MAX_NODE; tNode++)
		{ 
			cout << "( " << tNode << " )'s parent = " << parent_info[tNode] << " child = "; 
			for (NODE * pp = child_info[tNode]; pp != nullptr; pp = pp->prev)
			{ 
				cout << " " << pp->id; 
			}
			cout << endl; 
		}
		cout << endl << endl; 
	}
	
	//루트 노드 찾기...
	int node;
	for (node = MAX_NODE-1; node >= 0; node--)  //부모가 있는 노드 아무거나 검색 (1이 루트라, 일부러 뒤에서 검색) 
		if (parent_info[node] != -1) break;
	cout << "Found any node " << node << endl;
	do 
	{ 
		cout << "check " << node << " parent " << parent_info[node] << endl; node = parent_info[node]; 
	} while (parent_info[node] != -1);

	cout << "FOUND ROOT NODE : " << node << endl;

	//ROOT 이하의 모든 node traversing
	traversing_tree(node); 
}

void traversing_tree(int node) 
{ 
	cout << node << " ";
	for (NODE * pp = child_info[node]; pp != nullptr; pp = pp->prev)
	{ 
		traversing_tree(pp->id); 
	} 
}



 
 
    
    
    *트리 getif hash
    #include <iostream>
using namespace std;
struct HASH_NODE { int id;
	char name[7];
	HASH_NODE *prev; } b[1000];
#define MAX_TABLE 10
int hash_idx = 0; int id = 0; HASH_NODE *hash_table[MAX_TABLE];
unsigned long myhash(const char *str) { unsigned long hash = 5381;
	int c;
	while (c = *str++)
	{ hash = (((hash << 5) + hash) + c) % MAX_TABLE; }
	return hash % MAX_TABLE; }
HASH_NODE *myalloc(void) { return &b[hash_idx++]; }
void init_hash(void) { id = 0;
	hash_idx = 0;
	for (int i = 0; i < MAX_TABLE; i++) hash_table[i] = nullptr; }
int getid_hash(char *name) { int key = myhash(name);  //name 을 이용하여 hash key 를 만듭니다.
	for (HASH_NODE *pp = hash_table[key]; pp != nullptr; pp = pp->prev)   //Hash table(key) 에서 Name 을 검색합니다. { if (!strncmp(name, pp->name, 6))  //name 이 있는 경우  해당하는 id를 return 합니다. return pp->id; }
	//Hash Table 없으면 추가합니다.
	HASH_NODE *p = myalloc();
	p->id = id++;  // 새로운 id 를 부여합니다.
	strncpy(p->name, name, 7);
	p->prev = hash_table[key];
	hash_table[key] = p;
	//추가된 모든 노드 확인
	for (int _tKey = 0; _tKey < MAX_TABLE; _tKey++)
	{ cout << "Hash table( " << _tKey << " ) :";
		for (HASH_NODE * pp = hash_table[_tKey]; pp != NULL; pp = pp->prev)
		{ cout << " " << pp->name << "(" << pp->id << ") "; }
		cout << endl; }
	return p->id; }
        
        
        *트리 
        ㅇ테이블
        #include <iostream>
using namespace std;
#define MAX_NODE 11
int num_table; char name_table[MAX_NODE][7];
void init_table(void) { num_table = 0; }
int getid_table(char *name) { int i;
	for (i = 0; i < num_table; i++)  //전체 테이블에서
	{ if (!strncmp(name, name_table[i], 6))  //name 을 검색합니다.
		{ return i; } }
	strncpy(name_table[i], name, 7); //새로운 행을 추가합니다.
	num_table++;  //전체 테이블의 수를 늘입니다.
	//for (int kk = 0; kk < num_table; kk++)
	//	cout << kk << " : " << name_table[kk] << endl;
	return i; }
    
    *트리 main
    #include <iostream>
using namespace std;
void traversing_tree(int node);
#define MAX_NODE 11
int arr_idx = 0;
struct NODE { int id;    //id 번호
	NODE * prev;  //싱글 리스트를 위해 추가. } a[20000];
NODE *myalloc(void) { return &a[arr_idx++]; }
int parent_info[MAX_NODE]; NODE *child_info[MAX_NODE];
/////////////////////////////////////// extern void init_hash(void); extern int getid_hash(char *name);
/////////////////////////////////////// extern void init_table(void); extern int getid_table(char *name);
///////////////////////////////////////
int main(void) { freopen("input.txt", "r", stdin);
	//freopen("output.txt", "w", stdout);
	init_table();  //get ID 용 초기화
	init_hash();
	arr_idx = 0;  //사용할 힙 배열 초기화
	for (int i = 0; i < MAX_NODE; i++)  //tree 정보 초기화
	{ child_info[i] = nullptr;
		parent_info[i] = -1; }
	NODE *p;
	char pname[7], cname[7];
	int parent, child;
	int test_case;
	cin >> test_case;
	//tree 정보 추가
	for (int T = 0; T < test_case; T++)
	{ cin >> pname >> cname;  //부모 - 자식간의 연결정보
		parent = getid_hash(pname);
		child = getid_hash(cname);
		cout << "Parent " << pname << "( " << parent << ") child " << cname << " ( " << child << " )" << endl << endl;
		parent_info[child] = parent;  //부모 정보를 child에 저장
		p = myalloc();  //저장할 공간을 alloc 합니다.
		p->id = child;
		p->prev = child_info[parent];  //parent 의 linked list 에 child 정보를 저장합니다. child_info[parent] = p;
		//추가된 모든 노드 확인
		cout << "Tree info" << endl;
		for (int tNode = 0; tNode < MAX_NODE; tNode++)
		{ cout << "( " << tNode << " )'s parent = " << parent_info[tNode] << " child = "; for (NODE * pp = child_info[tNode]; pp != nullptr; pp = pp->prev)
			{ cout << " " << pp->id; }
			cout << endl; }
		cout << endl << endl; }
	//루트 노드 찾기...
	int node;
	for (node = MAX_NODE-1; node >= 0; node--)  //부모가 있는 노드 아무거나 검색 (1이 루트라, 일부러 뒤에서 검색) if (parent_info[node] != -1) break;
	cout << "Found any node " << node << endl;
	do { cout << "check " << node << " parent " << parent_info[node] << endl; node = parent_info[node]; } while (parent_info[node] != -1);
	cout << "FOUND ROOT NODE : " << node << endl;
	//ROOT 이하의 모든 node traversing
	traversing_tree(node); }
void traversing_tree(int node) { cout << node << " ";
	for (NODE * pp = child_info[node]; pp != nullptr; pp = pp->prev)
	{ traversing_tree(pp->id); } }
    
        *input 
        2 
        AAAAAA BBBBBB 
        AAAAAA CCCCCC 
        
        *