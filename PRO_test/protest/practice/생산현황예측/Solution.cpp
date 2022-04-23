#define MAX_N    100
#define MAX_TOOL 50
#define MAX_LOT 100000
#define NUL 0

int S; 
 
struct TOOL 
{
	int cnt;
	int start;
	int end;
	int pt;
};
TOOL tools[MAX_N][MAX_TOOL];
int tn[MAX_N];
int empty[MAX_N];
int wait[MAX_N + 1];
 
 
void sortByPt(int step) 
{
	TOOL tmp;
	for (int i = 0; i < tn[step] - 1; i++) 
	{
		for (int j = i + 1; j < tn[step]; j++) 
		{
			if (tools[step][i].pt > tools[step][j].pt) 
			{
				tmp = tools[step][i];
				tools[step][i] = tools[step][j];
				tools[step][j] = tmp;
			}
		}
	}
}
 
 
struct RUN 
{
	int edtime;
	int sid;
	int pt;
	int tidx;
};
inline bool operator>(RUN a, RUN b) //뒤에꺼가 우선순위 높은거. >
{
	if (a.edtime > b.edtime || (a.edtime == b.edtime && a.sid < b.sid) || (a.edtime == b.edtime && a.sid == b.sid && a.pt > b.pt))
		return true;
	else
		return false;
}
inline bool operator<(RUN a, RUN b) //앞에꺼가 우선순위 높은거. <
{
	if (a.edtime < b.edtime || (a.edtime == b.edtime && a.sid > b.sid) || (a.edtime == b.edtime && a.sid == b.sid && a.pt < b.pt))
		return true;
	else
		return false;
}
 
 
RUN heap[MAX_LOT]; //로트 개수만큼 heap만들어서 우선순위큐.
int heapSize = 0;
void heapInit(void) 
{
	heapSize = 0;
}
void heapPush(int edtime, int sid, int pt, int tidx)
{
	if (heapSize + 1 > MAX_LOT) 
	{
		return;
	}
	heap[heapSize].edtime = edtime;
	heap[heapSize].sid = sid;
	heap[heapSize].pt = pt;
	heap[heapSize].tidx = tidx; 
 
	RUN temp;
	int current = heapSize;
	while (current > 0 && heap[current] < heap[(current - 1) / 2]) 
	{
		temp = heap[(current - 1) / 2];
		heap[(current - 1) / 2] = heap[current];
		heap[current] = temp;
		current = (current - 1) / 2;
	 }
	 heapSize = heapSize + 1;
}
 
 
int heapPop(RUN *value) 
{
	if (heapSize <= 0) 
	{
		return -1;
	}

	*value = heap[0];
	heapSize = heapSize - 1;
	heap[0] = heap[heapSize];

	RUN temp;
	int current = 0;
	while (current * 2 + 1 < heapSize) 
	{
		int child;
		if (current * 2 + 2 == heapSize) 
		{
			child = current * 2 + 1;
		}
		else 
		{
			child = heap[current * 2 + 1] < heap[current * 2 + 2] ? current * 2 + 1 : current * 2 + 2;
		}

		if (heap[current] < heap[child]) 		
		{
			break;
		}
		temp = heap[current];
		heap[current] = heap[child];
		heap[child] = temp;
		current = child;
	}
	return 1;
}
  
int heap_peek_time() 
{
	return heap[0].edtime;
}

void init(int N) 
{
	S = N;
	for (int i = 0; i < S; i++) 
	{
		tn[i] = wait[i] = empty[i] = 0;
	}
	wait[S] = 0;
} 
 
void setupTool(int T, int stepNo[MAX_N*MAX_TOOL], int procTime[MAX_N*MAX_TOOL]) 
{
	int step, tcnt;
	for (int i = 0; i < T; i++) 
	{
		step = stepNo[i];
		tcnt = tn[step];
		tools[step][tcnt].pt = procTime[i];
		tools[step][tcnt].cnt = tools[step][tcnt].start = tools[step][tcnt].end = 0;
		empty[step]++;
		tn[step]++;
	}
	for (int i = 0; i < S; i++) 
	{
		if (tn[i] > 1) sortByPt(i);
	} 
	heapSize = 0;
}
 
 
bool putEmptyTool(int time, int sid) 
{
	for (int i = 0; i < tn[sid]; i++) 
	{
		if (tools[sid][i].cnt == 0) 
		{
			tools[sid][i].cnt++;
			tools[sid][i].start = time;
			tools[sid][i].end = time + tools[sid][i].pt;
			heapPush(tools[sid][i].end, sid, tools[sid][i].pt, i);
			empty[sid]--;
			wait[sid]--;
			return true;
		}
	}
	return false;
}
 
void addLot(int time, int number) 
{	
	RUN   item;
	while (heapSize > 0 && heap_peek_time() <= time) 
	{
		heapPop(&item);
		tools[item.sid][item.tidx].cnt--;
		empty[item.sid]++;
		wait[item.sid + 1]++;
		if (item.sid + 1 < S && empty[item.sid + 1] > 0)
			putEmptyTool(item.edtime, item.sid + 1);
		if (wait[item.sid] > 0)
			putEmptyTool(item.edtime, item.sid);
	}
	wait[0] += number;
	while (empty[0] > 0 && wait[0] > 0) 
	{
		putEmptyTool(time, 0);
	}
}
 
 
int simulate(int time, int wip[MAX_N]) 
{
	addLot(time, 0);
	for (int i = 0; i < S; i++)
		wip[i] = wait[i] + (tn[i] - empty[i]);
	return wait[S];
}


/*
#define MAX_N    100
#define MAX_TOOL 50
#define MAX_LOT 100000
#define NULL 0

int CASE_N = 0;
int CASE = 0;
int DONE;

struct TOOL
{
	int tool_id;
	int tool_pt;
	bool process;
};
struct FACTORY
{
	TOOL tool[MAX_TOOL];
	int tool_count;
	int lot_count;
	int not_proc_lot_count;
};
FACTORY factory[MAX_N] = {0,};

int heapSize = 0;
struct EVENT
{
	int event_time;
	int factory_number;
	int pt;
	int tool_number;
	bool operator<(const EVENT &comp) const
	{
		if(event_time != comp.event_time)
		{
			return event_time < comp.event_time;
		}
		if(factory_number != comp.factory_number)
		{
			return factory_number > comp.factory_number;
		}
		return pt < comp.pt;
	}
};
EVENT event_table[MAX_LOT] = {0,};

void heapPush(EVENT value)
{
	if (heapSize + 1 > MAX_LOT)
	{
		return;
	}
	event_table[heapSize] = value;

	int current = heapSize;
	while (current > 0 && 
			event_table[current] < event_table[(current - 1) / 2] ) 
	{
		EVENT temp = event_table[(current - 1) / 2];
		event_table[(current - 1) / 2] = event_table[current];
		event_table[current] = temp;
		current = (current - 1) / 2;
	}

	heapSize = heapSize + 1;

	return;
}

void heapPop(EVENT* value)
{
	if (heapSize <= 0)
	{
		return;
	}

	*value = event_table[0];
	heapSize = heapSize - 1;
	event_table[0] = event_table[heapSize];

	int current = 0;
	while (current * 2 + 1 < heapSize)
	{
		int child;
		if (current * 2 + 2 == heapSize)
		{
			child = current * 2 + 1;
		}
		else
		{
			child = event_table[current * 2 + 1] < event_table[current * 2 + 2] ? current * 2 + 1 : current * 2 + 2;
		}

		if (event_table[current] < event_table[child])
		{
			break;
		}

		EVENT temp = event_table[current];
		event_table[current] = event_table[child];
		event_table[child] = temp;

		current = child;
	}
	return;
}




void init(int N) 
{
	CASE++;
	DONE = 0;
	heapSize = 0;
	CASE_N = N;
	for(int i =0; i<CASE_N; i++)
	{		
		factory[i].lot_count=0;
		factory[i].not_proc_lot_count=0;
		factory[i].tool_count=0;
	}
}

void setupTool(int T, int stepNo[5000], int procTime[5000]) 
{
	

	int temp_idx_in_factory;
	for(int t =0; t<T; t++)
	{		
		temp_idx_in_factory = factory[stepNo[t]].tool_count;
		factory[stepNo[t]].tool[temp_idx_in_factory].tool_id = t;
		factory[stepNo[t]].tool[temp_idx_in_factory].tool_pt = procTime[t];
		factory[stepNo[t]].tool[temp_idx_in_factory].process = false;
		factory[stepNo[t]].tool_count++;

		//pt 오름차순으로 삽입정렬하면서 입력
		TOOL temp_tool;
		int i, j;
		for (i = 1; i < factory[stepNo[t]].tool_count; i++)
		{
			temp_tool = factory[stepNo[t]].tool[i];
			j = i - 1;

			while ((temp_tool.tool_pt < factory[stepNo[t]].tool[j].tool_pt) && (j >= 0))
			{
				factory[stepNo[t]].tool[j + 1].tool_id = factory[stepNo[t]].tool[j].tool_id;
				factory[stepNo[t]].tool[j + 1].tool_pt = factory[stepNo[t]].tool[j].tool_pt;
				j = j - 1;
			}
			factory[stepNo[t]].tool[j + 1].tool_id = temp_tool.tool_id;
			factory[stepNo[t]].tool[j + 1].tool_pt = temp_tool.tool_pt;
		}
	}
}


void process(int time)
{
	
	int currentTime;
	int count = 0;
	while(event_table[0].event_time <= time)
	{
		currentTime = event_table[0].event_time;
		count++;
		if(count>heapSize)
		{
			break;
		}

		//처리 완료
		EVENT popedEvent;
		int factory_number = event_table[0].factory_number;
		int tool_number = event_table[0].tool_number;
		factory[factory_number].lot_count -= 1;
		for(int y=0; y<factory[factory_number].tool_count; y++)
		{
			if(factory[factory_number].tool[y].tool_id == tool_number)
			{
				factory[factory_number].tool[y].process = false;
				heapPop(&popedEvent); 
				break;
			}
		}		

		factory_number++;
		if(factory_number == CASE_N)
		{
			DONE++;
		}
		else
		{
			//다음 공정
			factory[factory_number].lot_count += 1;
			factory[factory_number].not_proc_lot_count += 1;
			for(int j=0; j<factory[factory_number].tool_count; j++)
			{
				if(factory[factory_number].tool[j].process == false)
				{
					EVENT e;				
					e.event_time = currentTime+factory[factory_number].tool[j].tool_pt;
					e.tool_number = factory[factory_number].tool[j].tool_id;
					e.factory_number = factory_number;
					e.pt = factory[factory_number].tool[j].tool_pt;
					heapPush(e);

					factory[factory_number].tool[j].process = true;
					factory[factory_number].not_proc_lot_count -= 1;		
					break;
				}
			}			
		}	


		if(factory[factory_number-1].not_proc_lot_count>0)
		{
			EVENT e;				
			e.event_time = currentTime+factory[factory_number-1].tool[tool_number].tool_pt;
			e.tool_number = tool_number;
			e.factory_number = factory_number-1;
			e.pt = factory[factory_number-1].tool[tool_number].tool_pt;
			heapPush(e);

			factory[factory_number-1].tool[tool_number].process = true;
			factory[factory_number-1].not_proc_lot_count -= 1;
		}
	}

}

void addLot(int time, int number) 
{	
	if(CASE == 2)
	{
		int temp =0;
	}
	factory[0].lot_count += number;
	factory[0].not_proc_lot_count += number;

	//시간 event 처리
	process(time);

	for(int lot=0; lot<number; lot++)
	{
		for(int i=0; i<factory[0].tool_count; i++)
		{
			if(factory[0].tool[i].process == false)
			{
				EVENT e;				
				e.event_time = time+factory[0].tool[i].tool_pt;
				e.tool_number = factory[0].tool[i].tool_id;
				e.factory_number = 0;
				e.pt = time+factory[0].tool[i].tool_pt;
				heapPush(e);

				factory[0].not_proc_lot_count -= 1;
				factory[0].tool[i].process = true;
				break;
			}
		}
	}
}

int simulate(int time, int wip[MAX_N]) 
{
	if(CASE == 2)
	{
		int temp =0;
	}
	process(time);

	for(int i =0; i<CASE_N; i++)
	{		
		wip[i] = factory[i].lot_count;
	}

	return DONE;
}


*/