// <타겟 넘버>
import java.util.*;

class Solution {
    public int solution1(int[] numbers, int target) {
        int answer = 0;

        // BFS를 사용하기 위한 Queue 생성
        Queue<int[]> queue = new LinkedList<>();
        
        // 큐는 [레벨, 합계]를 담을 수 있도록 선언 
        // 첫 시작은 레벨 0, 합계 0
        queue.offer(new int[]{0, 0});
        
        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int level = node[0];
            int sum = node[1];
            
            // level이 numbers 배열 길이와 같으면 배열을 다 돈 것
            if(level == numbers.length) {
                // 이때 합계(sum)가 타켓넘버(target)과 같으면 경우의 수(answer) 1 증가
                if(sum == target) answer ++;
            } else {
                //  큐에 값을 집어넣는 것은 level의 값을 1 더하는 것과 같음
                //  numbers 배열에 있는 숫자를 지금까지 더한 합계에 더하거나 뺌
                queue.offer(new int[]{level + 1, sum + numbers[level]});
                queue.offer(new int[]{level + 1, sum - numbers[level]});
            }
        }
        
        return answer;
    }
}
