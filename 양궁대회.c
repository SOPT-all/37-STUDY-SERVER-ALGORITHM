#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int maxCha = 0;
int best[11];
bool canWin = false;
int N;
int* A;

// 점수계산
int calcScore(int lion[]) {
    int lionScore = 0;
    int apeachScore = 0;

    for (int i = 0; i < 11; i++) {
        int score = 10 - i;
        if (lion[i] > A[i]) {
            lionScore += score;
        } else if (A[i] > 0) {
            apeachScore += score;
        }
    }
    return lionScore - apeachScore;
}

// 낮은 점수 맞춘수부터 비교해서 같을 때 뺀 첫 맞춘수 비교해서 리턴
bool checkNowIsBest(int lion[]) {
    for (int i = 10; i >= 0; i--) {
        if (lion[i] != best[i]) {
            return lion[i] > best[i];
        }
    }
    return false;
}

void dfs(int idx, int used, int lion[]) {
  
    // 점수 다 확인/화살 다 쓴경우
    if (idx == 11 || used == N) {
        // 남은 화살 0점으로
        if (used < N) {
            lion[10] += (N - used);
        }

        int nowCha = calcScore(lion);
        // 라이언이 더 클때
        if (nowCha > 0) {
            // 처음으로 이기는경우 or 지금이 더 큰차이 우승일때 or 점수차는같+낮은걸 더많이인 경우
            if (!canWin || nowCha > maxCha || 
               (nowCha == maxCha && checkNowIsBest(lion))) {
                maxCha = nowCha;
                canWin = true;

                //업데이트
                for (int i = 0; i < 11; i++) {
                    best[i] = lion[i];
                }
            }
        }

        if (used < N) {
            lion[10] -= (N - used);
        }
        return;
    }

    // 이번 과녁점수 포기
    dfs(idx + 1, used, lion);

    // 이 점수 이기기
    int need = A[idx] + 1;
    if (used + need <= N) {
        lion[idx] = need;
        dfs(idx + 1, used + need, lion);
        lion[idx] = 0;
    }
}

// info_len은 배열 info의 길이입니다.
int* solution(int n, int info[], size_t info_len) {
    N = n;
    A = info;

    int lion[11] = {0};
    dfs(0, 0, lion);

    // 이길 수 있는 경우가 없는경우
    if (!canWin) {
        int* answer = (int*)malloc(sizeof(int));
        answer[0] = -1;
        return answer;
    }

    int* answer = (int*)malloc(sizeof(int) * 11);
    for (int i = 0; i < 11; i++) {
        answer[i] = best[i];
    }
    return answer;
}
