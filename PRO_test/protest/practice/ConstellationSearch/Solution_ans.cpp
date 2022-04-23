
 
 
#define MAX_N 1000
#define MAX_M 20
 
 
struct Result {
 int y, x;
};
 
 
int map[MAX_N][MAX_N];
int N, M;
Result S[MAX_N * MAX_N];
Result Con[MAX_M * MAX_M];
int NS, NCon;
 
 
void init(int n, int m, int Map[MAX_N][MAX_N])
{
 N = n;
 M = m;
 NS = 0;
 for(int i=0;i<N;i++) for(int j=0;j<N;j++){
  map[i][j] = Map[i][j];
  if(map[i][j] == 1){
   S[NS].y = i;
   S[NS].x = j;
   NS++;
  }
 }
}
 
 
int find(int stars[MAX_M][MAX_M]){
 int Y, X;
 for(int i=0; i<M;i++)
  for(int j=0;j<M;j++)
   if(stars[i][j] == 9) Y = i, X = j;
 
 
 NCon = 0;
 for(int i=0; i<M; i++)
  for(int j=0; j<M; j++)
   if(stars[i][j] == 1){
    Con[NCon].y = i - Y;
    Con[NCon].x = j - X;
    NCon++;
   }
  
 for(int i=0; i<NS; i++){
  bool flag = true;
  for(int j=0; j<NCon&&flag; j++)
   if(0 > S[i].y + Con[j].y || S[i].y + Con[j].y >= N ||
      0 > S[i].x + Con[j].x || S[i].x + Con[j].x >= N ||
      map[S[i].y + Con[j].y][S[i].x + Con[j].x] == 0) flag = false;
  if(flag) return i;
 }
 return -1;
}
 
 
Result findConstellation(int stars[MAX_M][MAX_M])
{
 int stars2[MAX_M][MAX_M];
 for(int i=0;i<4;i++){
  int res = find(stars);
  if(res >= 0){
   return S[res];
  }
  for(int j=0;j<M;j++) for(int k=0;k<M;k++) stars2[j][k] = stars[j][k];
  for(int j=0;j<M;j++) for(int k=0;k<M;k++) stars[j][k] = stars2[k][M-1-j];
 }
}
