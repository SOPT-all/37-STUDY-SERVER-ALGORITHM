# 같은 시간대 게임 이용하는 m명 늘어날 때 마다 1대 추가 필요
# 시간대 이용자가 m명 미만이라면 필요X
# 한 번 증설한 서버는 k시간동안 유지 후 반남
# 시간 복잡도: O(n + k)

def solution(players, m, k):
    # 누적합으로 현재 서버 수 관리
    # t시점에 x대 증설하면 diff[t+k] -= x
    n = len(players)
    
    diff = [0] * (n + k + 1)
    active = 0
    ans = 0
    for t, p in enumerate(players):
        # 만료 처리(이전까지의 증설 중 이번 시점에 만료되는 서버 반영)
        active += diff[t]

        need = p // m
        if need > active:
            add = need - active
            
            ans += add
            active += add
            diff[t + k] -= add

    return ans

if __name__ == "__main__":
    print(solution([0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5], 3, 5))