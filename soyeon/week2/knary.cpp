#include <string>
#include <cmath>
#include <vector>

using namespace std;

int solution(int n, int k) {
    int answer = -1;

    // n을 k진수로 바꾸기
    string knum = ""; // k진수로 바꾼 값

    vector<int> tmp;
    while(n > 0) {
        tmp.push_back(n % k);
        n/=k;
    }

    for(int i = tmp.size() - 1; i >=0; i--) {
        knum += ('0' + tmp[i]);
    }

    // 투포인터
    int i = 0, j = 0;
    while(j < knum.size()) {
        // j가 문자열 끝이거나, 0인 경우
        if(j == knum.size() -1 || knum[j] == '0') {
            // 이전 값도 0이면 포인터 업데이트
            if(knum[i] == '0') {
                j++;
                i = j;
                continue;
            }

            // 현재 부분 계산
            long long now = stoll(knum.substr(i, j == knum.size() -1? j - i + 1:j - i));

            // 소수인지 확인
            int count = 0;
            for(long long i = 2; i <= sqrt(now); i++) {
                if(now % i == 0) {
                    count++;
                    break;
                }
            }

            if(now != 1 && count == 0) { // 현재 값이 1이 아니고, 약수가 없을 때 소수
                answer++;
            }

            // 포인터 업데이트
            j++;
            i = j;
            continue;
        }

        j++;
    }

    return ++answer;
}
