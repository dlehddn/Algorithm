package TwoPointer.백준22862번_가장긴짝수연속한부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int coin = K;
        int result = 0;

        if (!isEven(arr[start])) coin--;
        if (isEven(arr[start])) result = 1;
        // 여기서 생각 부족 실수, 아래 while 문은 N = 1이면 진입할 수 없으므로, 처음에 result 값을 갱신하고 시작해야한다.

        while (end < N - 1) {
            while (isEven(arr[end + 1]) || coin > 0) {
                if (!isEven(arr[end + 1])) {
                    coin--;
                }
                end++;
                if (end == N - 1) break;
            }
            result = Math.max(result, end - start + 1 - (K - coin));
//            System.out.println(start + ", " + end);
//            System.out.println("cur result = " + result);
            if (!isEven(arr[start]) && coin < K) coin++;
            start++;
        }
        System.out.println(result);
    }

    static boolean isEven(int n) {
        return n % 2 == 0 ? true : false;
    }
}
