#include <stdio.h>
#include <stdlib.h>

int* solution(int n, long long left, long long right) {

    int len = right - left + 1;
    int* answer = (int*)malloc(len * sizeof(int));  // 결과 배열 동적 할당

    long long idx = 0;
    for (long long i = left; i <= right; i++) {
        int row = i / n;
        int col = i % n;
        
        // row/column중에서 더큰 값을 살리기
        answer[idx++] = (row > col) ? row + 1 : col + 1;
    }

    return answer;
}

int main() {
    int n;
    long long left, right;
    
    scanf("%d %lld %lld", &n, &left, &right);
    
    int* result = solution(n, left, right);
    int len = right - left + 1;
    
    for (int i = 0; i < len; i++) {
        printf("%d ", result[i]);
    }
    printf("\n");
    
    free(result);  // 동적 할당 메모리 해제
    return 0;
}
