#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int answer;
bool col[12];
bool deGack1[24]; // 좌상-우하 대각선 정보 저장 row-col+n
bool deGack2[24]; // 우상-좌후 대각선 정보 저장 row+col

void dfs(int row, int n) {
    if (row == n) {
        answer++;
        return;
    }

    for (int c = 0; c < n; c++) {
        if (!col[c] && !deGack1[row - c + n] && !deGack2[row + c]) {
            col[c] = true;
            deGack1[row - c + n] = true;
            deGack2[row + c] = true;

            dfs(row + 1, n);

            col[c] = false;
            deGack1[row - c + n] = false;
            deGack2[row + c] = false;
        }
    }
}

int solution(int n) {
    answer = 0;

    for (int i = 0; i < 12; i++) {
        col[i] = false;
    }
    for (int i = 0; i < 24; i++) {
        deGack1[i] = false;
        deGack2[i] = false;
    }

    dfs(0, n);
    return answer;
}
