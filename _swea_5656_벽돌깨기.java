import java.util.*;

public class _swea_5656_벽돌깨기 {
	static int map[][];
	static final int dr[] = {-1,0,1,0};
	static final int dc[] = {0,1,0,-1};
	static int N,W,H;
	static int res = 0;

	
	static int find(int[][] map) {
		int val = 0;
		for(int i = 0 ; i < H ; i++) {
			for(int j = 0 ; j < W ; j++) {
				if(map[i][j] != 0) val++;
			}
		}
		return val;
	}
	
	static void reback(int[][] now , int[][] target) {
		for(int i = 0 ; i < H ; i++) {
			for(int j = 0 ; j < W ; j++) {
				target[i][j] = now[i][j];
			}
		}
	}
	
	static void drop(int r,int c,int map[][]) {
		for(int i = r-1 ; i >= 0 ; i--) {
			if(map[i][c] != 0) {
				for(int j = i; j >= 0 ; j--) {
					map[r--][c] = map[j][c];
					map[j][c] = 0;
				}
				return;
			}
		}
	}
	
	static boolean topCheck(int r,int c,int[][] map) {
		for(int i = r-1 ; i >= 0 ; i--) {
			if(map[i][c] != 0) return true;
		}
		return false;
	}
	
	static void down(int[][] map) {
		for(int i = H-1 ; i >= 0 ; i--) {
			for(int j = 0 ; j < W ; j++) {
				if(map[i][j] == 0) {
					if(topCheck(i,j,map)) {
						drop(i,j,map);
					}else {
						continue;
					}
				}
			}
		}
	}
	
	static void explor(int r,int c,int val,int [][] map) {
		map[r][c] = 0;
		if(val == 1) return;
		val--;
		for(int k = 0 ; k < 4 ; k++) {
			for(int i = 1 ; i <= val ; i++) {
				int nr = r + dr[k]*i;
				int nc = c + dc[k]*i;
				if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
				if(map[nr][nc] != 0) {
					explor(nr,nc,map[nr][nc],map);
				}
			}
		}
	}

	static void dfs(int cnt, int[][] map) {
		if(cnt == N) {
			int val = find(map);
			if(val < res) res = val;
			return;
		}
		// 저장.
		int tmp[][] = new int[H][W];
		for(int i = 0 ; i < H ; i++) {
			for(int j = 0 ; j < W ; j++) {
				tmp[i][j] = map[i][j];
			}
		}

		for(int i = 0 ; i < W ; i++) {
			for(int j = 0; j < H ; j++) {
				if(map[j][i] != 0) {
					explor(j,i,map[j][i],map);
					break;
				}
			}
			down(map);
			dfs(cnt+1,map);
			reback(tmp,map);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1 ; tc <= T ; tc++) {
			res =Integer.MAX_VALUE;
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			map = new int[H][W];
			for(int i = 0 ; i < H ; i++) {
				for(int j = 0 ; j < W ; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			dfs(0,map);
			System.out.println("#"+tc + " " +res);
		}
	}
}
