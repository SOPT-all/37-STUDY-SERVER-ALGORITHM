#include <string>
#include <vector>
#include <queue>
using namespace std;

int solution(vector<int> players, int m, int k) {
    int answer = 0;    
    int server = 0;    
    queue<pair<int, int>> q;  
    
    for(int i = 0; i < players.size(); i++) {
        if(!q.empty() && q.front().first == i) { // 시간이 다되면
            server -= q.front().second;  // 서버 수 감소
            q.pop();  // 큐에서 제거
        }
        
        if(m <= players[i]) {
            int required = players[i] / m;  // 필요한 서버 수 계산
            
            if (required > server) { // 기존서버 보다 많으면
                int newServer = required - server;  // 추가로 필요한 서버 수
                server += newServer;  // 현재 서버 수 증가
                answer += newServer;  // 총 증설 횟수에 추가
                q.push({i + k, newServer});
            }
        }
    }
    
    return answer;  // 총 증설 횟수 반환
}
