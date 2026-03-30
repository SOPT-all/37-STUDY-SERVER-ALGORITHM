#include <queue>
using namespace std;

// 인접 리스트: Graph[u] = { {v, cost}, ... }
vector<pair<int, int>> Graph[55];

// 각 마을까지의 최소 거리 저장
vector<int> Dist;

// 다익스트라 알고리즘
void Dijkstra(int N)
{
    // 우선순위 큐 (최소 비용이 우선되도록 음수로 저장)
    priority_queue<pair<int, int>> PQ;

    // 시작점 push (비용 0, 노드 1)
    PQ.push({0, 1});
    Dist[1] = 0;

    while (!PQ.empty())
    {
        int currentCost = -PQ.top().first;  // 원래 비용 복원
        int currentNode = PQ.top().second;
        PQ.pop();

        // 현재 노드에서 이동 가능한 모든 인접 노드 탐색
        for (int i = 0; i < Graph[currentNode].size(); i++)
        {
            int nextNode = Graph[currentNode][i].first;
            int edgeCost = Graph[currentNode][i].second;

            // 현재까지 비용 + 다음 간선 비용
            int newCost = currentCost + edgeCost;

            // 더 짧은 경로 발견 시 갱신
            if (Dist[nextNode] > newCost)
            {
                Dist[nextNode] = newCost;
                // 최소 비용 정렬을 위해 음수로 push
                PQ.push({-newCost, nextNode});
            }
        }
    }
}

int solution(int N, vector<vector<int>> road, int K)
{
    // 거리 배열 초기화
    Dist.assign(N + 1, 2e9);

    // 그래프 구성
    for (int i = 0; i < road.size(); i++)
    {
        int nodeA = road[i][0];
        int nodeB = road[i][1];
        int cost = road[i][2];

        Graph[nodeA].push_back({nodeB, cost});
        Graph[nodeB].push_back({nodeA, cost});
    }

    // 다익스트라 실행 (1번 마을 시작)
    Dijkstra(N);

    // K 이하로 도달 가능한 마을 수 계산
    int answer = 0;
    for (int i = 1; i <= N; i++)
    {
        if (Dist[i] <= K) answer++;
    }

    return answer;
}
