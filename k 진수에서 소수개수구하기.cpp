#include <string>
#include <vector>
#include <stack>
#include <iostream>
#include <queue>

using namespace std;

stack<int> s;
vector <int> arr;
queue<int> q;

vector<int> trans(int n , int k){
    while( n > 0){
        s.push(n % k);
        n /= k;
    }
    
    while (!s.empty()){
        arr.push_back(s.top());
        s.pop();
    }
    
    return arr;
}

bool isPrime(long long num){
    if(num == 1 || num == 0){
        return false;
    }
    if(num == 2)
        return true;
    if(num % 2 == 0)
        return false;
    
    for (long long i =3 ; i * i <= num; i+=2){
        if(num % i == 0){
            return false;
        }
    }
    return true;
}

int solution(int n, int k) {
    
    int answer = 0;
    
    vector<int> result = trans(n ,k);
    
    for(int i =0 ; i <= result.size(); i++){
        if(i == result.size() || result[i] == 0){
            long long num = 0;
            while(!q.empty()){
                num = num * 10 + q.front();
                q.pop();
            }
            if(isPrime(num)){
                answer++;
            }
        }
        else{
            q.push(result[i]);
        }
    }
    return answer;    
}
