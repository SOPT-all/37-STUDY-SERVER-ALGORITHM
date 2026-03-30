import java.util.*;

class ProgrammersEnglishWordChain {
    public int[] solution(int n, String[] words) {
        Set<String> usedWords = new HashSet<>();

        // 첫 번째 단어 추가
        usedWords.add(words[0]);

        for(int i = 1; i < words.length; i++) {
            // 현재 단어와 이전 단어 가져오기
            String currentWord = words[i];
            String previousWord = words[i - 1];

            // (i % n) + 1 -> 몇 번째 사람인가
            // (i / n) + 1 -> 몇 번째 차례인가

            // 규칙 1 : 이미 말한 단어일때
            if(usedWords.contains(currentWord)) {
                return new int[] { (i % n) + 1, (i / n) + 1 };
            }

            // 규칙 2 : 앞 글자가 이전 단어의 뒷 글자와 다를때
            if(currentWord.charAt(0) != previousWord.charAt(previousWord.length() - 1)) {
                return new int[] { (i % n) + 1, (i / n) + 1 };
            }

            // 사용한 단어에 현재 단어 추가
            usedWords.add(currentWord);
        }

        // 탈락자 없이 끝난 경우
        return new int[] {0, 0};
    }
}
