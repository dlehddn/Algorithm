package Kakao._2022.신고결과받기;

import java.util.*;

class Solution {
    static Map<String, Integer> receives = new HashMap<>();
    static Map<String, Set<String>> reports = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {
        for (String id: id_list) {
            receives.put(id, 0);
        }
        for (String r: report) {
            String[] splited = r.split(" ");
            Set<String> reportOwners = reports.getOrDefault(splited[1], new HashSet<>());
            reportOwners.add(splited[0]);
            reports.put(splited[1], reportOwners);
        }
        for (String receiver: reports.keySet()) {
            Set<String> repoters = reports.get(receiver);
            if (repoters.size() >= k) {
                for (String repoter: repoters) {
                    receives.put(repoter, receives.get(repoter) + 1);
                }
            }
        }
        int[] answer = new int[id_list.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = receives.get(id_list[i]);
        }
        return answer;
    }
}
