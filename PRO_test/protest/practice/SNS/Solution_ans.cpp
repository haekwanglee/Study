
#define MAX_USER      1000
#define MAX_TAG       5000
 
 
// The below commented functions are for your reference. If you want 
// to use it, uncomment these functions.
 
 
int mstrcmp(const char *a, const char *b)
{
 int i; for (i = 0; a[i] != '\0'; ++i) if (a[i] != b[i]) return a[i] - b[i]; return a[i] - b[i];
}
 
 
void mstrcpy(char *dest, const char *src)
{
 int i = 0; while (src[i] != '\0') { dest[i] = src[i]; i++; } dest[i] = src[i];
}
 
 
#define MAX_HASHTAG (MAX_TAG * 2 + 7)
 
 
typedef struct _NODE {
 char tag[10];
 _NODE *n, *p;
 int midx;
} NODE;
NODE Node[500000];
int nCnt;
 
 
NODE HashTable[MAX_HASHTAG];
 
 
typedef struct {
 int id;
 int uid;
 int time;
} MESSAGE;
MESSAGE Msg[50000];
int mCnt;
 
 
typedef struct {
 int mcnt;
 int midx[100];
 int fcnt;
 int fidx[1000];
} USER;
USER User[1000];
int uCnt;
 
 
int count, ids[5];
 
 
void init()
{
 mCnt = uCnt = nCnt = 0;
 for (int i = 0; i < MAX_HASHTAG; ++i) {
  HashTable[i].n = HashTable[i].p = 0;
  HashTable[i].tag[0] = 0;
 }
 for (int i = 0; i < MAX_USER; ++i) User[i].mcnt = User[i].fcnt = 0;
}
 
 
int getHashCode(const char* str)
{
 unsigned long hash = 5381;
 int c;
 while (c = *str++) { hash = (((hash << 5) + hash) + c) % MAX_HASHTAG; }
 return hash % MAX_HASHTAG;
}
 
 
NODE* new_node()
{
 NODE* node = &Node[nCnt++];
 node->n = node->p = 0;
 return node;
}
 
 
void add_hashtag(char tag[], int midx)
{
 int hidx = getHashCode(tag);
 for (int i = 0; i < MAX_HASHTAG; ++i) {
  if (mstrcmp(HashTable[hidx].tag, tag) == 0 || HashTable[hidx].tag[0] == 0) {
   if (HashTable[hidx].tag[0] == 0) mstrcpy(HashTable[hidx].tag, tag);
 
 
   NODE *node = new_node();
   node->midx = midx;
   node->n = HashTable[hidx].n;
   node->p = &HashTable[hidx];
   if (HashTable[hidx].n != 0) HashTable[hidx].n->p = node;
   HashTable[hidx].n = node;
   break;
  }
  hidx++;
  if (hidx == MAX_HASHTAG) hidx = 0;
 }
}
 
 
void createMessage(int mID, int userID, char msgData[])
{
 Msg[mCnt].id = mID;
 Msg[mCnt].uid = userID;
 int hour = (msgData[0] - '0') * 10 + msgData[1] - '0';
 int min = (msgData[3] - '0') * 10 + msgData[4] - '0';
 int sec = (msgData[6] - '0') * 10 + msgData[7] - '0';
 Msg[mCnt].time = hour * 3600 + min * 60 + sec;
 
 
 int tcnt = 0;
 char tname[10];
 int pos = 10;
 while (1) {
  char c = msgData[pos++];
  if (c != 0 && c != ' ') tname[tcnt++] = c;
  else {
   tname[tcnt] = 0;
   add_hashtag(tname, mCnt);
   tcnt = 0;
   if (c == 0) break;
   pos++;
  }
 }
 
 
 User[userID].midx[User[userID].mcnt++] = mCnt;
 mCnt++;
}
 
 
void followUser(int userID1, int userID2)
{
 User[userID1].fidx[User[userID1].fcnt++] = userID2;
}
 
 
void add_list(int midx)
{
 int aidx = -1;
 for (int i = 0; i < count; ++i) {
  int idx = ids[i];
  if (Msg[idx].time < Msg[midx].time || (Msg[idx].time == Msg[midx].time && Msg[idx].uid > Msg[midx].uid)) {
   aidx = i;
   break;
  }
 }
 if (aidx == -1) {
  if (count < 5) ids[count++] = midx;
 }
 else {
  for (int i = 4; i > aidx; --i) ids[i] = ids[i - 1];
  ids[aidx] = midx;
  if (count < 5) count++;
 }
}
 
 
int searchByHashtag(char tagName[], int retIDs[])
{
 count = 0;
 
 
 int hidx = getHashCode(tagName + 1);
 for (int i = 0; i < MAX_HASHTAG; ++i) {
  if (mstrcmp(HashTable[hidx].tag, tagName + 1) == 0) {
   NODE *node = HashTable[hidx].n;
   while (node) {
    add_list(node->midx);
    node = node->n;
   }
   break;
  }
  hidx++;
  if (hidx == MAX_HASHTAG) hidx = 0;
 }
 
 
 for (int i = 0; i < count; ++i) retIDs[i] = Msg[ids[i]].id;
 return count;
}
 
 
int getMessages(int userID, int retIDs[])
{
 count = 0;
 
 
 if (User[userID].mcnt == 0) return 0;
 
 
 for (int i = 0; i < User[userID].mcnt; ++i)
  add_list(User[userID].midx[i]);
 
 
 for (int i = 0; i < User[userID].fcnt; ++i) {
  int fidx = User[userID].fidx[i];
  for (int k = 0; k < User[fidx].mcnt; ++k)
   add_list(User[fidx].midx[k]);
 }
 
 
 for (int i = 0; i < count; ++i) retIDs[i] = Msg[ids[i]].id;
 return count;
}
