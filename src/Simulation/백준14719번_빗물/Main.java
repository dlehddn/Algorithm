package Simulation.백준14719번_빗물;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int[][] blocks;
    private static int H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        blocks = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++) {
            int depth = Integer.parseInt(st.nextToken());
            for (int j = 0; j < depth; j++) {
                blocks[j][i] = 1;
            }
        }

        int temp = -1;
        int sum = 0;
        for(int i = 0; i  < H; i++) {
            temp = -1;
            for (int j = 0; j < W; j++) {
                if(blocks[i][j] == 1) {
                    if(temp == -1) {
                        temp = j;
                    }
                    else {
                        sum += (j - temp - 1);
                        temp = j;
                    }
                }
            }
        }

        bw.write(Integer.toString(sum));
        bw.flush();
        bw.close();
    }
}
