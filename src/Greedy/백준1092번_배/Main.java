package Greedy.백준1092번_배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Box> boxes = new ArrayList<>();
    static List<Crane> cranes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cranes.add(new Crane(Integer.parseInt(st.nextToken()), 0));
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxes.add(new Box(Integer.parseInt(st.nextToken())));
        }
        boxes.sort((b1, b2) -> b2.weigh - b1.weigh);
        cranes.sort((c1, c2) -> c2.maxWeigh - c1.maxWeigh);

        if (boxes.get(0).weigh > cranes.get(0).maxWeigh) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < M; i++) {
            Box curBox = boxes.get(i);
            int min = Integer.MAX_VALUE;
            int idx = 0;
            for (int j = 0; j < N; j++) {
                Crane curCrane = cranes.get(j);
                if (curCrane.maxWeigh < curBox.weigh) break;
                if (min > curCrane.cntOfWork) {
                    min = curCrane.cntOfWork;
                    idx = j;
                }
            }
            cranes.get(idx).cntOfWork++;
        }

        System.out.println(cranes.stream()
                .mapToInt(c -> c.cntOfWork)
                .max().orElse(0));
    }

    static class Box {
        int weigh;

        public Box(int weigh) {
            this.weigh = weigh;
        }
    }

    static class Crane {
        int maxWeigh;
        int cntOfWork;

        public Crane(int maxWeigh, int cntOfWork) {
            this.maxWeigh = maxWeigh;
            this.cntOfWork = cntOfWork;
        }
    }
}
