package Kakao._2022.성격유형검사하기;

import java.util.*;
class FirstSolve {

    static HashMap<Character, Integer> board = new HashMap<>();

    public String solution(String[] survey, int[] choices) {
        StringBuilder sb = new StringBuilder();

        board.put('R', 0);
        board.put('T', 0);
        board.put('C', 0);
        board.put('F', 0);
        board.put('J', 0);
        board.put('M', 0);
        board.put('A', 0);
        board.put('N', 0);


        for(int i = 0; i < survey.length; i++) {
            String cur = survey[i];

            if(choices[i] < 4) {
                board.put(cur.charAt(0), board.get(cur.charAt(0)) + 4 - choices[i]);
            } else if(choices[i] == 4) {
                board.put(cur.charAt(0), board.get(cur.charAt(0)));
                board.put(cur.charAt(1), board.get(cur.charAt(1)));
            } else if(choices[i] > 4) {
                board.put(cur.charAt(1), board.get(cur.charAt(1)) + choices[i] - 4);
            }
        }

        if(board.get('R') >= board.get('T')) sb.append("R");
        else if(board.get('R') < board.get('T')) sb.append("T");

        if(board.get('C') >= board.get('F')) sb.append("C");
        else if(board.get('C') < board.get('F')) sb.append("F");

        if(board.get('J') >= board.get('M')) sb.append("J");
        else if(board.get('J') < board.get('M')) sb.append("M");

        if(board.get('A') >= board.get('N')) sb.append("A");
        else if(board.get('A') < board.get('N')) sb.append("N");


        return sb.toString();

    }
}