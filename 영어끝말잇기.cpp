#include <string>
#include <vector>
#include <map>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    vector<int> answer = {0, 0};
    map<string, int> temp;
    
    char end = words[0][0];
    for(int i = 0; i < words.size(); ++i)
    {
        string str = words[i];
        if(temp.find(str) != temp.end() || end != str[0])
        {
            answer[0] = (i+1) % n == 0 ? n : (i+1) % n;
            answer[1] = (i+1) % n == 0 ? (i+1) / n : ((i+1) / n) + 1;
            return answer; 
        }
        
        temp.insert({str, 1});
        
        end = str[str.size() - 1];
    }

    return answer;
}
