import java.util.*;
import java.io.*;

public class _swea_2112_보호필름 {
	static int D,W,K;
	static int map[][];
	static int Min;

	static void change(int map[][], int index, int val) {
		for(int i = 0 ; i < W ; i++) {
			map[index][i] = val;
		}
	}

	static boolean check(int map[][]) {
		int res = 0;
		for(int i = 0 ; i < W ; i++) {
			int num = 0;
			for(int j = 1 ; j < D ; j++) {
				if(map[j][i] == map[j-1][i]) num++;
				else {
					if(num >= K-1) break;
					else num = 0;
				}
			}
			if(num >= K-1) res++;
			else return false;
		}
		if(res == W) {
			return true;
		}else {
			return false;
		}
	}

	static void dfs(int map[][], int index, int cnt) {
		if(cnt > Min) return;
		if(check(map)) {
			if(cnt < Min) {
				Min = cnt;
				return;
			}
			return;
		}
		if(index >= D) return;
		
		int tmp[][] = new int[D][W];
		for(int i = 0 ; i < D ; i++) {
			for(int j = 0 ; j < W ; j++) {
				tmp[i][j] = map[i][j];
			}
		}

		// 그 행 패스.
		dfs(tmp,index+1,cnt);
		// 1로 바꾸기
		change(tmp,index,1);
		dfs(tmp,index+1,cnt+1);
		// 0으로 바꾸기.
		for(int i = 0 ; i < D ; i++) {
			for(int j = 0 ; j < W ; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		change(tmp,index,0);
		dfs(tmp,index+1,cnt+1);

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Min = Integer.MAX_VALUE;
			map = new int[D][W];

			for(int i = 0 ; i < D ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < W ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 약품을 투입하지 않고도 성능 검사를 통과하는 경우
			if(check(map) || K == 1) {
				System.out.println("#"+tc + " " + 0);
				continue;
			}
			// gogo
			dfs(map,0,0);
			System.out.println("#"+tc + " " + Min);
		}

	}

}

