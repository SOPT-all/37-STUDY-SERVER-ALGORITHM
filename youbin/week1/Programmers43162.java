// <네트워크>
import java.util.*;

class Solution {
    
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        // 컴퓨터를 방문한 적이 있는지 확인하는 visited 배열
        visited = new boolean[n];
        
        for(int i = 0 ; i < n; i++) {
            // 0 ~ n-1까지 돌면서 BFS 함수 수행
            // 이때 visited 배열을 방문하지 않았다는 의미는 네트워크 하나의 네트워크 시작점이라는 의미
            // 따라서 bfs 함수 이후 answer 값 1증가
           if(!visited[i]) {
               // 해당 시작점과 연결된 컴퓨터 탐색
                bfs(i, n, computers);      
                answer++;
           }
        }
        
        return answer;
    }
    
    static void bfs(int start, int n, int[][] computers) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        
        while(!queue.isEmpty()) {
            int front = queue.poll();
            
            for(int i = 0; i < n; i++) {
                // computers 배열이 1이고 방문한 적이 없다면 큐에 추가하여 탐색 대상으로 등록
                if(computers[front][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        } 
    }
}
