#include <iostream>
using namespace std;
void traversing_tree(int node);
#define MAX_NODE 11
int arr_idx = 0;
struct NODE { 
	int id;    //id ��ȣ
	NODE * prev;  //�̱� ����Ʈ�� ���� �߰�. 
} a[20000];
NODE* myalloc(void) 
{ 
	return &a[arr_idx++]; 
}
int parent_info[MAX_NODE]; // => NODE  Ÿ���̸� �ɰ� ������? Ư�� index�� parent�� �ٷ� �����Ҽ� �ִ� �迭 ���̵��.
NODE* child_info[MAX_NODE]; //Ư�� index�� child���� SLL�� ������� ���̵��, NODE* child_info[0] <= 0�� NODE�� child�����ϴ� head����.


//�ƴ� �� NODE���� parent. �����Ͱ����ֱ�, prev�� �Ǿ������� child�� ����ϴ°� ������?

int main(void) 
{ 
	freopen("input.txt", "r", stdin);
	//freopen("output.txt", "w", stdout);

	arr_idx = 0;  //����� �� �迭 �ʱ�ȭ
	for (int i = 0; i < MAX_NODE; i++)  //tree ���� �ʱ�ȭ
	{ 
		child_info[i] = NULL;
		parent_info[i] = -1; 
	}

	NODE* p;
	int parent, child;
	int test_case;
	cin >> test_case;
	//tree ���� �߰�
	for (int T = 0; T < test_case; T++)
	{ 
		cin >> parent >> child;  //�θ� - �ڽİ��� ��������
		parent_info[child] = parent;  //�θ� ������ child�� ����
		p = myalloc();  //������ ������ alloc �մϴ�.
		p->id = child;
		p->prev = child_info[parent];  //parent �� linked list �� child ������ �����մϴ�. 
		child_info[parent] = p;
		//�߰��� ��� ��� Ȯ��
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
	
	//��Ʈ ��� ã��...
	int node;
	for (node = MAX_NODE-1; node >= 0; node--)  //�θ� �ִ� ��� �ƹ��ų� �˻� (1�� ��Ʈ��, �Ϻη� �ڿ��� �˻�) 
		if (parent_info[node] != -1) break;
	cout << "Found any node " << node << endl;
	do 
	{ 
		cout << "check " << node << " parent " << parent_info[node] << endl; node = parent_info[node]; 
	} while (parent_info[node] != -1);

	cout << "FOUND ROOT NODE : " << node << endl;

	//ROOT ������ ��� node traversing
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



 
 
    
    
    *Ʈ�� getif hash
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
int getid_hash(char *name) { int key = myhash(name);  //name �� �̿��Ͽ� hash key �� ����ϴ�.
	for (HASH_NODE *pp = hash_table[key]; pp != nullptr; pp = pp->prev)   //Hash table(key) ���� Name �� �˻��մϴ�. { if (!strncmp(name, pp->name, 6))  //name �� �ִ� ���  �ش��ϴ� id�� return �մϴ�. return pp->id; }
	//Hash Table ������ �߰��մϴ�.
	HASH_NODE *p = myalloc();
	p->id = id++;  // ���ο� id �� �ο��մϴ�.
	strncpy(p->name, name, 7);
	p->prev = hash_table[key];
	hash_table[key] = p;
	//�߰��� ��� ��� Ȯ��
	for (int _tKey = 0; _tKey < MAX_TABLE; _tKey++)
	{ cout << "Hash table( " << _tKey << " ) :";
		for (HASH_NODE * pp = hash_table[_tKey]; pp != NULL; pp = pp->prev)
		{ cout << " " << pp->name << "(" << pp->id << ") "; }
		cout << endl; }
	return p->id; }
        
        
        *Ʈ�� 
        �����̺�
        #include <iostream>
using namespace std;
#define MAX_NODE 11
int num_table; char name_table[MAX_NODE][7];
void init_table(void) { num_table = 0; }
int getid_table(char *name) { int i;
	for (i = 0; i < num_table; i++)  //��ü ���̺���
	{ if (!strncmp(name, name_table[i], 6))  //name �� �˻��մϴ�.
		{ return i; } }
	strncpy(name_table[i], name, 7); //���ο� ���� �߰��մϴ�.
	num_table++;  //��ü ���̺��� ���� ���Դϴ�.
	//for (int kk = 0; kk < num_table; kk++)
	//	cout << kk << " : " << name_table[kk] << endl;
	return i; }
    
    *Ʈ�� main
    #include <iostream>
using namespace std;
void traversing_tree(int node);
#define MAX_NODE 11
int arr_idx = 0;
struct NODE { int id;    //id ��ȣ
	NODE * prev;  //�̱� ����Ʈ�� ���� �߰�. } a[20000];
NODE *myalloc(void) { return &a[arr_idx++]; }
int parent_info[MAX_NODE]; NODE *child_info[MAX_NODE];
/////////////////////////////////////// extern void init_hash(void); extern int getid_hash(char *name);
/////////////////////////////////////// extern void init_table(void); extern int getid_table(char *name);
///////////////////////////////////////
int main(void) { freopen("input.txt", "r", stdin);
	//freopen("output.txt", "w", stdout);
	init_table();  //get ID �� �ʱ�ȭ
	init_hash();
	arr_idx = 0;  //����� �� �迭 �ʱ�ȭ
	for (int i = 0; i < MAX_NODE; i++)  //tree ���� �ʱ�ȭ
	{ child_info[i] = nullptr;
		parent_info[i] = -1; }
	NODE *p;
	char pname[7], cname[7];
	int parent, child;
	int test_case;
	cin >> test_case;
	//tree ���� �߰�
	for (int T = 0; T < test_case; T++)
	{ cin >> pname >> cname;  //�θ� - �ڽİ��� ��������
		parent = getid_hash(pname);
		child = getid_hash(cname);
		cout << "Parent " << pname << "( " << parent << ") child " << cname << " ( " << child << " )" << endl << endl;
		parent_info[child] = parent;  //�θ� ������ child�� ����
		p = myalloc();  //������ ������ alloc �մϴ�.
		p->id = child;
		p->prev = child_info[parent];  //parent �� linked list �� child ������ �����մϴ�. child_info[parent] = p;
		//�߰��� ��� ��� Ȯ��
		cout << "Tree info" << endl;
		for (int tNode = 0; tNode < MAX_NODE; tNode++)
		{ cout << "( " << tNode << " )'s parent = " << parent_info[tNode] << " child = "; for (NODE * pp = child_info[tNode]; pp != nullptr; pp = pp->prev)
			{ cout << " " << pp->id; }
			cout << endl; }
		cout << endl << endl; }
	//��Ʈ ��� ã��...
	int node;
	for (node = MAX_NODE-1; node >= 0; node--)  //�θ� �ִ� ��� �ƹ��ų� �˻� (1�� ��Ʈ��, �Ϻη� �ڿ��� �˻�) if (parent_info[node] != -1) break;
	cout << "Found any node " << node << endl;
	do { cout << "check " << node << " parent " << parent_info[node] << endl; node = parent_info[node]; } while (parent_info[node] != -1);
	cout << "FOUND ROOT NODE : " << node << endl;
	//ROOT ������ ��� node traversing
	traversing_tree(node); }
void traversing_tree(int node) { cout << node << " ";
	for (NODE * pp = child_info[node]; pp != nullptr; pp = pp->prev)
	{ traversing_tree(pp->id); } }
    
        *input 
        2 
        AAAAAA BBBBBB 
        AAAAAA CCCCCC 
        
        *