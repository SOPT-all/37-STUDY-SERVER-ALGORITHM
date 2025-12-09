# 주어진 단어를 순회하며 끝말잇기 규칙에 어긋나면 게임 종료
# 시간 복잡도: O(n)

def solution(n, words):
    for i in range(1, len(words)):
        if words[i-1][-1] != words[i][0] or words[i] in words[:i]:
            return [(i % n) + 1, (i // n) + 1]
        
    return [0, 0]

if __name__ == "__main__":
    print(solution(3, ["tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"]))
    print(solution(5, ["hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"]))
    print(solution(2, ["hello", "one", "even", "never", "now", "world", "draw"]))