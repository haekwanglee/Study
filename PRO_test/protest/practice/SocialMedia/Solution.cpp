#include<stdio.h>
#define NULL 0
#define MAX_USER 1000
#define MAX_FUNC 100000 
#define RESULT_CNT 10
int followTable[MAX_USER+1][MAX_USER+1] = {0,};

struct POST
{
	int pID;
	int postedUserID;
	int like;
	int timeStamp;
}post[MAX_FUNC+1]; 

int CASE = 0;
int curTimeStamp = 0; 
int curPID = 0;
int userNum = 0;

void init(int N)
{
	CASE++;	
	curTimeStamp = 0;
	curPID = 0;
	userNum = N;
}

void follow(int uID1, int uID2, int timestamp)
{
	curTimeStamp = timestamp;

	followTable[uID1][uID2] = CASE;
}

void makePost(int uID, int pID, int timestamp)
{
	curTimeStamp = timestamp;
	curPID = pID;
	
	post[pID].pID = pID;
	post[pID].postedUserID = uID;
	post[pID].timeStamp = timestamp;
	post[pID].like = 0;
}

void like(int pID, int timestamp)
{
	curTimeStamp = timestamp;
	post[pID].like +=1;
}

void getFeed(int uID, int timestamp, int pIDList[])
{
	curTimeStamp = timestamp;
	
	POST* p[RESULT_CNT+2] = {0,};
	int postedUserID = 0;
	POST* temp = NULL;

	int pIndex = 1;
	int sortIndex = 1;
	for(int cur=curPID; cur>=1; cur--)
	{
		postedUserID = post[cur].postedUserID;
		if(followTable[uID][postedUserID] == CASE || postedUserID == uID)
		{			
			//insert sort			
			temp = &post[cur];
			sortIndex = pIndex - 1; 
			if(curTimeStamp - temp->timeStamp <=1000) //1000 sec under
			{	
				while ((sortIndex >= 1) &&
					((temp->like) > (p[sortIndex]->like)))
				{
					p[sortIndex + 1] = p[sortIndex];
					sortIndex -=1;
				}
			}
			else
			{
				if(pIndex>RESULT_CNT) //1000sec 넘어서면 더이상 삽입 고려할 필요없음.
				{
					break;
				}
			}

			p[sortIndex + 1] = temp;

			pIndex++;
		}

		if(pIndex>RESULT_CNT+1) //큐 그기 넘어설때 커트라인에 저장하도록.
		{
			pIndex = RESULT_CNT+1;
		}
	}

	for(int i = 1; i<=RESULT_CNT; i++)
	{
		if(p[i] == NULL)
		{
			pIDList[i-1] = 0;
			continue;
		}
		pIDList[i-1] = p[i]->pID;
	}
}