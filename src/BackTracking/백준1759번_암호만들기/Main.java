package BackTracking.백준1759번_암호만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L, C;
    static String[] arr;
    static StringBuilder result;
    static Set<Character> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new String[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken();
        }
        result = new StringBuilder();
        set.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        Arrays.sort(arr);
        backTracking(0, new StringBuilder());
        System.out.println(result.toString());
    }

    static void backTracking(int start, StringBuilder words) {
        if (words.length() == L) {
            if (passCondition(words)) {
                result.append(words).append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            words.append(arr[i]);
            backTracking(i + 1, words);
            words.deleteCharAt(words.length() - 1);
        }
    }

    static boolean passCondition(StringBuilder words) {
//        System.out.println("words = " + words);
        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < words.length(); i++) {
            if (set.contains(words.charAt(i))) {
                vowels++;
            } else {
                consonants++;
            }
        }
//        System.out.println("vowels = " + vowels);
//        System.out.println("consonants = " + consonants);
        if (vowels >= 1 && consonants >= 2) {
            return true;
        } else {
            return false;
        }
    }
}
