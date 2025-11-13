# 조이스틱 움직여서 원하는 단어 만들기
# 수직 이동은 최적해와 관계없고 좌우 이동만 관계있음
# -> 원형에서 변경해야 하는 위치들을 모두 방문하는 최소 좌/우 이동 수
# 시간 복잡도: O(n)

def solution(name):
    n = len(name)
    updown = sum(min(ord(c) - 65, 26 - (ord(c) - 65)) for c in name)

    move = n - 1
    for i in range(n):
        # i 다음 위치부터 연속된 A구간을 탐색
        j = i + 1
        
        # 루프 종료 후 j는 연속된 A구간의 다음 인덱스
        while j < n and name[j] == 'A':
            j += 1
        # 오른쪽으로 i까지 갔다가 왼쪽으로 돌아서 j 이후로 가는 케이스들 비교
        # min(j기준 왼쪽으로 도는 경우, 오른쪽으로 쭉 가는 경우)
        move = min(move, 2 * i + (n-j), i + 2 * (n-j))

    return updown + move
  
if __name__ == "__main__":
    print(solution("JEROEN"))
    print(solution("JAN"))