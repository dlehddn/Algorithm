package Kakao._2023.q5;

import java.util.*;
import java.util.regex.Pattern;

class Solution {
    static String[][] map;
    static int[] parent;
    static Map<Integer, List<Integer>> mergedMap;
    static String[] result;
    static List<String> tmpResult;

    public String[] solution(String[] commands) {
        map = new String[50][50];
        parent = new int[2500];
        mergedMap = new HashMap<>();
        tmpResult = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                map[i][j] = "EMPTY";
            }
        }
        for (int i = 0; i < 2500; i++) {
            parent[i] = i;
        }

        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            String type = st.nextToken();
            if (type.equals("UPDATE")) {
                update(command);
            }
            else if (type.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int c1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;
                int c2 = Integer.parseInt(st.nextToken()) - 1;
                union(50 * r1 + c1, 50 * r2 + c2);
            } else if (type.equals("UNMERGE")) {
                unmerge(command);
            } else if (type.equals("PRINT")) {
                print(command);
            }
        }

        System.out.println(tmpResult);


        return result;
    }

    static int find(int a) {
        if (a == parent[a]) return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);
        if (findA == findB) return;

        String valueA = map[findA / 50][findA % 50];
        String valueB = map[findB / 50][findB % 50];
        if (!valueA.equals("EMPTY") && valueB.equals("EMPTY")) {
            parent[findB] = findA;
            merge(findA, findB);
        } else if (valueA.equals("EMPTY") && !valueB.equals("EMPTY")) {
            parent[findA] = findB;
            merge(findB, findA);
        } else if (!valueA.equals("EMPTY") && !valueB.equals("EMPTY")) {
            parent[findB] = findA;
            merge(findA, findB);
        }
        // 둘다 없는 경우는? 일단 조건에 없으니까 그냥 해보자.
        else {
            parent[findB] = findA;
            merge(findA, findB);
        }
    }

    private static void merge(int findA, int findB) {
        if (mergedMap.containsKey(findB)) {
            mergedMap.getOrDefault(findA, new ArrayList<>())
                    .addAll(mergedMap.get(findB));
            mergedMap.get(findA).add(findB);
            mergedMap.remove(findB);
        } else {
            mergedMap.getOrDefault(findA, new ArrayList<>())
                    .add(findB);
        }
        map[findB / 50][findB % 50] = "EMPTY";
    }

    static void update(String command) {
        StringTokenizer st = new StringTokenizer(command);
        st.nextToken();
        String question = st.nextToken();
        if (Pattern.matches("^[0-9]*$", question)) {
            int c = Integer.parseInt(st.nextToken()) - 1;
            String value = st.nextToken();
            int parent = find(50 * Integer.parseInt(question) + c);
            map[parent / 50][parent % 50] = value;
        } else {
            String value2 = st.nextToken();
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    if (map[i][j].equals(question)) {
                        map[i][j] = value2;
                    }
                }
            }
        }
    }

    static void unmerge(String command) {
        StringTokenizer st = new StringTokenizer(command);
        st.nextToken();
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int parents = find(r * 50 + c);
        List<Integer> list = mergedMap.get(parents);
        System.out.println(list.isEmpty());
        if (!map[parents / 50][parents % 50].equals("EMPTY")) {
            map[r][c] = map[parents / 50][parents / 50];
        }
//        for (Integer cell : list) {
//            parent[cell] = cell;
//            if (cell != r * 50 + c) {
//                map[cell / 50][cell % 50] = "EMPTY";
//            }
//        }
        parent[parents] = parents;
    }

    static void print(String command) {
        StringTokenizer st = new StringTokenizer(command);
        st.nextToken();
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int parents = find(r * 50 + c);
        tmpResult.add(map[parents / 50][parents % 50]);
    }


}