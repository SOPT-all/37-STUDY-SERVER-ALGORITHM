#include <stdio.h>
#include <stdlib.h>

int **answer;
int idx = 0;

// 옮길 원반 갯수
// from 원래 원반들이 있는 곳
// via 일단 옮겨 두는 곳
// to 옮길 목적지
void hanoi(int n, int from, int via, int to) {
    if (n == 1) {
        answer[idx][0] = from;
        answer[idx][1] = to;
        idx++;
        return;
    }

    hanoi(n - 1, from, to, via);   // 위에 있는 n-1개 이동
    hanoi(1, from, via, to);       // 가장 큰 원판 이동
    hanoi(n - 1, via, from, to);   // 다시 n-1개 이동
}

int** solution(int n, int* returnSize, int** returnColumnSizes) {
    // 2^n - 1 계산 -> 원반 이동할 총 횟수 알기 위해
    int total = 1;
    for (int i = 0; i < n; i++) {
        total *= 2;
    }
    total -= 1;

    // 이동경로 저장할 곳 생성
    answer = (int**)malloc(sizeof(int*) * total);
    *returnColumnSizes = (int*)malloc(sizeof(int) * total);

    for (int i = 0; i < total; i++) {
        answer[i] = (int*)malloc(sizeof(int) * 2);
        (*returnColumnSizes)[i] = 2;
    }

    idx = 0;
    hanoi(n, 1, 2, 3);

    *returnSize = total;
    return answer;
}

