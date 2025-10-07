package Kakao._2022.양궁대회;

// start 9:18
// end 10:31
// -> 8, 18 해결 못해 솔루션 봄(가장 낮은 점수를 더 많이 맞힌 경우까지 만족했다 생각했지만 아님)
import java.util.*;

// 결국 라이언은 어피치보다 k점에 한발 더 쏘거나, 아예 안쏘거나 하면 됨.
class Second {
    int max = 0;
    int[] answer = new int[11];

    public int[] solution(int n, int[] info) {
        shoot(new int[11], info, n, 10);
        return max != 0 ? answer : new int[]{-1};
    }

    private void check(int[] shootInfo) {
        for (int i = 10; i >= 0; i--) {
            if (shootInfo[i] > answer[i]) {
                deepCopy(shootInfo);
            } else if (shootInfo[i] < answer[i]) {
                break;
            }
        }
    }

    private void shoot(int[] shootInfo, int[] apeachInfo, int remain, int curScore) {
        if (curScore == 0) {
            if (remain > 0) {
                shootInfo[10] = remain;
            }
            int diff = calcScore(shootInfo, apeachInfo);
            if (diff > 0 && diff >= max) {
                if (diff > max) {
                    max = diff;
                    deepCopy(shootInfo);
                } else {
                    check(shootInfo);
                }
            }
            shootInfo[10] = 0;
            return;
        }
        if (remain >= apeachInfo[10 - curScore] + 1) {
            int target = apeachInfo[10 - curScore] + 1;
            shootInfo[10 - curScore] = target;
            shoot(shootInfo, apeachInfo, remain - target, curScore - 1);
            shootInfo[10 - curScore] = 0;
        }
        shoot(shootInfo, apeachInfo, remain, curScore - 1);
    }

    private void deepCopy(int[] shootInfo) {
        for (int i = 0; i < 11; i++) {
            answer[i] = shootInfo[i];
        }
    }

    private int calcScore(int[] shootInfo, int[] apeachInfo) {
        int lionScore = 0;
        int apeachScore = 0;

        for (int i = 0; i <= 10; i++) {
            if (shootInfo[i] > apeachInfo[i]) {
                lionScore += 10 - i;
            } else if (shootInfo[i] <= apeachInfo[i] && apeachInfo[i] > 0) {
                apeachScore += 10 - i;
            }
        }
        return lionScore - apeachScore;
    }
}
