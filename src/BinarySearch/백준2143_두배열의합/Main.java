package BinarySearch.백준2143_두배열의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N, M;
    static int[] arrA, arrB;
    static List<Integer> sumA, sumB;
    static Map<Integer, Integer> mapB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        arrA = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        arrB = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }
        sumA = new ArrayList<>();
        sumB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += arrA[j];
                sumA.add(sum);
            }
        }
        mapB = new HashMap<>();

        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = i; j < M; j++) {
                sum += arrB[j];
                mapB.put(sum, mapB.getOrDefault(sum, 0) + 1);
            }
        }
        for (Integer v : mapB.keySet()) {
            sumB.add(v);
        }
        sumA.sort(Comparator.naturalOrder());
        sumB.sort(Comparator.naturalOrder());

        long result = 0;
        for (Integer a : sumA) {
            result += binarySearch(a);
        }
        System.out.println(result);
    }

    static int binarySearch(int a) {
        int left = 0;
        int right = sumB.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Integer tmp = sumB.get(mid);
            if (a + tmp == T) {
                return mapB.get(tmp);
            }
            if (a + tmp < T) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }
}
