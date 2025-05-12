package BinarySearch.백준2512_예산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, balance;
    static int[] requests;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        requests = new int[N];
        int maxRequest = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            maxRequest = Math.max(maxRequest, requests[i]);
        }
        balance = Integer.parseInt(br.readLine());

        int start = 1;
        int end = maxRequest;
        int result = Integer.MIN_VALUE;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (isValidHighPrice(mid)) {
                result = Math.max(result, mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }

    static boolean isValidHighPrice(int target) {
        int tmp = 0;
        for (int request : requests) {
            if (request <= target) {
                tmp += request;
            } else {
                tmp += target;
            }
        }
        if (balance >= tmp) {
            return true;
        } else {
            return false;
        }
    }
}
