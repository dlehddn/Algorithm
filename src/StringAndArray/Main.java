package StringAndArray;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String name = "na,mn";

        boolean b = name.startsWith("nam");
        System.out.println(b);
        boolean n = name.contains("n");
        System.out.println(n);
        String replace = name.replace("na", "me");
        int n1 = name.lastIndexOf("n");
        int n2 = name.indexOf("n");
        System.out.println(n1);
        System.out.println(n2);
        String[] split = name.split(",");
        for (String s : split) {
            System.out.println(s);
        }
        String s = name.replaceAll("na", "aa").replaceAll("mn", "bb");
        System.out.println(s);
    }
}
