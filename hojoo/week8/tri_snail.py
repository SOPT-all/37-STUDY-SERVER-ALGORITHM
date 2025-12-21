# 정수 n
# 밑변과 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 당팽이 채우기
# 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열
# 시간 복잡도: O(n^2)

def solution(n):
    # 삼각형 배열 만들기
    tri = [[0] * (i+1) for i in range(n)]
    x, y = -1, 0
    num = 1

    # 아래, 오른쪽, 위로 시뮬레이션
    for i in range(n):
        for _ in range(n-i):
            if i % 3 == 0:      # 아래
                x += 1
            elif i % 3 == 1:    # 오른쪽
                y += 1
            else:               # 위
                x -= 1
                y -= 1
            tri[x][y] = num
            num += 1

    ans = []
    for i in tri:
        for j in i:
            ans.append(j)

    return ans

if __name__ == "__main__":
    print(solution(4))