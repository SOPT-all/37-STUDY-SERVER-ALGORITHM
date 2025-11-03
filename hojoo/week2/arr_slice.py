# n, left, right
# n*n 크기의 2차원 배열에 1부터 n까지 각 행/열에 순차적으로 숫자 채우기
# 1 <= n <= 10^7, 1 <= left <= right < n^2, right - left <= 10^5
# n의 크기가 너무 크기 때문에 실제로 배열을 채워서 시뮬레이션은 불가능
# 1차원 배열 -> 2차원 배열 인덱스 매핑
# 시간 복잡도: O(right - left) = O(10^5)

def solution(n, left, right):
    ans = []
    for i in range(left, right + 1):
        row = i // n
        col = i % n
        ans.append(max(row, col) + 1)
        
    return ans

if __name__ == "__main__":
    print(solution(3, 2, 5))
    print(solution(4, 7, 14))