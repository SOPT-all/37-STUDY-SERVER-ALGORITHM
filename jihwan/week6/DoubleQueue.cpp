#include <string>
#include <vector>
#include <map>

using namespace std;

map<int,int> m;

vector<int> solution(vector<string> operations) {
    vector<int> answer(2);
    for(auto&s: operations) {
        char cmd = s[0];
        string tmpValue = "";
        for(int i=2; i<s.length(); i++) {
            tmpValue += s[i];
        }
        int value = stoi(tmpValue);
        
        if(cmd=='I') {
            m[value]=1;
        }
        else if(cmd=='D' && !m.empty()) {
            if(value == 1) m.erase(prev(m.end()));
            else m.erase(m.begin());
        }
    }
    
    if(m.size()>0) {
        answer[1] = m.begin()->first;
        answer[0] = prev(m.end())->first;
    }
    
    return answer;
}
