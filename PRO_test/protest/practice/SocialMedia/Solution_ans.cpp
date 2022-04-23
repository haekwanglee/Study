
#define MAXN 1001
#define MAXPOST 100001
 
 
using namespace std;
 
 
bool isFollow[MAXN][MAXN];
int M; // Post Count
int postTime[MAXPOST]; // save post timestamp
int postUId[MAXPOST]; // save  post uID
int postLike[MAXPOST]; // save like count;
int postRank[11];
 
 
int min(int x, int y){
 return x<y?x:y;
}
bool cmp(int x, int y){
 if(postLike[x] != postLike[y]) return postLike[x] > postLike[y];
 return postTime[x] > postTime[y];
}
void init(int N)
{
 for(int i=1;i<=N;i++)
  for(int j=1;j<=N;j++){ 
   isFollow[i][j] = i==j?true:false;
  }
 M = 0;
}
 
 
void follow(int uID1, int uID2, int timestamp)
{
 isFollow[uID1][uID2] = true;
}
 
 
void makePost(int uID, int pID, int timestamp)
{
 M++;
 postTime[M] = timestamp;
 postUId[M] = uID;
 postLike[M] = 0;
}
 
 
void like(int pID, int timestamp)
{
 postLike[pID]++;
}
 
 
void getFeed(int uID, int timestamp, int pIDList[])
{
 int cnt = 0;
 for(int i=0;i<=10;i++) postRank[i] = -1;
 for(int i=M;i>=1;i--){
  if(cnt >= 10 && postTime[i] < timestamp - 1000) break;
  if(!isFollow[uID][postUId[i]]) continue;
  if(postTime[i] < timestamp - 1000) postRank[cnt++] = i;
  else {
   for(int j=min(cnt,10)-1; j>=0; j--){
    if(cmp(i, postRank[j])){
     postRank[j+1] = postRank[j];
     postRank[j] = -1;
    }
    else {
     postRank[j+1] = i;
     break;
    }
   }
   if(postRank[0] == -1) postRank[0] = i;
   cnt = min(cnt+1, 10);
  }
 
 
 }
 for(int i=0;i<min(10, cnt); i++)
  pIDList[i] = postRank[i];
}
