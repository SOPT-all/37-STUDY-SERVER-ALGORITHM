#include <vector>
#include <algorithm>

using namespace std;

vector<int> g_info;
vector<vector<int>> g_adj;
int max_sheep = 0;

void dfs(int sheep, int wolf, vector<int> next_nodes) {
    // 늑대가 양보다 많거나 같아지면 조건 불만족 -> 탐색 종료
    if (wolf >= sheep) return;

    // 현재까지 모은 양의 최댓값 갱신
    max_sheep = max(max_sheep, sheep);

    // 갈 수 있는 모든 노드에 대해 하나씩 시도
    for (int i = 0; i < next_nodes.size(); ++i) {
        int next_node = next_nodes[i];

        // 다음 단계의 후보 노드 리스트 생성
        vector<int> new_next_nodes = next_nodes;

        // 1. 현재 방문할 노드는 리스트에서 제거 (방문 했으므로)
        new_next_nodes.erase(new_next_nodes.begin() + i);

        // 2. 현재 방문한 노드의 자식들을 후보 리스트에 추가
        for (int child : g_adj[next_node]) {
            new_next_nodes.push_back(child);
        }

        // 3. 양/늑대 수 갱신
        int next_sheep = sheep + (g_info[next_node] == 0 ? 1 : 0);
        int next_wolf = wolf + (g_info[next_node] == 1 ? 1 : 0);

        // 재귀 호출
        dfs(next_sheep, next_wolf, new_next_nodes);
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    g_info = info;
    g_adj.assign(info.size(), vector<int>());
    max_sheep = 0; // 초기화

    // 인접 리스트(트리) 생성
    for (const auto& edge : edges) {
        g_adj[edge[0]].push_back(edge[1]);
    }

    // 초기 상태 설정
    // 0번 노드(루트)는 항상 양이고, 처음에 무조건 방문함.
    // 따라서 0번 노드의 자식들을 초기 방문 가능 리스트로 설정
    vector<int> initial_next_nodes;
    for (int child : g_adj[0]) {
        initial_next_nodes.push_back(child);
    }

    // 루트 노드 포함(양=1, 늑대=0) 상태로 탐색 시작
    dfs(1, 0, initial_next_nodes);

    return max_sheep;
}
