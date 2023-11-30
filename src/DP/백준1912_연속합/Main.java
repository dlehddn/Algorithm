package DP.백준1912_연속합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = Arrays.stream(br.readLine().split(" "))
                .map(w -> Integer.parseInt(w))
                .collect(Collectors.toList());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, -2000);

        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1] + list.get(i-1), list.get(i-1));
        }

        int max = Arrays.stream(dp)
                .max()
                .orElse(-1);
        System.out.println(max);

    }
}
