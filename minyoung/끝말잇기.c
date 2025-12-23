#include <stdio.h>
#include <string.h>

int n;
int words_len;
char words[101][51];
char used[101][51];
int used_cnt = 0;
int out_person = 0;
int out_turn = 0;

void do_run();
void do_print();
void reset_state();
int is_duplicate(char *word);
int is_invalid_link(char *prev, char *cur);

int main() {

    scanf("%d", &n);
    scanf("%d", &words_len);

    for (int i = 0; i < words_len; i++) {
        scanf("%s", words[i]);
    }

    do_run();
    do_print();
}

void do_run() {
    reset_state();

    char prev[51] = "";
    for (int i = 0; i < words_len; i++) {

        int person = (i % n) + 1;
        int turn = (i / n) + 1;

        // 한 글자 단어 체크
        if (strlen(words[i]) < 2) {
            out_person = person;
            out_turn = turn;
            return;
        }

        // 중복인지 확인
        if (is_duplicate(words[i])) {
            out_person = person;
            out_turn = turn;
            return;
        }

        // 끝말잇기 계속 가능한지 확인
        if (i > 0 && is_invalid_link(prev, words[i])) {
            out_person = person;
            out_turn = turn;
            return;
        }

        // 정상일때
        strcpy(used[used_cnt], words[i]);
        used_cnt++;
        strcpy(prev, words[i]);
    }
}

void do_print() {
    printf("%d %d\n", out_person, out_turn);
}

// 초기화
void reset_state() {
    used_cnt = 0;
    out_person = 0;
    out_turn = 0;

    for (int i = 0; i < 101; i++) {
        used[i][0] = '\0';
    }
}

// 중복인지 확인 함수
int is_duplicate(char *word) {
    for (int i = 0; i < used_cnt; i++) {
        if (strcmp(used[i], word) == 0)
            return 1;
    }
    return 0;
}

// 끝말잇기 연결 검사
int is_invalid_link(char *prev, char *cur) {
    int len = strlen(prev);
    char last = prev[len -1];
    char first = cur[0];

    if (last != first)
        return 1;
    return 0;
}
