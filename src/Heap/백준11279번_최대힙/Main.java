package Heap.백준11279번_최대힙;

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) {
//              bw.write(heap.poll() + "\n");
                try {
                    int a = heap.poll();
                    bw.write(a + "\n");
                } catch (NullPointerException e) {
                    bw.write(0 + "\n");
                }
            } else {
                heap.add(num);
            }
        }
        bw.flush();
        bw.close();
    }
}
