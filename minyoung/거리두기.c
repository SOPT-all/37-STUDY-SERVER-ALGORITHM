#include <stdio.h>
#include <string.h>
char place[5][5][6];
char room[5][6];
int visited[5][5];
int dy[4] = {1, -1, 0, 0};
int dx[4] = {0, 0, 1, -1};
void do_run();
void do_print(int *ans);
void load_room(int idx);
int check_room();
int dfs(int y, int x, int depth, int sy, int sx);
boolean is_valid(int y, int x);
int answer[5];

int main() {
    // 입력
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            scanf("%s", place[i][j]);
        }
    }

    do_run();

    do_print(answer);
}

void do_run() {
    for (int i = 0; i < 5; i++) {
        load_room(i);          // 방 로드
        answer[i] = check_room();  // 거리두기 체크
    }
}

void do_print() {
    for (int i = 0; i < 5; i++) {
        printf("%d ", ans[i]);
    }
    printf("\n");
}

// 방을 room에 복사
void load_room(int idx) {
    for (int i = 0; i < 5; i++) {
        strcpy(room[i], place[idx][i]);
    }
}

// 하나의 방에 대해 거리두기 검사
int check_room() {
    for (int y = 0; y < 5; y++) {
        for (int x = 0; x < 5; x++) {

            // 사람있을 때
            if (room[y][x] == 'P') {
                memset(visited, 0, sizeof(visited));
                visited[y][x] = 1;

                // DFS에서 위반 시 0 리턴
                if (!dfs(y, x, 0, y, x)) {
                    return 0;
                }
            }
        }
    }

    return 1;
}

int dfs(int y, int x, int depth, int sy, int sx) {
    if (depth > 2) 
        return 1;

    // 파티션 있을 때
    if (room[y][x] == 'X')
        return 1;

    // 시작점이 아닌데 P-> 거리두기 위반
    if (!(y == sy && x == sx) && room[y][x] == 'P') {
        return 0;
    }

    // 4방향 탐색
    for (int i = 0; i < 4; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];

        if (is_valid(ny, nx) && visited[ny][nx] == 0 && depth < 2) {
            visited[ny][nx] = 1;

            if (dfs(ny, nx, depth + 1, sy, sx) == 1) {
                return 0;
            }

            visited[ny][nx] = 0;
        }
    }

    return 1;
}

// 범위 체크 
boolean is_valid(int y, int x) {
    return (y >= 0 && y < 5 && x >= 0 && x < 5);
}
