import java.io.*;
import java.util.*;
public class _swea_5643_floyd {
	static final int INF = 987654321;
	static int arr[][];
	static int N,M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			arr = new int[N+1][N+1];
			for(int i = 1 ; i <= N ; i++) {
				for(int j = 1 ; j <= N ; j++) {
					if(i == j) arr[i][j] = 0;
					else arr[i][j] = INF;
				}
			}
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				arr[u][v] = 1;
			}
			
			
			for(int k = 1 ; k <= N ; k++) {
				for(int i = 1 ; i <= N ; i++) {
					for(int j = 1 ; j <= N ; j++) {
						arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
					}
				}
			}
			int res = 0;
			for(int i = 1 ; i <= N ; i++) {
				int cnt = 0;
				for(int j = 1 ; j <= N ; j++) {
					if(i == j) continue;
					if(arr[i][j] < INF || arr[j][i] < INF) cnt++;
				}
				if(cnt == N-1) res++;
			}
			System.out.println("#"+tc + " " + res);
		}			
	}
}
