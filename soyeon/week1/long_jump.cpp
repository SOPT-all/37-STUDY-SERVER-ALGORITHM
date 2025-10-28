#include <vector>

using namespace std;

vector<int> dp(2001, 0);

long long solution(int n) {
    long long answer = 0;

    /* 
        DP! 
        마지막에 한 칸 갔거나 두 칸 감
        => (한 칸 갔을 때 경우의 수) + (두 칸 갔을 때 경우의 수) = dp[n-1] + dp[n-2]
        (a + b) % c = ((a % c) + (b % c)) % c
    */

    dp[1] = 1;
    dp[2] = 2;
    for(int i = 3; i<= n; i++) {
        dp[i] = ((dp[i-1] % 1234567) + (dp[i-2] % 1234567)) % 1234567;
    }

    return dp[n];
}
