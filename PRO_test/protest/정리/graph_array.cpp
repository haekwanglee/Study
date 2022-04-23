//graph array
#include <iostream>
using namespace std;

int map[5][5];
int main(void) 
{ 
	freopen("graph_input.txt", "r", stdin);
	int node, edge, from, to;
	cin >> node >> edge;
	for (int h = 0; h <= node; h++)  //map 배열 초기화 
		for (int w = 0; w <= node; w++) 
			map[h][w] = 0;
	for (int e = 0; e < edge; e++)
	{ 
		cin >> from >> to;
		map[from][to] = 1;  //간선 연결정보를 map에 표시(가중치가 있는 경우 1대신 가중치로) 
	}
	//모든 map 정보를 표시.
	cout << "Print all amp info " << endl;
	for (int h = 0; h <= node; h++)
	{ 
		for (int w = 0; w <= node; w++) 
			cout << map[h][w] << " "; cout << endl; 
	}
	
	cout << endl << endl;
	//1번에 연결된 노드 검색.
	cout << "Searching edge from 1 to  ";
	for (int m = 0; m <= node; m++)
	{ 
		if (map[1][m] != 0)  
			cout << m << " , "; 
	}
	cout << endl; 
} 