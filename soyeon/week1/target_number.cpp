#include <vector>

using namespace std;

int sum = 0, answer = 0; // sum: 현재까지의 합, answer: target과 sum이 일치하는 경우의 수

void choice(vector<int> numbers, int cur_num, int target) {
    // 종료 조건: numbers.size()만큼 수를 더하거나 뺌
    if(cur_num == numbers.size()) {
        if(sum == target) answer++; // 현재까지의 합이 target과 일치하면 경우의 수 1 증가
        return;
    }

    // 해당 자릿수 더하기
    sum += numbers[cur_num];
    choice(numbers, cur_num+1, target);
    sum -= numbers[cur_num];

    // 해당 자릿수 빼기
    sum -= numbers[cur_num];
    choice(numbers, cur_num+1, target);
    sum += numbers[cur_num];
}

int solution(vector<int> numbers, int target) {
    choice(numbers, 0, target);
    return answer;
}
