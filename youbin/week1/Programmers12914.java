// <멀리뛰기>
class Solution {
    public long solution(int n) {
        // 풀이방법: DP
        long [] dp = new long[n + 1]; 
        
        // 1 또는 2일 때는 바로 값 반환. 이 코드가 없을 시 오류 발생
        if(n == 1) return 1;
        if(n == 2) return 2;
        
        // n = 1은 1로 고정, n = 2는 2로 고정 
        dp[1] = 1;
        dp[2] = 2;

        // 3부터는 점화식이 f(n) = f(n-1) + f(n-2) 
        // 즉, 피보나치 수열을 구하는 방식과 같음
        for(int i = 3; i <= n; i ++) {
            // 매번 값을 넣어줄 때마다 1234567로 나눔 -> 오버플로우 방지
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }
        
        long answer = dp[n];
        
        return answer;
    }
}
