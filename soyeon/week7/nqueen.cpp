#include <vector>
using namespace std;

int n;
int answer = 0;
vector<bool> col, diag1, diag2;

void dfs(int row) {
    if (row == n) {
        answer++;
        return;
    }

    for (int c = 0; c < n; c++) {
        if (col[c] || diag1[row + c] || diag2[row - c + n - 1])
            continue;

        col[c] = diag1[row + c] = diag2[row - c + n - 1] = true;
        dfs(row + 1);
        col[c] = diag1[row + c] = diag2[row - c + n - 1] = false;
    }
}

int solution(int N) {
    n = N;
    col.assign(n, false);
    diag1.assign(2 * n - 1, false);
    diag2.assign(2 * n - 1, false);

    dfs(0);
    return answer;
}
