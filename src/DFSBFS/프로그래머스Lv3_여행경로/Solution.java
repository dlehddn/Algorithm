package DFSBFS.프로그래머스Lv3_여행경로;

import java.util.*;
class Solution {

    static boolean[] ticketUsed;
    static ArrayList<String> result;
    static int count;

    public String[] solution(String[][] tickets) {
        ticketUsed = new boolean[tickets.length];
        result = new ArrayList<>();
        count = 0;

        dfs("ICN", "ICN", tickets, count);
        for(String a : result) System.out.println(a);

        Collections.sort(result);

        return result.get(0).split(" ");
    }

    private static void dfs(String start, String routes, String[][] tickets, int count) {

        if(count == tickets.length) {
            result.add(routes);
            return;
        }

        for(int i = 0; i < tickets.length; i++) {
            if(tickets[i][0].equals(start) && !ticketUsed[i]) {
                ticketUsed[i] = true;
                count++;
                dfs(tickets[i][1], routes + " " + tickets[i][1], tickets, count);
                ticketUsed[i] = false;
                count--;
            }
        }
    }
}
