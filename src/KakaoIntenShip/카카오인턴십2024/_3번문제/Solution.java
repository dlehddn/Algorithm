//import java.util.*;
//class Solution {
//
//    static boolean[] visited;
//    static ArrayList<Integer> pickA = new ArrayList<>();
//    static ArrayList<Integer> pickB = new ArrayList<>();
//    static int[][] score;
//
//    public int[] solution(int[][] dice) {
//        visited = new boolean[dice.length + 1];
//        back(0, 1, dice.length / 2, dice);
//        return new int[4];
//    }
//
//
//    static void back(int depth, int start, int n, int[][] dice) {
//        if (depth == n) {
//            pickB.clear();
//            for (int i = 1; i <= dice.length; i++) {
//                if (!pickA.contains(i)) {
//                    pickB.add(i);
//                }
//            }
//            for (int i = 0; i < pickA.size(); i++) {
//
//            }
//
//            return;
//
//        }
//
//        for (int i = start; i < dice.length + 1; i++) {
//            pickA.add(i);
//            back(depth + 1, i + 1, n, dice);
//            pickA.remove(pickA.size() - 1);
//        }
//    }
//
//}
//
