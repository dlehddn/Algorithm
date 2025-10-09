package Kakao._2023.표병합;

// start 3:13
// end 4:21
// -> 1h 8m 소요

// 표 크기 50 * 50 = 2,500 고정
// 유니온-파인드 알고리즘을 사용하면?
// 1. update 명령
// -> 1번 명령: 2,500 || 2번 명령: 2,500
// 2. merge 명령
// -> 병합 하려는 셀의 부모 찾기: 2,500
// 3. unmerge 명령
// -> 2,500에 대해서 모든 부모를 찾아봐야함.
import java.util.*;
class Second {
    String[] map = new String[2500];
    int[] parents = new int[2500];

    public String[] solution(String[] commands) {
        for (int i = 0; i < 2500; i++) {
            parents[i] = i;
        }
        List<String> list = new ArrayList<>();

        for (String command: commands) {
            String[] split = command.split(" ");
            String type = split[0];
            if (type.equals("UPDATE")) {
                if (split.length == 4) {
                    int location = toNumber(split[1], split[2]);
                    update(location, split[3]);
                } else {
                    update(split[1], split[2]);
                }
            } else if (type.equals("MERGE")) {
                merge(toNumber(split[1], split[2]), toNumber(split[3], split[4]));
            } else if (type.equals("UNMERGE")) {
                unmerge(toNumber(split[1], split[2]));
            } else {
                list.add(print(toNumber(split[1], split[2])));
            }
        }
        String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    private void merge(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) return;

        if (map[parentA] != null && map[parentB] != null) {
            parents[parentB] = parentA;
        } else if (map[parentA] == null && map[parentB] != null) {
            parents[parentA] = parentB;
        } else {
            parents[parentB] = parentA;
        }
    }

    private int toNumber(String r, String c) {
        return 50 * (Integer.parseInt(r) - 1) + (Integer.parseInt(c) - 1);
    }

    private void update(int a, String value) {
        int parent = find(a);
        map[parent] = value;
    }

    private void update(String value1, String value2) {
        for (int i = 0; i < 2500; i++) {
            int parent = find(i);
            if (map[parent] != null && map[parent].equals(value1)) {
                map[parent] = value2;
            }
        }
    }

    private String print(int a) {
        int parent = find(a);
        return map[parent] != null ? map[parent] : "EMPTY";
    }

    private void unmerge(int a) {
        int parent = find(a);
        String content = map[parent];

        for (int i = 0; i < 2500; i++) {
            find(i);
        }

        for (int i = 0; i < 2500; i++) {
            if (parents[i] == parent) {
                parents[i] = i;
                map[i] = null;
                if (i == a && content != null) {
                    map[i] = content;
                }
            }
        }
    }

    private int find(int x) {
        if (parents[x] == x) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }
}
