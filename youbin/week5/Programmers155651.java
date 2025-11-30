// <호텔 대실>
import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
     
        int[][] time = new int[book_time.length][2];
            
        for(int i = 0; i < book_time.length; i++) {
            String[] start = book_time[i][0].split(":");
            int startHour = Integer.parseInt(start[0]);
            int startMinute = Integer.parseInt(start[1]);
            int startTime = startHour * 60 + startMinute;
            
            String[] end = book_time[i][1].split(":");
            int endHour = Integer.parseInt(end[0]);
            int endMinute = Integer.parseInt(end[1]); 
            int endTime = endHour * 60 + endMinute + 10; // 청소시간 10분 더해줘야 함
            
            time[i][0] = startTime;
            time[i][1] = endTime;
        }
        
        // 시작 시간을 기준으로 정렬
        Arrays.sort(time, Comparator.comparingInt(o -> o[0]));
        
        // 우선순위 큐로 가장 빨리 비는 방을 앞으로 (종료 시간을 업데이트)
        PriorityQueue<Integer> pq = new PriorityQueue<>(); 

        for (int i = 0; i < book_time.length; i++) {
            int startTime = time[i][0];
            int endTime = time[i][1];

            // 가장 빨리 빌 방이 예약 시작 시간보다 먼저 끝난다면 재사용
            if (!pq.isEmpty() && pq.peek() <= startTime) {
                pq.poll(); 
            }

            // 재사용 방의 종료시간 업데이트
            pq.offer(endTime);

            // 사용 중인 방의 최대값 갱신
            answer = Math.max(answer, pq.size());
        }

        return answer;
    }
}
