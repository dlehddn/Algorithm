package BruteForce.백준7490번_0만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N;
    static StringBuilder middleResult, finalResult;
    static List<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        finalResult = new StringBuilder();
        while (T > 0) {
            N = Integer.parseInt(br.readLine());
            middleResult = new StringBuilder();
            list = new ArrayList<>();
            recursion(1, new ArrayList<>());
            list.sort(Comparator.naturalOrder());
            for (String str : list) {
                middleResult.append(str);
            }
            middleResult.append("\n");
            finalResult.append(middleResult);
            T--;
        }
        System.out.println(finalResult);

    }

    static void recursion(int idx, List<Integer> records) {
        if (idx == N + 1) {
            if (sum(records) == 0) {
                list.add(makeString(records));
            }
            return;
        }
        // +
        records.add(idx);
        recursion(idx + 1, records);
        records.remove(records.size() - 1);

        // -
        if (idx != 1) {
            records.add(idx * -1);
            recursion(idx + 1, records);
            records.remove(records.size() - 1);
        }

        // 공백
        if (idx != 1) {
            int prev = records.get(records.size() - 1);
            int next = Math.abs(prev) * 10 + idx;
            next = prev > 0 ? next : next * -1;
            records.remove(records.size() - 1);
            records.add(next);
            recursion(idx + 1, records);
        }
    }

    static String makeString(List<Integer> records) {
        StringBuilder sb = new StringBuilder();
        Integer firstNum = records.get(0);
        if (firstNum < 10) {
            sb.append(firstNum);
        } else {
            sb.append(Math.abs(firstNum / 10));
            sb.append(" ");
            sb.append(Math.abs(firstNum % 10));
        }
        for (int i = 1; i < records.size(); i++) {
            Integer num = records.get(i);
            if (num > 0) {
                sb.append("+");
            } else if (num < 0) {
                sb.append("-");
            }
            if (Math.abs(num) < 10) {
                sb.append(Math.abs(num));
            } else {
                sb.append(Math.abs(num / 10));
                sb.append(" ");
                sb.append(Math.abs(num % 10));
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    static int sum(List<Integer> records) {
        return records.stream()
                .mapToInt(r -> r)
                .sum();
    }
}
