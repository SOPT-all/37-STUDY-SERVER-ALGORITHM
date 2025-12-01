# 최소한의 객실만 사용하여 예약 손님 받기
# 퇴실시간 기준으로 10분 후부터 다시 손님 받을 수 있음
# 필요한 최소 객실의 수
# 최소힙 사용
# 시간 복잡도: O(n logN)

from heapq import heappop, heappush

def to_minutes(t):
        h, m = map(int, t.split(':'))
        return h * 60 + m
    
# 접근 1: 힙
def solution(book_time):
    # 예약 시간을 분 단위로 변환 + 청소시간
    intervals = []
    for start, end in book_time:
        s = to_minutes(start)
        e = to_minutes(end) + 10
        intervals.append((s, e))
    intervals.sort()

    heap = []  # 각 방의 다음 사용 가능 시각(퇴실 + 청소)
    for start, end in intervals:
        # 현재 예약의 시작 시각 이전에 비는 방은 하나만 재사용
        if heap and heap[0] <= start:
            heappop(heap)
        heappush(heap, end)

    return len(heap)

# 접근 2: 누적합 -> O(n)
def solution(book_time):
    MAX_T = 24 * 60 + 10  # 하루 24시간 + 청소 10분
    timeline = [0] * (MAX_T + 2)

    for start, end in book_time:
        s = to_minutes(start)
        e = to_minutes(end) + 10
        timeline[s] += 1
        if e <= MAX_T:
            timeline[e] -= 1

    cur = 0
    answer = 0
    for t in range(MAX_T + 1):
        cur += timeline[t]
        if cur > answer:
            answer = cur
    return answer

# 접근 3: 투 포인터
def solution(book_time):
    starts, ends = [], []
    for start, end in book_time:
        s = to_minutes(start)
        e = to_minutes(end) + 10
        starts.append(s)
        ends.append(e)

    starts.sort()
    ends.sort()

    n = len(starts)
    i = j = 0
    rooms = 0
    answer = 0

    while i < n:
        # 끝난 예약(방 반납)부터 처리
        while j < n and ends[j] <= starts[i]:
            rooms -= 1
            j += 1
        # 현재 예약 배정
        rooms += 1
        if rooms > answer:
            answer = rooms
        i += 1

    return answer

if __name__ == "__main__":
    print(solution([["15:00", "17:00"], ["16:40", "18:20"], ["14:20", "15:20"], ["14:10", "19:20"], ["18:20", "21:20"]]))
    print(solution([["09:10", "10:10"], ["10:20", "12:20"]]))