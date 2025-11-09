class ProgrammersLongJump {
    public long solution(int n) {
        // n이 2이하 이면 n을 그대로 반환
        if (n <= 2) {
            return n;
        }

        // dp 배열 초기화
        long[] dp = new long[n + 1];

        // 초기값 설정
        dp[1] = 1;
        dp[2] = 2;

        // 점화식에 따라 dp 배열 채우기
        for (int i = 3; i <= n; i++) {
            // 1칸 점프와 2칸 점프의 경우의 수 합산 및 1234567로 나눈 나머지 저장
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }

        // n번째 경우의 수 반환
        return dp[n];
    }
}
