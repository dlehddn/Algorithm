package Greedy.백준1931번_회의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static TimeTable[] timeTables;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        timeTables = new TimeTable[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            timeTables[i] = new TimeTable(start, end);
        }

        Arrays.sort(timeTables);

//        for (TimeTable timeTable : timeTables) {
//            System.out.println(timeTable.start + ", " + timeTable.end);
//        }

        int cnt = 0;
        int lastEnd = 0;
        for (int i = 0; i < N; i++) {
            if (timeTables[i].start >= lastEnd) {
                lastEnd = timeTables[i].end;
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static class TimeTable implements Comparable<TimeTable> {
        int start, end;

        public TimeTable(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(TimeTable o) {
            if (this.end == o.end) {
                return this.start - o.start;
            } else {
                return this.end - o.end;
            }
        }
    }
}
