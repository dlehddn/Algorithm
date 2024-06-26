package Greedy.백준1461번_도서관;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Integer> negatives, positives;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        negatives = new ArrayList<>();
        positives = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) {
                positives.add(num);
            } else {
                negatives.add(Math.abs(num));
            }
        }

        negatives.sort(Comparator.naturalOrder());
        positives.sort(Comparator.naturalOrder());

        int result = 0;
        result += solution(negatives);
        result += solution(positives);

//        System.out.println(solution(negatives));
//        System.out.println(solution(positives));

        int max = 0;
        if (negatives.size() > 0 && negatives.get(negatives.size() - 1) > max) {
            max = negatives.get(negatives.size() - 1);
        }
        if (positives.size() > 0 && positives.get(positives.size() - 1) > max) {
            max = positives.get(positives.size() - 1);
        }
        result -= max;

        System.out.println(result);


    }

    static int solution(List<Integer> list) {
        int sum = 0;
        int taking = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (taking == 0) {
                int target = list.get(i);
                sum += 2 * target;
            } else if (taking == 0 && i == 0) {
                int target = list.get(i);
                sum += 2 * target;
            }
            taking++;
            if (taking == M) {
                taking = 0;
            }
        }
        return sum;
    }
}
