class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, 0, target);
        return answer;
    }
    
    private void dfs(int[] numbers, int depth, int sum, int target) {

        if (depth == numbers.length) { //끝까지 탐색을 완료하면
            if (sum == target) { //탐색 완료 결과 sum 값이 target과 일치하면
                answer++; 
            }
            return;
        }

        dfs(numbers, depth + 1, sum + numbers[depth], target); //숫자를 더해지는 경우
        dfs(numbers, depth + 1, sum - numbers[depth], target); //숫자가 빼지는 경우
    }
}
