class ProgrammersArcheryCompetition {
    // 라이언이 얻을 수 있는 최대 점수 차이 (라이언 점수 - 어피치 점수)
    int maxDiff = 0;

    // 최대 점수 차이를 만들었던 라이언의 화살 배치
    int[] answer = null;

    public int[] solution(int n, int[] info) {

        // DFS를 통해 라이언의 모든 화살 배치 경우를 탐색
        // idx = 0 → 10점부터 시작
        // n = 남은 화살 수
        // new int[11] = 라이언의 화살 배치 상태
        dfs(0, n, new int[11], info);

        // 한 번도 라이언이 이긴 경우가 없다면 -1 반환
        if (answer == null) {
            return new int[]{-1};
        }

        return answer;
    }

    // idx        : 현재 고려 중인 점수 인덱스 (0 ~ 10)
    // arrowsLeft : 낭은 화살 개수
    // ryan       : 라이언의 현재 화살 배치 상태
    // info       : 어피치의 화살배치

    void dfs(int idx, int arrowsLeft, int[] ryan, int[] info) {

        // 모든 점수(10점 ~ 0점)를 다 고려한 경우
        if (idx == 11) {

            // 화살이 남아 있다면 전부 0점(마지막 인덱스)에 몰아준다
            // (문제 조건상 반드시 n발을 다 쏴야 함)
            if (arrowsLeft > 0) {
                ryan[10] += arrowsLeft;
            }

            // 현재 배치에서의 점수 차이 계산
            int diff = scoreDiff(ryan, info);

            // 라이언이 이긴 경우만 고려 (점수 차이 > 0)
            if (diff > 0) {
                // 1) 더 큰 점수 차이거나
                // 2) 점수 차이가 같고, 더 낮은 점수를 많이 맞힌 경우
                if (diff > maxDiff || (diff == maxDiff && isBetter(ryan, answer))) {

                    maxDiff = diff;
                    // 현재 상태를 저장 (배열 복사 중요!)
                    answer = ryan.clone();
                }
            }

            // 다음 탐색을 위해 0점에 넣어둔 화살 원복
            if (arrowsLeft > 0) {
                ryan[10] -= arrowsLeft;
            }
            return;
        }

        // 현재 점수(idx 점수)를 라이언이 가져오기 위해 필요한 최소 화살 수
        // 어피치보다 1발 더 맞히면 해당 점수를 가져감
        int need = info[idx] + 1;

        // [선택 1] 이 점수를 이기는 경우
        if (arrowsLeft >= need) {
            ryan[idx] = need;                    // 필요한 만큼 화살 사용
            dfs(idx + 1, arrowsLeft - need, ryan, info);
            ryan[idx] = 0;                       // 백트래킹 (원상 복구)
        }

        // [선택 2] 이 점수를 포기하는 경우 (0발 쏨)
        dfs(idx + 1, arrowsLeft, ryan, info);
    }


    // 현재 라이언 배치와 어피치 배치를 비교해 점수 차이를 계산
    int scoreDiff(int[] ryan, int[] info) {
        int r = 0, a = 0;

        for (int i = 0; i < 11; i++) {
            int score = 10 - i;

            // 라이언이 더 많이 맞히면 라이언이 해당 점수 획득
            if (ryan[i] > info[i]) {
                r += score;
            }

            // 그렇지 않으면 (같거나 적으면) 어피치가 점수 획득
            // 단, 어피치가 0발 맞힌 경우는 아무도 점수 없음
            else if (info[i] > 0) {
                a += score;
            }
        }

        return r - a;
    }


    // 점수 차이가 같은 경우, 더 낮은 점수를 많이 맞힌 배치가 우선순위
    boolean isBetter(int[] cand, int[] best) {

        // 아직 저장된 답이 없다면 무조건 갱신
        if (best == null) {
            return true;
        }

        // 0점부터 역순 비교 (낮은 점수 우선)
        for (int i = 10; i >= 0; i--) {
            if (cand[i] != best[i]) {
                return cand[i] > best[i];
            }
        }

        return false;
    }
}
