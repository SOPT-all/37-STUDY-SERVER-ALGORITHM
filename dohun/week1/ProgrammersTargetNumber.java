class ProgrammersTargetNumber {
    public int solution(int[] numbers, int target) {
        // DFS를 이용한 모든 경우의 수 탐색
        return dfs(numbers, target, 0, 0);
    }

    private int dfs(int[] numbers, int target, int index, int currentSum) {
        // 현재 인덱스가 배열의 길이와 같다면, 현재 합이 타겟과 같은지 확인 후 같다면 1 반환 아니면 0 반환
        if (index == numbers.length) {
            return currentSum == target ? 1 : 0;
        }

        // 현재 인덱스의 숫자를 더하는 경우와 빼는 경우로 나누어 재귀 호출
        int add = dfs(numbers, target, index + 1, currentSum + numbers[index]);
        int subtract = dfs(numbers, target, index + 1, currentSum - numbers[index]);

        // 두 경우의 수를 합산하여 반환, 결과적으로 총 경우의 수를 반환하게 됨
        return add + subtract;
    }
}
