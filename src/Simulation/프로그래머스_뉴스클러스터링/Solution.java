package Simulation.프로그래머스_뉴스클러스터링;

import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        String lowerStr1 = str1.toLowerCase();
        String lowerStr2 = str2.toLowerCase();

        ArrayList<String> union1 = makeUnion(lowerStr1);
        ArrayList<String> union2 = makeUnion(lowerStr2);

        if(union1.size() == 0 && union2.size() == 0) {
            return 65536;
        }

        int crossSize = crossSection(union1, union2);
        int sumSize = union1.size() + union2.size() - crossSize;
        double jaccard = (double) crossSize / sumSize * 65536;

        return (int) jaccard;

    }


    private static ArrayList<String> makeUnion(String str) {
        ArrayList<String> union = new ArrayList<>();
        int N = str.length();
        char first, second;

        for(int i = 0; i < N-1; i++) {
            first = str.charAt(i);
            second = str.charAt(i+1);
            if(Character.isLowerCase(first) && Character.isLowerCase(second)) {
                String data = String.valueOf(first) + String.valueOf(second);
                union.add(data);
            }
        }
        return union;
    }

    private static int crossSection(ArrayList<String> union1, ArrayList<String> union2) {

        if(union1.size() == 0 || union2.size() == 0) {
            return 0;
        }
        int crossSize = 0;
        int size1 = union1.size();
        int size2 = union2.size();

        for(int i = 0; i < size1; i++) {
            for(int j = 0; j < size2; j++) {
                if(union1.get(i).equals(union2.get(j))) {
                    crossSize++;
                    union2.set(j, "");
                    break;
                }
            }
        }
        return crossSize;
    }

}