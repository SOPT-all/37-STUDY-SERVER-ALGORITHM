import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        // [가장 먼저 탈락하는 사람, 탈락하는 차례]
        int[] answer = {0, 0};

        // 이미 사용된 단어를 저장할 Map (중복 체크용)
        Map<String, Boolean> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            // 0번째 단어는 이전 단어가 없으므로 비교 제외
            if (i > 0) {
                String prevWord = words[i - 1];  // 이전 사람이 말한 단어
                String currWord = words[i];      // 현재 사람이 말한 단어

                char lastChar = prevWord.charAt(prevWord.length() - 1); // 이전 단어의 마지막 문자
                char firstChar = currWord.charAt(0);                    // 현재 단어의 첫 문자

                // 규칙 검사
                // 1. 이전에 등장했던 단어는 사용할 수 없습니다.
                // 2. 앞사람이 말한 단어의 마지막 문자로 시작하는 단어를 말해야 합니다.
                if (map.containsKey(currWord) || lastChar != firstChar) {

                    // 탈락한 사람 번호 
                    answer[0] = (i % n) + 1;

                    // 몇 번째 턴인지 계산 
                    answer[1] = (i / n) + 1;

                    return answer;
                }
            }

            // 단어 사용 기록 저장
            map.put(words[i], true);
        }

        // 아무도 탈락하지 않은 경우
        return answer;
    }
}
