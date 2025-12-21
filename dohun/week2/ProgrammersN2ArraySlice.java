class Solution {
    public int[] solution(int n, long left, long right) {
        // n x n 크기의 2차원 배열에서 left부터 right까지의 원소를 1차원 배열로 반환
        int length = (int) (right - left + 1);

        // 결과를 저장할 배열 초기화
        int[] result = new int[length];

        // left부터 right까지의 인덱스에 해당하는 값을 계산하여 결과 배열에 저장
        for (long i = left; i <= right; i++) {
            long row = i / n;  // 행 번호
            long col = i % n;  // 열 번호
            // 해당 위치의 값은 행 번호와 열 번호 중 큰 값에 1을 더한 값
            result[(int)(i - left)] = (int)(Math.max(row, col) + 1);
        }

        return result;
    }
}
