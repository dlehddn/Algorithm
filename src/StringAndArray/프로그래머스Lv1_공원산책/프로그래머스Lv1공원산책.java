package StringAndArray.프로그래머스Lv1_공원산책;

class Solution {

    // 1. park 2차원 배열로 만들기
    public char[][] makePark2D (String[] park, String[] routes) {
        char[][] park2D = new char[park.length][park[0].length()];
        for(int i = 0; i < park.length; i++) {
            for(int j = 0; j < park[0].length(); j++) {
                park2D[i][j] = park[i].charAt(j);
            }
        }
        return park2D;
    }
    // 2. 출발점 위치(S좌표) 찾기
    public int[] search_S (char[][] park2D) {
        for(int i = 0; i < park2D.length; i++) {
            for(int j = 0; j < park2D[0].length; j++) {
                if(park2D[i][j] == 'S') {
                    int[] s = {i,j};
                    return s;
                }
            }
        }
        return null;
    }
    // 3. 장애물 위치(X좌표들의 모음) 찾기
    public int[][] search_X (char[][] park2D) {
        int count = 0;
        int[][] x_null = {{-1, -1}, {-2,-2}};
        for(int i = 0; i < park2D.length; i++) {
            for(int j = 0; j < park2D[0].length; j++) {
                if(park2D[i][j] == 'X') {
                    count++;
                }
            }
        }
        if(count == 0) return x_null;    //장애물이 없으면, 이동에 문제가 생김 --> 상관없는 초기값 설정

        int [][] x_arr = new int[count][2];
        int index = 0;
        for(int i = 0; i < park2D.length; i++) {
            for(int j = 0; j < park2D[0].length; j++) {
                if(park2D[i][j] == 'X') {
                    int[] x = {i,j};
                    x_arr[index] = x;
                    index++;
                }
            }
        }
        return x_arr;
    }

    // 4. [E 2]처럼 이동 할 때 장애물에 안걸리고 끝에 안걸리면 이동시켜라
    public int[] GoGo (char[][] park2D, int[][] x_arr, int[] s, String[] routes, int r_num) {
        int tmpX = s[1];
        int tmpY = s[0]; // 장애물 마주치는지, 배열을 나가버리는지 확인하는 변수
        int [] s_result = new int[2];

        for(int i = 0; i < x_arr.length; i++) {
            tmpX = s[1];
            tmpY = s[0]; //초기화 안해주면 numMoves가 x_arr.length만큼 곱해짐
            int numMoves = Integer.parseInt(String.valueOf(routes[r_num].charAt(2))); //몇칸 움직여야돼? 의 변수
            for(int j = 0; j < numMoves; j++){
                switch (routes[r_num].charAt(0)) {
                    case 'E' :
                        if((tmpY == x_arr[i][0] && tmpX+1 == x_arr[i][1]) || tmpX+1 == park2D[0].length){
                            s_result = s;
                            return s_result; // 메인에서 결과를 다시 여기로 가져오기 위해 사용
                        }
                        else {
                            tmpX += 1;
                        }
                        break;
                    case 'W' :
                        if((tmpY == x_arr[i][0] && tmpX-1 == x_arr[i][1]) || tmpX-1 == -1){
                            s_result = s;
                            return s_result;
                        }
                        else {
                            tmpX -= 1;
                        }
                        break;
                    case 'S' :
                        if((tmpY+1 == x_arr[i][0] && tmpX == x_arr[i][1]) || tmpY+1 == park2D.length){
                            s_result = s;
                            return s_result;
                        }
                        else {
                            tmpY += 1;
                        }
                        break;
                    case 'N' :
                        if((tmpY-1 == x_arr[i][0] && tmpX == x_arr[i][1]) || tmpY-1 == -1){
                            s_result = s;
                            return s_result;
                        }
                        else {
                            tmpY -= 1;
                        }
                        break;

                    default : break;
                }
            }
        }
        s[0] = tmpY;
        s[1] = tmpX;
        return s;
    }
    // 메인 메소드
    public int[] solution(String[] park, String[] routes) {
        char[][] park2D = makePark2D(park, routes);       // 기존 1차원 park --> 2차원 배열로 생성
        int[] s = search_S(park2D);                       // 시작위치 찾기
        int[][] x_arr = search_X(park2D);                 // 장애물 어디있는지 찾기, 여러개일 수 있으므로 2차원배열로 생성

        int[] s_result = {};                              // 결과 받을 배열 생성
        for(int i = 0; i < routes.length; i++) {
            s_result = GoGo(park2D, x_arr, s, routes, i); // 반복문을 통해 움직이라는 명령 수만큼 GoGo 메소드 실행
        }
        return s_result;
    }
}