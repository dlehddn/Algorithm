package DP.백준1106_호텔;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N, C;
    static List<City> list = new ArrayList<>();
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new int[C + 101];
        Arrays.fill(dp, 10000000);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new City(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        list = list.stream()
                .sorted(Comparator.comparing(city -> city.gain))
                .collect(Collectors.toList());

        for (int i = 1; i < C + 101; i++) {
            for (City city : list) {
                if (i == city.gain) {
                    dp[i] = Math.min(dp[i], city.cost);
                }

                if (city.gain <= i) {
                    dp[i] = Math.min(dp[i], dp[i - city.gain] + city.cost);
                } else {
                    break;
                }
            }
        }

        int minV = Arrays.stream(dp)
                .skip(C)
                .min()
                .orElse(0);

        System.out.println(minV);
    }

    static class City {
        int cost;
        int gain;

        public City(int cost, int gain) {
            this.cost = cost;
            this.gain = gain;
        }
    }
}
