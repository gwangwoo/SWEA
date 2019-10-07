import java.util.*;
import java.io.*;

public class _swea_5643 {
	static int N,M;
	static ArrayList<Integer>[] arl;
	static ArrayList<Integer>[] brl;
	static boolean check[];

	static int dfs(ArrayList<Integer> list[], int idx) {
		int res = 0;
		check[idx] = true;
		for(int y : list[idx]) {
			if(check[y]) continue;
			res += dfs(list,y) + 1;
		}
		return res;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			arl = new ArrayList[N+1];
			brl = new ArrayList[N+1];
			check = new boolean[N+1];
			for(int i = 1 ; i <= N ; i++) {
				arl[i] = new ArrayList<>();
				brl[i] = new ArrayList<>();
			}
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				arl[u].add(v);
				brl[v].add(u);
			}
			int arr[][] = new int[N+1][N+1];
			for(int i = 1 ; i <= N ; i++) {
				Arrays.fill(check, false);
				int val1 = dfs(arl,i);
				Arrays.fill(check, false);
				int val2 = dfs(brl,i);
				arr[i][0] = val1;
				arr[i][1] = val2;
			}

			int res = 0;
			for(int i = 1 ; i <= N ; i++) {
				if(arr[i][0] + arr[i][1] == N-1) res++;
			}
			System.out.println("#"+tc + " " + res);
		}

	}
}
