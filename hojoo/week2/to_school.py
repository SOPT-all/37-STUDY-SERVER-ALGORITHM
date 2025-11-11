# m*n 격자에서 puddles를 제외하고 [0, 0]에서 [m, n]에 도달할 수 있는 경로 수 % 10^9 + 7
# 접근1: 2차원 dp
# dp[i][j] = [0, 0]에서부터 [i, j]까지 도달할 수 있는 경로 수
# 움직일 수 있는 방법 = 오른쪽 or 아래
# 시간 복잡도 = O(m * n)

MOD = 10**9 + 7

def solution(m, n, puddles):
    dp = [[0] * m for _ in range(n)]
    for x, y in puddles:
        dp[y-1][x-1] = -1
        
    for i in range(m):
        if dp[0][i] == -1: break
        dp[0][i] = 1
    for i in range(n):
        if dp[i][0] == -1: break
        dp[i][0] = 1
    
    for i in range(1, n):
        for j in range(1, m):
            if dp[i][j] == -1: continue
            
            dp[i][j] = max(dp[i-1][j], 0) + max(dp[i][j-1], 0)
    
    return dp[-1][-1] % MOD

# 접근2: 1차원 dp
# dp[i] = 현재 행(y)에서 (x,y)까지의 경로 수
def solution(m, n, puddles):
    blocked = set((x-1, y-1) for x, y in puddles)

    dp = [0] * m
    dp[0] = 1

    for y in range(n):
        for x in range(m):
            if (x, y) in blocked:
                dp[x] = 0
            elif x > 0:
                dp[x] = dp[x] + dp[x-1]
            # x == 0이면 위에서 내려오는 값(dp[0])만 유지

    return dp[-1] % MOD


if __name__ == "__main__":
    print(solution(4, 3, [[2, 2]]))