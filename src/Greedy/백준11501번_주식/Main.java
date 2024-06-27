package Greedy.백준11501번_주식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /**
     * 놓친게 있음 ㅇㅇ
     * 다시 풀어
     */

    static int T, N;
    static int[] infos;
    static PriorityQueue<Price> sellPoint;
    static Queue<Integer> buying;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            infos = new int[N];
            sellPoint = new PriorityQueue<>((p1, p2) -> {
                if (p1.value != p2.value) {
                    return p2.value - p1.value;
                } else {
                    return p1.day - p2.day;
                }
            });
            buying = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                infos[j] = n;
                sellPoint.add(new Price(j, n));
            }

            long max = 0;
            for (int j = 0; j < N; j++) {
                Price peek = sellPoint.peek();
                if (peek.value == infos[j]) {
                    while (!buying.isEmpty()) {
                        max += peek.value - buying.poll();
                    }
                    sellPoint.poll();
                }
//                else if (peek.value == infos[j] && peek.day != j) {
//                    while (true) {
//                        if (sellPoint.peek().value == infos[j] && sellPoint.peek().day < j) {
//                            sellPoint.poll();
//                        } else if (sellPoint.peek().value == infos[j] && sellPoint.peek().day == j) {
//                            Price poll = sellPoint.poll();
//                            while (!buying.isEmpty()) {
//                                max += poll.value - buying.poll();
//                            }
//                        } else break;
//                    }
//                }
                else {
                    buying.add(infos[j]);
                }
            }
            sb.append(max + "\n");
        }
        System.out.println(sb);
    }

    static class Price {
        int day, value;

        public Price(int day, int value) {
            this.day = day;
            this.value = value;
        }
    }
}
