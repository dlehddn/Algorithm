package Greedy.백준1202번_보석도둑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Diamond> diamonds;
    static List<Integer> bags;
    static PriorityQueue<Diamond> pq;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        diamonds = new ArrayList<>();
        bags = new ArrayList<>();
        pq = new PriorityQueue<>((d1, d2) -> d2.V - d1.V);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            diamonds.add(new Diamond(M, V));
        }

        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        diamonds.sort(Comparator.comparing(d -> d.M));
        bags.sort(Comparator.naturalOrder());

        int pointer = -1;
        long result = 0;

        for (int i = 0; i < K; i++) {
            int curM = bags.get(i);

            for (int j = pointer + 1; j < N && diamonds.get(j).M <= curM; j++) {
                pointer = j;
                pq.add(diamonds.get(j));
            }

            if (!pq.isEmpty()) {
                Diamond poll = pq.poll();
                result += poll.V;
            }

        }

        System.out.println(result);

    }

    static class Diamond {
        int M, V;

        public Diamond(int m, int v) {
            M = m;
            V = v;
        }
    }
}
