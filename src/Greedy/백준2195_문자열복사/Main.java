package Greedy.백준2195_문자열복사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 11:04 start
// 11:45 end
// -> 41m 소요
public class Main {
    static String S, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        P = br.readLine();

        StringBuilder sb = new StringBuilder();
        int startPIdx = 0;
        int cnt = 0;
        while (!sb.toString().equals(P)) {
            cnt++;
            String maxStr = "";
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == P.charAt(startPIdx)) {
                    String tmp = getMaxPartition(i, startPIdx);
                    if (maxStr.length() < tmp.length()) {
                        maxStr = tmp;
                    }
                }
            }
            startPIdx += maxStr.length();
            sb.append(maxStr);
        }
        System.out.println(cnt);
    }

    static String getMaxPartition(int sStartIdx, int pStartIdx) {
        for (int i = 1; i + pStartIdx <= P.length() && i + sStartIdx <= S.length(); i++) {
            if (i + sStartIdx == S.length()) {
                return S.substring(sStartIdx, S.length());
            }
            if (i + pStartIdx == P.length()) {
                return S.substring(sStartIdx, sStartIdx + i);
            }
            if (S.charAt(sStartIdx + i) != P.charAt(pStartIdx + i)) {
                String substring = P.substring(pStartIdx, pStartIdx + i);
                return substring;
            }
        }
        return null;
    }
}
