#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(int n) {

    int now = 1; // 채워 넣을 현재 숫자

    // 삼각형 모양의 2차원 벡터 생성
    vector<vector<int>> snale;
    for(int i = 1; i <= n; i++)
    {
        vector<int> line;
        for(int j = 1; j <= i; j++)
        {
            line.push_back(-1); // 아직 안 채워진 칸
        }
        snale.push_back(line);
    }

    // 달팽이 채우기
    for(int i = 0; i < n; i++)
    {
        // i % 3 == 0 → 아래 방향 ↓
        if(i % 3 == 0)
        {
            for(int j = 0; j < n - i; j++)
            {
                snale[
                    j + 2 * (i / 3)   // 행(row): 이전 달팽이 껍질에서 채운 위쪽 2줄을 건너뛰고 아래로 이동
                ][
                    (i / 3)           // 열(col): 달팽이 껍질 깊이만큼 오른쪽으로 이동
                ] = now;
                now++;
            }
        }

        // i % 3 == 1 → 오른쪽 방향 →
        else if(i % 3 == 1)
        {
            for(int j = 0; j < n - i; j++)
            {
                snale[
                    (n - 1) - (i / 3) // 행(row): 현재 달팽이 껍질의 가장 아래 행
                ][
                    j + 1 + (i / 3)   // 열(col): 이미 채운 왼쪽 영역을 피해 오른쪽으로 이동
                ] = now;
                now++;
            }
        }

        // i % 3 == 2 → 왼쪽 위 대각선 ↖
        else if(i % 3 == 2)
        {
            for(int j = 0; j < n - i; j++)
            {
                snale[
                    (n - 1) - (i / 3) - j - 1   // 행(row): 아래에서 시작해 j만큼 위로 이동
                ][
                    (n - 1) - 2 * (i / 3) - j - 1 // 열(col): 오른쪽에서 시작해 j만큼 왼쪽으로 이동
                ] = now;
                now++;
            }
        }
    }

    // 2차원 삼각형 벡터를 1차원 벡터로 변환
    vector<int> answer;
    for(vector<int> line : snale)
    {
        for(int a : line)
        {
            answer.push_back(a);
        }
    }

    return answer;
}
