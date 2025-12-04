import java.util.*;

class ProgrammersMenuRenewal {

    private void combination(String order, int courseSize, int index, StringBuilder current, Map<String, Integer> countMap) {
        // 조합의 길이가 코스 사이즈와 같아지면 빈도수 맵에 추가
        if(current.length() == courseSize) {
            countMap.put(current.toString(), countMap.getOrDefault(current.toString(), 0) + 1);
            return;
        }

        // 조합 생성
        for(int i = index; i < order.length(); i++) {
            // 현재 문자 추가
            current.append(order.charAt(i));

            // 재귀 호출로 다음 문자 추가
            combination(order, courseSize, i + 1, current, countMap);

            // 백트래킹: 마지막 문자 제거
            current.deleteCharAt(current.length() - 1);
        }
    }

    public String[] solution(String[] orders, int[] course) {
        // 최종 결과를 담을 리스트
        List<String> answer = new ArrayList<>();

        // 조합을 만들 때 순서가 다르면 다른 조합으로 인식되기 때문에 각 주문을 알파벳 순서로 정렬
        // 예: "CBA" -> "ABC" 로 변환
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = new String(arr);
        }

        // 각 코스 사이즈에 대해 조합 생성 및 빈도수 계산
        for (int courseSize : course) {
            Map<String, Integer> countMap = new HashMap<>();

            // 각 주문에 대해 조합 생성
            for (String order : orders) {
                // 주문의 길이가 코스 사이즈보다 작으면 조합 생성 불가
                if (order.length() < courseSize) {
                    continue;
                }
                // 조합 생성
                combination(order, courseSize, 0, new StringBuilder(), countMap);
            }

            int maxCount = 0;
            // 가장 많이 등장한 조합의 빈도수 찾기
            for (int count : countMap.values()) {
                maxCount = Math.max(maxCount, count);
            }

            // 가장 많이 등장한 조합이 2번 이상 등장한 경우에만 결과에 추가
            if (maxCount < 2) {
                continue;
            }

            // 최대 빈도수와 같은 조합들을 결과에 추가
            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() == maxCount) {
                    answer.add(entry.getKey());
                }
            }
        }

        // 결과를 알파벳 순서로 정렬
        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }
}
