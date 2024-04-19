package StringAndArray.백준2607번_비슷한단어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static Map<Character, Integer> initMap;

    static int N, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        initMap = new HashMap<>();
        String st = br.readLine();
        for (int i = 0; i < st.length(); i++) {
            initMap.put(st.charAt(i), initMap.getOrDefault(st.charAt(i), 0) + 1);
        }

        for (int i = 0; i < N - 1; i++) {
            check(br.readLine());
        }
        System.out.println(result);

    }

    private static void check(String st) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character character : initMap.keySet()) {
            map.put(character, initMap.get(character));
        }

        int up = 0;
        int down = 0;

        for (int i = 0; i < st.length(); i++) {
            if (map.containsKey(st.charAt(i))) {
                if (map.get(st.charAt(i)) > 1) {
                    map.put(st.charAt(i), map.get(st.charAt(i)) - 1);
                } else if (map.get(st.charAt(i)) == 1) {
                    map.remove(st.charAt(i));
                }
            } else {
                up++;
            }
        }
        down = map.size();

        if ((up == 0 && down == 0) || (up == 0 && down == 1) || (up == 1 && down == 0) || (up == 1 && down == 1)) {
            result++;
        }
    }
}
