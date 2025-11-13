import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(int n, int k) {
        List<Integer> digits = nToKBaseDigits(n, k);

        int count = 0;
        //찾은 숫자를 담을 스트링빌더
        StringBuilder sb = new StringBuilder();

        for (int d : digits) {
            if (d == 0) {
                // 0을 만나면 지금까지 누적된 덩어리 처리
                if (sb.length() > 0) {
                    long val = Long.parseLong(sb.toString());
                    if (isPrime(val)) count++;
                    sb.setLength(0); // 초기화
                }
            } else {
                // 0이 아니면 덩어리에 이어 붙이기
                sb.append(d);
            }
        }
        // 마지막 덩어리 처리 (끝이 0이 아닐 때)
        if (sb.length() > 0) {
            long val = Long.parseLong(sb.toString());
            if (isPrime(val)) count++;
        }

        return count;
    }

    /*
     * 주어진 숫자 n을 k진수 자리 리스트로 변환 
     * 예: n=437674, k=3 -> [2,1,1,0,2,0,1,0,1,0,1,1]
     */
    private List<Integer> nToKBaseDigits(int n, int k) {
        //나머지들을 저장하는 리스트
        List<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % k); 
            n /= k;
        }
        Collections.reverse(digits); 
        return digits;
    }

    // 소수 판별
    private boolean isPrime(long num) {
        //0이나 1은 소수가 아님
        if (num < 2) return false;
        
        //가장 작은 짝수(2) 로 나누어지는지 검사
        // + 주어진 수가 2면 true, 아니면 false
        if (num % 2 == 0) return num == 2;
        
        //짝수의 경우에는 이전 if문에서 검증했으므로 홀수의 경우만 조사
        for (long i = 3; i*i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
