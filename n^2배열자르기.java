class Solution {
    public int[] solution(int n, long left, long right) {
        //답 담을 1차원 array 생성
        int len = (int) (right - left + 1);
        int[] answer = new int[len];

        /*
        1차원 배열의 한 index는 크기가 n*n인 2차원 배열의 [index/n][index%n]인 인덱스에 대응됨.
        e.g. n이 4, left가 7일 때 array1D[7] = array2D[1][3]
        */
        for (long idx = left; idx <= right; idx++) {
            long r = idx / n; // 행 
            long c = idx % n; // 열 
            answer[(int) (idx - left)] = (int) (Math.max(r, c) + 1); 
        }
        return answer;
    }
}
