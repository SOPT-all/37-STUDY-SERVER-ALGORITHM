import java.util.*;

class Solution {

    // 그래프에서 사용할 노드 정보
    static class Node implements Comparable<Node> {
        int to;      // 도착한 이웃 마을 번호
        int cost;    // 현재까지의 거리(시간)

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;  // 비용 작은 순으로 우선순위 큐 정렬
        }
    }

    public int solution(int N, int[][] road, int K) {
        // 인접 리스트 그래프 생성 및 초기화. 보기 편하게 1번부터 N번까지 쓸 거라 N+1개 초기화
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {         
            graph.add(new ArrayList<>());
        }

        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];

            // a<->b 양방향 도로 정보 저장 (이웃 마을, 이웃 마을까지 걸리는 시간)
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        // 1번 마을에서 i번 마을까지 가는 데 걸리는 최단 시간을 넣을 리스트 생성
        int[] dist = new int[N + 1];
        
        // 아직 최단 거리를 모르니 모든 값 '무한대' 로 설정
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // 1번 마을에서 자기 자신까지는 0
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        // 탐색해야 할 마을 후보가 남아있는 동안 반복
        while (!pq.isEmpty()) {
            Node current = pq.poll(); 
            int curVillage = current.to;
            int curCost = current.cost;

            // 이미 더 짧은 경로로 방문한 적 있으면 스킵
            if (curCost > dist[curVillage]) continue;

            // 현재 마을에서 갈 수 있는 이웃 마을들 확인
            for (Node next : graph.get(curVillage)) {
                int nextVillage = next.to;
                int nextCost = curCost + next.cost;

                // 더 짧은 경로를 찾았으면 갱신
                if (nextCost < dist[nextVillage]) {
                    dist[nextVillage] = nextCost;
                    pq.offer(new Node(nextVillage, nextCost));
                }
            }
        }

        // K 시간 이하로 도달 가능한 마을 수 세기
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}
