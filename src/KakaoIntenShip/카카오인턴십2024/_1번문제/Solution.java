//import java.util.*;
//class Solution {
//
//    static int[] gift;
//    static int[] take;
//    static int[] result;
//    static HashMap<Integer, Integer>[] table;
//    static HashMap<String, Integer> map = new HashMap<>();
//    public int solution(String[] friends, String[] gifts) {
//        StringTokenizer st;
//        gift = new int[friends.length];
//        take = new int[friends.length];
//        result = new int[friends.length];
//        table = new HashMap[friends.length];
//
//        for (int i = 0; i < friends.length; i++) {
//            table[i] = new HashMap<>();
//            for (int j = 0; j < friends.length; j++) {
//                table[i].put(j, 0);
//            }
//            map.put(friends[i], i);
//        }
//
//        for (int i = 0; i < gifts.length; i++) {
//            st = new StringTokenizer(gifts[i]);
//            String first = st.nextToken();
//            String second = st.nextToken();
//            table[map.get(first)].put(map.get(second), table[map.get(first)].get(map.get(second)) + 1);
//            gift[map.get(first)] += 1;
//            take[map.get(second)] += 1;
//        }
//
//        for (HashMap<Integer, Integer> map : table) {
//            for (Integer a : map.keySet()) {
//                System.out.print(a + ", " + map.get(a) + " + ");
//            }
//            System.out.println();
//        }
//
//        for (int i = 0; i < friends.length; i++) {
//            HashMap<Integer, Integer> cur = table[i];
//            for (int j = 0; j < friends.length; j++) {
//                HashMap<Integer, Integer> compare = table[j];
//                if(i == j) continue;
//                if (cur.get(j) > compare.get(i)) {
//                    result[i] += 1;
//                } else if (cur.get(j) == compare.get(i)) {
//                    if (gift[i] - take[i] > gift[j] - take[j]) {
//                        cur.put(j, cur.get(j) + 1);
//                        result[i] += 1;
//                    } else if (gift[i] - take[i] < gift[j] - take[j]) {
//                        compare.put(i, compare.get(i) + 1);
//                    }
//                }
//            }
//        }
//        int max = Integer.MIN_VALUE;
//        for (int i : result) {
//            if(i > max) max = i;
//        }
//        return max;
//    }
//}