# 접근: dp
# 첫 번째 칸부터 순회하면 칸에 도달할 때 마다 해당 칸에 도달할 수 있는 방법의 수가 확정됨
# dp[i] = i칸에 도달할 수 있는 방법의 수
# 시간 복잡도: O(n)

def solution(n):
    dp = [0] * n
    
    # n이 1일땐 1반환
    if n == 1:
        return 1
    dp[0] = 1
    dp[1] = 1
    
    for i in range(n-1):
        dp[i+1] += dp[i]
        try:
            dp[i+2] += dp[i]
        except:
            continue
    
    return dp[-1] % 1234567


if __name__ == "__main__":
    print(solution(4))
    print(solution(3))
    print(solution(1))