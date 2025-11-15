#include <vector>

using namespace std;

int solution(vector<int> players, int m, int k) {
    int answer = 0;
    vector<int> servers(24, 0);

    for(int i = 0; i<24; i++) {
        int need = players[i] /  m;
        int now = servers[i];

        // 지금 필요한 서버 수가 이용 가능한 서버 수보다 많으면 증설
        if(need > now) {
            for(int j = 0; j<k; j++) {
                if(i+j >= 24) break;
                servers[i+j] += (need - now);
            }
            answer += (need - now);

        }
    }


    return answer;
}
