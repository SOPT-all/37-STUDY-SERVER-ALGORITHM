// 양궁대회
class Solution {
    
    private int maxDiff = -1;   // 라이언-어피치 최대 점수 차
    private int[] best = null;  // 최대 점수 차를 만드는 라이언의 화살 배치

    public int[] solution(int n, int[] info) {
         int[] answer;

        int[] ryan = new int[11];   // 라이언이 각 점수칸에 쏜 화살 수
        dfs(0, n, info, ryan);

        // 이길 수 있는 경우가 없으면 -1 반환
        if (best == null) answer = new int[]{-1};
        else answer = best;
        
        return answer;
    }
    
    /**
    idx: 현재 점수 칸
    arrowsLeft: 이만큼 arrowsLeft발 남아있을 때 
    가능한 모든 선택을 하는 것
    */
    private void dfs(int idx, int arrowsLeft, int[] apeach, int[] ryan) {
        // 11칸 모두 결정했으면 점수 계산
        if (idx == 11) {
            // 남은 화살은 0점 칸에 몰아넣기(동점 규칙에서 유리)
            int added = arrowsLeft;
            ryan[10] += added; // 인덱스10: 0점

            int diff = calcDiff(apeach, ryan);

            // diff > 0 이어야 라이언이 "이기는 경우"로 인정
            if (diff > 0) {
                if (diff > maxDiff) {
                    maxDiff = diff;
                    best = ryan.clone(); // ryan 배열의 현재 값들만 복사해서 새로운 배열 생성
                } else if (diff == maxDiff && isBetter(ryan, best)) {
                    best = ryan.clone();
                }
            }

            // 백트래킹: 넣었던 남은 화살 원상복구
            ryan[10] -= added;
            return;
        }

        // 선택 1) idx 점수 칸을 "이긴다"
        int need = apeach[idx] + 1; // 이기려면 필요한 화살 수
        if (need <= arrowsLeft) {
            ryan[idx] = need; // 이 칸에 need발 쏘고
            dfs(idx + 1, arrowsLeft - need, apeach, ryan);
            ryan[idx] = 0;    // 백트래킹(되돌리기)
        }

        // 선택 2) idx 점수 칸을 "포기한다"(0발)
        ryan[idx] = 0;
        dfs(idx + 1, arrowsLeft, apeach, ryan);
    }

    // 라이언 점수 - 어피치 점수 계산
    // 라이언이 1발이라도 더 많으면 그 점수는 라이언 것
    private int calcDiff(int[] apeach, int[] ryan) {
        int aScore = 0, rScore = 0;

        for (int i = 0; i < 11; i++) {
            int score = 10 - i;

            // 둘 다 0발이면 점수 없음
            if (apeach[i] == 0 && ryan[i] == 0) continue;

            // 라이언이 더 많이 쏘면 라이언이 점수 획득
            if (ryan[i] > apeach[i]) rScore += score;
            else aScore += score; // 아니면 어피치가 점수(같아도 어피치가 가져감)
        }

        return rScore - aScore;
    }

    // 동점(점수 차 같음)일 때 더 좋은 배치인지 판단
    // 규칙: "더 낮은 점수 칸(0점부터)"에 화살이 더 많은 배치가 우선
    private boolean isBetter(int[] cand, int[] curBest) {
        // 0점 칸부터(인덱스 10) 거꾸로 비교
        for (int i = 10; i >= 0; i--) {
            if (cand[i] != curBest[i]) {
                return cand[i] > curBest[i];
            }
        }
        return false; // 완전히 동일
    }
}
