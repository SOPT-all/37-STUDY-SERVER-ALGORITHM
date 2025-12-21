class Solution {
    public int[] solution(int n) {

        // 삼각형 배열 생성
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
        }

        int num = 1;     // 채울 숫자
        int row = -1;    // 현재 행 위치
        int col = 0;     // 현재 열 위치

        // 방향 반복 (총 n번)
        for (int i = 0; i < n; i++) {

            // 한 방향으로 이동하는 횟수는 n - i
            for (int j = i; j < n; j++) {

                // 아래로 이동
                if (i % 3 == 0) {
                    row++;
                }
                // 오른쪽 이동
                else if (i % 3 == 1) {
                    col++;
                }
                // 왼쪽 위 대각선 이동
                else {
                    row--;
                    col--;
                }

                triangle[row][col] = num++;
            }
        }

        // 삼각형을 1차원 배열로 변환
        int size = n * (n + 1) / 2;
        int[] answer = new int[size];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                answer[idx++] = triangle[i][j];
            }
        }

        return answer;
    }
}
