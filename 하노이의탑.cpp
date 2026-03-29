#include <vector>

using namespace std;

void move(int n, int go, int to, vector<vector<int>>& answer) {
    if (n == 1) { 
        answer.push_back({go, to});
        return ;
    }
    move(n-1, go, 6-go-to, answer);
    move(1, go, to, answer);
    move(n-1, 6-go-to, to, answer);
}

vector<vector<int>> solution(int n) {
    vector<vector<int>> answer;
    move(n, 1, 3, answer);
    return answer;
}
