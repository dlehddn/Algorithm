package Recursion.백준10870번_피보나치수5;

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());


        bw.write(Integer.toString(fibonacci(n)));
        bw.flush();
        bw.close();
    }

    private static int fibonacci (int n) {
        if(n == 1)  return 1;
        if(n == 0)  return 0;

        return fibonacci(n-1) + fibonacci(n-2);
    }
}