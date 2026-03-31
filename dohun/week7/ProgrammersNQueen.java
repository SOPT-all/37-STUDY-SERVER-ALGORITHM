class ProgrammersNQueen {
    private int count = 0;
    private int[] cols;

    public int solution(int n) {
        // 열 배열 초기화
        cols = new int[n];
        // 시작 행과 체스판 크기로 DFS 호출
        dfs(0, n);
        return count;
    }

    private void dfs(int row, int n) {
        // 모든 행에 퀸을 다 둔 경우
        if (row == n) {
            count++;
            return;
        }

        // 현재 행에 놓을 열 선택
        for (int col = 0; col < n; col++) {
            cols[row] = col;

            if (isPossible(row)) {
                dfs(row + 1, n);
            }
        }
    }

    private boolean isPossible(int row) {
        for (int i = 0; i < row; i++) {
            // 같은 열 체크
            if (cols[i] == cols[row]) {
                return false;
            }

            // 대각선 체크
            if (Math.abs(row - i) == Math.abs(cols[row] - cols[i])) {
                return false;
            }
        }
        return true;
    }
}
