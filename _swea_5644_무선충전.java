import java.util.*;

public class _swea_5644_무선충전 {
	static int map[][][];
	static int A_road[];
	static int B_road[];
	static int N,M;
	static final int dr[] = {0,-1,0,1,0};
	static final int dc[] = {0,0,1,0,-1};
	static ArrayList<BC> bc;
	static int A_sum[],B_sum[],Total_sum;


	static void go(int A_c,int A_r,int B_c,int B_r,int index) {
		int a_c = A_c;
		int a_r = A_r;
		int b_c = B_c;
		int b_r = B_r;
		int idx = index;
		for(int x = 0 ; x <= N ; x++) {
//			System.out.println(a_c + " " + a_r);
//			System.out.println(b_c + " " + b_r);
//			System.out.println();
			// A와 B가 무엇을 밟고있는지 검색.
			ArrayList<Integer> A_check = new ArrayList<>();
			ArrayList<Integer> B_check = new ArrayList<>();
			for(int i = 0 ; i < M ; i++) {
				if(map[i][a_c][a_r] != 0) A_check.add(i);
				if(map[i][b_c][b_r] != 0) B_check.add(i);
			}

			// A_size와 B_size가  0인경우
			if(A_check.size() == 0 && B_check.size() == 0);

			// A_size만 0인 경우 B값만 갱신.
			else if(A_check.size() == 0) {
				for(int i = 0; i < B_check.size(); i++) {
					B_sum[x] = Math.max(B_sum[x], bc.get(B_check.get(i)).perfo);
				}
				// B_size만 0인 경우 A값만 갱신.
			}else if(B_check.size() == 0) {
				for(int i = 0; i < A_check.size(); i++) {
					A_sum[x] = Math.max(A_sum[x], bc.get(A_check.get(i)).perfo);
				}
			}
			else{

				// 밟는거 같은거 찾기. A와 B 둘다 size가 1이상.
				boolean choice = false;
				for(int i = 0 ; i < A_check.size() ; i++) {
					int now = A_check.get(i);
					for(int j = 0 ; j < B_check.size() ; j++) {
						int target = B_check.get(j);
						if(now == target) choice = true;
					}
				}
//				System.out.println(x+ " " +choice);
				// 2가지로 나뉨.. 1 . 공통으로 밟는게 없다면 그냥 최대값만 찾아준다.  2. 공통으로 밟는게 있다면 2로 나누어주면서 최대값을 찾아줘야함.
				if(!choice) {
					for(int i = 0; i < A_check.size(); i++) {
						A_sum[x] = Math.max(A_sum[x], bc.get(A_check.get(i)).perfo);
					}
					for(int i = 0; i < B_check.size(); i++) {
						B_sum[x] = Math.max(B_sum[x], bc.get(B_check.get(i)).perfo);
					}
				}else {
					int MAX = 0;
					int A_idx = -1,B_idx = -1;
//					System.out.println(A_check.size() + " " + B_check.size());
					for(int i = 0 ; i < A_check.size() ; i++) {
						int A_choice = A_check.get(i);
						for(int j = 0 ; j < B_check.size() ; j++) {
							int B_choice = B_check.get(j);
//							System.out.println(MAX + " " + A_choice + " " + B_choice);
							if(A_choice == B_choice) {
								if(MAX < (bc.get(A_choice).perfo + bc.get(B_choice).perfo)/2) {
									MAX = (bc.get(A_choice).perfo + bc.get(B_choice).perfo)/2;
									A_idx = A_choice;
									B_idx = A_choice;
								}
							}else if(MAX < (bc.get(A_choice).perfo + bc.get(B_choice).perfo)) {
								MAX = (bc.get(A_choice).perfo + bc.get(B_choice).perfo);
								A_idx = A_choice;
								B_idx = B_choice;
							}
						}
					}
//					System.out.println("요기" + A_idx + " " + B_idx);
					if(A_idx == B_idx) {
						A_sum[x] = bc.get(A_idx).perfo/2;
						B_sum[x] = bc.get(B_idx).perfo/2;
					}else{
						A_sum[x] = bc.get(A_idx).perfo;
						B_sum[x] = bc.get(B_idx).perfo;
					}
				}
			}
			if(idx == N) break;
			// 방향 설정
			switch(A_road[idx]) {
			case 1:
				a_c = a_c + dc[1];
				a_r = a_r + dr[1];
				break;
			case 2:
				a_c = a_c + dc[2];
				a_r = a_r + dr[2];
				break;
			case 3:
				a_c = a_c + dc[3];
				a_r = a_r + dr[3];
				break;
			case 4:
				a_c = a_c + dc[4];
				a_r = a_r + dr[4];
				break;
			}
			// B 방향 설정
			switch(B_road[idx]) {
			case 1:
				b_c = b_c + dc[1];
				b_r = b_r + dr[1];
				break;
			case 2:
				b_c = b_c + dc[2];
				b_r = b_r + dr[2];
				break;
			case 3:
				b_c = b_c + dc[3];
				b_r = b_r + dr[3];
				break;
			case 4:
				b_c = b_c + dc[4];
				b_r = b_r + dr[4];
				break;
			}
			idx = idx+1;
		}
	}

	static void bfs(int index, int c,int r, int cover, int perfo) {
		boolean visited[][] = new boolean[10][10];
		Queue<Point> q = new LinkedList<>();
		visited[c][r] = true;
		map[index][c][r] = perfo;
		q.add(new Point(c,r,0));
		while(!q.isEmpty()) {
			Point p = q.poll();
			c = p.c;
			r = p.r;
			int cnt = p.cnt;

			if(cnt == cover) continue;

			for(int k = 1; k < 5 ; k++) {
				int nc = c + dc[k];
				int nr = r + dr[k];
				if(nc < 0 || nc >= 10 || nr < 0 || nr >= 10) continue;
				if(!visited[nc][nr]) {
					map[index][nc][nr] = perfo;
					visited[nc][nr] = true;
					q.add(new Point(nc,nr,cnt+1));
				}
			}
		}
	}

	static void makeMap() {
		for(int i = 0 ; i < bc.size() ; i++) {
			bfs(i,bc.get(i).c , bc.get(i).r , bc.get(i).cover , bc.get(i).perfo);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T ; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			A_road = new int[N];
			B_road = new int[N];
			map = new int[M][10][10];
			for(int i = 0 ; i < N ; i++) {
				A_road[i] = sc.nextInt();
			}
			for(int i = 0; i < N ; i++) {
				B_road[i] = sc.nextInt();
			}
			bc = new ArrayList<>();
			for(int i = 0 ; i < M ; i++) {
				bc.add(new BC(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt(),sc.nextInt()));
			}
			// 입력 끝.

			// 맵 그리기.
			makeMap();

			// 출발.
			A_sum = new int[N+1];
			B_sum = new int[N+1];
			Total_sum = 0;
			go(0,0,9,9,0);
			
			for(int i = 0 ; i <= N ; i++) {
//				System.out.print(A_sum[i] + " ");
				Total_sum += A_sum[i];
			}
//			System.out.println();
			for(int i = 0 ; i <= N ; i++) {
//				System.out.print(B_sum[i] + " " );
				Total_sum += B_sum[i];
			}
//			System.out.println();
			
			System.out.println("#"+tc + " " + Total_sum);

			//			for(int k = 0 ; k < M ; k++) {
			//				for(int i = 0 ; i < 10 ; i++) {
			//					for(int j = 0 ; j < 10 ; j++) {
			//						System.out.print(map[k][j][i] + " ");
			//					}
			//					System.out.println();
			//				}
			//				System.out.println();
			//			}

		}
	}
	static class BC {
		int c,r;
		int cover,perfo;
		BC(int c,int r,int cover,int perfo) {
			this.c = c;
			this.r = r;
			this.cover = cover;
			this.perfo = perfo;
		}
	}
	static class Point {
		int c,r,cnt;
		Point(int c,int r,int cnt) {
			this.c = c;
			this.r = r;
			this.cnt = cnt;
		}
	}
}
