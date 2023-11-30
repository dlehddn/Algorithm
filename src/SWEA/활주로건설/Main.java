package SWEA.활주로건설;

/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.io.*;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Main {

    static int[][] map;
    static int N, X, result;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/SWEA/활주로건설/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            result = 0;
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                colCheck(i);
                rowCheck(i);
            }

            System.out.println("#" + test_case + " " + result);

        }
    }

    static void colCheck(int col) {
        int[] slideArr = new int[N];
        for (int i = 1; i < N; i++) {
            if (map[i][col] == map[i-1][col] || slideArr[i] == 1) {
                continue;
            }
            if(Math.abs(map[i][col] - map[i - 1][col]) > 1) return;
            if (map[i][col] < map[i-1][col]) {
//                System.out.println(row + ", " + i);
                int cnt = 0;
                int tmp = map[i][col];
                boolean ok = false;
                for (int j = i; j < N; j++) {
                    if (map[j][col] == tmp) {
                        cnt++;
                    }
                    if (cnt == X) {
                        ok = true;
                        for (int k = i; k < i + X; k++) {
                            slideArr[k] = 1;
                        }
                        break;
                    }
                    if(map[j][col] != tmp) break;
                }
                if (!ok) return;

            } else if (map[i][col] > map[i-1][col]) {
                int cnt = 0;
                int tmp = map[i - 1][col];
                boolean ok = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (map[j][col] == tmp) {
                        cnt++;
                    }
                    if (slideArr[j] == 1) {
                        return;
                    }
                    if (cnt == X) {
                        ok = true;
                        for (int k = i - 1; k > i - 1 - X; k--) {
                            slideArr[k] = 1;
                        }
                        break;
                    }
                    if(map[j][col] != tmp) break;
                }
                if (!ok) return;
            }
        }
        result++;
    }

    static void rowCheck(int row) {
        int[] slideArr = new int[N];
        for (int i = 1; i < N; i++) {
            if (map[row][i] == map[row][i - 1] || slideArr[i] == 1) {
                continue;
            }
            if(Math.abs(map[row][i] - map[row][i-1]) > 1) return;
            if (map[row][i] < map[row][i - 1]) {
//                System.out.println(row + ", " + i);
                int cnt = 0;
                int tmp = map[row][i];
                boolean ok = false;
                for (int j = i; j < N; j++) {
                    if (map[row][j] == tmp) {
                        cnt++;
                    }
                    if (cnt == X) {
                        ok = true;
                        for (int k = i; k < i + X; k++) {
                            slideArr[k] = 1;
                        }
                        break;
                    }
                    if(map[row][j] != tmp) return;
                }
                if (!ok) return;

            } else if (map[row][i] > map[row][i - 1]) {
                int cnt = 0;
                int tmp = map[row][i - 1];
                boolean ok = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (map[row][j] == tmp) {
                        cnt++;
                    }
                    if (slideArr[j] == 1) {
                        return;
                    }
                    if (cnt == X) {
                        ok = true;
                        for (int k = i - 1; k > i - 1 - X; k--) {
                            slideArr[k] = 1;
                        }
                        break;
                    }
                    if(map[row][j] != tmp) return;
                }
                if (!ok) return;
            }
        }
        result++;
    }

    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }
}