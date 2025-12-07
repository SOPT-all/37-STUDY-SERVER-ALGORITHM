#include <string>
#include <vector>
#include <iostream>
#include <set>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    vector<int> answer;
    set<string> said_words; // 등장한 단어 관리

    for(int i = 0; i<words.size(); i++) {
        string now = words[i];


        // 현재 글자 자릿수가 1이면 answer에 넣고 종료
        if(now.size() == 1) {
            answer.push_back(i % n + 1); // 탈락한 사람 번호
            answer.push_back(i / n + 1); // 탈락한 차례
            break;
        }

        // 이전 단어의 마지막 글자랑 현재 단어의 첫 글자랑 다르면 answer에 넣고 종료
        if(i >= 1 && words[i-1][words[i-1].size() - 1] != now[0]) {
            answer.push_back(i % n + 1); // 탈락한 사람 번호
            answer.push_back(i / n + 1); // 탈락한 차례
            break;
        }

        // 이미 등장한 단어면 answer에 넣고 종료
        if(said_words.find(now) != said_words.end()) {
            answer.push_back(i % n + 1); // 탈락한 사람 번호
            answer.push_back(i / n + 1); // 탈락한 차례
            break;
        }

        // 처음 말한 단어면 said_words에 추가
        said_words.insert(now);
    }

    // 아무도 탈락하지 않은 경우 0, 0 return
    if(answer.empty()) {
        answer.push_back(0);
        answer.push_back(0);
    }


    return answer;
}