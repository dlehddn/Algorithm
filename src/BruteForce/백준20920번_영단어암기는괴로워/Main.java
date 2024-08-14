package BruteForce.백준20920번_영단어암기는괴로워;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Map<String, Integer> map;
    static List<Word> list;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            if (name.length() >= M) {
                map.put(name, map.getOrDefault(name, 0) + 1);
            }
        }

        map.forEach((s, integer) -> list.add(new Word(s, integer)));
        list.sort((w1, w2) -> {
            if (w1.freq != w2.freq) {
                return w2.freq - w1.freq;
            } else if (w1.name.length() != w2.name.length()) {
                return w2.name.length() - w1.name.length();
            } else {
                return w1.name.compareTo(w2.name);
            }
        });

        StringBuilder sb = new StringBuilder();
        list.forEach(w -> sb.append(w.name));
        System.out.println(sb.toString());
    }

    static class Word {
        String name;
        int freq;

        public Word(String name, int freq) {
            this.name = name;
            this.freq = freq;
        }
    }
}
