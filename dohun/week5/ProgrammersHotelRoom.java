import java.util.*;

class Solution {

    public int solution(String[][] book_time) {
        // 1. 시간을 모두 분 단위로 변환
        int[][] times = new int[book_time.length][2];

        for (int i = 0; i < book_time.length; i++) {
            times[i][0] = toMinute(book_time[i][0]); // 시작 시간
            times[i][1] = toMinute(book_time[i][1]); // 종료 시간
        }

        // 2. 시작 시간 기준 오름차순 정렬
        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        // 3. Min-Heap: 방이 언제 비는지(= 종료 + 10분)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] t : times) {
            int start = t[0];
            int end = t[1] + 10;

            // 4. 가장 빨리 비는 방이 현재 예약 시작보다 먼저 비면 재사용
            if (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll();
            }

            // 5. 새 예약 배정 (혹은 재사용된 방 업데이트)
            pq.add(end);
        }

        return pq.size();
    }

    // HH:MM → 총 분(minute)으로 변환하는 함수
    private int toMinute(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
