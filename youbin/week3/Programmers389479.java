// <서버 증설 횟수>
import java.util.*;

class Solution {
    static int globalK;
    // 내부적으로 정렬된 상태를 유지하기 위해 우선순위 큐 사용
    static PriorityQueue<Integer> pq;
    
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        globalK = k;
        pq = new PriorityQueue<>();
        
        for (int i = 0; i < players.length; i++) {
            int player = players[i];
            
            // 1. 현재 시간에 만료된 서버 제거
            while (!pq.isEmpty() && pq.peek() <= i) {
                pq.poll();
            }
            
            // 2. 이 시간에 필요한 서버 개수 계산
            int needServerNum = player / m; 
            
            // 3. 지금 켜져 있는 서버로 충분한지 확인 + 부족하면 증설
            int plusAnswer = isServerAvailable(i, needServerNum);
            answer += plusAnswer;
        }
        
        return answer;
    }
    
    public static int isServerAvailable(int time, int needServerNum) {
        int currentServers = pq.size();   // 지금 돌아가는 서버 수
        
        // 지금 돌아가는 서버 수가 많으면, 증설 필요 없음
        if (currentServers >= needServerNum) return 0;
        
        // 증설해야 하는 서버 개수
        int addServer = needServerNum - currentServers; 
        
        // 새로 증설된 서버들의 반납 시간을 우선순위 큐에 등록
        for (int i = 0; i < addServer; i++) {
            pq.add(time + globalK); 
        }
        
        return addServer;
    }
}
