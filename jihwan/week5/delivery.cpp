#include <iostream>
#include <vector>
#include <queue>
using namespace std;
vector<int> board[51];
int cost[51][51];
int dis[51];
int visit[51];
int solution(int N, vector<vector<int> > road, int K) {
    int answer = 0;
    for(int i=1; i<=N; i++)for(int j=1; j<=N; j++) cost[i][j]=-1;
    for(auto&a:road)
    {
        board[a[0]].push_back(a[1]);
        board[a[1]].push_back(a[0]);
        if(cost[a[0]][a[1]]==-1||cost[a[0]][a[1]]>a[2]) cost[a[0]][a[1]] = a[2];
        if(cost[a[1]][a[0]]==-1||cost[a[1]][a[0]]>a[2]) cost[a[1]][a[0]] = a[2];
    }
    queue<pair<int,int>> q;
    q.push({1,0});
    for(int i=1; i<=N; i++) dis[i] = -1;
    dis[1]=0;
    while(!q.empty())
    {
        int n = q.front().first;
        int d = q.front().second;
        q.pop();
        for(auto&a:board[n])
        {
            if(dis[a]==-1||dis[a]>dis[n]+cost[n][a])
            {
                dis[a]=dis[n]+cost[n][a];
                q.push({a,d+cost[n][a]});
            }
        }
    }
    for(int i=1; i<=N; i++) if(dis[i]<=K) answer++;
    return answer;
}