#include <string>
#include <vector>

using namespace std;
int visited[200];
void dfs(int n, const vector<vector<int>>& computers) //연결되어있는 것들을 찾고 방문처리
{
    visited[n] = 1;
    for(int i=0; i<computers.size(); i++)
    {
        if(computers[n][i]==1)
        {
            if(visited[i]!=1) dfs(i,computers);
        }
    }
}
int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    for(int i=0; i<n; i++)
    {
        if(visited[i]!=1) // 방문처리 되지 않은 컴퓨터를 기준으로 연결되어있는 것들 찾기
        {
            dfs(i,computers);
            answer++;
        }
    }
    return answer;
}