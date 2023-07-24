package Heap.백준2075번_N번째큰수;

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {

        int a = 0; // -21억 ~ 21억
        a = 2100000000;
        System.out.println(a);
        long b = 0l; //
        Queue<Integer> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pq.add(Integer.parseInt(st.nextToken()));
                if (pq.size() > N) pq.remove();
            }
        }
        bw.write(String.valueOf(pq.peek()));
        bw.flush();
        bw.close();
    }
}
