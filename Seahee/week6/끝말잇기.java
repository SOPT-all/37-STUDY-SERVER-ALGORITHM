import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n, String[] words) {

        List<String> usedWord = new ArrayList<>();
        char lastAlpha = 0;  // 이전 단어의 마지막 글자
        int failedSeq = 0;   // 몇 번째 단어에서 실패했는지

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // 이미 나온 단어인지 검사
            if (usedWord.contains(word)) {
                failedSeq = i + 1;  
                break;
            }

            // 이전 단어의 마지막 글자로 시작하는지 검사
            if (i > 0) { // 첫 번째 글자인 경우 제외
                if (lastAlpha != word.charAt(0)) {
                    failedSeq = i + 1;
                    break;
                }
            }
            
            usedWord.add(word);
            lastAlpha = word.charAt(word.length() - 1);
        }

        if (failedSeq == 0) {
            return new int[]{0, 0};
        }

        // failedSeq번째 단어를 말한 사람과 그 사람의 차례 계산
        int person = failedSeq % n;      
        if (person == 0) person = n;    

        int turn = (failedSeq - 1) / n + 1;  // 몇 번째 차례인지 (라운드)

        return new int[]{person, turn};
    }
}
