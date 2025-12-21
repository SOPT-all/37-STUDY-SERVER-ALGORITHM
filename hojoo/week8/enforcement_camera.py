# 모든 차량이 고속도로를 이용하면서 단속용 카메라를 최소 한 번 만나도록 카메라 설치
# 최소 몇 대의 카메라가 필요?
# 시간 복잡도: O(n logn)

def solution(routes):
    INF = float("-inf")
    
    routes.sort(key= lambda x: x[1])

    ans = 0
    for start, end in routes:
        if INF < start:
            ans += 1
            INF = end

    return ans

if __name__ == "__main__":
    print(solution([[-20,-15], [-14,-5], [-18,-13], [-5,-3]]))