package Toss2024.q1;

import java.util.*;

class Solution {
    public int solution(int[] levels) {
        Arrays.sort(levels);
        int mount = levels.length / 4;
        return mount == 0 ? -1 : levels[levels.length - mount];
    }
}