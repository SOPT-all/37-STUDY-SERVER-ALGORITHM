#include <string>
#include <vector>
using namespace std;

vector<int> best;
int bestDiff;

int calcDiff(const vector<int>& ryan, const vector<int>& apeach) {
    int r = 0, a = 0;
    for (int i = 0; i < 11; i++) {
        int score = 10 - i;
        if (ryan[i] == 0 && apeach[i] == 0) continue;
        if (ryan[i] > apeach[i]) r += score;
        else a += score; // 같은 발을 맞히면 어피치
    }
    return r - a;
}

// 낮은 점수(0점=인덱스10)부터 더 많이 맞힌 배열이 우선
bool betterByRule(const vector<int>& cand, const vector<int>& curBest) {
    for (int i = 10; i >= 0; i--) {
        if (cand[i] != curBest[i]) return cand[i] > curBest[i];
    }
    return false;
}

void dfs(int idx, int arrowsLeft, const vector<int>& apeach, vector<int>& ryan) {
    if (idx == 10) { // 0점 칸
        ryan[10] = arrowsLeft;
        int diff = calcDiff(ryan, apeach);
        if (diff > 0) {
            if (diff > bestDiff || (diff == bestDiff && betterByRule(ryan, best))) {
                bestDiff = diff;
                best = ryan;
            }
        }
        ryan[10] = 0; // backtrack
        return;
    }

    // 이 점수 칸을 이긴 경우: apeach[idx] + 1발 필요
    int need = apeach[idx] + 1;
    if (need <= arrowsLeft) {
        ryan[idx] = need;
        dfs(idx + 1, arrowsLeft - need, apeach, ryan);
        ryan[idx] = 0;
    }

    // 포기(0발)
    dfs(idx + 1, arrowsLeft, apeach, ryan);
}

vector<int> solution(int n, vector<int> info) {
    bestDiff = -1;
    best.clear();

    vector<int> ryan(11, 0);
    dfs(0, n, info, ryan);

    if (bestDiff <= 0) return vector<int>{-1};
    return best;
}
