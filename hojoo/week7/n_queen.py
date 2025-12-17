def solution(n):
    # 열 or 대각선 점유 여부
    mask = (1 << n) - 1
    count = 0

    def dfs(row, cols, diag1, diag2):
        nonlocal count

        if row == n:
            count += 1
            return

        # 현재 줄에서 놓을 수 있는 위치 후보
        available = mask & ~(cols | diag1 | diag2)
        while available:
            bit = available & -available
            available -= bit
            dfs(row + 1, cols | bit, (diag1 | bit) << 1 & mask, (diag2 | bit) >> 1)

    dfs(0, 0, 0, 0)
    
    return count