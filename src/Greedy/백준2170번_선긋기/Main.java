package Greedy.백준2170번_선긋기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Line> lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        lines = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines.add(new Line(s, e));
        }
        lines.sort((l1, l2) -> {
            if (l1.start != l2.start) {
                return l1.start - l2.start;
            } else {
                return l1.end - l2.end;
            }
        });
        int result = 0;

        int lastStart = Integer.MIN_VALUE;
        int lastEnd = Integer.MIN_VALUE;
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            if (line.start > lastEnd) {
                result += lastEnd - lastStart;
                lastStart = line.start;
                lastEnd = line.end;
            } else {
                lastEnd = Math.max(lastEnd, line.end);
            }
            if (i == lines.size() - 1) {
                result += lastEnd - lastStart;
            }
        }
        System.out.println(result);
    }

    static class Line {
        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
