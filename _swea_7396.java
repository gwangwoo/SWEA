import java.io.*;
import java.util.*;
public class _swea_7396 {
	static int N,M;
	static char map[][];
	static String res;
	static int dr[] = {1,0};
	static int dc[] = {0,1};
	static boolean check[][];
	static void bfs(int sr, int sc, String sstr) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(sr,sc,sstr));
		check[sr][sc] = true;
		while(!pq.isEmpty()) {
			Point p = pq.poll();
			int r = p.r;
			int c = p.c;
			String str = p.str;
			
			if(r == N-1 && c == M-1) {
				if(res != "" && res.compareTo(str) > 0) {
					res = str;
				}else if(res == "") res = str;
				return;
			}
			
			for(int k = 0 ; k < 2 ; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || check[nr][nc]) continue;
				pq.add(new Point(nr,nc,str+map[nr][nc]));
				check[nr][nc] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++) {
			res = "";
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			check = new boolean[N][M];
			for(int i = 0 ; i < N ; i++) {
				String str = br.readLine();
				for(int j = 0 ; j < M ; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			bfs(0,0,map[0][0]+"");
			System.out.println("#"+tc + " " + res);
		}
	}
	static class Point implements Comparable<Point>{
		int r,c;
		String str;
		Point(int r,int c,String str) {
			this.r =r ;
			this.c = c;
			this.str = str;
			
		}
		@Override
		public int compareTo(Point target) {
			return this.str.compareTo(target.str); 
		}
	}
}
