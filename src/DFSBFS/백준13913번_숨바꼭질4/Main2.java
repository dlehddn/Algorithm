package DFSBFS.백준13913번_숨바꼭질4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int N, K, result;
    static boolean[] visited;
    static int[] moveLog;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        result = Integer.MAX_VALUE;
        moveLog = new int[100001];


        Queue<Node> q = new LinkedList();
        q.add(new Node(N, 0));

        while (!q.isEmpty()) {
            Node poll = q.poll();

            if (poll.node == K) {
                System.out.println(poll.time);
                printLog();
                return;
            }

            if (poll.node - 1 >= 0 && !visited[poll.node - 1]) {
                visited[poll.node - 1] = true;
                moveLog[poll.node - 1] = poll.node;
                q.add(new Node(poll.node - 1, poll.time + 1));
            }

            if (poll.node + 1 <= 100000 && !visited[poll.node + 1]) {
                visited[poll.node + 1] = true;
                moveLog[poll.node + 1] = poll.node;
                q.add(new Node(poll.node + 1, poll.time + 1));
            }

            if (poll.node * 2 <= 100000 && !visited[poll.node * 2]) {
                visited[poll.node * 2] = true;
                moveLog[poll.node * 2] = poll.node;
                q.add(new Node(poll.node * 2, poll.time + 1));
            }
        }


    }

    private static void printLog() {
        Stack<Integer> stack = new Stack<>();

        stack.push(K);
        while (K != N) {
            stack.push(moveLog[K]);
            K = moveLog[K];
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb.toString());
    }

    static class Node {
        int node, time;

        public Node(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }
}
