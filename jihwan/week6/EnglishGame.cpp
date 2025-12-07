#include <string>
#include <vector>
#include <map>

using namespace std;

map<string,int> m;
vector<int> solution(int n, vector<string> words) {
    vector<int> answer;
    answer.push_back(0);
    answer.push_back(0);
    string prevStr="";
    for(int i=0; i<words.size(); i++) {
        string word = words[i];
        if ( i==0 || (m[word]!=1 && prevStr[prevStr.size()-1]==word[0])) {
            m[word]=1;
            prevStr = word;
        }
        else {
            int idx = (i+1)%n;
            if(idx==0) idx = n;
            int turn = i/n+1;
            
            answer[0] = idx;
            answer[1] = turn;
            
            break;
        }
    }

    return answer;
}
