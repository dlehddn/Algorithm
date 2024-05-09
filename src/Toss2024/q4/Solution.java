package Toss2024.q4;

import java.util.*;

class Solution {

    static List<Integer>[] map;

    public int[][] solution(int servers, boolean sticky, int[] requests) {
        map = new List[servers];
        for (int i = 0; i < servers; i++) {
            map[i] = new ArrayList<>();
        }

        int curServer = 0;
        int prevServer = 0;

        for (int i = 0; i < requests.length; i++) {
            if (i == 0) {
                map[curServer].add(requests[i]);
                prevServer = curServer;
                curServer = (curServer + 1 + servers) % servers;
                continue;
            }

            if (sticky == true && requests[i] == requests[i - 1]) {
                map[prevServer].add(requests[i]);
                continue;
            }

            map[curServer].add(requests[i]);
            prevServer = curServer;
            curServer = (curServer + 1) % servers;
        }

        int[][] result = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            List<Integer> list = map[i];
            result[i] = list.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        }

        return result;
    }
}
