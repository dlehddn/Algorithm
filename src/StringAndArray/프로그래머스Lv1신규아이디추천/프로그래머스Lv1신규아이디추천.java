package StringAndArray.프로그래머스Lv1신규아이디추천;

class Solution {
    public String solution(String new_id) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < new_id.length(); i++) {    // 소문자 치환
            if(new_id.charAt(i) >= 'A' && new_id.charAt(i) <= 'Z') {
                sb.append((char)((int)new_id.charAt(i) + 32));
            }
            else sb.append(new_id.charAt(i));
        }
        String phase1 = sb.toString();

        StringBuffer sb_p1 = new StringBuffer();   // 필요없는 문자 제거 단계
        for(int i = 0; i < phase1.length(); i++) {
            if(phase1.charAt(i) >= 'a' && phase1.charAt(i) <= 'z') {
                sb_p1.append(phase1.charAt(i));
            }
            if(phase1.charAt(i) >= '0' && phase1.charAt(i) <= '9') {
                sb_p1.append(phase1.charAt(i));
            }
            if(phase1.charAt(i) == '-' || phase1.charAt(i) == '_' || phase1.charAt(i) == '.') {
                sb_p1.append(phase1.charAt(i));
            }
        }
        String phase2 = sb_p1.toString();

        StringBuffer sb_p2 = new StringBuffer();
        sb_p2.append(phase2.charAt(0));
        for(int i = 1; i < phase2.length(); i++) {
            if(phase2.charAt(i) != '.') {
                sb_p2.append(phase2.charAt(i));
            }
            if(phase2.charAt(i) == '.' && phase2.charAt(i - 1) != '.') {
                sb_p2.append(phase2.charAt(i));
            }
        }
        String phase3 = sb_p2.toString();
        String phase4 = removeDot(phase3);

        String phase5 = "";
        if(phase4.length() >= 16) {
            phase4 = phase4.substring(0, 15);
            phase5 = removeDot(phase4);
        }
        else if(phase4.length() < 3) {
            if(phase4.length() == 1){
                phase5 = phase4 + phase4 + phase4;
            }
            if(phase4.length() == 2){
                phase5 = phase4 + phase4.charAt(1);
            }
        }
        else phase5 = phase4;
        return phase5;
    }

    private String removeDot(String new_id) {
        if(new_id.length() > 1) {
            if(new_id.charAt(0) == '.') new_id = new_id.substring(1);
            if(new_id.charAt(new_id.length() - 1) == '.') new_id = new_id.substring(0, new_id.length() - 1);
        }
        else if(new_id.charAt(0) == '.') {
            new_id = "a";
        }
        return new_id;
    }
}