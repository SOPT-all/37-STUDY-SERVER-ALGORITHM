// <배달>
import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        // 마을이 연결되어 있는 것을 표시할 graph 이차원 배열 생성
        int[][] graph = new int[N + 1][N +1];
        // 1번 마을부터 N번 마을까지의 최단 거리가 담겨 있는 dist 배열 생성
        int[] dist = new int[N + 1];
        int MAX_VAL = Integer.MAX_VALUE;
        
         // 자기 자신은 항상 0, 나머지는 MAX로 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], MAX_VAL); // Arrays.fill은 행을 채워줌
            graph[i][i] = 0;
        }
        
        // dist도 MAX값으로 초기화
        Arrays.fill(dist, MAX_VAL);
        
        // graph에 값 넣어주기
        for(int i = 0; i < road.length; i++) { 
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            // 두 마을을 연결하는 도로에 대해 더 작은 값이 있을 때 그 값으로 갱신
            if (graph[a][b] > c) { 
                graph[a][b] = c;
                graph[b][a] = c;
            }
        }
        
        // 우선순위 큐 사용 [마을 번호, 지금까지의 비용]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{1, 0});
        
        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int curNum = node[0];
            int curCost = node[1];
            
            // curNum 마을에서 i번 마을로 갈 수 있는 모든 마을 탐색
            for(int i = 1; i <= N; i++) {
                if(graph[i][curNum] == MAX_VAL) continue;
 
                int sumCost = curCost + graph[i][curNum];
                // 최단 경로 dist[i]보다 더 작은 값이 있다면 그 값으로 갱신
                if(sumCost < dist[i]) {
                    dist[i] = sumCost;
                    pq.offer(new int[]{i, sumCost});
                }
            }
        }
        
        for(int i = 1; i <= N; i++) {
            if(K >= dist[i]) answer++;
        }
        
        return answer;
    }
}
