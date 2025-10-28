# 접근1: bfs 탐색
# 모든 노드에 대해서 bfs를 돌려서 visited 체크
# 한 번의 bfs = 해당 노드가 포함된 하나의 네트워크 탐색
# 시간 복잡도: 그래프가 인접 행렬로 주어지므로 O(n^2)

from collections import deque
    
def solution(n, computers):
    # visited와 ans를 전역에서 관리
    visited = [False] * n
    ans = 0
    
    def bfs(graph, visited, start):
        nonlocal ans
        queue = deque([start])
        visited[start] = True

        while queue:
            x = queue.popleft()
            
            # nx = 노드 번호, e = 간선의 유무
            for nx, e in enumerate(graph[x]):
                if e and not visited[nx]:
                    queue.append(nx)
                    visited[nx] = True
                    
        # 한 번의 bfs(네트워크 탐색)이 끝나면 ans += 1
        ans += 1

        return visited
    
    for i in range(n):
        if not visited[i]:
            visited = bfs(computers, visited, i)
    
    return ans

# 접근2: union find
# union find를 이용하여 네트워크 구분
# 시간 복잡도: O(n^2)

def solution(n, computers):
    # parent[i] = 노드 i의 부모 노드 (초기값은 본인)
    parent = list(range(n))
    rank = [0] * n

    # 부모 노드 찾기
    def find(x):
        if parent[x] != x:
            parent[x] = find(parent[x])
        return parent[x]

    # 두 노드를 하나의 네트워크로 합치기
    def union(a, b):
        ra, rb = find(a), find(b)
        if ra == rb:
            return
        
        if rank[ra] < rank[rb]:
            parent[ra] = rb
        elif rank[ra] > rank[rb]:
            parent[rb] = ra
        else:
            parent[rb] = ra
            rank[ra] += 1
    
    # 두 노드가 연결되어있다면 union 연산으로 합침
    for i in range(n):
        for j in range(i + 1, n):
            if computers[i][j] == 1:
                union(i, j)

    # 같은 네트워크라면 find(i)의 값이 같으므로 집합으로 중복 제거 후 길이 반환 = 서로 다른 네트워크의 수
    return len({find(i) for i in range(n)})



if __name__ == "__main__":
    print(solution(3, [[1, 1, 0], [1, 1, 0], [0, 0, 1]]))
    print(solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]]))
    # print(solution(1))