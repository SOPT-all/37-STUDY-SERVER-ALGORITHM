#include <stdio.h>
#include <string.h>

int info[18];
int edges[20][2];
int n;
int edge_len;
int graph[18][18];
int graph_cnt[18];
int max_sheep = 0;
int visited[18];
void do_run();
void do_print();
void reset_state();
void dfs(int node, int sheep, int wolf);

int main() {

    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        scanf("%d", &info[i]);
    }

    edge_len = n - 1;
    for (int i = 0; i < edge_len; i++) {
        scanf("%d %d", &edges[i][0], &edges[i][1]);
    }


    do_run();
    do_print();
}

void do_run() {
    reset_state();

    for (int i = 0; i < edge_len; i++) {
        int from = edges[i][0];
        int to   = edges[i][1];

        graph[from][graph_cnt[from]++] = to;
    }

    dfs(0, 0, 0);
}

void do_print() {
    printf("%d\n", max_sheep);
}

void reset_state() {
    max_sheep = 0;

    for (int i = 0; i < 18; i++) {
        graph_cnt[i] = 0;
        visited[i] = 0;
    }
}

void dfs(int node, int sheep, int wolf) {
    visited[node] = 1;
    if (info[node] == 0)
        sheep++;
    else
        wolf++;

    // 늑대가 같, 많아지면 중단
    if (wolf >= sheep) {
        visited[node] = 0;
        return;
    }

    // 최대 양 수 업데이트
    if (sheep > max_sheep)
        max_sheep = sheep;

    // 탐색진행
    for (int i = 0; i < n; i++) {
        if (visited[i]) {
            for (int j = 0; j < graph_cnt[i]; j++) {
                int next = graph[i][j];
                if (!visited[next]) {
                    dfs(next, sheep, wolf);
                }
            }
        }
    }
    visited[node] = 0;
}
