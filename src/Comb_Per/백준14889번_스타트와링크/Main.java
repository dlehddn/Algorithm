package Comb_Per.백준14889번_스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, min;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        min = Integer.MAX_VALUE;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        splitTeam(0, 0, new int[N / 2]);
        System.out.println(min);
    }

    static void splitTeam(int cnt, int start, int[] teamA) {
        if (cnt == N / 2) {
            int[] teamB = findTeamB(teamA);
            compareAB(teamA, teamB);
            return;
        }

        for (int i = start; i <= N - 1; i++) {
            teamA[cnt] = i;
            splitTeam(cnt + 1, i + 1, teamA);
        }

    }

    static void compareAB(int[] teamA, int[] teamB) {
        int scoreA = findScore(teamA);
        int scoreB = findScore(teamB);
        int diff = Math.abs(scoreA - scoreB);
        if (diff < min) {
            min = diff;
        }
    }

    static int findScore(int[] team) {
        int score = 0;
        for (int i = 0; i < team.length; i++) {
            for (int j = i + 1; j < team.length; j++) {
                score += map[team[i]][team[j]];
                score += map[team[j]][team[i]];
            }
        }
        return score;
    }

    static int[] findTeamB(int[] teamA) {
        int[] teamB = new int[N / 2];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(i);
        }
        for (int i : teamA) {
            set.remove(i);
        }
        int idx = 0;
        for (Integer i : set) {
            teamB[idx] = i;
            idx++;
        }
        return teamB;
    }


}
