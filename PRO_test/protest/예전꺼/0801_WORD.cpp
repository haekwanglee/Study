#include <iostream>
using namespace std;

struct TEST
{
	int value;
	TEST* next;
};
TEST test[3] = {0,};

int idx = 0;
TEST* myalloc()
{
	test[idx].next = NULL;
	test[idx].value = 0;
	return &test[idx++];
}



TEST* cur = NULL;
TEST* before = cur;

TEST* head = NULL;
TEST* tail = head; //tail 관리를 안함!
int main()
{
	cur = myalloc();
	cur->value = 1;
	before = NULL;

	cur->next = myalloc();	
	before = cur;
	cur = cur->next;
	cur->value = 2;
	
	cur->next = myalloc();
	before = cur;
	cur = cur->next;
	cur->value = 3;

	before->next =  cur->next;

	return 0;

}