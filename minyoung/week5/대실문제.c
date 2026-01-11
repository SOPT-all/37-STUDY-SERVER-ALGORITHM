#include <stdio.h>
#include <string.h>
int n;
char start_time[1002][6];  // 입력받는 버전
char end_time[1002][6];
int start_min[1002];  // 분 변환 버전
int end_min[1002];
int room_end[1002];
int room_count = 0;
void do_run();
void do_print();
int convert_to_min(char *t);
void reset_room();
void sort_by_start();

int main() {

    // 입력
    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        scanf("%s %s", start_time[i], end_time[i]);
    }

    do_run();

    do_print();
}

int assigned;
int s, e;
void do_run() {
    // 시각을 분 단위로 변환
    for (int i = 0; i < n; i++) {
        start_min[i] = convert_to_min(start_time[i]);
        end_min[i] = convert_to_min(end_time[i]) + 10;
    }
    reset_room();
  
    // 시작 시각 기준 정렬
    sort_by_start();

    // 방 배정
    for (int i = 0; i < n; i++) {
        assigned = 0;
        s = start_min[i];
        e = end_min[i];

        // 기존 방 확인
        for (int r = 0; r < room_count; r++) {
            if (room_end[r] <= s) {
                // 대실 가능시 대실처리
                room_end[r] = e;
                assigned = 1;
                break;
            }
        }

        // 방을 새로 만들어야 할 때
        if (!assigned) {
            room_end[room_count] = e;
            room_count++;
        }
    }
}


void do_print() {
    printf("%d\n", room_count);
}

// 시간:분 형식을 분단위로 변환
int convert_to_min(char *t) {
    int h = (t[0]-'0') * 10 + (t[1]-'0');
    int m = (t[3]-'0') * 10 + (t[4]-'0');
    return h * 60 + m;
}


void reset_room() {
    for (int i = 0; i < 1002; i++) {
        room_end[i] = 0;
    }
    room_count = 0;
}

// 시작 시간 기준으로 정렬
void sort_by_start() {
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (start_min[i] > start_min[j]) {
                // 교환 진행
                int tmp1 = start_min[i];
                start_min[i] = start_min[j];
                start_min[j] = tmp1;

                int tmp2 = end_min[i];
                end_min[i] = end_min[j];
                end_min[j] = tmp2;

                // 문자열도 교환
                char ts[6], te[6];
                
                strcpy(ts, start_time[i]);
                strcpy(start_time[i], start_time[j]);
                strcpy(start_time[j], ts);

                strcpy(te, end_time[i]);
                strcpy(end_time[i], end_time[j]);
                strcpy(end_time[j], te);
            }
        }
    }
}
