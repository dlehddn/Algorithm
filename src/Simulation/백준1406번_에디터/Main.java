package Simulation.백준1406번_에디터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Character> list;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        list = new LinkedList<>();
        for (int i = 0; i < line.length(); i++) {
            list.add(line.charAt(i));
        }
        M = Integer.parseInt(br.readLine());

        int cursor = list.size();
        StringTokenizer st;

        ListIterator<Character> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String first = st.nextToken();

            if (first.equals("L")) {
                if (iterator.hasPrevious()) {
                    System.out.println(iterator.previous());
                    System.out.println(list);
                }
            } else if (first.equals("D")) {
                if (iterator.hasNext()) {
                    System.out.println(iterator.next());
                System.out.println(list);

                }
            } else if (first.equals("B")) {
                if (iterator.hasPrevious()) {
                    System.out.println(iterator.previous());
                    iterator.remove();
                System.out.println(list);

                }
            } else if (first.equals("P")) {
                Character add = st.nextToken().charAt(0);
                iterator.add(add);
                System.out.println(list);

            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : list) {
            sb.append(character);
        }
        System.out.println(sb.toString());
    }
}
