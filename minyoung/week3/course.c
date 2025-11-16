#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    char combo[11]; // 최대 10글자
    int count;
} Combo;

int comboCount = 0;

//비교 함수
int cmpStr(const void* a, const void* b) {
    return strcmp((char*)a, (char*)b);
}
Combo combos[20000]; // 조합과 등장횟수 저장

 // DFS 재귀함수
        void dfs(char* order, int n, int idx, int depth) {
            if (depth == targetLen) {
                temp[depth] = '\0';
                // combos 배열에서 이미 있는지 확인
                int found = 0;
                for (int i = 0; i < comboCount; i++) {
                    if (strcmp(combos[i].combo, temp) == 0) {
                        combos[i].count++;
                        found = 1;
                        break;
                    }
                }
                if (!found) {
                    strcpy(combos[comboCount].combo, temp);
                    combos[comboCount].count = 1;
                    comboCount++;
                }
                return;
            }

            for (int i = idx; i < n; i++) {
                temp[depth] = order[i];
                dfs(order, n, i + 1, depth + 1);
            }
        }

void solution(char orders[][11], int orderSize, int course[], int courseSize, char result[][11], int* resultSize) {
    char temp[11];
    *resultSize = 0;

    for (int c = 0; c < courseSize; c++) {
        int targetLen = course[c];
        comboCount = 0;

        // 각 주문 문자열에 대해 정렬 후 DFS 실행
        for (int o = 0; o < orderSize; o++) {
            int n = strlen(orders[o]);
            if (n < targetLen) continue;

            // 문자열 알파벳 순으로 정렬
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (orders[o][i] > orders[o][j]) {
                        char tmp = orders[o][i];
                        orders[o][i] = orders[o][j];
                        orders[o][j] = tmp;
                    }
                }
            }

            dfs(orders[o], n, 0, 0);
        }

        // 최대 등장 횟수 계산
        int maxCount = 0;
        for (int i = 0; i < comboCount; i++) {
            if (combos[i].count >= 2 && combos[i].count > maxCount) {
                maxCount = combos[i].count;
            }
        }

        // 최대 등장 조합들을 결과에 추가
        for (int i = 0; i < comboCount; i++) {
            if (combos[i].count == maxCount && maxCount >= 2) {
                strcpy(result[*resultSize], combos[i].combo);
                (*resultSize)++;
            }
        }
    }

    // 결과 배열 사전순 정렬
    qsort(result, *resultSize, sizeof(result[0]), cmpStr);
}

// 테스트용 main
int main() {
    char orders[6][11] = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
    int course[] = {2,3,4};
    char result[100][11];
    int resultSize;

    solution(orders, 6, course, 3, result, &resultSize);

    for (int i = 0; i < resultSize; i++)
        printf("%s\n", result[i]);

    return 0;
}
