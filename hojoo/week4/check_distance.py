# 대기실 5개, 각 대기실은 5 * 5
# 응시자끼리는 맨해튼 거리 3이상
# 응시자 사이에 파티션이 있다면 상관없음
# 접근 1: bfs -> bfs로 상하좌우 격자 돌리면 맨해튼 거리 구할 수 있음
# 파티션 조건 -> 그냥 파티션은 안가면 됨
# 시간 복잡도: 최악 O(5 * 5 * 5 * 25 * 4)

from collections import deque

dx = [0, 1, -1, 0]
dy = [1, 0, 0, -1]

def bfs(matrix, sx, sy):
    queue = deque([(sx, sy)])
    visited = set([(sx, sy)])
    
    cnt = 1
    while queue:
        for _ in range(len(queue)):
            x, y = queue.popleft()
            
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                
                # 5 * 5 조건
                if 0 <= nx < 5 and 0 <= ny < 5:
                    # 파티션이 아니고 방문한 적 없으면 탐색
                    if matrix[nx][ny] != 'X' and (nx, ny) not in visited:
                        # 맨해튼 거리 2 내로 사람이 있으면 false 반환
                        if matrix[nx][ny] == 'P' and cnt <= 2:
                            return False
                        visited.add((nx, ny))
                        queue.append((nx, ny))
        
        cnt += 1
        if cnt > 2:
            return True

def solution(places):
    ans = []
    
    # 모든 대기실의 모든 사람을 시작점으로 bfs 돌리기
    for place in places:
        flag = 1
        for j in range(5):
            for k in range(5):
                if place[j][k] == 'P':
                    # 한 명이라도 안 지켜졌으면 break 후 0으로 세팅
                    if bfs(place, j, k) == 0:
                        flag = 0
                        break
        
            if not flag:
                break
        
        # 모두 지켜졌으면 1
        ans.append(flag)
        
    return ans


# 접근 2: 맨해튼 거리 2까지 직접 확인 
def check_place(place):
    for i in range(5):
        for j in range(5):
            if place[i][j] != 'P':
                continue

            # 거리 1 (상하좌우)
            for di, dj in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                ni, nj = i + di, j + dj
                if 0 <= ni < 5 and 0 <= nj < 5 and place[ni][nj] == 'P':
                    return 0

            # 거리 2 (직선)
            for di, dj in [(2, 0), (-2, 0), (0, 2), (0, -2)]:
                ni, nj = i + di, j + dj
                mi, mj = i + di // 2, j + dj // 2  # 중간 칸
                if 0 <= ni < 5 and 0 <= nj < 5:
                    if place[ni][nj] == 'P' and place[mi][mj] == 'O':
                        return 0

            # 거리 2 (대각선)
            for di, dj in [(1, 1), (1, -1), (-1, 1), (-1, -1)]:
                ni, nj = i + di, j + dj
                if 0 <= ni < 5 and 0 <= nj < 5 and place[ni][nj] == 'P':
                    # 대각선일 때 두 중간 칸 중 하나라도 O면 파티션이 없는 판정
                    if place[i][nj] == 'O' or place[ni][j] == 'O':
                        return 0

    return 1


def solution(places):
    return [check_place(place) for place in places]

if __name__ == "__main__":
    print(solution([["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"], 
                    ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"], 
                    ["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"], 
                    ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"], 
                    ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]]))
