package Hash.프로그래머스Lv2_오픈채팅방;

import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> memberTable = new HashMap<>();
        ArrayList<String[]> accessList = new ArrayList<>();

        for(String a : record) changeRecord(a, memberTable, accessList);
        String[] answer = new String[accessList.size()];
        makeAnswer(answer, memberTable, accessList);

        return answer;
    }

    private void changeRecord(String record, HashMap<String, String> memberTable, ArrayList<String[]> accessList) {
        String[] input = record.split(" ");

        if(input[0].equals("Enter")) {
            memberTable.put(input[1], input[2]);
            accessList.add(new String[]{"Enter", input[1]});
        }
        if(input[0].equals("Leave")) {
            accessList.add(new String[]{"Leave", input[1]});
        }
        if(input[0].equals("Change")) {
            memberTable.put(input[1], input[2]);
        }
    }

    private void makeAnswer(String[] answer, HashMap<String, String> memberTable, ArrayList<String[]> accessList) {
        for(int i = 0; i < accessList.size(); i++) {
            if(accessList.get(i)[0].equals("Enter")) {
                answer[i] = memberTable.get(accessList.get(i)[1]) + "님이 들어왔습니다.";
            }
            if(accessList.get(i)[0].equals("Leave")) {
                answer[i] = memberTable.get(accessList.get(i)[1]) + "님이 나갔습니다.";
            }
        }
    }
}