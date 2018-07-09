import java.io.*;
import java.util.*;

public class _4008_숫자만들기 {
	static int arr[];
	static int op[] = new int[4];
	static int N;
	static int MAX,MIN;

	static void dfs(int sum , int cnt) {
		if(cnt == N) {
			MAX = Math.max(MAX, sum);
			MIN = Math.min(MIN, sum);
			return;
		}
		for(int i = 0 ; i < 4 ; i++) {
			if(op[i] > 0) {
				op[i]--;
				switch(i) {
				case 0:
					dfs(sum + arr[cnt] , cnt+1);
					break;
				case 1:
					dfs(sum - arr[cnt] , cnt+1);
					break;
				case 2:
					dfs(sum * arr[cnt] , cnt+1);
					break;
				case 3:
					dfs(sum / arr[cnt] , cnt+1);
					break;
				}
				op[i]++;
			}

		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int x = 1; x <= tc ; x++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 4 ; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			MIN = Integer.MAX_VALUE; MAX = Integer.MIN_VALUE;
			dfs(arr[0],1);
			int res = MAX - MIN;
			System.out.println("#"+x + " " + res);
		}
	}
}

