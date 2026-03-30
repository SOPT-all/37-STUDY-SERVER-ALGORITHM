#include <string>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<vector<string>> s_book_time) {
    int answer(0), hour(0), min(0);

    // book_time[i][0] = 시작시간(분)
    // book_time[i][1] = 종료시간(분) + 청소시간(10분)
    vector<vector<int>> book_time(s_book_time.size(), vector<int>(2, 0));

    // 문자열 시각을 분 단위 정수로 변환
    for (int idx = 0; idx < s_book_time.size(); ++idx) {

        // 시작 시간 변환
        hour = stoi(s_book_time[idx][0].substr(0, 2));
        min = stoi(s_book_time[idx][0].substr(3, 2));
        book_time[idx][0] = hour * 60 + min;

        // 종료 시간 변환
        hour = stoi(s_book_time[idx][1].substr(0, 2));
        min = stoi(s_book_time[idx][1].substr(3, 2));
        book_time[idx][1] = hour * 60 + min + 10;  // 퇴실 후 청소
    }

    //시작 시간이 빠른 순서대로 정렬
    sort(book_time.begin(), book_time.end());

    priority_queue<int, vector<int>, greater<int>> room;

    // 예약들을 순회하며 방 배정
    for (int idx = 0; idx < book_time.size(); ++idx) {

        int start = book_time[idx][0];  // 현재 예약 시작 시간
        int end = book_time[idx][1];    // 현재 예약 종료+청소 시간

        while (!room.empty() && room.top() <= start) {
            room.pop();
        }

        room.push(end);

        int room_size = room.size();
        answer = max(answer, room_size);
    }

    return answer;
}
