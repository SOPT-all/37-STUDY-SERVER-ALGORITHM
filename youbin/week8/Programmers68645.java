// 삼각 달팽이
class Solution {
    public int[] solution(int n) {
        int[] answer = new int[(n * (n + 1)) / 2]; // 등차수열의 합 
        int[][] graph = new int[n][n]; // 삼각달팽이를 그릴 2차원 배열
        
        int x = -1, y = 0; // x++을 처음에 할거라서 x = -1부터 시작함
        int num = 1;
        
        // 2차원 배열 삼각 달팽이를 그리는 작업
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j ++) {
                if (i % 3 == 0) x ++; // 아래로 이동 
                else if (i % 3 == 1) y++; // 오른쪽으로 이동
                else if (i % 3 == 2) { x--; y--; } // 대각선 위로 이동
                
                graph[x][y] = num++;
            }
        }
        
        // 일차원 배열 형태의 정답 구하기
        int index = 0;
        for(int j = 0; j < n; j++) {
            for(int k = 0; k <= j; k++) {
                answer[index ++] = graph[j][k];
            }
        }
        
        return answer;
    }
}
