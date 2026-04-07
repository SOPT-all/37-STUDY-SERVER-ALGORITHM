#include <string>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;

// 정렬 기준: 등장 횟수(int)가 큰 순서대로 정렬
bool cmp(pair<string, int> a, pair<string, int> b){
    return a.second > b.second;
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;
    map<string, int> m; // 메뉴조합 문자열 → 등장 횟수 저장

    for(string i : orders){
        sort(i.begin(), i.end()); // 주문을 정렬 

        for(int j : course){
            if(i.length() < j) continue;  // 주문 길이가 j보다 짧으면 조합 불가능 

            // false = 선택할 문자, true = 선택하지 않을 문자
            vector<bool> tmp(i.length(), true);      
            for(int k = 0 ; k < j ; k++) tmp[k] = false; 
            
            // next_permutation을 사용해 모든 조합 생성
            do{ 
                string str;

                // false 위치의 문자를 선택하여 조합 문자열 생성
                for(int k = 0 ; k < tmp.size() ; k++){
                    if(!tmp[k]) str += i[k];
                }

                // 조합 등장 횟수 증가
                m[str]++;
            }
            while(next_permutation(tmp.begin(), tmp.end())); // 다음 조합 생성
        }
    }

    // course 길이별 최대 등장 횟수를 기록할 배열
    int arr[course.back() + 1];
    fill(arr, arr + course.back() + 1, 0);

    // 등장 횟수 기준 내림차순 정렬
    vector<pair<string, int>> v(m.begin(), m.end());
    sort(v.begin(), v.end(), cmp);

    // 가장 많이 등장한 조합만 answer에 추가
    for(auto i : v){
        // 등장횟수 2 이상일 때만 포함
        if(i.second >= arr[i.first.length()] && i.second > 1) {
            arr[i.first.length()] = i.second; // 해당 길이의 최대 등장 횟수 업데이트
            answer.push_back(i.first);       // 결과에 추가
        }
    }

    // 최종 결과를 사전순 정렬 후 반환
    sort(answer.begin(), answer.end());

    return answer;
}
