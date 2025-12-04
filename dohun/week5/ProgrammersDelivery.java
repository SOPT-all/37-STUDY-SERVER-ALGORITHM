import java.util.*;

class Solution {

    // 하나의 연결 정보 (다음 마을 번호, 그 마을까지 걸리는 시간)
    static class Node implements Comparable<Node> {
        int to;     // 도착 마을 번호
        int cost;   // 이동에 걸리는 시간(거리)

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        // 우선순위 큐에서 cost가 작은 순으로 나오도록 정렬 기준 설정
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public int solution(int N, int[][] road, int K) {

        // 1. 그래프 초기화
        // graph[i] = i번 마을에서 갈 수 있는 (도착 마을, 비용) 리스트
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 도로 정보는 양방향이므로 둘 다 추가
        for (int[] r : road) {
            int a = r[0];   // 시작 마을
            int b = r[1];   // 도착 마을
            int c = r[2];   // 이동 비용(시간)

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        // 2. 최단 거리 테이블(dist)
        // dist[i] = 1번 마을에서 i번 마을까지의 "현재까지 알려진 최단시간"
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 처음엔 모든 마을이 무한히 멀다고 가정
        dist[1] = 0; // 1번 마을에서 1번 마을까지의 거리는 0

        // 3. 다익스트라를 위한 우선순위 큐 (비용이 가장 적은 노드부터 탐색)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0)); // 시작점(1번 마을)

        // 4. 다익스트라 알고리즘 시작
        while (!pq.isEmpty()) {

            // 현재까지 비용이 가장 적은 노드 꺼냄
            Node cur = pq.poll();

            int currentVillage = cur.to;      // 현재 마을
            int currentCost = cur.cost;       // 1번 → 현재 마을까지 걸리는 시간

            // 이미 더 짧은 경로가 있다면 이 노드는 무시(가지치기)
            if (currentCost > dist[currentVillage]) continue;

            // 현재 마을에서 갈 수 있는 이웃 마을들을 확인
            for (Node next : graph.get(currentVillage)) {

                int nextVillage = next.to;
                int nextCost = currentCost + next.cost; // 현재 비용 + 다음 마을로 가는 비용

                // 지금 발견한 경로(nextCost)가 기존 dist보다 더 짧으면 업데이트
                if (nextCost < dist[nextVillage]) {
                    dist[nextVillage] = nextCost;
                    pq.add(new Node(nextVillage, nextCost)); // 큐에 새로운 정보 넣기
                }
            }
        }

        // 5. 최종적으로 dist 배열 중 K 이하인 마을의 개수를 센다
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}
