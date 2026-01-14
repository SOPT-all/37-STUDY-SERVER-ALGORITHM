#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// 소수 판별 함수
bool is_prime(int num) {
    if (num <= 1) return false;
    for (int i = 2; i * i <= num; i++) {
        if (num % i == 0) 
          return false;
    }
    return true;
}

int solution(int n, int k) {
    int answer = 0;
    int tmp = 0;  // k진수로 변환한 값을 저장할 변수
    int tmp_na;    // 진수 변환 후 각자리 저장용 임시 변수
    int so_co = 0; // 소수 개수 카운트
    int b = 1;     // 10진수로 변환용 자릿수

    for (; n > 0; n = n / k) {
        // 현재 자리 값을 tmp에 누적
        if (n % k != 0) {
            tmp_na = (n % k) * b;
            tmp += tmp_na;
            b *= 10;
        } else {
            // 나누어 떨어질 때, 소수 판별 진행
            if (tmp != 0 && is_prime(tmp)) {
                so_co++; // 소수 카운팅+1
            }
            tmp = 0;   // tmp 초기화
            b = 1;     // 10진수 변환용 자릿수 초기화
        }
    }

    // 마지막에 남아있는 숫자가 소수인지 체크
    if (tmp != 0 && is_prime(tmp)) {
        so_co++;
    }

    return so_co;
}

int main() {
    int n, k;
    scanf("%d", &n);
    scanf("%d", &k);
    
    int result = solution(n, k);
    printf("%d", result);
    
    return 0;
}
