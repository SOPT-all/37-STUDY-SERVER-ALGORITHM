#include <stdio.h>
#include <stdlib.h>
int dir;


int* solution(int n) {
    int size = n * (n + 1) / 2;
    int* answer = (int*)malloc(sizeof(int) * size);

    // 배열 저장할 곳(2차원 매트릭스) 생성
    int** arr = (int**)malloc(sizeof(int*) * n);
    for (int i = 0; i < n; i++) {
        arr[i] = (int*)calloc(i + 1, sizeof(int));
    }

    int row = -1, col = 0;
    int num = 1;

    // 로직 진행
    for (int step = n; step > 0; step--) {
        // 방향 세팅
        dir = (n - step) % 3;

        for (int i = 0; i < step; i++) {
            if (dir == 0) {  // 아래로        
                row++;
            } else if (dir == 1) {   // 오른쪽
                col++;
            } else {   // 좌상향
                row--;
                col--;
            }
            arr[row][col] = num++;
        }
    }

    int idx = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            answer[idx++] = arr[i][j];
        }
    }

    for (int i = 0; i < n; i++) {
        free(arr[i]);
    }
    free(arr);

    return answer;
}
