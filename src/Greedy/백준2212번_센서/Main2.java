package Greedy.백준2212번_센서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
    
    static int N, K;
    static int[] arr;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr = Arrays.stream(arr)
                .sorted()
                .distinct()
                .toArray();

        for (int i = 0; i <= arr.length - 2; i++) {
            pq.add(arr[i + 1] - arr[i]);
        }

        int result = 0;
        int loopCnt = arr.length - K;
        while (loopCnt > 0) {
            result += pq.poll();
            loopCnt--;
        }

        System.out.println(result);

    }
}
