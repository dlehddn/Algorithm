package DFSBFS.백준14226_이모티콘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        Queue<Emoticon> q = new ArrayDeque<>();
        q.add(new Emoticon(1, 0, 0));
        boolean[][] visited = new boolean[2000][2000];
        visited[1][0] = true;

        while (!q.isEmpty()) {
            Emoticon poll = q.poll();

            if (poll.displayCnt == S) {
                System.out.println(poll.time);
                return;
            }

            if (poll.displayCnt != poll.clipboardCnt && !visited[poll.displayCnt][poll.displayCnt]) {
                visited[poll.displayCnt][poll.displayCnt] = true;
                q.add(new Emoticon(poll.displayCnt, poll.displayCnt, poll.time + 1));
            }
            if (poll.clipboardCnt != 0 && poll.displayCnt + poll.clipboardCnt < 2000 && !visited[poll.displayCnt + poll.clipboardCnt][poll.clipboardCnt]) {
                visited[poll.displayCnt + poll.clipboardCnt][poll.clipboardCnt] = true;
                q.add(new Emoticon(poll.displayCnt + poll.clipboardCnt, poll.clipboardCnt, poll.time + 1));
            }
            if (poll.displayCnt > 0 && !visited[poll.displayCnt - 1][poll.clipboardCnt]) {
                visited[poll.displayCnt - 1][poll.clipboardCnt] = true;
                q.add(new Emoticon(poll.displayCnt - 1, poll.clipboardCnt, poll.time + 1));
            }
        }

    }

    static class Emoticon {
        int displayCnt, clipboardCnt, time;

        public Emoticon(int displayCnt, int clipboardCnt, int time) {
            this.displayCnt = displayCnt;
            this.clipboardCnt = clipboardCnt;
            this.time = time;
        }
    }
}
