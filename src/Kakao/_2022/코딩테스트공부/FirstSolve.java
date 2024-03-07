package Kakao._2022.코딩테스트공부;


class FirstSolve {

    static int[][] dp;
    static int goalA, goalC;

    public int solution(int alp, int cop, int[][] problems) {
        for(int i = 0; i < problems.length; i++) {
            int[] p = problems[i];
            if (p[0] > goalA) goalA = p[0];
            if (p[1] > goalC) goalC = p[1];
        }

        dp = new int[goalA + 5][goalC + 5];
        for (int i = 0; i < goalA + 5; i++) {
            for (int j = 0; j < goalC + 5; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        if(alp > goalA) alp = goalA;
        if(cop > goalC) cop = goalC;

        findLeastTime(alp, cop, problems);

        System.out.println(goalA);
        System.out.println(goalC);


        return dp[goalA][goalC];
    }

    static void findLeastTime(int alp, int cop, int[][] problems) {

        dp[alp][cop] = 0;

        for (int i = alp; i <= goalA; i++) {
            for (int j = cop; j <= goalC; j++) {
                if (dp[i + 1][j] > dp[i][j] + 1) dp[i + 1][j] = dp[i][j] + 1;
                if (dp[i][j + 1] > dp[i][j] + 1) dp[i][j + 1] = dp[i][j] + 1;

                for (int[] p : problems) {
                    if (i >= p[0] && j >= p[1]) {
                        int aIdx = Math.min(goalA, i + p[2]);
                        int cIdx = Math.min(goalC, j + p[3]);

                        if (dp[aIdx][cIdx] > dp[i][j] + p[4]) {
                            dp[aIdx][cIdx] = dp[i][j] + p[4];
                        }
                    }
                }
            }
        }

    }

}