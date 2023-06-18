package StringAndArray.프로그래머스Lv1_바탕화면정리;

class Solution {
    public int[] solution(String[] wallpaper) {
        int arr_size = wallpaper.length;
        int max_north_num = 0;
        int max_south_num = arr_size;
        int max_west_num = wallpaper[0].length();
        int max_east_num = 0;

        for(int i = 0; !wallpaper[i].contains("#"); i++) max_north_num++;
        for(int i = arr_size - 1; !wallpaper[i].contains("#"); i--) max_south_num--;
        //
        for(int i = 0; i < arr_size; i++) {
            int cmp_to_east = wallpaper[i].indexOf("#");
            int cmp_to_west = wallpaper[i].lastIndexOf("#");
            if(wallpaper[i].contains("#") && cmp_to_east < max_west_num) {
                max_west_num = cmp_to_east;
            }
            if(wallpaper[i].contains("#") && cmp_to_west > max_east_num) {
                max_east_num = cmp_to_west;
            }
        }

        int[] answer = {max_north_num, max_west_num, max_south_num, max_east_num + 1};
        return answer;
    }
}