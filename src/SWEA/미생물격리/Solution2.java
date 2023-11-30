package SWEA.미생물격리;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution2 {
	private static ArrayList<Node>[][] coordinate;
	private static int[] dy = {0, -1, 1, 0, 0};
	private static int[] dx = {0, 0, 0, -1, 1};
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("src/SWEA/미생물격리/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ArrayList<Node> set;
		HashSet<Node> removeSet;
		int T = Integer.parseInt(br.readLine());
		int N, M, K, x, y, bio, dir;
		for (int tc = 1; tc <= T; tc++) {
			removeSet = new HashSet<>();
			set = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			coordinate = new ArrayList[N][N];
			/**
			 * 리스트 초기화
			 */
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					coordinate[i][j] = new ArrayList<>();
				}
			}

			/**
			 * 입력으로 배열 만들기
			 */
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				bio = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken());
				Node node = new Node(x, y, bio, dir);
				coordinate[y][x].add(node);
				set.add(node);
			}

//			for (Node node : set) {
//				System.out.println(node.getBio());
////			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(coordinate[i][j].size());
//				}
//				System.out.println();
//			}
//				System.out.println();


			while (M > 0) {
				M--;
				for (int i = 0; i < set.size(); i++) {
					Node node = set.get(i);
					int nextY = node.getCurY() + dy[node.getDir()];
					int nextX = node.getCurX() + dx[node.getDir()];
					coordinate[nextY][nextX].add(node);
					coordinate[node.getCurY()][node.getCurX()].remove(node);
					node.setCurX(nextX);
					node.setCurY(nextY);
					if(nextY == 0 || nextX == 0 || nextY == N - 1 || nextX == N - 1) {
						node.setBio(node.getBio() / 2);
						if (node.getBio() == 0) {
							coordinate[nextY][nextX].remove(node);
							removeSet.add(node);
						} else {
							switch (node.getDir()) {
								case 1:
									node.setDir(2);
									break;
								case 2:
									node.setDir(1);
									break;
								case 3:
									node.setDir(4);
									break;
								case 4:
									node.setDir(3);
									break;
								default:
									break;
							}
						}
					}
				}
				set.removeAll(removeSet);
				removeSet.clear();
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (coordinate[i][j].size() > 1) {
							Node maxNode = null;
							int maxBio = Integer.MIN_VALUE;
							for (int k = 0; k < coordinate[i][j].size(); k++) {
								Node curNode = coordinate[i][j].get(k);
								if (curNode.getBio() > maxBio) {
									maxBio = curNode.getBio();
									maxNode = curNode;
								}
							}
							int size = coordinate[i][j].size();
							for (int k = 0; k < size; k++) {
								Node curNode = coordinate[i][j].get(k);
								if (!curNode.equals(maxNode)) {
									maxNode.setBio(maxNode.getBio() + curNode.getBio());
									set.remove(curNode);
									removeSet.add(curNode);
								}
							}
							for (Node node : removeSet) {
								int curY = node.getCurY();
								int curX = node.getCurX();
								coordinate[curY][curX].remove(node);
							}

						}
					}
				}
			}
//
			int result = 0;
			for (Node node : set) {
				result += node.getBio();
			}
			System.out.println("#" + tc + " " + result);

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(coordinate[i][j].size());
//				}
//				System.out.println();
//			}

		}
	}

	static class Node {
		private int curX;
		private int curY;
		private int bio;
		private int dir;

		public Node(int curX, int curY, int bio, int dir) {
			this.curX = curX;
			this.curY = curY;
			this.bio = bio;
			this.dir = dir;
		}

		public int getCurX() {
			return curX;
		}

		public void setCurX(int curX) {
			this.curX = curX;
		}

		public int getCurY() {
			return curY;
		}

		public void setCurY(int curY) {
			this.curY = curY;
		}

		public int getBio() {
			return bio;
		}

		public void setBio(int bio) {
			this.bio = bio;
		}

		public int getDir() {
			return dir;
		}

		public void setDir(int dir) {
			this.dir = dir;
		}


	}
}