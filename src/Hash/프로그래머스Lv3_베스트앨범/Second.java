package Hash.프로그래머스Lv3_베스트앨범;

import java.util.*;

class Second {
    static Map<String, Integer> playCounts;
    static Map<String, List<Music>> board;
    static List<Integer> result;

    public int[] solution(String[] genres, int[] plays) {
        playCounts = new HashMap<>();
        board = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {
            List<Music> list = board.computeIfAbsent(genres[i], k -> new ArrayList<>());
            list.add(new Music(plays[i], i));
            playCounts.put(genres[i], playCounts.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<Genre> list = new ArrayList<>();
        for (String key: playCounts.keySet()) {
            list.add(new Genre(key, playCounts.get(key)));
        }
        list.sort((g1, g2) -> g2.totalPlay - g1.totalPlay);

        result = new ArrayList<>();
        for (Genre g: list) {
            List<Music> musics = board.get(g.name);
            musics.sort((m1, m2) -> {
               if (m1.play != m2.play) {
                   return m2.play - m1.play;
               } else {
                   return m1.idx - m2.idx;
               }
            });
            result.add(musics.get(0).idx);
            if (musics.size() >= 2) {
                result.add(musics.get(1).idx);
            }
        }

        return result.stream()
            .mapToInt(n -> n)
            .toArray();
    }

    static class Genre {
        String name;
        int totalPlay;

        public Genre(String name, int totalPlay) {
            this.name = name;
            this.totalPlay = totalPlay;
        }
    }

    static class Music {
        int play, idx;

        public Music(int play, int idx) {
            this.play = play;
            this.idx = idx;
        }
    }
}
