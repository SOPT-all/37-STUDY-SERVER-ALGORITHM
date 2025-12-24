# 1번 기둥에 순서대로 있는 원판을 3번 기둥으로 최소 횟수로 옮기기
# 시간 복잡도: O(2^n)

def solution(n):
    ans = []

    def rec(count, start, goal, mid):
        if count == 1:
            ans.append([start, goal])
            return
        
        rec(count - 1, start, mid, goal)
        ans.append([start, goal])
        rec(count - 1, mid, goal, start)

    rec(n, 1, 3, 2)
    return ans

if __name__ == "__main__":
    print(solution(2))