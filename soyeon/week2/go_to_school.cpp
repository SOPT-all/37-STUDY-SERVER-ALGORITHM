#include <vector>

using namespace std;

int solution(int m, int n, vector<vector<int>> puddles) {
    int divider = 1000000007;

    // dp => dp[m][n] = dp[m-1][n] + dp[m][n-1] 이용
    vector<vector<int>> dp(101, vector<int>(101, 0));

    // puddles dp 배열에 -1로 체크해놓기 => 나중에 계산할 때 puddles가 있으면 건너 뛰고 계산
    for(int i = 0; i<puddles.size(); i++) {
        int x = puddles[i][1];
        int y = puddles[i][0];
        dp[x][y] = -1;
    }

    dp[1][1] = 1;
    for(int i = 1; i <= n; i++) {
        for(int j = 1; j<= m; j++) {
            if(i == 1 && j == 1) continue; // 시작 지점인 경우 건너뜀
            if(dp[i][j] == -1) continue; // 웅덩이인 경우 건너뜀
            int up = dp[i-1][j] == -1 ? 0 : dp[i-1][j];
            int left = dp[i][j-1] == -1 ? 0 : dp[i][j-1];
            dp[i][j] = (up % divider + left % divider) % divider;
        }
    }

    return dp[n][m];
}
