package DFSBFS.백준3584번_가장가까운공통조상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /**
     * 1. 인접 리스트 생성
     * 2. 루트 노드 찾기 -> 인접 리스트 생성 시 in 개수 파악
     * 3. 각 노드가 갖는 레벨 찾기 -> 별도 배열로 관리
     * 4. BFS 실행
     */

    static List<Integer>[] tree;
    static int[] ins, levels;
    static int T, N, targetA, targetB, root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            tree = new List[N + 1];
            ins = new int[N + 1];
            levels = new int[N + 1];
            for (int j = 1; j < N + 1; j++) {
                tree[j] = new ArrayList<>();
            }
            StringTokenizer st;
            for (int j = 0; j < N - 1; j++) {
                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                tree[p].add(c);
                tree[c].add(p);
                ins[c]++;
            }
            st = new StringTokenizer(br.readLine());
            targetA = Integer.parseInt(st.nextToken());
            targetB = Integer.parseInt(st.nextToken());

            for (int j = 1; j < N + 1; j++) {
                if (ins[j] == 0) {
                    root = j;
                    break;
                }
            }
            findLevels();
            findRoot();
        }

    }

    static void findRoot() {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(new Node(targetA, targetA));
        visited[targetA] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (poll.vertex == targetB) {
                System.out.println(poll.parent);
                return;
            }

            for (int next : tree[poll.vertex]) {
                if (!visited[next]) {
                    if (levels[next] < levels[poll.parent]) {
                        q.add(new Node(next, next));
                    } else {
                        q.add(new Node(next, poll.parent));
                    }
                    visited[next] = true;
                }
            }
        }
    }

    static void findLevels() {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(new Node(root, 0));
        visited[root] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();
            levels[poll.vertex] = poll.parent;

            for (int next : tree[poll.vertex]) {
                if (!visited[next]) {
                    q.add(new Node(next, poll.parent + 1));
                    visited[next] = true;
                }
            }
        }
    }

    static class Node {
        int vertex, parent;

        public Node(int vertex, int parent) {
            this.vertex = vertex;
            this.parent = parent;
        }
    }
}
