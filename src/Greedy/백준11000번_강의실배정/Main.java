package Greedy.백준11000번_강의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, result;
    static PriorityQueue<Meeting> beforeLecture, inLecture;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        beforeLecture = new PriorityQueue<>((l1, l2) -> {
            if (l1.start != l2.start) {
                return l1.start - l2.start;
            } else {
                return l1.end - l2.end;
            }
        });
        inLecture = new PriorityQueue<>((l1, l2) -> {
            if (l1.end != l2.end) {
                return l1.end - l2.end;
            } else {
                return l1.start - l2.start;
            }
        });
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            beforeLecture.add(new Meeting(s, e));
        }
        inLecture.add(beforeLecture.poll());
        result = 1;
        while (!beforeLecture.isEmpty()) {
            Meeting poll = beforeLecture.poll();
            if (poll.start >= inLecture.peek().end) {
                inLecture.poll();
                inLecture.add(poll);
            } else {
                inLecture.add(poll);
                result = inLecture.size();
            }
        }
        System.out.println(result);
    }

    static class Meeting {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
