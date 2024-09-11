package DFSBFS.백준12886번_돌그룹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int sum = A + B + C;

        Queue<Group> q = new LinkedList<>();
        boolean[][] visited = new boolean[1500][1500];
        q.add(new Group(A, B));
        visited[A][B] = true;
        visited[B][A] = true;

        while (!q.isEmpty()) {
            Group poll = q.poll();
            int c = sum - poll.a - poll.b;

            if (poll.a == poll.b && poll.b == c) {
                System.out.println(1);
                return;
            }

            if (poll.a < poll.b && !visited[2 * poll.a][poll.b - poll.a] && !visited[poll.b - poll.a][2 * poll.a]) {
                q.add(new Group(2 * poll.a, poll.b - poll.a));
                visited[2 * poll.a][poll.b - poll.a] = true;
                visited[poll.b - poll.a][2 * poll.a] = true;
            } else if (poll.a > poll.b && !visited[poll.a - poll.b][2 * poll.b] && !visited[2 * poll.b][poll.a - poll.b]) {
                q.add(new Group(poll.a - poll.b, 2 * poll.b));
                visited[poll.a - poll.b][2 * poll.b] = true;
                visited[2 * poll.b][poll.a - poll.b] = true;
            }

            if (poll.a < c && !visited[2 * poll.a][c - poll.a] && !visited[c - poll.a][2 * poll.a]) {
                q.add(new Group(2 * poll.a, c - poll.a));
                visited[2 * poll.a][c - poll.a] = true;
                visited[c - poll.a][2 * poll.a] = true;
            } else if (poll.a > c && !visited[poll.a - c][2 * c] && !visited[2 * c][poll.a - c]) {
                q.add(new Group(poll.a - c, 2 * c));
                visited[poll.a - c][2 * c] = true;
                visited[2 * c][poll.a - c] = true;
            }

            if (poll.b < c && !visited[2 * poll.b][c - poll.b] && !visited[c - poll.b][2 * poll.b]) {
                q.add(new Group(2 * poll.b, c - poll.b));
                visited[2 * poll.b][c - poll.b] = true;
                visited[c - poll.b][2 * poll.b] = true;
            } else if (poll.b > c && !visited[poll.b - c][2 * c] && !visited[2 * c][poll.b - c]) {
                q.add(new Group(poll.b - c, 2 * c));
                visited[poll.b - c][2 * c] = true;
                visited[2 * c][poll.b - c] = true;
            }
        }
        System.out.println(0);

    }

    static class Group {
        int a, b;

        public Group(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
