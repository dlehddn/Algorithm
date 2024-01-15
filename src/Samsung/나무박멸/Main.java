package Samsung.나무박멸;

import java.util.*;
import java.io.*;
public class Main {
    /**
     * 디버깅 : 대각선 방향으로 뻗어나갈 때 중간에 못가는 상황이면 바로 break;로 끊어야하는데
     * 별 생각없이 continue로 써서 이후까지 탐색하고 있었따.
     */
	static int N, M, K, C;
	static int[][] map;
	static int[][] diedMap;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[] dx4 = {1, -1, -1, 1};
	static int[] dy4 = {1, 1, -1, -1};
	static int result;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		diedMap = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 인터프리터
		int turn = 0;
		while(turn < M) {
			step1_GrowTree();
			step2_SpreadTree();
			step3_RemoveTree();
			turn++;
		}
		System.out.println(result);
	}


	static void step1_GrowTree() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] <= 0) continue;
				int count = 0;
				for(int k = 0; k < 4; k++) {
					int nextY = i + dy[k];
					int nextX = j + dx[k];
					if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) continue;
					if(map[nextY][nextX] > 0) {
						count++;
					}
				}
				if(count > 0) {
					map[i][j] += count;
				}
			}
		}
	}

	static void step2_SpreadTree() {
		int[][] tmp = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				ArrayList<Pair> canSpread = calculateSpread(i, j);
				if(canSpread.size() > 0) {
					for(Pair pair : canSpread) {
						tmp[pair.y][pair.x] += map[i][j] / canSpread.size();
					}
				}
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(tmp[i][j] > 0) {
					map[i][j] = tmp[i][j];
				}
			}
		}
	}

	static ArrayList<Pair> calculateSpread(int y, int x) {
		if(map[y][x] <= 0) return new ArrayList<>();

		ArrayList<Pair> tmp = new ArrayList<>();
		for(int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) continue;
			if(map[nextY][nextX] != 0) continue;
			if(diedMap[nextY][nextX] != 0) continue;
			tmp.add(new Pair(nextY, nextX));
		}

		return tmp;

	}


	static void step3_RemoveTree() {
		Pair selected = new Pair(-1, -1);
		int maxScore = Integer.MIN_VALUE;
		for(int i = N-1; i >= 0; i--) {
			for(int j = N-1; j >= 0; j--) {
				if(map[i][j] <= 0) continue;
				int cnt = map[i][j];
				Pair cur = new Pair(i, j);
				for(int k = 0; k < 4; k++) {
					for(int l = 1; l <= K; l++) {
						int nextY = cur.y + l * dy4[k];
						int nextX = cur.x + l * dx4[k];
						if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) continue;
						if(map[nextY][nextX] <= 0) break;
						cnt += map[nextY][nextX];
					}
				}
				if(cnt >= maxScore) {
					maxScore = cnt;
					selected = new Pair(i, j);
				}
			}
		}
		// 여기까지 오면 우선순위 조건에 맞는, 제초제를 뿌릴 위치를 다 구했다.

		if(selected.y == -1 && selected.x == -1) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 0) {
						selected = new Pair(i, j);
						diedMap[selected.y][selected.x] = C;
						return;
					}
				}
			}
		}
		// 혹시 안정해진 상황 처리 완료
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (diedMap[i][j] > 0) {
					diedMap[i][j] -= 1;
				}
			}
		}
		result += map[selected.y][selected.x];
		diedMap[selected.y][selected.x] = C;
		map[selected.y][selected.x] = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j <= K; j++) {
				int nextY = selected.y + j * dy4[i];
				int nextX = selected.x + j * dx4[i];
				if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) continue;
				if(map[nextY][nextX] == -1) {
					break;
				} else if(map[nextY][nextX] == 0) {
					diedMap[nextY][nextX] = C;
					break;
				} else if(map[nextY][nextX] > 0) {
					result += map[nextY][nextX];
					diedMap[nextY][nextX] = C;
					map[nextY][nextX] = 0;
				}
			}
		}

	}

	static class Pair {
		int y;
		int x;

		public Pair (int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
