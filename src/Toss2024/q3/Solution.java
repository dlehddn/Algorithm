package Toss2024.q3;

import java.util.*;
import java.util.regex.Pattern;

class Solution {
    public boolean solution(String amountText) {
        String regex = "^[0-9,]+$";
        if (!Pattern.matches(regex, amountText)) {
            return false;
        }

        if (amountText.length() != 1 && amountText.charAt(0) == '0') {
            return false;
        }

        regex = "^\\d{1,3}(,\\d{3})*$";
        if (amountText.contains(",") && !Pattern.matches(regex, amountText)) {
            return false;
        }

        return true;
    }
}
