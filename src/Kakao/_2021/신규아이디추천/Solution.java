package Kakao._2021.신규아이디추천;

// 12:54 start
// 1:31 end
// -> 37m 소요
import java.util.*;

class Solution {
    public String solution(String new_id) {
        return step7(step6(step5(step4(step3(step2(step1(new_id)))))));
    }

    private String step1(String id) {
        return id.toLowerCase();
    }

    private String step2(String id) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            char cur = id.charAt(i);
            if (Character.isAlphabetic(cur) || Character.isDigit(cur) || cur == '-' || cur == '_' || cur == '.') {
                sb.append(cur);
            }
        }
        return sb.toString();
    }

    private String step3(String id) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            char cur = id.charAt(i);
            if (cur != '.') {
                sb.append(cur);
            } else {
                if (sb.length() == 0 || sb.charAt(sb.length() - 1) != '.') {
                    sb.append(cur);
                }
            }
        }
        return sb.toString();
    }

    private String step4(String id) {
        if (id.startsWith(".")) {
            id = id.substring(1);
        }
        if (id.length() > 0 && id.charAt(id.length() - 1) == '.') {
            id = id.substring(0, id.length() - 1);
        }
        return id;
    }

    private String step5(String id) {
        if (id.equals("")) {
            return "a";
        }
        return id;
    }

    private String step6(String id) {
        if (id.length() >= 16) {
            id = id.substring(0, 15);
            if (id.charAt(14) == '.') {
                return id.substring(0, 14);
            }
        }
        return id;
    }

    private String step7(String id) {
        if (id.length() <= 2) {
            char target = id.charAt(id.length() - 1);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < id.length(); i++) {
                sb.append(id.charAt(i));
            }
            while(sb.length() < 3) {
                sb.append(target);
            }
            return sb.toString();
        }
        return id;
    }
}
