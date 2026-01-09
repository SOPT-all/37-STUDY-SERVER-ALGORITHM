class Solution {
    int count = 0;
    boolean[] column;
    boolean[] diagM;
    boolean[] diagP;
    
    public int solution(int n) {
        column = new boolean[n]; 
        diagM = new boolean [ 2 * n - 1 ]; // 인덱스 보정
        diagP = new boolean [ 2 * n - 1 ];
        
        dfs(0, n);
        return count;
    }
    
    private void dfs (int row, int n) {
        // 규칙에 맞춰 각 행마다 퀸 하나씩 놓았다면 체스판 완성
        if (row == n) {
            count++;
            return;
        }
        
        // 우하향 방향 대각선 요소들은 row - col 값이 같음
        // 우상향 방향 대각선 요소들은 row + col 값이 같음
        for (int c = 0; c<n; c++) {
            int dM = row - c + (n-1); // 음수 방지 보정
            int dP = row + c;
            
            // 다른 퀸의 범위와 충돌하는지 체크
            if (column[c] || diagM[dM] || diagP[dP]) continue;
            
            column[c] = diagM[dM] = diagP[dP] = true;
            dfs(row + 1, n);
            column[c] = diagM[dM] = diagP[dP] = false;
        }
    }
}
