import java.util.*;

class ProgrammersHanoi {

    // 이동 경로를 저장할 리스트
    // 각 원소는 [from, to] 형태
    List<int[]> moves = new ArrayList<>();

    public int[][] solution(int n) {
        // n개의 원판을 1번 기둥에서 3번 기둥으로 옮긴다
        hanoi(n, 1, 3, 2);

        // List -> int[][] 변환
        int[][] answer = new int[moves.size()][2];
        for (int i = 0; i < moves.size(); i++) {
            answer[i] = moves.get(i);
        }
        return answer;
    }

    //      하노이 재귀 함수
    //      n    옮길 원판 개수
    //      from 시작 기둥
    //      to   목표 기둥
    //      aux  보조 기둥
    private void hanoi(int n, int from, int to, int aux) {
        // 종료 조건: 옮길 원판이 없으면 끝
        if (n == 0) {
            return;
        }

        // n-1개를 from → aux 로 이동
        hanoi(n - 1, from, aux, to);

        // 가장 큰 원판을 from → to 로 이동
        moves.add(new int[]{from, to});

        // n-1개를 aux → to 로 이동
        hanoi(n - 1, aux, to, from);
    }
}
