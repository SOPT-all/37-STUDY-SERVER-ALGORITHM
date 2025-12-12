class Solution {
    int maxScoreDiff = 0; 
    int[] nowBest = {-1};
    int[] ryan = new int[11];
    int[] info; 
    
    public int[] solution(int n, int[] info) {
        this.info = info;
        dfs(0, n, ryan); 
        
        return nowBest;
    }
    
    private void dfs(int idx, int leftArrows, int[] ryan) {
        if (idx == 11) { // dfs 종료
            int diff = getDiff(ryan);
            
            if (diff <= 0) return; // 라이언이 이기지 못한 경우
            
            if (diff > maxScoreDiff) { // 기존 최고 점수차이보다 현재 점수차이가 크다면
                maxScoreDiff = diff; // 최고 점수를 갱신하고, 이 때 화살배치를 저장
                nowBest = ryan.clone();
            }
            else if (diff == maxScoreDiff) { // 기존 최고 점수차이와 현재 점수차이가 같다면
                if (isBetter(ryan, nowBest)) {
                    nowBest = ryan.clone();
                }
            }
            return; 
        }
        
        if (idx == 10) { // 마지막 칸이면 화살 전부 사용하기
            ryan[idx] = leftArrows;
            dfs(idx + 1, 0, ryan);
            ryan[idx] = 0; // 백트래킹을 위해 이전분기 영향 제거
            return; 
        }
        
        // 점수를 따기로 한 경우
        int needArrows = info[idx] + 1;
        if (needArrows <= leftArrows) {
            ryan[idx] = needArrows;
            dfs(idx + 1, leftArrows - needArrows, ryan);
            ryan[idx] = 0;
        }
        
        // 점수를 포기하기로 한 경우
        dfs(idx + 1, leftArrows, ryan);
    }
    
    private int getDiff(int[] ryan) {
        int ryanS = 0;
        int apeachS = 0;
        
        for (int i = 0; i<11; i++) {
            int score = 10-i;
            int a = info[i];
            int r = ryan[i];
            
            if (a == 0 && r == 0) continue;
            
            if (a >= r) apeachS += score;
            else ryanS += score;
        }
        
        return ryanS - apeachS;
    }
    
    // 우승 방법이 여러가지일 경우, 가장 낮은 점수를 더 많이 맞힌 경우 구하기
    private boolean isBetter(int[] cand, int[] cur) {
        // 아직 아무 정답이 없는 상태면 무조건 cand 선택
        if (cur.length == 1 && cur[0] == -1) return true;

        for (int i = 10; i >= 0; i--) {
            if (cand[i] > cur[i]) return true;
            else if (cand[i] < cur[i]) return false;
        }
        return false; // 완전히 같은 경우
    }
}
