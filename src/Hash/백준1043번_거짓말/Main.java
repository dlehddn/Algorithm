package Hash.백준1043번_거짓말;

/**
 * 해시셋을 사용한 풀이는 실패. 유니온 파인드 사용해야 모든 부모 관계를 다 파악할 수 있음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, M, result, trueFriendSize;
    static HashSet<Integer> set = new HashSet<>();
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            list[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        trueFriendSize = Integer.parseInt(st.nextToken());

        for (int i = 0; i < trueFriendSize; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int loop = Integer.parseInt(st.nextToken());
            HashSet<Integer> tmpSet = new HashSet<>();
            for (int j = 0; j < loop; j++) {
                int cur = Integer.parseInt(st.nextToken());
                tmpSet.add(cur);
                list[i].add(cur);
            }
            HashSet<Integer> retainSet = new HashSet<>(tmpSet);
            retainSet.retainAll(set);
            if (retainSet.size() >= 1) {
                set.addAll(tmpSet);
            }
        }

        for (int i = 0; i < M; i++) {
            boolean contains = false;
            for (Integer n : list[i]) {
                if (set.contains(n)) {
                    contains = true;
                    break;
                }
            }
            if(!contains) result += 1;
        }

        System.out.println(result);
    }
}
