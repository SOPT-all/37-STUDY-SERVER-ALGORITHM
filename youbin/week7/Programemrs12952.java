// N-Queen
class Solution {
    
    int answer;
    boolean[] col; // 같은 열에 이미 퀸이 있는지 확인
    boolean[] down; // 좌상단 -> 우하단 방향 대각선 확인
    boolean[] up; // 좌하단 -> 우상단 방향 대각선 확인
    
    public int solution(int n) {
        answer = 0;
        
        col = new boolean[n];
        down = new boolean[2 * n - 1]; // 대각선은 총 2n-1개 존재 
        up = new boolean[2 * n - 1]; // 이것도 마찬가지

        dfs(0, n); // 0번째 행부터 시작
        
        return answer;
    }
    
        // 현재 행에 대해 모든 열을 두고 판별
        private void dfs(int row, int n) {
        // row == n이면 0 ~ n-1행까지 모두 퀸을 성공적으로 배치함
        if (row == n) { 
            answer++;
            return;
        }

        for (int curCol = 0; curCol < n; curCol++) {
            int x = row + curCol; // 좌상단 -> 우하단 번호 매기기
            int y = row - curCol + (n - 1); // 좌하단 -> 우상단 번호 매기기

            // 현재 행에 대해 같은 열이 있는지, 두 방향 대각선에 있는지 확인
            if (col[curCol] || down[x] || up[y]) continue;

            // 그게 아니라면, 나머지 부분에 퀸을 놓음
            col[curCol] = true;
            down[x] = true;
            up[y] = true;

            // 다음 행으로 넘어가서 배치 시도
            dfs(row + 1, n);

            // [백트래킹] : (row, i)에 퀸을 놓았던 기록을 지움 -> 다음 열 후보를 시도할 수 있도록 함
            col[curCol] = false;
            down[x] = false;
            up[y] = false;
        }
    }
}
