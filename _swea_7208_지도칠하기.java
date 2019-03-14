import java.io.*;
import java.util.*;

public class _swea_7208_지도칠하기 {
	static int N;
	static ArrayList<Integer> arl[];
	static String color;
	static int res;

	static int find(boolean visited[], int index) {
		int res = 0;
		for(int y : arl[index]) {
			if(visited[y]) continue;
			if(color.charAt(index-1) == color.charAt(y-1)) {
				res++;
				visited[y] = true;
			}
		}
		return res;
	}


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			arl = new ArrayList[N+1];
			color = "";
			res = Integer.MAX_VALUE;
			for(int i = 1 ; i <= N ; i++) arl[i] = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) color += st.nextToken();

			for(int i =1 ; i <= N ;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1 ; j <= N ; j++) {
					int now = Integer.parseInt(st.nextToken());
					if(now == 0) continue;
					arl[i].add(j);
				}
			}
			int val = 0;
			boolean visited[] = new boolean[N+1];
			for(int i = 1 ; i <= N ; i++) {
				if(!visited[i]) {
					visited[i] = true;
					val += find(visited, i);
				}
			}
			System.out.println("#"+tc + " " +  val);
			
		}

	}

}
