# 이진트리 루트에서 양을 모음
# 노드를 방문하면 해당 노드에 있는 동물이 따라옴
# 양 <= 늑대 가 되면 양 잡아먹힘
# 최대한 많은 양을 모아서 다시 루트 노드로 복귀
# 모을 수 있는 최대 양의 수

# 접근 1: dfs
# 시간 복잡도: O(n!) 이긴한데 실제론 가지치기 때문에 줄음
def solution(info, edges):
    n = len(info)

    children = [[] for _ in range(n)]
    for parent, child in edges:
        children[parent].append(child)

    max_sheep = 0
    def dfs(sheep, wolf, candidates):
        nonlocal max_sheep
        max_sheep = max(max_sheep, sheep)

        # 현재 시점에서 갈 수 있는 모든 노드 시도
        for node in candidates:
            next_sheep = sheep + (info[node] == 0)
            next_wolf = wolf + (info[node] == 1)

            # 늑대 수가 양 이상이 되면 더 진행 불가
            if next_wolf >= next_sheep:
                continue

            # 다음 탐색에서 사용할 후보 노드 집합 구성
            next_candidates = candidates.copy()
            next_candidates.remove(node)
            next_candidates.extend(children[node])

            dfs(next_sheep, next_wolf, next_candidates)

    # 루트 시작
    initial_sheep = 1 if info[0] == 0 else 0
    initial_wolf = 1 if info[0] == 1 else 0

    dfs(initial_sheep, initial_wolf, children[0][:])

    return max_sheep

# 접근 2: dp
# 시간 복잡도: O(N * 2^N)
def solution(info, edges):
    from functools import lru_cache

    n = len(info)
    parent = [-1] * n
    for p, c in edges:
        parent[c] = p

    @lru_cache(maxsize=None)
    def dfs(mask):
        # 현재 mask에서 양 / 늑대 수 계산
        sheep = 0
        wolf = 0
        for i in range(n):
            if mask & (1 << i):
                if info[i] == 0:
                    sheep += 1
                else:
                    wolf += 1

        if wolf >= sheep:
            return 0

        best = sheep

        # 아직 방문하지 않았고, 부모가 방문된 노드만 확장
        for i in range(n):
            if mask & (1 << i):
                continue
            if parent[i] == -1 or not (mask & (1 << parent[i])):
                continue

            next_sheep = sheep + (info[i] == 0)
            next_wolf = wolf + (info[i] == 1)
            if next_wolf >= next_sheep:
                continue

            best = max(best, dfs(mask | (1 << i)))

        return best

    return dfs(1)


if __name__ == "__main__":
    print(solution([0,0,1,1,1,0,1,0,1,0,1,1], [[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]))