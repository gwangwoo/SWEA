import java.io.*;
import java.util.*;

public class _swea_7234_안전기지 {
	static int N,B;
	static int map[][];
	static int dist[][];
	static int dr[] = {-1,-2,0,0,1,2,0,0};
	static int dc[] = {0,0,1,2,0,0,-1,-2};
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T ; tc++) {
			N = sc.nextInt();
			B = sc.nextInt();
			map = new int[N][N];
			dist = new int[N][N];
			for(int i = 0 ; i  < B ; i++) {
				int r= sc.nextInt()-1;
				int c= sc.nextInt()-1;
				map[r][c] = (int)(-1e9);
				
				// install
				for(int k = 0 ; k < 8 ; k++) {
					int nr = r+ dr[k];
					int nc = c+ dc[k];
					if(nr < 0 || nr >= N || nc <0 || nc >= N) continue;
					dist[nr][nc]++;
				}
			}
			
			int res = 0;
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					res = Math.max(dist[i][j], res);
				}
			}
			System.out.println("#"+tc + " " + res);
			
		}
		
	}

}
