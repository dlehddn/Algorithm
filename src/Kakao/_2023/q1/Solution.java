package Kakao._2023.q1;

import java.util.*;

class Solution {
    static Map<String, Integer> map;
    static int[] result;
    static List<Integer> tmp;

    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer st;
        map = new HashMap<>();
        tmp = new ArrayList<>();
        for (String s : terms) {
            st = new StringTokenizer(s);
            String kind = st.nextToken();
            String num = st.nextToken();
            map.put(kind, Integer.parseInt(num));
        }

        for (int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i]);
            String date = st.nextToken();
            String type = st.nextToken();
            if (check(date, type, today)) {
                tmp.add(i + 1);
            }
        }
        result = new int[tmp.size()];

        for (int i = 0; i < tmp.size(); i++) {
            result[i] = tmp.get(i);
        }

        return result;
    }

    static boolean check(String date, String type, String today) {
        int duration = map.get(type);
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        int toYear = Integer.parseInt(today.substring(0, 4));
        int toMonth = Integer.parseInt(today.substring(5, 7));
        int toDay = Integer.parseInt(today.substring(8));

        day--;
        if (day == 0) {
            month--;
            day = 28;
        }
        if (month == 0) {
            year--;
            month = 12;
        }

        month += duration;
        year += month / 12;
        month = month % 12;

        if (month == 0) {
            year--;
            month = 12;
        }

        if (year < toYear) {
            return true;
        } if (year == toYear && month < toMonth) {
            return true;
        } if (year == toYear && month == toMonth && day < toDay) {
            return true;
        }
        return false;
    }
}