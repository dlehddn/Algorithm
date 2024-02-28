package Kakao._2023.q4;

class Solution {
    /**
     * 기존 순서
     * 1. 포화 이진트리 만들기
     * 2. 2진수 생성
     * 3. 10진수 변환
     *
     * 구해야할 것
     * 1. 10진수 주어짐
     * 2. 2진수 변환
     * 3. 포화 이진트리를 만들 수 있는지 판단
     *
     * 솔루션
     * 1. 루트 노드를 구해본다.
     * -> 루트 노드는 무조건 1
     * -> 루트 노드 기준으로 좌, 우측에는 1, 3, 7, 15, 31 ... 개가 존재해야 포화 이진트리를 만족. -> 2^i - 1 (i == 1 부터)
     * -> 전체 숫자로 보면 총 3, 7, 31 ... 개가 존재해야 포화 이진트리를 만족.
     * -> 2진수로 변환값에 가장 근접한 포화 이진트리의 개수를 파악.
     * -> 루트 노드는 가장 가운데, 0이면 바로 false
     *
     * 2. 좌 우측으로 절반씩 쪼개면서 탐색, 만약 부모 노드가 0인데 하위에 1이 존재한다면 false
     */

    static int[] result;

    public static void main(String[] args) {
        solution(new long[]{423L, 128L, 1L});
    }

    public static int[] solution(long[] numbers) {
        result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            checkAvailable(numbers[i], i);
        }
        return result;
    }

    static void checkAvailable(long number, int cnt) {
        String binary = Long.toBinaryString(number);
        if (canTransform(binary)) {
            result[cnt] = 1;
        } else {
            result[cnt] = 0;
        }
    }

    static boolean canTransform(String binary) {
        String maxBinaryTree = findMaxBinaryTree(binary);
        int size = maxBinaryTree.length();

        //1. 루트 노드가 1인지 확인
        if (maxBinaryTree.charAt(size / 2) == '0') {
            return false;
        }

        //2. 좌, 우로 절반씩 쪼개가며 확인
        return checkLeafNode(maxBinaryTree);
    }

    static boolean checkLeafNode(String binary) {
        if (binary.length() == 1) {
            return true;
        }
        if (binary.length() == 3) {
            if (binary.charAt(1) == '1') {
                return true;
            } else {
                if (binary.charAt(0) == '0' && binary.charAt(2) == '0') {
                    return true;
                } else return false;
            }
        }

        int parentIdx = binary.length() / 2;
        String leftSide = binary.substring(0, parentIdx);
        String rightSide = binary.substring(parentIdx + 1);
        if (binary.charAt(parentIdx) == '0') {
            if (leftSide.charAt(leftSide.length() / 2) == '1'
                    || rightSide.charAt(rightSide.length() / 2) == '1') {
                return false;
            }
        }
        return checkLeafNode(leftSide) && checkLeafNode(rightSide);
    }

    static String findMaxBinaryTree(String binary) {
        int power = (int) (Math.log(binary.length()) / Math.log(2));
        int goal = (int) Math.pow(2, power + 1) - 1;
        int size = binary.length();

        StringBuilder sb = new StringBuilder();
        while (size < goal) {
            sb.append("0");
            size++;
        }
        return sb.toString() + binary;
    }

}