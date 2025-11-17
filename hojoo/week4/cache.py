# 캐시 크기에 따른 실행시간 측정
# 캐시 교체는 LRU 알고리즘 (가장 오래된 참조 제거)
# 캐시 히트 = 1, 미스 = 5
# 배열을 순서대로 처리할 때 실행시간
# 큐를 사용해서 구현
# 시간 복잡도: O(n * m) n: 배열 크기, m: 캐시 사이즈

from collections import deque

def solution(cacheSize, cities):
    if cacheSize == 0:
        return 5 * len(cities)

    ans = 0
    cache = deque([])
    for city in cities:
        # 대소문자 구분 없으니까 정규화
        city = city.lower()

        if city in cache:
            ans += 1
            cache.remove(city)
            cache.append(city)
        else:
            ans += 5
            if len(cache) == cacheSize:
                cache.popleft()

            cache.append(city)

    return ans


# 최적화: OrderedDict 사용
# 시간 복잡도: O(n)
from collections import OrderedDict

def solution(cacheSize, cities):
    if cacheSize == 0:
        return 5 * len(cities)

    ans = 0
    cache = OrderedDict()

    for city in cities:
        city = city.lower()

        if city in cache:
            ans += 1
            cache.move_to_end(city)
        else:
            ans += 5
            if len(cache) == cacheSize:
                cache.popitem(last=False)
            cache[city] = True

    return ans

if __name__ == "__main__":
    print(solution(3, ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"]))
    print(solution(3, ["Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"]))


# 캐시 사이즈가 커진다면
# LFU 라면