// <k진수에서 소수 개수 구하기> 
class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        // k진수로 바꾸기
        String kNumber = makeKNumber(n, k);

        // 0 또는 연속된 0 기준으로 잘랐을 때 그게 소수인지 판별하면 됨
        String[] splitArray = kNumber.split("0+");

        // 소수인 조건 판별
        for (String str : splitArray) {
            long val = Long.parseLong(str);    // long 타입으로 해야 오류가 안 남
            if (isPrime(val)) {
                answer++;
            }
        }

        return answer;
    }

    public static String makeKNumber(int n, int k) {
        // k 진수 구하는 법
        // 찾아보니까 Integer.toString(n, k) 이라는 내장 함수도 있더라구요
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }
        return sb.reverse().toString();
    }

    private boolean isPrime(long number) {
        if (number < 2) return false; // 0, 1은 소수 아님
        for (long i = 2; i * i <= number; i++) { // 해당 숫자의 √number 까지 확인
            if (number % i == 0) return false; // 하나라도 나눠지면 소수 아님
        }
        return true; // 끝까지 나눠지지 않으면 소수
    }
}
