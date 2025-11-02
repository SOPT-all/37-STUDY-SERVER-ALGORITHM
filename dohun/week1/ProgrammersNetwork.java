class ProgrammersNetwork {
    private boolean[] visited;
    private int count;

    public int solution(int n, int[][] computers) {
        // 네트워크의 개수를 세기 위한 DFS 탐색

        // visited 배열 초기화 및 네트워크 개수 초기화
        visited = new boolean[n];
        count = 0;

        // 모든 컴퓨터를 순회하며 방문하지 않은 컴퓨터가 있으면 DFS 수행
        for (int i = 0; i < n; i++) {
            // 방문하지 않은 컴퓨터가 있으면 DFS 수행
            if (!visited[i]) {
                dfs(i, computers, visited);
                // 네트워크 개수 증가
                count++;
            }
        }

        // 최종 네트워크 개수 반환
        return count;
    }

    // DFS 메서드 정의
    private void dfs(int current, int[][] computers, boolean[] visited) {
        // 현재 컴퓨터를 방문 처리
        visited[current] = true;
        // 현재 컴퓨터와 연결된 모든 컴퓨터를 순회
        for (int i = 0; i < computers.length; i++) {
            // 연결되어 있고 방문하지 않은 컴퓨터가 있으면 재귀적으로 DFS 수행
            if (computers[current][i] == 1 && !visited[i]) {
                dfs(i, computers, visited);
            }
        }
    }
}

