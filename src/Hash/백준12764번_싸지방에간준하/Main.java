package Hash.백준12764번_싸지방에간준하;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue<Integer> computers;
    static PriorityQueue<Computer> ends;
    static PriorityQueue<Time> times;
    static int[] results;
    static int N, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        results = new int[N + 1];
        computers = new PriorityQueue<>();
        ends = new PriorityQueue<>(Comparator.comparingInt(n -> n.endTime));
        times = new PriorityQueue<>(Comparator.comparingInt(n -> n.start));

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            times.add(new Time(s, e));
        }

        for (int i = 1; i < N + 1; i++) {
            computers.add(i);
        }

        while (!times.isEmpty()) {
            Time time = times.poll();

            while (!ends.isEmpty() && ends.peek().endTime < time.start) {
                Computer poll = ends.poll();
                computers.add(poll.num);
            }

            int poll = computers.poll();
            if (poll > max) {
                max = poll;
            }
            results[poll]++;

            ends.add(new Computer(poll, time.end));
        }

        System.out.println(max);
        for (int i = 1; i < N + 1; i++) {
            if (results[i] != 0) {
                System.out.print(results[i] + " ");
            } else {
                break;
            }
        }
    }

    static class Computer {
        int num, endTime;

        public Computer(int num, int endTime) {
            this.num = num;
            this.endTime = endTime;
        }
    }

    static class Time {
        int start, end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
