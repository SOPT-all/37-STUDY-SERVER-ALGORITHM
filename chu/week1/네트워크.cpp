#include <string>
#include <vector>

using namespace std;
void dfs (int n, vector<vector<int>> &computers, vector<int> &visit, int k ){
    if(visit[k] == 0){ // 방문하지 않았으면
        visit[k] = 1;   // 방문처리 
        for(int i = 0 ; i < n; i++){
            if(computers[k][i] == 1 && k != i){  // 연결되어있고 자기자신이 아니면
                dfs(n, computers, visit, i); // 재귀호출
            }
        }  
    }
}

int solution(int n, vector<vector<int>> computers) {
    
    vector<int> visit(n);
    
    int answer = 0;
    
    for (int i = 0 ; i < n; i++){
        if(visit[i] == 0){
            dfs(n, computers, visit, i);   
            answer += 1;
        }
    } 
    return answer;
}