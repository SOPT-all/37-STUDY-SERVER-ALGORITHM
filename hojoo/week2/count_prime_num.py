# 양의 정수 n을 k진수로 바꿨을 때 변환된 수 안에 조건(0으로 split)에 맞는 소수가 몇 개?
# -> 0으로 split 후 소수 판별

# 0 <= n <= 1_000_000, 3 <= k <= 10
# 시간 복잡도: O(n^0.5 * log_k(n))

# O(log_k(n))
def convert_to_k(n, k):
    if n == 0:
        return "0"
    
    ret = []
    while n > 0:
        ret.append(str(n % k))
        n //= k
        
    return "".join(reversed(ret))

# O(n^0.5)
def is_prime(x):
    if x < 2:
        return False
    
    for i in range(2, int(x**0.5)+1):
        if x % i == 0:
            return False
        
    return True

def solution(n, k):
    k_num = convert_to_k(n, k)
    
    # "", "1" 제거 후 int로 변환
    # O(log_k(n))
    num = list(map(int, filter(lambda x: x not in ("", "1"), k_num.split("0"))))
    
    # O(n^0.5 * log_k(n))
    return sum(is_prime(i) for i in num)

if __name__ == "__main__":
    print(solution(437674, 3))
    print(solution(110011, 10))