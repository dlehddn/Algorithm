package Greedy.백준1082_방번호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    /**
     * 1. 가장 싼 번호로 쭉 채우기
     * 2. 000000 꼴이라면, 하나 채울 수 있을 때 까지 0 없애주기
     * 3. 맨 앞자리부터 남은 가격에서 채울 수 있는 가장 비싼 순으로 채워주기
     */
    static int N, M;
    static List<Room> rooms;
    static int[] roomPrices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rooms = new ArrayList<>();
        roomPrices = new int[11];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int price = Integer.parseInt(st.nextToken());
            rooms.add(new Room(i, price));
            roomPrices[i] = price;
        }
        rooms.sort((r1, r2) -> {
            if (r1.price != r2.price) {
                return r1.price - r2.price;
            } else {
                return r2.num - r1.num;
            }
        });
        M = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        // 1. 싼거로 다 채우기
        while (rooms.get(0).price <= M) {
            result.append(rooms.get(0).num);
            M -= rooms.get(0).price;
        }
        rooms.remove(0);

        if (isOnlyZero(result)) {
            result = makeGeneralType(result);
        }

        int idx = 0;
        rooms.sort((r1, r2) -> r2.num - r1.num);
        loop:
        while (idx <= result.length() - 1) {
            for (Room room : rooms) {
                int prevPrice = roomPrices[result.charAt(idx) - '0'];
                if (M + prevPrice >= room.price && result.charAt(idx) - '0' < room.num) {
                    result.setCharAt(idx, (char) (room.num + '0'));
                    M = M + prevPrice - room.price;
                    idx++;
                    continue loop;
                }
            }
            break;
        }
        System.out.println(result.toString());
    }

    static StringBuilder makeGeneralType(StringBuilder result) {
        int min = rooms.stream()
                .mapToInt(r -> r.price)
                .min()
                .orElse(Integer.MAX_VALUE);
        while (result.length() > 1) {
//            System.out.println("loop");
            if (M + roomPrices[0] >= min) {
                return result;
            } else {
                result.deleteCharAt(0);
                M += roomPrices[0];
            }
        }
        return result;
    }

    static boolean isOnlyZero(StringBuilder result) {
        int zeroCnt = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '0') {
                zeroCnt++;
            }
        }
        if (zeroCnt == result.length() && zeroCnt != 1) {
            return true;
        }
        return false;
    }

    static class Room {
        int num, price;

        public Room(int num, int price) {
            this.num = num;
            this.price = price;
        }
    }
}
