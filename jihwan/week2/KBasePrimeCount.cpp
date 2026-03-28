#include <string>
#include <vector>
#include <iostream>
#include <unordered_map>
#include <math.h>
using namespace std;

//소수판별 함수
bool isPrime(long long n) {
    if(n==1) return false;
    for(int i=2; i <= sqrt(n); i++) {
        if(n%i==0) return false;
    }
    return true;
}

int solution(int n, int k) {
    int answer = 0;
    
    vector<int> nums;
    vector<string> nums2;
    
    //k진수로 변환
    while(n>0) {
        nums.push_back(n%k);
        n/=k;
    }
    
    //위 결과에서 거꾸로 숫자 찾아서 nums2에 삽입
    string tmpNum="";
    for(int i=nums.size()-1; i>=0; i--) {
        if(nums[i]==0) {
            if(!tmpNum.empty()) nums2.push_back(tmpNum);
            nums2.push_back("0");
            tmpNum="";
        }
        else tmpNum += to_string(nums[i]); 
    }
    if(!tmpNum.empty()) nums2.push_back(tmpNum);
    
    //소수인지 판별
    for(int i=0; i<nums2.size(); i++) {
        if(nums2[i]!="0") {
            if(isPrime(stol(nums2[i]))) answer++;
        }
    }
    return answer;
}
