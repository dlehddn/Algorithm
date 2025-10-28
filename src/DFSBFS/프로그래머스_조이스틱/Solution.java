package DFSBFS.프로그래머스_조이스틱;

class Solution {
    private int minMoveCnt;
    private int transferCnt = 0;

    public int solution(String name) {
        int remain = 0;
        minMoveCnt = name.length() - 1;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != 'A') {
                remain++;
                transferCnt += findCost(name.charAt(i));
            }
        }
        char[] names = new char[name.length()];
        for (int i = 0; i < name.length(); i++) {
            names[i] = name.charAt(i);
        }
        dfs(names, remain, 0, 0);
        return minMoveCnt + transferCnt;
    }

    private void dfs(char[] name, int remain, int moveCnt, int curIdx) {
        if (name[curIdx] != 'A') {
            remain--;
        }
        if (remain == 0) {
            minMoveCnt = Math.min(minMoveCnt, moveCnt);
            return;
        }
        if (moveCnt >= name.length - 1) {
            return;
        }

        int rightIdx = (curIdx + 1) % name.length;
        int leftIdx = (curIdx - 1 + name.length) % name.length;
        char cache = name[curIdx];
        if (name[curIdx] != 'A') {
            name[curIdx] = 'A';
            dfs(name, remain, moveCnt + 1, leftIdx);
            name[curIdx] = cache;
        } else {
            dfs(name, remain, moveCnt + 1, leftIdx);
        }

        if (name[curIdx] != 'A') {
            name[curIdx] = 'A';
            dfs(name, remain, moveCnt + 1, rightIdx);
            name[curIdx] = cache;
        } else {
            dfs(name, remain, moveCnt + 1, rightIdx);
        }
    }

    private int findCost(char target) {
        if (target - 'A' <= 13) {
            return target - 'A';
        } else {
            return 'Z' - target + 1;
        }
    }
}
