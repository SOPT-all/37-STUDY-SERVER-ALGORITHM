#include <string>
#include <vector>
#include <queue>
#include <iostream>
using namespace std;

int solution(vector<int> numbers, int target) {
    int answer = 0;
    queue<pair<int,int>> q;
    q.push({0,0});
    while(!q.empty())
    {
        int a = q.front().first; //숫자의 합
        int cnt = q.front().second; //숫자의 개수
        q.pop();
        if(target==a && cnt==numbers.size()) answer++; //합이 target과 일치하고 numbers에 주어진 숫자만큼 사용했다면 정답++;
        if(cnt>numbers.size()-1) continue; //numbers의 사이즈만큼 이미 사용했다면 push 하지 않고 다시.
        else{
            q.push({a+numbers[cnt],cnt+1});
            q.push({a-numbers[cnt],cnt+1});
        }
    }
    
    return answer;
}