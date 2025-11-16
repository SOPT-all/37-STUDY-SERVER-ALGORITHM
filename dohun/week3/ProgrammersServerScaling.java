import java.util.*;

class ProgrammersServerScaling {
    public int solution(int[] players, int m, int k) {
        int n = players.length; // 24
        // expire[t] : 시각 t에 운영이 끝나는 서버 개수
        int[] expire = new int[n + k + 1];

        int activeServerCount = 0; // 현재 시각에 운영 중인 서버 수
        int answer = 0; // 총 증설 횟수

        for (int t = 0; t < n; t++) {
            // 1. 이 시각에 끝나는 서버 제거
            activeServerCount -= expire[t];

            // 2. 이 시각에 필요한 서버 수 (floor 나눗셈!)
            int requiredServerAmount = players[t] / m;

            // 3. 부족하면 그만큼 서버 증설
            if (requiredServerAmount > activeServerCount) {
                int addServerCount = requiredServerAmount - activeServerCount; // 새로 증설해야 하는 서버 수
                answer += addServerCount;
                activeServerCount += addServerCount;

                // 지금 증설한 서버들은 t ~ t+k-1 동안 운영되고,
                // t+k 시각부터는 빠지므로 만료 시각을 t+k로 기록
                if (t + k < expire.length) {
                    expire[t + k] += addServerCount;
                }
            }
        }

        return answer;
    }
}
