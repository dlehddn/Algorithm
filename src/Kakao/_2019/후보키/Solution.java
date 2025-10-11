package Kakao._2019.후보키;

// start 9:51

import java.util.*;
class Solution {
    int r, c;
    int answer = 0;
    Set<String> candidates = new HashSet<>();

    public int solution(String[][] relation) {
        r = relation.length;
        c = relation[0].length;
        for (int i = 1; i <= r; i++) {
            combinationKeys(i, 0, 0, new ArrayList<>(), relation);
        }
        return answer;
    }

    private void candidateCheck(List<Integer> columnIdxs, String[][] relation) {
        Set<String> tmp = new HashSet<>();
        for (int i = 0; i < r; i++) {
            StringBuilder sb = new StringBuilder();
            for (int column: columnIdxs) {
                sb.append(relation[i][column]).append(" ");
            }
            if (tmp.contains(sb.toString())) {
                return;
            }
            tmp.add(sb.toString());
        }
        StringBuilder sb = new StringBuilder();
        for (int n: columnIdxs) {
            sb.append(n).append(" ");
        }
        candidates.add(sb.toString());
        answer++;
    }

    private boolean alreadyUnique(int size, int cnt, int start, List<Integer> columnIdxs, List<Integer> picks) {
        if (cnt == size) {
            StringBuilder sb = new StringBuilder();
            for (int n: picks) {
                sb.append(n).append(" ");
            }
            if (candidates.contains(sb.toString())) {
                return true;
            }
            return false;
        }


        for (int i = start; i < columnIdxs.size(); i++) {
            picks.add(columnIdxs.get(i));
            boolean result = alreadyUnique(size, cnt+1, i + 1, columnIdxs, picks);
            if (result) {
                return true;
            }
            picks.remove(picks.size() - 1);
        }
        return false;
    }

    private void combinationKeys(int total, int cnt, int start, List<Integer> columnIdxs, String[][] relation) {
        if (cnt == total) {
            for (int i = 1; i < columnIdxs.size(); i++) {
                if (alreadyUnique(i, 0, 0, columnIdxs, new ArrayList<>())) {
                    return;
                }
            }
            candidateCheck(columnIdxs, relation);
            return;
        }
        for (int i = start; i < c; i++) {
            columnIdxs.add(i);
            combinationKeys(total, cnt+1, i + 1, columnIdxs, relation);
            columnIdxs.remove(columnIdxs.size() - 1);
        }
    }
}