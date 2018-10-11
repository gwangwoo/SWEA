import java.util.*;

public class _swea_5650_핀볼게임 {
	static int N;
	static int map[][];
	static final int dr[] = {-1,0,1,0};
	static final int dc[] = {0,1,0,-1};
	static ArrayList<Worm> worm6;
	static ArrayList<Worm> worm7;
	static ArrayList<Worm> worm8;
	static ArrayList<Worm> worm9;
	static ArrayList<Worm> worm10;
	static int res;
	static Worm start;

	static void dfs(int r,int c,int k, int point) {
		while(true) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
				if(start.r == r && start.c == c) {
					if(res < point) {
						res = point;
						return;
					}
					return;
				}
				point++;
				k = (k+2)%4;
				
				if(map[r][c] == 0) continue;
				else {
					nr = r;
					nc = c;
				}
			}
			if((start.r == nr && start.c == nc) || map[nr][nc] == -1) {
				if(res < point) {
					res = point;
					return;
				}
				return;
			}
			if(map[nr][nc] == 0) {
				r = nr;
				c = nc;
				continue;
			}
			switch(map[nr][nc]) {
			// 1번블럭;
			case 1:
				r = nr;
				c = nc;
				switch(k) {
				case 2:
					k = 1;
					point++;
					break;
				case 3:
					k = 0;
					point++;
					break;
				case 0:
					k = 2;
					point++;
					break;
				case 1:
					k = 3;
					point++;
					break;
				}
				break;
				// 2번블럭;
			case 2:
				r = nr;
				c = nc;
				switch(k) {
				case 0:
					k = 1;
					point++;
					break;
				case 3:
					k = 2;
					point++;
					break;
				case 1:
					k = 3;
					point++;
					break;
				case 2:
					k = 0;
					point++;					
					break;
				}
				break;
				// 3번블럭;
			case 3:
				r = nr;
				c = nc;
				switch(k) {
				case 0:
					k = 3;
					point++;
					break;
				case 1:
					k = 2;
					point++;
					break;
				case 2:
					k = 0;
					point++;
					break;
				case 3:
					k = 1;
					point++;
					break;
				}
				break;
				// 4번블럭;
			case 4:
				r = nr;
				c = nc;
				switch(k) {
				case 1:
					k = 0;
					point++;
					break;
				case 2:
					k = 3;
					point++;
					break;
				case 0:
					k = 2;
					point++;
					break;
				case 3:
					k = 1;
					point++;
					break;
				}
				break;
				// 5번블럭;
			case 5:
				r = nr;
				c = nc;
				switch(k) {
				case 0:
					k = 2;
					point++;
					break;
				case 1:
					k = 3;
					point++;
					break;
				case 2:
					k = 0;
					point++;
					break;
				case 3:
					k = 1;
					point++;
					break;
				}
				break;
			case 6:
				for(int x = 0 ; x < worm6.size() ; x++) {
					if(worm6.get(x).r == nr && worm6.get(x).c == nc) {
						r = worm6.get(1-x).r;
						c = worm6.get(1-x).c;
						break;
					}
				}
				break;
			case 7:
				for(int x = 0 ; x < worm7.size() ; x++) {
					if(worm7.get(x).r == nr && worm7.get(x).c == nc) {
						r = worm7.get(1-x).r;
						c = worm7.get(1-x).c;
						break;
					}
				}
				break;
			case 8:
				for(int x = 0 ; x < worm8.size() ; x++) {
					if(worm8.get(x).r == nr && worm8.get(x).c == nc) {
						r = worm8.get(1-x).r;
						c = worm8.get(1-x).c;
						break;
					}
				}
				break;
			case 9:
				for(int x = 0 ; x < worm9.size() ; x++) {
					if(worm9.get(x).r == nr && worm9.get(x).c == nc) {
						r = worm9.get(1-x).r;
						c = worm9.get(1-x).c;
						break;
					}
				}
				break;
			case 10:
				for(int x = 0 ; x < worm10.size() ; x++) {
					if(worm10.get(x).r == nr && worm10.get(x).c == nc) {
						r = worm10.get(1-x).r;
						c = worm10.get(1-x).c;
						break;
					}
				}
				break;
			}
		}


	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T ; tc++) {
			worm6 = new ArrayList<>();
			worm7 = new ArrayList<>();
			worm8 = new ArrayList<>();
			worm9 = new ArrayList<>();
			worm10 = new ArrayList<>();

			N = sc.nextInt();
			map = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = sc.nextInt();
					if(6 <=map[i][j] && map[i][j] <= 10) {
						switch(map[i][j]) {
						case 6:
							worm6.add(new Worm(i,j));
							break;
						case 7:
							worm7.add(new Worm(i,j));
							break;
						case 8:
							worm8.add(new Worm(i,j));
							break;
						case 9:
							worm9.add(new Worm(i,j));
							break;
						case 10:
							worm10.add(new Worm(i,j));
							break;
						}
					}
				}
			}
			res = 0;
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(map[i][j] != 0) continue;
					for(int k = 0; k < 4 ; k++) {
						start = new Worm(i,j);
						dfs(i,j,k,0);
					}
				}
			}
			System.out.println("#"+tc + " " + res);

		}
	}
	static class Worm {
		int r,c,dir;
		Worm(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
}
