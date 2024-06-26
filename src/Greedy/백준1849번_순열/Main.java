package Greedy.백준1849번_순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] result, inputs;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new int[N];
        inputs = new int[N];

        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            int cnt = inputs[i];
            
        }
    }
}
