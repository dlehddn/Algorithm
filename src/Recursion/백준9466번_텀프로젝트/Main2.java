//package Recursion.백준9466번_텀프로젝트;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Stack;
//import java.util.StringTokenizer;
//
//public class Main2 {
//
//    static int T, N;
//    static int[] arr;
//    static boolean[] done, matched;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        T = Integer.parseInt(br.readLine());
//
//        for (int i = 0; i < T; i++) {
//            N = Integer.parseInt(br.readLine());
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            arr = new int[N + 1];
//            done = new boolean[N + 1];
//            matched = new boolean[N + 1];
//            for (int j = 1; j <= N; j++) {
//                arr[j] = Integer.parseInt(st.nextToken());
//            }
//
//            for (int j = 1; j <= N; j++) {
//            }
//
//            System.out.println(count());
//
//        }
//    }
//
//    static int count() {
//        int result = 0;
//        for (int i = 1; i <= N; i++) {
//            if (!matched[i]) result++;
//        }
//        return result;
//    }
//
//    static void dfs(int cur, Stack<Integer> stack, boolean[] visited) {
//        if (visited[cur]) {
//
//            while ()
//
//
//        }
//
//
//    }
//
//}
