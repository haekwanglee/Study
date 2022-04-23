
 
 
#define MAXR  99
#define MAXC  26
 
 
struct Node {
 int x1, y1, x2, y2, type;
 Node() {}
 Node(int x1, int y1, int x2, int y2, int type) : x1(x1), y1(y1), x2(x2), y2(y2), type(type) {}
};
Node v[MAXR][MAXC];
int A[MAXR][MAXC];
bool calc[MAXR][MAXC];
int N, M;
char T_[] = { 'D', 'B', 'L', 'V', 'X', 'N', 'M' };
int T[256];
 
 
void init(int C, int R) {
 N = R;
 M = C;
 for (int i = 0; i < N; i++) {
  for (int j = 0; j < M; j++) {
   A[i][j] = 0;
   v[i][j] = Node(0, 0, 0, 0, -1);
  }
 }
 for (int i = 0; i < 7; i++) {
  T[T_[i]] = i;
 }
}
 
 
int _atoi(char const *c) {
 int value = 0;
 int positive = 1;
 
 
 if (*c == '\0') {
  return 0;
 }
 if (*c == '-') {
  positive = -1;
 }
 while (*c) {
  if (*c >= '0' && *c <= '9') {
   value = value * 10 + *c - '0';
  }
  c++;
 }
 
 
 return value * positive;
}
 
 
void set(int col, int row, char input[]) {
 col--; row--;
 if (input[0] == '-' || ('0' <= input[0] && input[0] <= '9')) {
  A[row][col] = _atoi(input);
  v[row][col] = Node(0, 0, 0, 0, 7);
  return;
 }
 int type = T[input[2]];
 int i = 4;
 int y1 = input[i++] - 'A';
 int x1 = 0;
 while ('0' <= input[i] && input[i] <= '9') {
  x1 = x1 * 10 + input[i++] - '0';
 }
 i++;
 int y2 = input[i++] - 'A';
 int x2 = 0;
 while ('0' <= input[i] && input[i] <= '9') {
  x2 = x2 * 10 + input[i++] - '0';
 }
 
 
 v[row][col] = Node(x1 - 1, y1, x2 - 1, y2, type);
}
 
 
int max(int a, int b) {
 if (a > b) {
  return a;
 }
 return b;
}
 
 
int min(int a, int b) {
 if (a < b) {
  return a;
 }
 return b;
}
 
 
int update(int x, int y) {
 if (calc[x][y] || v[x][y].type == 7) {
  return A[x][y];
 }
 calc[x][y] = true;
 if (v[x][y].type == -1) {
  A[x][y] = 0;
  return 0;
 }
 int type = v[x][y].type;
 int x1 = v[x][y].x1;
 int y1 = v[x][y].y1;
 int x2 = v[x][y].x2;
 int y2 = v[x][y].y2;
 
 
 if (type == 0) {
  A[x][y] = update(x1, y1) + update(x2, y2);
 }
 else if (type == 1) {
  A[x][y] = update(x1, y1) - update(x2, y2);
 }
 else if (type == 2) {
  A[x][y] = update(x1, y1) * update(x2, y2);
 }
 else if (type == 3) {
  A[x][y] = update(x1, y1) / update(x2, y2);
 }
 else {
  int res = update(x1, y1);
  for (int i = x1; i <= x2; i++) {
   for (int j = y1; j <= y2; j++) {
    if (i == x1 && j == y1) continue;
    if (type == 4) {
     res = max(res, update(i, j));
    }
    else if (type == 5) {
     res = min(res, update(i, j));
    }
    else {
     res += update(i, j);
    }
   }
  }
  A[x][y] = res;
 }
 return A[x][y];
}
 
 
void update(int value[MAXR][MAXC]) {
 for (int i = 0; i < N; i++) {
  for (int j = 0; j < M; j++) {
   calc[i][j] = false;
  }
 }
 for (int i = 0; i < N; i++) {
  for (int j = 0; j < M; j++) {
   value[i][j] = update(i, j);
  }
 }
}

