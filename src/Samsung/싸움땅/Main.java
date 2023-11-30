package Samsung.싸움땅;
/**
 * 1. 각 칸은 객체로 만들어야한다.
 * 2. 바라보는 방향으로 이동하는 메소드 만들기. 격자 벗어나면 반대방향으로
 * 3. 이동 후 총이 있으면 획득하고, 이미 총이 있다면 더 공격력이 큰 총으로 교체
 * 4. 이동 후 플레이어가 있으면 싸운다. 총 공격력 비교해서 승, 패 따진다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    private static Node[][] map;
    private static HashMap<Integer, int[]> personMap = new HashMap<>();
    private static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Node[N][N];
        score = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(Integer.parseInt(st.nextToken()));
                map[i][j] = new Node(temp, 0, 0, 0, -1);
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            map[y][x].initPerson(i, s, d);
            personMap.put(i, new int[]{y, x});
        }

        while (K > 0) {
            K--;
            for (int i = 1; i <= M; i++) {
                move(i);
            }
        }


//        for (Node[] a : map) {
//            for (Node b : a) {
//                System.out.print(b.getPersonNum() + " ");
//            }
//            System.out.println();
//        }

        for (int a : score) {
            System.out.print(a + " ");
        }
    }
    private static void move(Integer personNum) {
        int[] cur = personMap.get(personNum);
        int curY = cur[0];
        int curX = cur[1];
        Node curNode = map[curY][curX];
        int dir = curNode.getPersonDir();
        int nextY = curY + dy[dir];
        int nextX = curX + dx[dir];
        if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
            dir += 2;
            if(dir >= 4) {
                dir = dir % 4;
            }
            nextY = curY + dy[dir];
            nextX = curX + dx[dir];
            curNode.setPersonDir(dir);
        }

        if (map[nextY][nextX].getPersonNum() == 0) {
            map[nextY][nextX].addPerson(curNode);
            map[curY][curX].removePerson();
            map[nextY][nextX].editPersonGun();
            personMap.put(personNum, new int[]{nextY, nextX});
        } else {
            fight(map[curY][curX], map[nextY][nextX]);
        }
    }

    private static void fight(Node curNode, Node nextNode) {
        int curPersonPower = curNode.getPersonPower();
        int curPersonGun = curNode.getPersonGun();
        int curTotal = curPersonPower + curPersonGun;
        int nextPersonPower = nextNode.getPersonPower();
        int nextPersonGun = nextNode.getPersonGun();
        int nextTotal = nextPersonPower + nextPersonGun;
        int[] next = personMap.get(nextNode.getPersonNum());
        if (curTotal > nextTotal || curTotal == nextTotal && curPersonPower > nextPersonPower) {
            ArrayList<Integer> tempArr = new ArrayList<>();
            Node temp = new Node(tempArr, 0, 0, 0, -1);
            temp.addPerson(curNode);
            curNode.removePerson();
            moveLoser(nextNode);
            nextNode.addPerson(temp);
            personMap.put(nextNode.getPersonNum(), next);
            winner(nextNode);
            score[nextNode.getPersonNum() - 1] += curTotal - nextTotal;
        } else if (curTotal < nextTotal || curTotal == nextTotal && curPersonPower < nextPersonPower) {
            ArrayList<Integer> tempArr = new ArrayList<>();
            Node temp = new Node(tempArr, 0, 0, 0, -1);
            temp.addPerson(nextNode);
            nextNode.removePerson();
            nextNode.addPerson(curNode);
            personMap.put(nextNode.getPersonNum(), next);
            curNode.removePerson();
            moveLoser(nextNode);
            nextNode.addPerson(temp);
            winner(nextNode);
            score[nextNode.getPersonNum() - 1] += nextTotal - curTotal;
        }

    }

    private static void moveLoser(Node node) {
        node.dropGun();
        int[] cur = personMap.get(node.getPersonNum());
        int curY = cur[0];
        int curX = cur[1];
        int dir = node.getPersonDir();
        int nextY = curY + dy[dir];
        int nextX = curX + dx[dir];

        while (true) {
            if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N || map[nextY][nextX].getPersonNum() != 0) {
                dir++;
                if (dir == 4) {
                    dir = 0;
                }
                nextY = curY + dy[dir];
                nextX = curX + dx[dir];
            } else {
                node.setPersonDir(dir);
                map[nextY][nextX].addPerson(node);
                personMap.put(node.getPersonNum(), new int[]{nextY, nextX});
                map[curY][curX].removePerson();
                if(map[nextY][nextX].getGuns().size() != 0) {
                    map[nextY][nextX].getGun();
                }
                break;
            }
        }
    }

    private static void winner(Node node) {
       node.editPersonGun();
    }


    static class Node {
        private ArrayList<Integer> guns;
        private int personNum; // 0이면 없는거
        private int personPower;
        private int personGun;
        private int personDir;

        public Node(ArrayList<Integer> guns, int personNum, int personPower, int personGun, int personDir) {
            this.guns = guns;
            this.personNum = personNum;
            this.personPower = personPower;
            this.personGun = personGun;
            this.personDir = personDir;
        }

        public void initPerson(int personNum, int personPower, int personDir) {
            this.personNum = personNum;
            this.personPower = personPower;
            this.personDir = personDir;
        }

        public void removePerson() {
            personNum = 0;
            personGun = 0;
            personDir = 0;
            personPower = 0;
        }

        public void addPerson(Node curNode) {
            this.personNum = curNode.getPersonNum();
            this.personGun = curNode.getPersonGun();
            this.personDir = curNode.getPersonDir();
            this.personPower = curNode.getPersonPower();
        }

        public void editPersonGun() {
            guns.add(personGun);
            Collections.sort(guns);
            personGun = guns.get(guns.size() - 1);
            guns.remove(guns.size() - 1);
        }

        public void getGun() {
            Collections.sort(guns);
            personGun = guns.get(guns.size() - 1);
            guns.remove(guns.size() - 1);
        }

        public void dropGun() {
            guns.add(personGun);
            personGun = 0;
        }

        public ArrayList<Integer> getGuns() {
            return guns;
        }

        public int getPersonNum() {
            return personNum;
        }

        public int getPersonPower() {
            return personPower;
        }

        public int getPersonGun() {
            return personGun;
        }


        public int getPersonDir() {
            return personDir;
        }

        public void setPersonDir(int personDir) {
            this.personDir = personDir;
        }
    }
}
