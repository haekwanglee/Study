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

//재귀
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

void showtreeWhile(NODE* root)
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
				stack[stackIdx] = now->child[i];
				if(now->child[i]->finish)
				{					
					now->child[i]->tag = true;
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
				now = now->child[i];
				stackIdx++;
				break;
			}
		}			
		if(found == false)
		{
			now->tag = true;			
			now = stack[stackIdx-1];
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
	//showtreeRecursion(root, temp, 0);
	showtreeWhile(root);

}

