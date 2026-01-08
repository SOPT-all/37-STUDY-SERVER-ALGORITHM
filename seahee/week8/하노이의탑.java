import java.util.*;

class Solution {
    public int[][] solution(int n) {
        List<int[]> moves = new ArrayList<>();
        hanoi(n, 1, 3, 2, moves); // 두번째 기둥을 이용해서 첫번째 -> 세번째로 큰 원판 옮기기
        return moves.toArray(new int[moves.size()][]);
    }

    private void hanoi(int n, int from, int to, int via, List<int[]> moves) {
        if (n == 1) { // 옮겨야 할 원판이 하나 남으면
            moves.add(new int[]{from, to});
            return;
        }

        // 1) 제일 큰 원판 제외 옮기기
        hanoi(n - 1, from, via, to, moves);

        // 2) 가장 큰 원판을 옮기기
        moves.add(new int[]{from, to});

        // 3) 1)에서 옮겨놓은 원반으로 다시 반복
        hanoi(n - 1, via, to, from, moves);
    }
}
