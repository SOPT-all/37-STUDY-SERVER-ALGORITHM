#include <string>
#include <vector>
#include <functional>

using namespace std;

int solution(vector<int> numbers, int target) {
    int answer = 0;
    
    function<void(int, int)> dfs = [&](int index, int sum) {
        if (index == numbers.size()) { // 모든 숫자를 다 사용했을 때
            if (sum == target).   // 현재 합계와 목표값이 같으면
                answer++;       // 경우의 수 증가
            return;
        }

        dfs(index + 1, sum + numbers[index]); // 현재 숫자를 더하는 경우
        dfs(index + 1, sum - numbers[index]); // 현재 숫자를 빼는 경우
    };

    dfs(0, 0);
    
    return answer;
}
