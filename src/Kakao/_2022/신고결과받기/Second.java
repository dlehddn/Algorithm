package Kakao._2022.신고결과받기;

// start 9:01
// end 9:14
// -> 13m 소요
import java.util.*;

// 임의의 유저를 신고한 유저를 관리하는 Map
// 내가 신고한 유저 중 정지된 메일을 받는 수를 관리하는 Map
class Second {
    Map<String, Set<String>> reportedMap = new HashMap<>();
    Map<String, Integer> mailCounts = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {
        for (String id: id_list) {
            reportedMap.put(id, new HashSet<>());
            mailCounts.put(id, 0);
        }
        for (String r: report) {
            String[] split = r.split(" ");
            String reporter = split[0];
            String criminal = split[1];
            reportedMap.get(criminal).add(reporter);
        }

        for (String criminal: reportedMap.keySet()) {
            Set<String> reporters = reportedMap.get(criminal);
            if (reporters.size() >= k) {
                for (String reporter: reporters) {
                    mailCounts.put(reporter, mailCounts.get(reporter) + 1);
                }
            }
        }
        int[] answer = new int[id_list.length];

        for (int i = 0; i < id_list.length; i++) {
            answer[i] = mailCounts.get(id_list[i]);
        }
        return answer;
    }
}
