#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int cnt=1;
int ser_cnt[25];
int nam;
int tmp_ser; //시간별로 추가 해야하는 서버 갯수
int tmp_mem; //최근 5시간동안 추가될 인원명수
// players_len은 배열 players의 길이입니다.
int solution(int players[], size_t players_len, int m, int k) {
    int answer = 0;
    
    //서버 초기화
    for(int i=0;i<24;i++){
        ser_cnt[i]=0;
    }
    
    for(int i=0;i<24;i++){
        //서버 증설 필요한지 확인
        if(players[i]>ser_cnt[i]){
            //서버 증설 얼마나 필요한지 확인
            tmp_ser =0;
            nam = (players[i]-ser_cnt[i])%m;
            tmp_ser = (players[i]-ser_cnt[i])/m;
            if(nam>0){
                tmp_ser++;
                
            }
            cnt+=tmp_ser; //실제 총 사용할 서버갯수에 업데이트

            
            //5시간동안 가능 인원 추가하기
            for(int j=0;j<k;j++){
                if(i+j>24)
                    break;
                tmp_mem=tmp_ser*m;
                ser_cnt[i+j]+=tmp_mem;
            }
            
        }
    }
    
    answer=cnt;
    
    return answer;
}



