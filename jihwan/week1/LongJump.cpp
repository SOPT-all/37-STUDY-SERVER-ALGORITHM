#include <string>
#include <vector>

using namespace std;

long long DP[2001]; //i번째 칸에 올 수 있는 경우의 수 저장

void dp(int n) {
    DP[1]=1; //초기값
    DP[2]=1;
    for(int i=1; i<n; i++) {
        if(i+1<=n) { // 1칸 이동하는 경우 현재칸까지 오는 경우의수를 i+1칸에 더해줌
            DP[i+1] = (DP[i+1]+DP[i])%1234567;
        }
        if(i+2<=n) { //위와 동일
            DP[i+2] = (DP[i+2]+DP[i])%1234567;
        }
    }
}
long long solution(int n) {
    long long answer = 0;
    dp(n);
    answer = DP[n];
    return answer;
}