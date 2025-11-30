import java.util.*;

class Solution {
    public int solution(String[][] bookTime) {
        // 시간을 분 단위로 바꾸고, 청소 시간을 포함해 방 사용 시간을 [start, end+10] 형태로 저장
        // e.g. bookTime[["15:00", "17:00"], ["16:40", "18:20"]] 
        // -> times[[900,1030], [1000, 1110], ...]
        int n = bookTime.length;
        int[][] times = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            int start = toMinutes(bookTime[i][0]);
            int end = toMinutes(bookTime[i][1]) + 10; 
            
            times[i][0] = start;
            times[i][1] = end;
        }
        
        // 시작 시간 기준으로 오름차순 정렬
        Arrays.sort(times, (a, b) -> a[0] - b[0]);
        
        // 방이 언제까지 점유 중인지를 저장하는 PQ. 방의 사용 종료 시간이 제일 빠른 게 우선 순위를 가지게 됨.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            int start = times[i][0];
            int end = times[i][1];
            
            // 현재 가장 빨리 비는 방의 시간(pd.peek())이 이번 예약의 시작 시간(start)보다 작거나 같으면 방 재사용 가능
            if (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll(); // 그 방은 이번 손님에게 재사용
            }
            
            // 이번 예약의 end(=퇴실+청소완료 시간)를 방에 배정
            pq.offer(end);
        }
        
        // PQ에 남아 있는 방 개수가 최소 필요한 객실 수
        return pq.size();
    }
    
    // String인 "HH:MM" → int인 minute으로 변환
    // e.g. 16:40 -> 1000 
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int min = Integer.parseInt(parts[1]);
        return hour * 60 + min;
    }
}
