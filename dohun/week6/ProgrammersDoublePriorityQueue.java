import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        // 최소 힙과 최대 힙 생성
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // 명령어 처리
        for (String command : operations) {
            String[] temp = command.split(" ");
            if (temp[0].equals("I")) {
                // 삽입은 두 힙에 모두 삽입
                minHeap.add(Integer.parseInt(temp[1]));
                maxHeap.add(Integer.parseInt(temp[1]));
            } else if (temp[1].equals("1")) {
                // 최대값 삭제
                if (!minHeap.isEmpty()) {
                    // 최대 힙에서 꺼낸 값을 최소 힙에서도 삭제
                    minHeap.remove(maxHeap.poll());
                }
            } else {
                // 최소값 삭제
                if (!maxHeap.isEmpty()) {
                    // 최소 힙에서 꺼낸 값을 최대 힙에서도 삭제
                    maxHeap.remove(minHeap.poll());
                }
            }
        }
        // 결과 반환
        int[] answer = new int[2];
        // 힙이 비어있는 경우
        if (minHeap.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else if (minHeap.size() == 1) { // 원소가 하나 남아있는 경우
            int temp = minHeap.poll();
            answer[0] = temp;
            answer[1] = temp;
        } else { // 원소가 두 개 이상 남아있는 경우
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }
        return answer;
    }
}
