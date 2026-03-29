#include <string>
#include <vector>

using namespace std;

long long solution(int n) {
    long long answer = 0;
    
    if(n == 1){
        answer = 1; // 1일 경우 1
    }
    else if(n == 2) {
        answer = 2; // 2일 경우 2
    } else{
        int a1 = 1; // 초기값세팅
        int a2 = 2;
        int an;
        for(int i = 3; i <= n; i++){
            an = (a1 + a2) % 1234567; // n은 (n - 1) + (n - 2)  
            a1 = a2;                  // (n - 1)에서 1칸 뛰는 방법이랑
            a2 = an;                  // (n - 2)에서 2칸 뛰는 방법을 
                                      // 더하면 n칸째의 방법을 얻을 수있음
        }
        answer = an;
    }
    
    return answer;
}