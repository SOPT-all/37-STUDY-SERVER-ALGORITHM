#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

void comb(int idx, int depth, int limit, string &now, string &order, map<string,int> &count) {
    if (depth == limit) {
        count[now]++;
        return;
    }
    if (idx >= order.size()) return;

    // 1) 선택
    now.push_back(order[idx]);
    comb(idx + 1, depth + 1, limit, now, order, count);
    now.pop_back();

    // 2) 선택 안 함
    comb(idx + 1, depth, limit, now, order, count);
}


vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;

    // 1. 각 주문 정렬
    for (auto &s : orders) sort(s.begin(), s.end());

    for (int len : course) {
        map<string, int> count; // 이번 길이에 대한 조합 카운트

        // 2. 모든 주문에 대해 조합 생성
        for (string &order : orders) {
            if (order.size() < len) continue;
            string now = "";
            comb(0, 0, len, now, order, count);
        }

        // 3. 최대 등장 횟수 찾기
        int mx = 0;
        for (auto &p : count) {
            if (p.second >= 2)
                mx = max(mx, p.second);
        }
        if (mx < 2) continue;

        // 4. 최대값과 같은 조합만 answer에 추가
        for (auto &p : count) {
            if (p.second == mx)
                answer.push_back(p.first);
        }
    }

    // 5. 최종 정렬
    sort(answer.begin(), answer.end());

    return answer;
}
