package Greedy.백준2141번_우체국;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<House> houses;
    static int N;
    static long entire;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        houses = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int humans = Integer.parseInt(st.nextToken());
            houses.add(new House(idx, humans));
        }
        houses.sort(Comparator.comparingInt(h -> h.idx));

        for (House house : houses) {
            entire += house.humans;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer integer : map.keySet()) {
            
        }

        long cnt = 0;
        int result = 0;
        for (int i = 0; i < N; i++) {
            House house = houses.get(i);
            cnt += house.humans;
            if (cnt >= (entire + 1) / 2) {
                result = house.idx;
                break;
            }
        }
        System.out.println(result);
    }

    static class House {
        int idx, humans;

        public House(int idx, int humans) {
            this.idx = idx;
            this.humans = humans;
        }
    }
}
