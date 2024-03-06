package Kakao._2023.q6;

import java.util.*;
class FirstSolve {
    /**
    * 배울게 많은 문제.
    * 임의의 턴에 같은 자리를 다시 도착한다면,
    * 추가적으로 탐색할 필요가 없음.이전에 여기 왔을 때도 이미 답을 못찾았던거니까
    * 꼭 기억하길...
    */
    static int[] dy = {1, 0, 0, -1}; // 하, 좌, 우, 상
    static int[] dx = {0, -1, 1, 0};
    static boolean[][][] visited;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        visited = new boolean[n][m][k + 1];
        String result = dfs(n, m, x-1, y-1, r-1, c-1, k, 0, "");
        String tmp = "";
        if(!result.equals("impossible")) {
            for(int i = 0; i < k; i++) {
                if(result.charAt(i) == '0') {
                    tmp += "d";
                }
                else if(result.charAt(i) == '1') {
                    tmp += "l";
                }
                else if(result.charAt(i) == '2') {
                    tmp += "r";
                }
                else if(result.charAt(i) == '3') {
                    tmp += "u";
                }
            }
            return tmp;
        } else {
            return result;
        }
    }

    static String dfs (int n, int m, int x, int y, int r, int c, int k, int cnt, String routes) {
        if(cnt == k) {
            if(x == r && y == c) {
                return routes;
            }
            else {
                return "impossible";
            }
        }

        String result = null;
        visited[x][y][cnt] = true;
        for(int i = 0; i < 4; i++) {
            int nextX = x + dy[i];
            int nextY = y + dx[i];
            if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;
            if(visited[nextX][nextY][cnt + 1]) continue;
            result = dfs(n, m, nextX, nextY, r, c, k, cnt+1, routes + i);
            if(!result.equals("impossible")) {
                break;
            }
        }
        if(result == null) {
            return "impossible";
        } else {
            return result;
        }
    }
}