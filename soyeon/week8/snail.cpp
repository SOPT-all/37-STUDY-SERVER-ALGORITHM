#include <vector>
using namespace std;

vector<int> solution(int n) {
    vector<vector<int>> a(n, vector<int>(n, 0));
    vector<int> answer;

    int x = -1, y = 0;
    int num = 1;

    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            if (i % 3 == 0) {          // 아래
                x++;
            } else if (i % 3 == 1) {   // 오른쪽
                y++;
            } else {                   // 왼쪽 위
                x--;
                y--;
            }
            a[x][y] = num++;
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            answer.push_back(a[i][j]);
        }
    }

    return answer;
}
