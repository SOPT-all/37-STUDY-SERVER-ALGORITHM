# 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴를 코스요리로 구성
# 코스요리는 최소 2가지 이상의 단품메뉴
# course 배열의 담긴 숫자의 메뉴를 가진 코스요리를 만들기
# 주어진 course의 모든 조합을 만든 후 몇 개 나오는지 개수 세기
# 시간 복잡도: 최악 -> O(10C5 * order * course)

from collections import Counter
from itertools import combinations
  
def solution(orders, course):
    # 길이 i 코스요리의 종류와 빈도
    counts = {i: Counter() for i in course}

    # 개수 세기 (길이에 따른 모든 조합)
    for i in orders:
        for j in course:
            if len(i) >= j:
                for comb in combinations(i, j):
                    counts[j][''.join(sorted(comb))] += 1   # WX, XW 처럼 같은 키가 분리되는 경우

    ans = []
    for i in course:
        if not counts[i]: continue
        
        # 길이 i의 코스요리의 종류에서 2명 이상이 시킨 조합들 중 빈도가 최댓값 조합만 ans에 추가
        max_cnt = max(counts[i].values())
        if max_cnt >= 2:
            ans += [k for k, v in counts[i].items() if v == max_cnt]
        
    return sorted(ans)

if __name__ == "__main__":
    print(solution(["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"], [2, 3, 4]))
    print(solution(["XYZ", "XWY", "WXA"], [2, 3, 4]))