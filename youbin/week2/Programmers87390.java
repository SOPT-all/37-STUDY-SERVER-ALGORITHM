// <n^2 배열 자르기>
class Solution {
    public int[] solution(int n, long left, long right) {
       
        // 2차원 배열을 채우는 걸로 했다가 되돌리기..
        // 구해야 하는 것은 1차원 배열이므로 시간복잡도를 줄이기 위해 구하고자 하는 만큼만 구하자!
        int length = (int)(right - left) + 1;
        int[] answer = new int[length];
        
        /* 0행 0열 값이 1어서 헷갈렸는데, 1행 1열 값이 1이라고 생각하면
      
        1	2	3
        2	2	3
        3	3	3
        
        (1 ,1) = 1  (1, 2) = 2 (1, 3) = 3
        즉, 행과 열 값 중에 큰 것을 따라간다는 것을 알 수 있음
        */
        for(int i = 0; i < length; i++) {
            int row = (int)((i + left) / n) + 1; // 행
            int col = (int)((i + left) % n) + 1; // 열
            answer[i] = Math.max(row, col);
        }
        
        return answer;
    }
}
