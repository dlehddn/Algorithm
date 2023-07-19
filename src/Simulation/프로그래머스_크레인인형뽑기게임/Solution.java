package Simulation.프로그래머스_크레인인형뽑기게임;

import java.util.*;

class Solution {

    private static Stack<Integer> stack = new Stack<>();
    private static int count = 0;

    public int solution(int[][] board, int[] moves) {
        for(int index : moves) {
            pushOrPop(pick(board, index));
        }
        return count;
    }

    private static int pick(int[][] board, int index) {
        int N = board.length;
        int temp = 0;
        for(int i = 0; i < N; i++) {
            if(board[i][index - 1] != 0) {
                temp = board[i][index - 1];
                board[i][index - 1] = 0;
                return temp;
            }
        }
        return 0;
    }

    private static void pushOrPop(int pickedNum) {
        if(pickedNum == 0) return;

        if(stack.isEmpty()) {
            stack.push(pickedNum);
        }
        else {
            int temp = stack.peek();
            if(temp != pickedNum) {
                stack.push(pickedNum);
            }
            else {
                stack.pop();
                count += 2;
            }
        }
    }

}