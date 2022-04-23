#define MAX_USER      1000
#define MAX_TAG       5000
#define MAX_MSG 1000000
#define MAX_MSG_PER_USER 100
#define INVALID -1
#define MAX_RESULT 5
#define MAX_TRIE 10000
#define NULL 0
#define NULLCH '\0'
#define HASHTAGCH '#'
struct MSG
{
	int caseID;
	int msgID;
	int userID;
	long time;
	bool operator>(const MSG& right) const
	{
		if(time != right.time)
		{
			return time > right.time;
		}		
		return userID < right.userID;
	}
};
MSG msg[MAX_MSG] = {0,};

struct USER
{
	int msgCount;
	int msg[MAX_MSG_PER_USER];
};
USER user[MAX_USER] = {0,};

struct TRIE
{
	TRIE* next[26];
	char c;
	bool finished;
	int resultCount;
	int result[MAX_RESULT+1];
};
TRIE trie[MAX_TRIE] = {0,};
int trieID = 0;
TRIE* myalloc()
{
	for(int i=0; i<26; i++)
	{
		trie[trieID].next[i] = NULL;
	}
	trie[trieID].c = 0;
	trie[trieID].finished = false;
	trie[trieID].resultCount = 0;
	for(int i=0; i<MAX_RESULT+1; i++)
	{
		trie[trieID].result[i] = INVALID;
	}
	return &trie[trieID++];
}

// The below commented functions are for your reference. If you want 
// to use it, uncomment these functions.
/*
int mstrcmp(const char *a, const char *b)
{
int i;
for (i = 0; a[i] != '\0'; ++i) if (a[i] != b[i]) return a[i] - b[i];
return a[i] - b[i];
}

void mstrcpy(char *dest, const char *src)
{
int i = 0;
while (src[i] != '\0') { dest[i] = src[i]; i++; }
dest[i] = src[i];
}
*/

int CASE = 0;
int follow[1000][1000] = {0,};

void init()
{
	CASE++;

	for(int i=0; i<MAX_USER; i++)
	{
		user[i].msgCount = 0;
	}
}

void createMessage(int msgID, int userID, char msgData[])
{
	//msg
	msg[msgID].caseID = CASE;
	msg[msgID].msgID = msgID;
	msg[msgID].userID = userID;
	int hour = (msgData[0] - '0')*10 + (msgData[1] - '0');
	int min = (msgData[3] - '0')*10 + (msgData[4] - '0');
	int sec = (msgData[6] - '0')*10 + (msgData[7] - '0');
	long time = sec + (min*60) + (hour*3600);
	msg[msgID].time = time;
	
	//user
	int idx = user[userID].msgCount;
	user[userID].msg[idx] = msgID;
	user[userID].msgCount +=1;	
	
	//hashtag	
	//1.trie이용
	int msgIndex = 9; //hashtag시작	
	TRIE* root = NULL;
	root = myalloc();
	TRIE* cur = NULL;
	cur = root;
	int chaIndex = 0;
	while(1)
	{
		
		if(msgData[msgIndex] == ' ' || msgData[msgIndex] == NULLCH)
		{
			cur->finished = true;
			cur->result[cur->resultCount] = msgID;
			cur->resultCount++;			
			if(msgData[msgIndex] == NULLCH)
			{
				break;
			}			
		}

		if(msgData[msgIndex] == '#')
		{
			cur = root;
		}	

		int check = msgData[msgIndex] - 'a';
		if(0 <= check && check < 26)
		{
			chaIndex = msgData[msgIndex] - 'a';
			if(cur->next[chaIndex] == NULL)
			{
				cur->next[chaIndex] = myalloc();
			}
			cur->next[chaIndex]->c = msgData[msgIndex];
			cur = cur->next[chaIndex];
		}
		msgIndex++;
	}
}

void followUser(int userID1, int userID2)
{
	follow[userID1][userID2] = CASE;
	follow[userID2][userID1] = CASE;
}

int searchByHashtag(char tagName[], int retIDs[])
{
	return 0;
}



MSG result[MAX_RESULT+1]; //5개 인데 삽입정렬로 추가하면서 정렬할거라 1개 공간 여유분.
int getMessages(int userID, int retIDs[])
{
	//init result
	for(int i=0; i<MAX_RESULT+1; i++)
	{
		result[i].msgID = INVALID;
	}

	MSG temp;
	int i;
	int j;
	int last = 0;
	for(int u=0; u<MAX_USER; u++)
	{
		if(u== userID || follow[userID][u] == CASE) //자기 자신,  follow
		{
			int msgCount = user[u].msgCount;
			int msgID;
			for (int m = 0; m < msgCount; m++)
			{
				msgID = user[u].msg[m];
				result[last] = msg[msgID]; 
				last++;
				if(last>MAX_RESULT)
				{
					last = MAX_RESULT; //삽입정렬을 위한 여유공간까지만 계속 임시 입력가능함.
				}

				for (i = 1; i < MAX_RESULT+1; i++)
				{
					if(result[i].msgID == INVALID)
					{
						break;
					}

					temp = result[i];
					j = i - 1;
					while ((temp > result[j]) && (j >= 0))
					{
						result[j + 1] = result[j];
						j = j - 1;
					}
					result[j + 1] = temp;
				}
			}
		}
	}	

	int result_count = 0;
	for(int i=0; i<MAX_RESULT; i++)
	{
		if(result[i].msgID == INVALID)
		{
			break;
		}
		retIDs[i] = result[i].msgID;
		result_count++;
	}	
	return result_count;
}