#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <unordered_map>
using namespace std;

int solution(vector<vector<string>> book_time) {
    int answer = 0;
    vector<pair<int,int>> v;
    unordered_map<int,int> m;
    unordered_map<int,int> mm;
    for(auto&a:book_time)
    {
        int s = ((a[0][0]-'0')*10+(a[0][1]-'0'))*60 + (a[0][3]-'0')*10 + (a[0][4]-'0');
        int e = ((a[1][0]-'0')*10+(a[1][1]-'0'))*60 + (a[1][3]-'0')*10 + (a[1][4]-'0');
        v.push_back({s,e});
    }
    sort(v.begin(),v.end());
    for(int i=0; i<v.size(); i++)
    {
        if(m[i]==0)
        {
            answer++;
            int s = v[i].first;
            int e = v[i].second;
            m[i]=1;
            for(int j=i+1; j<v.size(); j++)
            {
               if(m[j]==0 && v[j].first >= e+10)
               {
                   m[j] = 1;
                   e = v[j].second;
               }
            }
        }
    }
    return answer;
}