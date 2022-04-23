#include <iostream>
using namespace std;

struct NODE
{
	bool finish;
	NODE* child[26];
	char value;
	bool tag;
}node[10000];

int idx = 0;
NODE* myalloc(void)
{
	node[idx].finish = false;
	for(int i =0; i<26; i++)
	{
		node[idx].child[i] = NULL;
		node[idx].value = '\0';
		node[idx].tag = false;
	}	
	return &node[idx++];
}


void showtreeRecursion(NODE* now, char* str, int depth)
{
	if(depth >= 10)
	{
		return;
	}

	if(now->finish)
	{
		cout << str << endl;
	}

	for(int i=0; i<26; i++)
	{
		if(now->child[i])
		{
			str[depth] = i+'a';
			str[depth+1] = 0;
			showtreeRecursion(now->child[i], str, depth+1);
		}
	}
}

void print(NODE** stack)
{
	for(int j = 0; j<10; j++)
	{
		if(stack[j] == NULL)
		{
			cout << endl;
			break;
		}
		cout << stack[j]->value;
	}		
}
void showtreeIter(NODE* root)
{
	NODE* stack[10] = {0,};
	int stackIdx = 0;
	bool found = false;
	NODE* now = root;
	while(1)
	{
		found = false;
		for(int i=0; i<26; i++)
		{
			if(now->child[i] && now->child[i]->tag == false)
			{
				found = true;
				if(stack[stackIdx]!=NULL)
				{
					stackIdx++;
				}
				stack[stackIdx] = now->child[i]; //push
				if(now->child[i]->finish)
				{					
					now->child[i]->tag = true;
					print(stack);	
				}				
				now = now->child[i];
				stackIdx++;
				break;
			}
		}			

		if(found == false)
		{
			if(stackIdx ==0)
			{
				break;
			}
			now->tag = true;			
			now = stack[stackIdx-1]; //pop
			stack[stackIdx] = NULL;
			stackIdx--;
		}
	}	
}


int main(void)
{
	idx = 0;

	//input
	int inputSize = 8;
	int maxLength = 11; //최대 10글자
	char input[][11] = {
		"single", "sing", "singer", "singleton", "singapole", "sinner", "saw", "safe"
	};

	NODE* root = NULL;
	root = myalloc();

	NODE* now = NULL;
	for(int i =0 ; i<inputSize; i++)
	{
		now = root;
		for(int j=0; j<maxLength; j++)
		{			
			if(input[i][j] == NULL)
			{
				now->finish = true;
				break;
			}
			int index = input[i][j] -'a';
			if(now->child[index] == NULL)
			{
				now->child[index] = myalloc();
			}
			now->child[index]->value = input[i][j];
			now = now->child[index];
		}
	}

	char temp[11] = {0,};
	showtreeRecursion(root, temp, 0);
	//showtreeIter(root);

	//show all (완전탐색) https://brenden.tistory.com/10
	//재귀호출 주의 https://ifyouwanna.tistory.com/entry/%EC%9E%AC%EA%B7%80-%ED%98%B8%EC%B6%9C%EC%8B%9C-%EC%A3%BC%EC%9D%98
}











