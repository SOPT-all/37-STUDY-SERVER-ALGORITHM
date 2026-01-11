#include <stdio.h>

int n;                  // 마을 수
int amount;             // 도로(간선) 수
int a, b, c;
int time_map[52][52];   // 연결 시간 저장 (가중치)
int k;                  // 제한 시간 K
int min_time[52];       // 최단거리 저장
int visit[52];          // 방문 체크

// 함수 선언
void do_run();
void do_print();
void reset_visit();
void reset_min_time();
void dfs(int now, int cost, int goal);

int main() {

    // 입력
    scanf("%d", &n);
    scanf("%d", &amount);

    // time_map 초기화 (큰 값으로)
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (i == j) time_map[i][j] = 0;
            else time_map[i][j] = 100000000; // 매우 큰 값
        }
    }

    for (int i = 0; i < amount; i++) {
        scanf("%d %d %d", &a, &b, &c);
        if (c < time_map[a][b]) { 
            time_map[a][b] = c;
            time_map[b][a] = c;
        }
    }

    scanf("%d", &k);

    // 처리
    do_run();

    // 출력
    do_print();

    return 0;
}

void do_run() {

    reset_min_time();

    // 1번 마을에서 모든 마을까지의 최단 거리 계산
    for (int goal = 1; goal <= n; goal++) {
        reset_visit();
        visit[1] = 1;
        dfs(1, 0, goal);
    }
}

// DFS 완전 탐색
void dfs(int now, int cost, int goal) {

    if (cost > min_time[goal]) return;

    if (now == goal) {
        if (cost < min_time[goal]) min_time[goal] = cost;
        return;
    }

    for (int next = 1; next <= n; next++) {
        if (time_map[now][next] == 100000000) continue; // 연결X

        if (visit[next] == 0) {
            visit[next] = 1;
            dfs(next, cost + time_map[now][next], goal);
            visit[next] = 0; 
        }
    }
}

void do_print() {
    int answer = 0;
    for (int i = 1; i <= n; i++) {
        if (min_time[i] <= k) answer++;
    }
    printf("%d\n", answer);
}

void reset_visit() {
    for (int i = 0; i < 52; i++) visit[i] = 0;
}

void reset_min_time() {
    for (int i = 1; i <= n; i++)
        min_time[i] = 100000000;
}
