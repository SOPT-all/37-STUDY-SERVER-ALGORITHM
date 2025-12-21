import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[][] tri = new int[n][n];  //삼각형 모양대로 값을 담을 리스트
        int total = n * (n + 1) / 2;
        int[] answer = new int[total];

        int r = -1, c = 0;
        int num = 1;

        for (int i = 0; i < n; i++) { // i : 채워야 할 삼각형 변 순서
            for (int step = 0; step < n - i; step++) {
                if (i % 3 == 0) {          // 아래로 이동
                    r++;
                } else if (i % 3 == 1) {   // 오른쪽 이동
                    c++;
                } else {                   // 왼쪽 위 대각선으로 이동
                    r--;
                    c--;
                }
                tri[r][c] = num++; // 값 채우기
            }
        }

        // 각 행 숫자 합치기
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) { // 삼각형 부분만
                answer[idx++] = tri[i][j];
            }
        }
        return answer;
    }
}
