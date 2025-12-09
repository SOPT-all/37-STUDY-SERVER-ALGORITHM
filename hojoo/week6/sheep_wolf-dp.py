# 비트마스크 + dp
# 시간 복잡도: O(N * 2^N)
def solution(info, edges):
    n = len(info)
    parent = [-1] * n
    for p, c in edges:
        parent[c] = p

    total_masks = 1 << n
    sheep_count = [0] * total_masks
    wolf_count = [0] * total_masks

    # mask를 구성하는 가장 낮은 비트부터 채우며 양/늑대 수 미리 계산
    for mask in range(1, total_masks):
        lsb = mask & -mask
        node = (lsb.bit_length() - 1)
        prev_mask = mask ^ lsb
        sheep_count[mask] = sheep_count[prev_mask] + (info[node] == 0)
        wolf_count[mask] = wolf_count[prev_mask] + (info[node] == 1)

    candidate = [False] * total_masks
    start_mask = 1  # 루트(0번 노드) 방문 상태
    if wolf_count[start_mask] < sheep_count[start_mask]:
        candidate[start_mask] = True

    ans = 0
    for mask in range(total_masks):
        if not candidate[mask]:
            continue

        sheep = sheep_count[mask]
        wolf = wolf_count[mask]

        if wolf >= sheep:
            continue

        ans = max(ans, sheep)

        # 아직 방문하지 않은 노드 중 부모가 방문된 노드만 확장
        for node in range(n):
            if mask & (1 << node):
                continue

            parent_node = parent[node]
            if parent_node != -1 and not (mask & (1 << parent_node)):
                continue

            next_mask = mask | (1 << node)
            next_sheep = sheep_count[next_mask]
            next_wolf = wolf_count[next_mask]

            if next_wolf >= next_sheep:
                continue

            candidate[next_mask] = True

    return ans


if __name__ == "__main__":
    print(solution([0,0,1,1,1,0,1,0,1,0,1,1], [[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]))