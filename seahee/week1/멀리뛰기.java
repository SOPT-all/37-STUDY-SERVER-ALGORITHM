class Solution {
    public long solution(int n) {
        if (n == 1) return 1; //디폴트케이스 반환
        
        long[] dp = new long[n + 1]; //리스트 초기화
        dp[1] = 1; 
        dp[2] = 2;

        for (int i = 3; i <= n; i++) { 
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567; 
          /*
          n까지의 도달 방법은 
          1) n-1번째까지 도달 후, 1칸 점프
          2) n-2번째까지 도달 후, 2칸 점프. (1칸 + 1칸을 점프하는 경우는 1번 경우에 이미 포함되어 있음)
          => n-1번째까지 도달 방법 + n-2번째까지 도달 방법
          */
        }

        return dp[n];
    }
}
