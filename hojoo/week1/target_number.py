# 접근1: dfs로 브루트포스
# 각 숫자마다 -, + 두 가지 경우의 수를 모두 확인하며 내려가기
# 시간 복잡도: 각 원소마다 양수, 음수를 확인하며 진행하므로 O(2^n)
# -> 숫자의 개수는 최대 20개이므로 최악의 경우 2^20 = 1_000_000 이지만 중간에 가지치기로 실제로는 좀 더 작음

def solution(numbers, target):
    ans = 0
    
    n = len(numbers)
    # 가지치기를 위한 누적합 배열
    suffix = [0] * (n+1)
    for i in range(n-1, -1, -1):
        suffix[i] = suffix[i+1] + numbers[i]

    def dfs(idx, total):
        nonlocal ans
        
        # 가지치기 조건: 남은 모든 수를 더해도 target 보다 작을 때 혹은 모든 수를 빼도 target 보다 클 때
        if abs(target - total) > suffix[idx]:
            return
        
        # 탈출조건: 배열의 마지막에 도착
        if idx == n:
            if total == target:     # 지금까지 더한 숫자가 target과 같으면 ans += 1
                ans += 1
            return
        
        dfs(idx+1, total + numbers[idx])
        dfs(idx+1, total - numbers[idx])

    dfs(0, 0)
    return ans

    

# 접근2: dp
# 가능한 합과 그 개수를 단계별로 누적
# dp[i] = i를 만들 수 있는 개수
# 시간 복잡도: O(n * s) -> s = 모든 수를 더했을 때의 값(상한)

# 1. 모든 상태공간을 dp 테이블로 만들어 놓고 기입
def solution(numbers, target):
    S = sum(numbers)
    off = S
    
    dp = [0] * (2*S + 1)
    dp[off] = 1
    for i in numbers:
        nxt = [0] * (2*S + 1)
        for s in range(-S, S + 1):
            cnt = dp[s + off]
            if cnt:
                nxt[s + i + off] += cnt
                nxt[s - i + off] += cnt
        dp = nxt
        
    return dp[target + off] if -S <= target <= S else 0


# 2. hashmap 사용하여 필요한 값만 추가
from collections import Counter

def solution(numbers, target):
    # i: j -> i = 만들 수 있는 값, j = 방법의 수
    dp = Counter({0: 1})
    
    for i in numbers:
        nxt = Counter()
        
        for s, c in dp.items():
            nxt[s+i] += c
            nxt[s-i] += c
        dp = nxt
        
    return dp[target]



if __name__ == "__main__":
    print(solution([1, 1, 1, 1, 1], 3))
    print(solution([4, 1, 2, 1], 4))