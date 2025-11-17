# 작업의 우선순위 -> 소요시간이 짧은 것, 요청 시각이 빠른 것, 번호가 작은 것
# 주어지는 jobs에는 [요청시점, 소요시간]
# heap에는 [소요시간, 요청시점]로 저장
# 모든 요청 작업의 반환 시간 평균 구하기
# 번호가 작은건 굳이 고려하지 않음 -> 소요시간과 요청시각이 같으면 뭘 먼저 처리해도 동일한 시간이 걸림
# 시간 복잡도: O(n logn)

from heapq import heappush, heappop
from collections import deque

def solution(jobs):
    # 요청 시각 기준으로 정렬
    jobs.sort()
    jobs = deque(jobs)

    time = 0        # 각 작업의 시작 시간
    heap = []       # 대기 큐
    total_time = 0  # 전제 작업의 소요 시간
    job_length = len(jobs)
    idx = 0
    while idx < job_length:
        # 현재 시각까지 도착한 작업들을 힙에 넣기
        while jobs and jobs[0][0] <= time:
            request, duration = jobs.popleft()
            heappush(heap, (duration, request))

        # 대기 큐가 있다면
        if heap:
            duration, request = heappop(heap)
            time += duration
            total_time += time - request
            # 실제로 작업이 처리될 때만 증가
            idx += 1
        else:
            # 대기 큐가 비어있으면 다음 작업의 요청 시각으로 이동
            if jobs:
                time = jobs[0][0]

    return total_time // job_length


if __name__ == "__main__":
    print(solution([[0, 3], [1, 9], [2, 6]]))