#define MAX 13

using namespace std;

bool visited[MAX][MAX] = { false };
int N, cnt = 0;

bool is_placed(int row, int col) {
    for(int i = 0; i < N; i++) {
        if(i != row && visited[i][col]) return false;
    }
    
    int l_r_diag = row - col;
    int r_l_diag = row + col;

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            if((i - j == l_r_diag || i + j == r_l_diag) && visited[i][j]) return false;
        }
    }
    return true;
}

void DFS(int queen) {
    if(queen == N) {
        cnt++;
        return;
    }

    for(int i = 0; i < N; i++) {
        if(is_placed(queen, i)) {
            visited[queen][i] = true;
            DFS(queen + 1);
            visited[queen][i] = false;
        }
    }

}


int solution(int n) {
    N = n;
    DFS(0);
    return cnt;
}
