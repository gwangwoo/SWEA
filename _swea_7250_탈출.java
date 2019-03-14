import java.io.*;
import java.util.*;

public class _swea_7250_탈출 {
	static int scat[][][];	// scat의 방문좌표.
	static int enemy[][];	// 악당의 방문좌표.
	static int fir[][];		// 불이 퍼지는 방문좌표.
	static char map[][];
	static int dr[] = {-1,0,1,0};
	static int dc[] = {0,1,0,-1};
	static int N,M,K;
	static int sr,sc;	// scat의 시작점 r,c
	static int vr,vc;	// 악당의 시작점 r,c 
	static Queue<Point> firq = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			scat = new int[N][M][4]; // r,c,dir
			enemy = new int[N][M];
			fir = new int[N][M];
			for(int i = 0 ; i < N ; i++) {
				String str = br.readLine();
				for(int j = 0 ; j < M ; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == 'S') {
						sr = i; sc = j;
						for(int x = 0 ; x < 4 ; x++) scat[i][j][x] = 1;
					}else if(map[i][j] == 'F') {
						firq.add(new Point(i,j,0));
						fir[i][j] = 1;	// 불의 시작점 기록.
						for(int x = 0 ; x < 4 ; x++) scat[i][j][x] = -1;	// 불의 위치는 스캇이 방문할수없다.
					}else if(map[i][j] == 'V') {
						vr = i; vc = j;
						enemy[i][j] = 1;	// 악당의 시작점기록.
					}else if(map[i][j] == 'X') {
						enemy[i][j] = -1;	// 악당은 'x' 벽을 갈수없기에 갈수없다를 -1로 기록.
						for(int x = 0 ; x < 4 ; x++) scat[i][j][x] = -1; // 스캇또한 위와 동일.
					}else if(map[i][j] == 'W') {
						enemy[i][j] = -1;	// 스캇은 갈수 있을수 있으니 냅두고, 악당은 못가니까 -1 기록.
					}
				}
			}

			Queue<Scat_Point> sq = new LinkedList<>();
			Queue<Point> eq = new LinkedList<>();
			sq.add(new Scat_Point(sr,sc,K,0));
			eq.add(new Point(vr,vc,0));

			// 불 가즈아.
			int ret = 0;
			int time = 0;
			boolean scat_flag = false;
			boolean enemy_flag = false;
			while(!sq.isEmpty()) {
				int firq_size = firq.size();
				while(firq_size-- > 0) {
					Point p = firq.poll();
					int r = p.r;
					int c = p.c;

					for(int k = 0 ; k < 4 ; k++) {
						int nr = r + dr[k];
						int nc = c + dc[k];
						if(nr < 0 || nr >= N || nc < 0 || nc >= M || fir[nr][nc] != 0 || map[nr][nc] == 'W' || map[nr][nc] == 'X' || map[nr][nc]== 'E') continue;
						firq.add(new Point(nr,nc,0));
						fir[nr][nc] = 1;
						for(int x = 0 ; x < 4 ; x++) scat[nr][nc][x] = -2;
						map[nr][nc] = 'F';
					}
				}
				// 악당 가즈아.
				int eq_size = eq.size();
				while(eq_size-- > 0) {
					Point p = eq.poll();
					int r = p.r;
					int c = p.c;
					for(int k = 0 ; k < 4 ; k++) {
						int nr = r + dr[k];
						int nc = c + dc[k];
						if(nr < 0 || nr >= N || nc < 0 || nc >= M || enemy[nr][nc] != 0 || map[nr][nc] == 'W' || map[nr][nc] == 'X' ) continue;
						if(map[nr][nc] == 'E') {
							enemy_flag = true;
							break;
						}						
						eq.add(new Point(nr,nc,0));
						enemy[nr][nc] = 1;
					}
				}
				// 스캇 가즈아.
				int sq_size = sq.size();
				while(sq_size-- > 0) {
					Scat_Point p = sq.poll();
					int r = p.r;
					int c = p.c;
					int cnt = p.cnt;
					int dir = p.dir;

					for(int k = 0 ; k <4 ; k++) {
						int nr = r + dr[k];
						int nc = c + dc[k];
						if(nr < 0 || nr >= N || nc < 0 || nc >= M || scat[nr][nc][k] != 0 || map[nr][nc] == 'X' || map[nr][nc] == 'F') continue;
						if(map[nr][nc] == 'A') {
							sq.add(new Scat_Point(nr,nc,K,dir));
							scat[nr][nc][dir] = 1;
						}else if(map[nr][nc] == 'W' && cnt > 0) {
							sq.add(new Scat_Point(nr,nc,cnt-1,dir));
							scat[nr][nc][dir] = 1;
						}else if(map[nr][nc] == 'E') {
							scat_flag = true;
							break;
						}
					}
				}
				time++;
				if(enemy_flag) {
					ret = -1;
					break;
				}
				if(scat_flag) {
					ret = time;
					break;
				}
			}
			System.out.println("#"+tc + " " + ret);
			
		}

	}
	static class Point {
		int r,c,cnt;
		Point(int r,int c,int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static class Scat_Point {
		int r,c,cnt,dir;
		Scat_Point(int r,int c,int cnt,int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
}
