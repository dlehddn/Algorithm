package BruteForce.백준21315_카드섞기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int maxK;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = Integer.parseInt(st.nextToken());
        }
        maxK = (int) (Math.log(N) / Math.log(2));

        for (int i = 0; i <= maxK; i++) {
            loop:
            for (int j = 0; j <= maxK; j++) {
                List<Integer> tmp = new ArrayList<>();
                for (int k = 0; k < N; k++) {
                    tmp.add(k + 1);
                }
                tmp = shuffle(i, tmp);
                tmp = shuffle(j, tmp);
                for (int k = 0; k < N; k++) {
                    if (tmp.get(k) != result[k]) {
                        continue loop;
                    }
                }
                System.out.println(i + " " + j);
                return;
            }
        }
    }

    static List<Integer> shuffle(int k, List<Integer> tmp) {
        if (k == -1) {
            return tmp;
        }

        List<Integer> left = new ArrayList<>(tmp.subList(0, tmp.size() - (int) Math.pow(2, k)));
        List<Integer> right = new ArrayList<>(tmp.subList(tmp.size() - (int) Math.pow(2, k), tmp.size()));
        List<Integer> result = shuffle(k - 1, right);
        result.addAll(left);
        return result;
    }
}
