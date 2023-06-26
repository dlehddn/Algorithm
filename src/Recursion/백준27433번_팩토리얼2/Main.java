package Recursion.백준27433번_팩토리얼2;

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());


        bw.write(Long.toString(fac(n)));
        bw.flush();
        bw.close();
    }

    private static long fac(int n) {
        if(n <= 1)  return 1;

        return (long) n * fac(n-1);
    }
}