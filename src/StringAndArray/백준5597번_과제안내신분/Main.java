package StringAndArray.백준5597번_과제안내신분;


import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[30];
        for (int i = 0; i < 28; i++) {
            int N = Integer.parseInt(br.readLine());
            arr[N-1] = N;
        }

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != i+1) {
                System.out.println(i+1);
            }
        }
    }
}
