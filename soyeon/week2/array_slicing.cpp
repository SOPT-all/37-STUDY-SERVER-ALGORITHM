#include <vector>
#include <algorithm>
#define ll long long

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> answer;

    // 문제에서 주어진 알고리즘 그대로 구현하면 시간초과
    // => 주어진 부분만 index로 계산해서 answer에 넣기
    for(ll i = left; i <= right; i++) {
        // 2차원 배열에서의 좌표 구하기
        ll x = i / n;
        ll y = i % n;
        answer.push_back(max(x, y) + 1);
    }
    return answer;
}
