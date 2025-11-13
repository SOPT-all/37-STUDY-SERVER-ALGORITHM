#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> answer;
    for(long long i=left; i<=right; i++) {
        //left~right 까지 행, 열을 구하고, 더 큰값을 answer에 넣기
        int r,c;
        r = i/n+1;
        c = i%n+1;
        answer.push_back(max(r,c));
    }
    return answer;
}
