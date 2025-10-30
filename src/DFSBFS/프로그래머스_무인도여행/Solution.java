package DFSBFS.프로그래머스_무인도여행;

import java.util.*;
class Solution {
    private int[][] map;
    private List<Integer> days = new ArrayList<>();
    private int[] dy = {1, -1, 0, 0};
    private int[] dx = {0, 0, 1, -1};

    public int[] solution(String[] maps) {
        map = new int[maps.length][maps[0].length()];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                char type = maps[i].charAt(j);
                if (type == 'X') {
                    map[i][j] = -1;
                } else {
                    map[i][j] = type - '0';
                }
            }
        }
        boolean[][] visited = new boolean[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != -1 && !visited[i][j]) {
                    bfs(i, j, visited);
                }
            }
        }
        if (days.size() == 0) {
            return new int[]{-1};
        }
        int[] answer = new int[days.size()];
        days.sort(Comparator.naturalOrder());
        for (int i = 0; i < answer.length; i++) {
            answer[i] = days.get(i);
        }
        return answer;
    }

    private void bfs(int startY, int startX, boolean[][] visited) {
        Queue<Pair> q = new ArrayDeque();
        q.add(new Pair(startY, startX));
        visited[startY][startX] = true;
        int sum = 0;

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            sum += map[poll.y][poll.x];

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX) || map[nY][nX] == -1 || visited[nY][nX]) {
                    continue;
                }
                q.add(new Pair(nY, nX));
                visited[nY][nX] = true;
            }
        }
        days.add(sum);
    }

    private boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= map.length || x >= map[0].length;
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
