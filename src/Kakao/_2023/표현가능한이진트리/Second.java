package Kakao._2023.표현가능한이진트리;

// start 12:15
// end 1:20
// -> 1h 5m 소요
// -> 종료조건을 마지막을 기준으로 했는데, 중간에 루트가 0인데 자식이 1인 경우가 체크가 안됐었음.
// -> 이거 때문에 20분은 더 소요
class Second {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long n = numbers[i];
            String binaryNum = Long.toBinaryString(n);
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < 100; j++) {
                int targetLength = (int) Math.pow(2, j) - 1;
                if (binaryNum.length() <= targetLength) {
                    int remain = targetLength - binaryNum.length();
                    while (remain > 0) {
                        sb.append("0");
                        remain--;
                    }
                    break;
                }
            }
            sb.append(binaryNum);
            binaryNum = sb.toString();
            answer[i] = check(binaryNum, false, 0);
        }
        return answer;
    }

    private int check(String binaryNum, boolean isRootZero, int depth) {
        int mid = binaryNum.charAt(binaryNum.length() / 2) - '0';
        if (mid == 0) {
            isRootZero = true;
        }
        if (depth == 0 && mid == 0 || isRootZero && mid == 1) {
            return 0;
        }
        if (binaryNum.length() == 1) {
            if (!isRootZero || isRootZero && binaryNum.equals("0")) {
                return 1;
            } else {
                return 0;
            }
        }
        int left = check(binaryNum.substring(0, binaryNum.length() / 2), isRootZero, depth + 1);
        int right = check(binaryNum.substring(binaryNum.length() / 2 + 1), isRootZero, depth + 1);

        if (left == 1 && right == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
