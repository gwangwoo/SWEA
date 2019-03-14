import java.io.*;
import java.util.*;

public class _swea_7236_저수지의물의총깊이구하기 {
	static int dr[] = {-1,-1,0,1,1,1,0,-1};
	static int dc[] = {0,1,1,1,0,-1,-1,-1};
	static char map[][];
	static int N;
	static ArrayList<Point> cand = new ArrayList<>();;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++) {
			cand.clear();
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for(int i = 0 ; i < N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = st.nextToken().charAt(0);
					if(map[i][j] == 'W') cand.add(new Point(i,j));
				}
			}
			int res = 0;
			for(int i = 0 ; i < cand.size() ; i++) {
				Point now = cand.get(i);
				int r= now.r;
				int c = now.c;
				int val = 0;
				for(int k = 0 ; k < 8 ; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if(map[nr][nc] == 'W') val++;
				}
				if(val == 0 ) val = 1;
				res = Math.max(res, val);
			}
			System.out.println("#"+tc + " " + res);
		}
		
	}
	static class Point {
		int r,c;
		Point(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
}
