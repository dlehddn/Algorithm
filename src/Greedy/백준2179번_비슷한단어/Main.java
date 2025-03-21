package Greedy.백준2179번_비슷한단어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static int N;
    static List<Word> words = new ArrayList<>();
    static List<WordPair> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            words.add(new Word(br.readLine(), i));
        }
        words.sort(Comparator.comparing(w -> w.word));

        int left = 0;
        int right = 1;
        int max = Integer.MIN_VALUE;

        while (left < right && right <= words.size() - 1) {
            String word1 = words.get(left).word;
            String word2 = words.get(right).word;
            int l = sameLength(word1, word2);
            if (l == -1) {
                if (right + 1 <= words.size() - 1) {
                    right++;
                } else {
                    left++;
                    right = left + 1;
                }
                continue;
            }
            if (l >= max) {
                if (l > max) {
                    max = l;
                    result.clear();
                }
                result.add(new WordPair(words.get(left), words.get(right)));
                if (right + 1 <= words.size() - 1) {
                    right++;
                    continue;
                }
            }
            left++;
            right = left + 1;
        }

        result.sort((p1, p2) -> {
            if (p1.word1.idx != p2.word1.idx) {
                return p1.word1.idx - p2.word1.idx;
            } else {
                return p1.word2.idx - p2.word2.idx;
            }
        });

        System.out.println(result.get(0).word1.word);
        System.out.println(result.get(0).word2.word);
    }

    static int sameLength(String word1, String word2) {
        if (word1.equals(word2)) {
            return -1;
        }
        int cnt = 0;
        int min = Math.min(word1.length(), word2.length());

        for (int i = 0; i < min; i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                cnt++;
            } else {
                break;
            }
        }
        return cnt;
    }

    static class WordPair {
        Word word1, word2;

        public WordPair(Word word1, Word word2) {
            this.word1 = word1.idx < word2.idx ? word1 : word2;
            this.word2 = word1.idx > word2.idx ? word1 : word2;
        }
    }

    static class Word {
        String word;
        int idx;

        public Word(String word, int idx) {
            this.word = word;
            this.idx = idx;
        }
    }
}
