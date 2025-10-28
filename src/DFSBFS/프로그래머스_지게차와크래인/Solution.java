package DFSBFS.프로그래머스_지게차와크래인;

import java.util.*;

class Solution {
    private int[] dy = {1, -1, 0, 0};
    private int[] dx = {0, 0, 1, -1};
    private char[][] containers;
    private final char DELETED = '.';
    private boolean[][] visited;
    private int N, M;

    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        visited = new boolean[N + 2][M + 2];
        containers = new char[N + 2][M + 2];
        initialize(storage);
        for (String request: requests) {
            if (request.length() == 1) {
                externalDelete(request.charAt(0));
            } else {
                allDelete(request.charAt(0));
            }
        }
        return countRemainBox();
    }

    private int countRemainBox() {
        int sum = 0;
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < M + 2; j++) {
                if (containers[i][j] != DELETED) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private void allDelete(char target) {
        for (int i = 1; i < containers.length - 1; i++) {
            for (int j = 1; j < containers[0].length - 1; j++) {
                if (containers[i][j] == target) {
                    containers[i][j] = DELETED;
                }
            }
        }
    }

    private boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N + 2 || x >= M + 2;
    }

    private void externalDelete(char target) {
        Queue<Pair> q = new ArrayDeque();
        List<Pair> removeTargets = new ArrayList<>();
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < M + 2; j++) {
                if (visited[i][j]) {
                    q.add(new Pair(i, j));
                }
            }
        }
        while (!q.isEmpty()) {
            Pair poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX)) continue;
                if (containers[nY][nX] == target) {
                    removeTargets.add(new Pair(nY, nX));
                } else if (containers[nY][nX] == DELETED && !visited[nY][nX]) {
                    visited[nY][nX] = true;
                    q.add(new Pair(nY, nX));
                }
            }
        }
        for (Pair pair: removeTargets) {
            containers[pair.y][pair.x] = DELETED;
            visited[pair.y][pair.x] = true;
        }
    }

    private void initialize(String[] storage) {
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < M + 2; j++) {
                if (i == 0 || i == N + 1 || j == 0 || j == M + 1) {
                    visited[i][j] = true;
                }
            }
        }
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < M + 2; j++) {
                containers[i][j] = DELETED;
            }
        }
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                containers[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
