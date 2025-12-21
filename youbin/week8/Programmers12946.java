// 하노이의 탑
import java.util.*;

class Solution {
    /**
    n = 3일 때로 생각을 해 보면 
    총 7번을 움직였고,
    가장 큰 원판이 3번 기둥에 있어야 함, 
    그리고 2번 기둥에 다른 원판들이 순서대로 꽂혀 있어야 함.
    
    그러면 2개의 원판을 3번 기둥에 옮기는 작업이 또 실행돼야 함. 즉 재귀!
    
    ✅ hanoi(N, start, to, via): start에서 to로 via를 거쳐 총 N개의 원반을 운반할 때 각 이동 과정을 출력
    
     => 한 번의 재귀, 가장 큰 원반 옮기기 이후 다시 한 번의 재귀. (단, n = 1 제외)
    */
   ArrayList<int[]> list;
    
    public int[][] solution(int n) {
        list = new ArrayList<>();
        
        hanoi(n, 1, 3, 2); // 1번 기둥에서 3번 기둥으로 2번 기둥을 거치기
        
        int[][] answer = new int[list.size()][2];
        
        for(int i = 0; i < list.size(); i ++) {
            int[] array = list.get(i);
            answer[i][0] = array[0];
            answer[i][1] = array[1];
        }
        
        return answer;
    }
    
    private void hanoi(int n, int from, int to, int via) {
        int[] move = {from, to}; // move = [출발지, 목적지]
        
        if(n == 1) {
            list.add(move); // n = 1일때는 그냥 움직이기만 하면 끝
        } else {
            hanoi(n - 1, from, via, to); // 일단 n-1개를 출발지에서 경유지로 옮김
            list.add(move); // 그리고 n번째 원반을 목적지로 옮김
            hanoi(n - 1, via, to, from); // 경유지에 있는 n-1개 원판을 목적지로 옮김 
        }
    }
}
