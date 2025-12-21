#include <vector>
using namespace std;

void hanoi(int n, int from, int via, int to, vector<vector<int>>& answer) {
    if (n == 0) return;

    hanoi(n - 1, from, to, via, answer);
    answer.push_back({from, to});
    hanoi(n - 1, via, from, to, answer);
}

vector<vector<int>> solution(int n) {
    vector<vector<int>> answer;
    hanoi(n, 1, 2, 3, answer);
    return answer;
}
