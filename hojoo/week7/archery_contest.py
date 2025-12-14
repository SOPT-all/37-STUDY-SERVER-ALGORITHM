# 어피치 화살 n발 후 라이언 화살 n발
# 더 많은 화살을 k점에 맞추면 k점을 얻음
# 같은 화살을 맞추면 어피치가 얻음
# 가장 큰 점수 차이로 이기기 위해 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지
# 시간 복잡도: O(2^n)

def solution(n, info):
    # best는 최댓값, ans는 최종 배치를 저장
    best = 0
    ans = [-1]
    lion = [0] * 11

    # 동점일때 낮은 점수부터 비교
    def check(candidate):
        for i in range(10, -1, -1):
            if candidate[i] > ans[i]:
                return True
            if candidate[i] < ans[i]:
                return False

        return False

    # idx번째 과녁, 남은 화살 arrows_left
    def dfs(idx, arrows_left, lion_score, apeach_score):
        nonlocal best, ans

        if idx == 11:
            if arrows_left > 0:
                lion[10] += arrows_left
            diff = lion_score - apeach_score

            if diff > 0:
                if diff > best or (diff == best and (ans == [-1] or check(lion))):
                    best = diff
                    ans = lion[:]

            if arrows_left > 0:
                lion[10] -= arrows_left
            
            return

        score = 10 - idx

        # 현재 점수 가져가는 경우
        need = info[idx] + 1
        if arrows_left >= need:
            lion[idx] = need
            dfs(idx + 1, arrows_left - need, lion_score + score, apeach_score)
            lion[idx] = 0

        # 현재 점수를 가져가지 않는 경우
        lion[idx] = 0
        extra = score if info[idx] > 0 else 0
        dfs(idx + 1, arrows_left, lion_score, apeach_score + extra)

    dfs(0, n, 0, 0)

    return ans