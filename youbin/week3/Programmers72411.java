// <메뉴 리뉴얼>
import java.util.*;

class Solution {
    
    static HashMap<String,Integer> map; 
        
    public ArrayList<String> solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        
        // 각 orders 원소들을 오름차순으로 배열
        for(int i = 0;i < orders.length; i++) {
            char[] charArr = orders[i].toCharArray();
            Arrays.sort(charArr);
            orders[i] = String.valueOf(charArr); 
        }
             
        for(int i = 0;i < course.length; i++) { // 지금 만들고 싶은 코스 메뉴 개수
            map = new HashMap<>();
            int max = Integer.MIN_VALUE;   

            for(int j = 0; j < orders.length; j++) {  
                StringBuilder sb = new StringBuilder(); 
                
                // 가능한 모든 조합을 탐색
                if(course[i] <= orders[j].length()) { 
                    dfs(orders[j], sb, 0, 0, course[i]);
                }
            }
            
            // 탐색 후, 조건에 맞는 것만 선별
            // [조건 1] 가장 많이 주문한 단품 메뉴
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    max = Math.max(max, entry.getValue()); 
            }
            
            // [조건 2] 최소 2가지인지 확인
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if(max >= 2 && entry.getValue() == max) {
                        answer.add(entry.getKey());
                    }
            }
        }

        // 마지막으로 정렬하기
        Collections.sort(answer);
        
        return answer;
    }

    public static void dfs(String str, StringBuilder sb, int index, int count, int courseNum) {
        if(count == courseNum) { // 현재 찾는 코스 메뉴라면 map에 저장
           map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
          
            return;
        }
        
        for(int i = index ; i< str.length(); i++) {
            sb.append(str.charAt(i));
            dfs(str, sb, i + 1, count + 1, courseNum);
            sb.delete(count, count + 1);
        }
    }
}
