#include <stdio.h>
#include <string.h>
int cacheSize;
char cities[100002][25];
char cache[32][25];
int cache_age[32]; // LRU 판단용
int n;
int total_time = 0;

void do_run();
void do_print();
void reset_cache();
int to_lower(char *dest, char *src);
int find_city(char *city);
void use_cache(int idx);
void insert_city(char *city);
void age_increase();

int main() {

    scanf("%d", &cacheSize);
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        scanf("%s", cities[i]);
    }

    do_run();

    do_print();
}

int idx;
char city_lower[25];
void do_run() {
    reset_cache();
    for (int i = 0; i < n; i++) {
        to_lower(city_lower, cities[i]); //소문자로 변환
        age_increase(); //캐시들 나이 올리기

        // 캐시에서 검색
        idx = find_city(city_lower);

        if (idx != -1) {
            // HIT일때
            total_time += 1;
            use_cache(idx); //age 새로 업데이트

        } else {
            // MISS일때
            total_time += 5;
            insert_city(city_lower);
        }
    }
}

void do_print() {
    printf("%d\n", total_time);
}

// 캐시 초기화
void reset_cache() {
    for (int i = 0; i < 32; i++) {
        cache[i][0] = '\0';
        cache_age[i] = 100000000;
    }
}

// 문자열 소문자 변환
int to_lower(char *dest, char *src) {
    int i = 0;
    while (src[i]) {
        if (src[i] >= 'A' && src[i] <= 'Z')
            dest[i] = src[i] + 32;
        else
            dest[i] = src[i];
        i++;
    }
    dest[i] = '\0';
    return 0;
}

// 캐시에서 도시 검색
int find_city(char *city) {
    for (int i = 0; i < cacheSize; i++) {
        if (strcmp(cache[i], city) == 0)
            return i;
    }
    return -1;
}

// 캐시 HIT일때 -> 가장 최근으로 갱신
void use_cache(int idx) {
    cache_age[idx] = 0;
}

// 모든 age 증가
void age_increase() {
    for (int i = 0; i < cacheSize; i++) {
        cache_age[i]++;
    }
}

// 캐시 MISS -> 삽입/교체
void insert_city(char *city) {

    // 캐시 크기가 0일 때
    if (cacheSize == 0)
        return;

    // 빈자리 있는지 확인 -> 있으면 넣고 리턴
    for (int i = 0; i < cacheSize; i++) {
        if (cache[i][0] == '\0') {
            strcpy(cache[i], city);
            cache_age[i] = 0;
            return;
        }
    }

    // 빈자리 없을 때 -> LRU 찾아서 교체
    int old_idx = 0;
    for (int i = 1; i < cacheSize; i++) {
        if (cache_age[i] > cache_age[old_idx])
            old_idx = i;
    }
    strcpy(cache[old_idx], city);
    cache_age[old_idx] = 0;
}
