#include <vector>
#include <queue>

using namespace std;

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    vector<bool> visited(n, false); // 방문 여부
    queue<int> que; // 방문할 노드 저장

    for(int i = 0; i<n; i++) {
        // 방문하지 않은 노드에 대해서 새로 방문
        if(visited[i]) continue;
        que.push(i);
        answer++;

        // 더이상 연결된 노드가 없을 때까지 연결 따라서 방문
        while(!que.empty()) {
            // 큐에서 빼내고 현재 노드 방문 처리
            int now = que.front(); que.pop();
            visited[now] = true;

            for(int j = 0; j<n; j++) {
                // 현재 노드가 아닌 노드 중, 방문하지 않은 노드와 연결되어 있으면 해당 노드 큐에 저장
                if(computers[now][j] && now != j && !visited[j]) que.push(j);
            }
        }
    }

    return answer;
}
