package Kakao._2023.표병합;

// 명령별 시간복잡도
// 1. update r c value -> 2,500
// 2. update value value2 -> 2,500
// 3. merge r1 c1 r2 c2 -> 2,500
// 4. unmerge r c -> 2,500

// merge된 그룹에 대해 임의의 (r, c)가 어떤 merged 그룹인지 체크 가능해야함
// 임의의 merged 그룹에 대해서 소속된 cell 정보를 알 수 있어야함
// -> 두 개를 만족하면 1, 2, 3, 4 모두 만족
// -> 2,500 * 1,000 = 2,500,000의 시간 복잡도 보장
import java.util.*;

class Solution {
    Map<Integer, Integer> whereIams = new HashMap<>();
    Map<Integer, Set<Integer>> groupMembers = new HashMap<>();
    String[][] map = new String[51][51];
    int groupCounter = 1;

    public String[] solution(String[] commands) {

        for(int i = 1; i <= 50; i++) {
            for(int j = 1; j <= 50; j++) {
                int myLoc = toLocation(Integer.toString(i), Integer.toString(j));
                whereIams.put(myLoc, groupCounter);
                Set<Integer> set = new HashSet();
                set.add(myLoc);
                groupMembers.put(groupCounter, set);
                  groupCounter++;
            }
        }
        List<String> result = new ArrayList<>();
        for (String command: commands) {
            if (command.startsWith("UPDATE")) {
                update(command);
            } else if (command.startsWith("MERGE")) {
                merge(command);
            } else if (command.startsWith("UNMERGE")) {
                unmerge(command);
            } else {
                result.add(print(command));
            }
        }
        String[] answer = new String[result.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    private int toLocation(String r, String c) {
        return (Integer.parseInt(r) - 1) * 50 + (Integer.parseInt(c) - 1);
    }

    private String print(String command) {
        String[] splited = command.split(" ");
        return map[Integer.parseInt(splited[1])][Integer.parseInt(splited[2])] == null ? "EMPTY" : map[Integer.parseInt(splited[1])][Integer.parseInt(splited[2])];
    }

    private void unmerge(String command) {
        String[] splited = command.split(" ");
        int location = toLocation(splited[1], splited[2]);
        String value = map[Integer.parseInt(splited[1])][Integer.parseInt(splited[2])];
        Set<Integer> groupMember = groupMembers.get(whereIams.get(location));
        for(int gm: groupMember) {
            int r = gm / 50 + 1;
            int c = gm % 50 + 1;
            whereIams.put(gm, groupCounter);
            Set<Integer> set = new HashSet<>();
            set.add(gm);
            groupMembers.put(groupCounter, set);
            groupCounter++;
            map[r][c] = null;
        }
        map[Integer.parseInt(splited[1])][Integer.parseInt(splited[2])] = value;
    }

    private void merge(String command) {
        String[] splited = command.split(" ");
        int location1 = toLocation(splited[1], splited[2]);
        int location2 = toLocation(splited[3], splited[4]);
        if ((int) whereIams.get(location1) == whereIams.get(location2)) return;

        String value1 = map[Integer.parseInt(splited[1])][Integer.parseInt(splited[2])];
        String value2 = map[Integer.parseInt(splited[3])][Integer.parseInt(splited[4])];
        if (value1 != null && value2 != null || value1 != null && value2 == null) {
            Set<Integer> groupMembers1 = groupMembers.get(whereIams.get(location1));
            Set<Integer> groupMembers2 = groupMembers.get(whereIams.get(location2));
            for(Integer memberLoc: groupMembers2) {
                int r = memberLoc / 50 + 1;
                int c = memberLoc % 50 + 1;
                map[r][c] = value1;
                whereIams.put(memberLoc, whereIams.get(location1));
            }
            groupMembers1.addAll(groupMembers2);
            groupMembers2.clear();
        }
        else if (value1 == null && value2 != null) {
            Set<Integer> groupMembers1 = groupMembers.get(whereIams.get(location1));
            Set<Integer> groupMembers2 = groupMembers.get(whereIams.get(location2));
            for(Integer memberLoc: groupMembers1) {
                int r = memberLoc / 50 + 1;
                int c = memberLoc % 50 + 1;
                map[r][c] = value2;
                whereIams.put(memberLoc, whereIams.get(location2));
            }
            groupMembers2.addAll(groupMembers1);
            groupMembers1.clear();
        }
        else if (value1 == null && value2 == null) {
            Set<Integer> groupMembers1 = groupMembers.get(whereIams.get(location1));
            Set<Integer> groupMembers2 = groupMembers.get(whereIams.get(location2));
            for(Integer memberLoc: groupMembers1) {
                whereIams.put(memberLoc, whereIams.get(location2));
            }
            groupMembers2.addAll(groupMembers1);
            groupMembers1.clear();
        }
    }

    private void update(String command) {
        String[] splited = command.split(" ");
        if (splited.length == 4) {
            int location = toLocation(splited[1], splited[2]);
            for (Integer member: groupMembers.get(whereIams.get(location))) {
                int r = member / 50 + 1;
                int c = member % 50 + 1;
                map[r][c] = splited[3];
            }
        }
        else {
            for(int i = 1; i <= 50; i++) {
                for(int j = 1; j <= 50; j++) {
                    if (map[i][j] != null && map[i][j].equals(splited[1])) {
                        map[i][j] = splited[2];
                    }
                }
            }
        }
    }
}