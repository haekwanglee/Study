#define MAXR		99
#define MAXC		26
#define NULL 0
#define MAX_NUM 1000000000

#define INIT 0
#define CONSTANT -1
#define ADD 1
#define SUB 2
#define MUL 3
#define DIV 4
#define MAX 5
#define MIN 6
#define SUM 7

#define MINVAL -1000000000 
#define MAXVAL 1000000000 

typedef struct
{	
	int type;
	int value;
	int addr1_C;
	int addr1_R;
	int addr2_C;
	int addr2_R;
	bool done;
}TABLEITEM;
TABLEITEM table[MAXR+1][MAXC+1] = {0,};

int m_C = 0;
int m_R = 0;
int TC = 0;

int get(int row, int col)
{	
	if(table[row][col].type == CONSTANT || table[row][col].done == true)
	{
		return table[row][col].value;
	}

	int addr1_R = table[row][col].addr1_R;
	int addr1_C = table[row][col].addr1_C;
	int addr2_R = table[row][col].addr2_R;
	int addr2_C = table[row][col].addr2_C;

	switch(table[row][col].type)
	{
	case ADD:
		table[row][col].value = get(addr1_R, addr1_C) + get(addr2_R, addr2_C);
		break;
	case SUB:
		table[row][col].value = get(addr1_R, addr1_C) - get(addr2_R, addr2_C);
		break;
	case MUL:
		table[row][col].value = get(addr1_R, addr1_C) * get(addr2_R, addr2_C);
		break;
	case DIV:
		table[row][col].value = get(addr1_R, addr1_C) / get(addr2_R, addr2_C);
		break;
	case MAX:
		{
			int result = MINVAL;
			int temp = 0;
			if(addr1_C == addr2_C) //세로
			{
				int start = addr1_R;
				int end = addr2_R;
				for(int i=start; i<=end; i++)
				{
					temp = get(i,addr1_C);
					if(result<temp)
					{
						result = temp;
					}
				}
			}
			else //가로
			{
				int start = addr1_C;
				int end = addr2_C;
				for(int i=start; i<=end; i++)
				{
					temp = get(addr1_R,i);
					if(result<temp)
					{
						result = temp;
					}
				}
			}
			table[row][col].value = result;
			break;
		}
	case MIN:
		{
			int result = MAXVAL;
			int temp = 0;
			if(addr1_C == addr2_C) //세로
			{
				int start = addr1_R;
				int end = addr2_R;
				for(int i=start; i<=end; i++)
				{
					temp = get(i,addr1_C);
					if(result>temp)
					{
						result = temp;
					}
				}
			}
			else //가로
			{
				int start = addr1_C;
				int end = addr2_C;
				for(int i=start; i<=end; i++)
				{
					temp = get(addr1_R,i);
					if(result>temp)
					{
						result = temp;
					}
				}
			}
			table[row][col].value = result;
			break;
		}
	case SUM:
		{
			int result = 0;
			if(addr1_C == addr2_C) //세로
			{
				int start = addr1_R;
				int end = addr2_R;
				for(int i=start; i<=end; i++)
				{
					result+=get(i,addr1_C);
				}
			}
			else //가로
			{
				int start = addr1_C;
				int end = addr2_C;
				for(int i=start; i<=end; i++)
				{
					result+=get(addr1_R,i);
				}
			}
			table[row][col].value = result;
			break;
		}		
	default:
		return 0;
	}

	table[row][col].done = true;
	return table[row][col].value;
}

bool isNumber(char input[])
{
	if(input[0] == '-' || ((input[0]-'0') > 0 && (input[0]-'0') < 10) )
	{
		return true;
	}
	return false;
}

void init(int C, int R) 
{
	TC++;
	m_C = C;
	m_R = R;
	for(int i = 1; i<=R; i++)
	{
		for(int j = 1; j<=C; j++)
		{
			table[i][j].type = INIT;		
			table[i][j].value = 0;
			table[i][j].addr1_C = 0;
			table[i][j].addr1_R = 0;
			table[i][j].addr2_C = 0;
			table[i][j].addr2_R = 0;
			table[i][j].done = false;		
		}
	}
}

void set(int col, int row, char input[]) //input 배열로 주어진 문자열은 ‘\0’으로 끝나고 ‘A’ ~ ‘Z’, ‘0’ ~ ‘9’, ‘-‘, ‘(‘, ‘)’, ‘,’, ‘\0’로만 구성되어 있다.
{
	int idx = 0;	
	table[row][col].done = false;

	if(isNumber(input))
	{
		int positive = 1;
		if(input[idx] == '-')
		{
			positive = -1;
			idx++;
		}

		int temp = 0;
		while(input[idx] != '\0')
		{
			temp*=10;
			temp+=(input[idx]-'0');
			idx++;	
		}	
		
		table[row][col].type = CONSTANT;
		table[row][col].value = temp*positive;
		return;
	}
	
	if(input[2] == 'D') //ADD
	{
		table[row][col].type = ADD;
	}
	else if(input[2] == 'B') //SUB
	{
		table[row][col].type = SUB;
	}
	else if(input[2] == 'L') //MUL
	{
		table[row][col].type = MUL;
	}
	else if(input[2] == 'V') //DIV
	{
		table[row][col].type = DIV;
	}
	else if(input[2] == 'X') //MAX
	{
		table[row][col].type = MAX;
	}
	else if(input[2] == 'N') //MIN
	{
		table[row][col].type = MIN;
	}
	else if(input[2] == 'M') //SUM
	{
		table[row][col].type = SUM;
	}

	int addr1_C = input[4]-'A'+1;
	int addr1_R = 0;
	int addr2_C = 0;
	int addr2_R = 0;
	int addrIdx = 5;

	while(input[addrIdx]!=',')
	{	
		addr1_R*=10;
		addr1_R+=(input[addrIdx] - '0');
		addrIdx++;
	}
	addrIdx++;

	addr2_C = input[addrIdx++]-'A'+1;

	while(input[addrIdx]!=')')
	{	
		addr2_R*=10;
		addr2_R+=(input[addrIdx] - '0');
		addrIdx++;
	}	
	table[row][col].addr1_C = addr1_C;
	table[row][col].addr1_R = addr1_R;
	table[row][col].addr2_C = addr2_C;
	table[row][col].addr2_R = addr2_R;
	table[row][col].value = 0;
}

void update(int value[MAXR][MAXC]) 
{
	if(TC==6)
	{
		int temp = 0;
	}

	for(int i=0; i<MAXR; i++)
	{
		for(int j=0; j<MAXC; j++)
		{
			if(j > m_C || i > m_R)
			{
				value[i][j] = 0;
			}
			else
			{
				value[i][j] = get(i+1,j+1);
			}			
		}
	}

	for(int i=1; i<=MAXR; i++)
	{
		for(int j=1; j<=MAXC; j++)
		{
			table[i][j].done = false;
		}
	}
}