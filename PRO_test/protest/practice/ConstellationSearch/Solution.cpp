#define MAX_N 1000
#define MAX_M 20
#define STAR 1
#define PIVOT 9
#define DIR0 0
#define DIR90 1
#define DIR180 2
#define DIR270 3

//test
//#include <stdio.h>

int CASE = 0;
int CASE_N = 0;
int CASE_M = 0;
int starMap[MAX_N][MAX_N] = {0,};
int findStar[MAX_M][MAX_M] = {0,};


struct Result {
	int y, x;
};

struct Diff
{
	int x;
	int y;
}diff[MAX_M*MAX_M];
int IdxDiff = 0;
Diff* myalloc(void)
{
	diff[IdxDiff].x = 0;
	diff[IdxDiff].y = 0;
	return &diff[IdxDiff++]; 
}

struct FOUNDTEMP
{
	int x;
	int y;
}foundTemp[MAX_M*MAX_M];
int IdxFoundTemp = 0;
FOUNDTEMP* myallocFoundTemp(void)
{
	foundTemp[IdxFoundTemp].x = 0;
	foundTemp[IdxFoundTemp].y = 0;
	return &foundTemp[IdxFoundTemp++]; 
}


struct STARPOS
{
	int x;
	int y;
}starPos[MAX_N*MAX_N];
int IdxStarPos = 0;
STARPOS* myallocStarPos(void)
{
	starPos[IdxStarPos].x = 0;
	starPos[IdxStarPos].y = 0;
	return &starPos[IdxStarPos++]; 
}


struct TEMP
{
	int x;
	int y;
};
TEMP temp;



void init(int N, int M, int Map[MAX_N][MAX_N])
{
	CASE++;
	IdxDiff = 0;
	IdxFoundTemp= 0;
	IdxStarPos = 0;

	CASE_N = N;
	CASE_M = M;
	for(int r=0; r<CASE_N; r++)
	{
		for(int c=0; c<CASE_N; c++)
		{
			starMap[r][c] = Map[r][c];

			if(Map[r][c] != 0)
			{
				STARPOS* star = myallocStarPos();
				star->x = c;
				star->y = r;
			}
			//test
			//printf("%d", starMap[r][c]);
		}		
		//test
		//printf("\n");
	}
}

TEMP getPos(int dir, int map_r, int map_c, int x, int y)
{
	//map_r, map_c: 기준점인지 테스트 
	switch(dir)
	{
	case DIR0:
		temp.x = map_c + x;
		temp.y = map_r + y;
		return temp;
	case DIR90:
		temp.x = map_c - y;
		temp.y = map_r + x;
		return temp;
	case DIR180:
		temp.x = map_c - x;
		temp.y = map_r - y;
		return temp;
	case DIR270:
		temp.x = map_c + y;
		temp.y = map_r - x;
		return temp;
	default:
		break;
	}

	temp.x = 0;
	temp.y = 0;
	return temp;
}

TEMP getRotate(int dir, int map_r, int map_c)
{
	//map_r, map_c: 기준점인지 테스트 
	switch(dir)
	{
	case DIR0:
		temp.x = map_c;
		temp.y = map_r;
		return temp;
	case DIR90:
		temp.x = (CASE_M-1) - map_r;
		temp.y = map_c;
		return temp;
	case DIR180:
		temp.x = (CASE_M-1) - map_c;
		temp.y = (CASE_M-1) - map_r;
		return temp;
	case DIR270:
		temp.x = map_r;
		temp.y = (CASE_M-1) - map_c;
		return temp;
	default:
		break;
	}

	temp.x = 0;
	temp.y = 0;
	return temp;
}


Result findConstellation(int stars[MAX_M][MAX_M])
{
	IdxDiff = 0;
	IdxFoundTemp = 0;
	Result res;
	res.y = res.x = 0;

	int pivotX = 0;
	int pivotY = 0;
	

	for(int r=0; r<CASE_M; r++)
	{
		for(int c=0; c<CASE_M; c++)
		{
			findStar[r][c] = stars[r][c];
			if(stars[r][c] == PIVOT)
			{
				pivotX = c;
				pivotY = r;
			}
			//test
			//printf("%d", findStar[r][c]);
		}		
		//test
		//printf("\n");
	}

	for(int r=0; r<CASE_M; r++)
	{
		for(int c=0; c<CASE_M; c++)
		{			
			if(findStar[r][c] == STAR || findStar[r][c] == PIVOT)
			{
				Diff* diff = myalloc();
				diff->x = c - pivotX;
				diff->y = r - pivotY;
			}
		}		
	}
		
	int find_diff_x =0;
	int find_diff_y =0;
	int found = false;
	int find_count = 0;

	//기준점 후보
	int r = 0;
	int c = 0;

	for(int s=0; s<IdxStarPos; s++)
	{	
		c = starPos[s].x;
		r = starPos[s].y;

		for(int loop=0; loop<4; loop++) //4방향
		{
				
			IdxFoundTemp = 0;
			find_count = 0;

			for(int d=0; d<IdxDiff; d++)
			{		
				//r, c = 기준점 후보
				find_diff_x = (getPos(loop, r, c, diff[d].x, diff[d].y)).x;
				find_diff_y = (getPos(loop, r, c, diff[d].x, diff[d].y)).y;

				if(find_diff_x<0 || find_diff_x>=CASE_N ||  
					find_diff_y<0 || find_diff_y>=CASE_N)
				{
					break;
				}
				if(starMap[find_diff_y][find_diff_x] != STAR)
				{
					break;
				}

				FOUNDTEMP* found = myallocFoundTemp();
				found->x = find_diff_x;
				found->y = find_diff_y;

				find_count++;
			}
				
			if(find_count == IdxDiff)
			{
				//맵에서 찾은 별자리는 제거해보자
				for(int i = 0; i<find_count; i++)
				{
					starMap[foundTemp[i].y][foundTemp[i].x] = 0;
				}
				res.y = r;
				res.x = c;
				return res;
			}
		}			
	}	
	return res;
}