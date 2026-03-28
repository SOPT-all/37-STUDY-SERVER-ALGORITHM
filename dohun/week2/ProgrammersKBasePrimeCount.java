class ProgrammersKBasePrimeCount {

    public int solution(int n, int k) {
        String kBaseNumberString = Integer.toString(n, k); // 1. k진수 변환
        String[] parts = kBaseNumberString.split("0");     // 2. 0 기준 분리

        int count = 0;

        for (String p : parts) {
            if (p.isEmpty()) {
                continue; // 빈 문자열은 제외
            }

            long num = Long.parseLong(p); // 10진수로 변환
            if (isPrime(num)) {
                count++;
            }
        }

        return count;
    }

    private boolean isPrime(long num) {
        // 2보다 작은 수는 소수가 아님
        if (num < 2) {
            return false;
        }
        // 2부터 num의 제곱근까지 나누어 떨어지는지 확인
        for (long i = 2; i * i <= num; i++) {
            // 나누어 떨어지면 소수가 아님
            if (num % i == 0) {
                return false;
            }
        }
        // 나누어 떨어지는 수가 없으면 소수임
        return true;
    }
}
