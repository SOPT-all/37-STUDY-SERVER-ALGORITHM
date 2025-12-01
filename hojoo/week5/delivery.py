# n개의 마을, 1번 마을의 음식점에서 각 마을로 배달
# k시간 이하로 걸리는 마을의 개수
# 1번 마을에서 다익스트라 돌리기
# 시간 복잡도: O(E logN) -> e 도로 수, n 마을 수

INF = float('inf')

def solution(N, road, K):
    import heapq

    # 인접 리스트 그래프 생성 (양방향 도로)
    graph = [[] for _ in range(N + 1)]
    for a, b, t in road:
        graph[a].append((b, t))
        graph[b].append((a, t))

    dist = [INF] * (N + 1)
    dist[1] = 0

    heap = [(0, 1)]  # (시간, 마을 번호)
    while heap:
        cur_time, node = heapq.heappop(heap)
        if cur_time > dist[node]:
            continue

        for next_node, time_cost in graph[node]:
            next_time = cur_time + time_cost
            
            if next_time < dist[next_node]:
                dist[next_node] = next_time
                heapq.heappush(heap, (next_time, next_node))

    # K시간 이하로 도달 가능한 마을 수
    return sum(1 for d in dist[1:] if d <= K)


if __name__ == "__main__":
    print(solution(5, [[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]], 3))
    print(solution(6, [[1,2,1],[1,3,2],[2,3,2],[3,4,3],[3,5,2],[3,5,3],[5,6,1]], 4))
