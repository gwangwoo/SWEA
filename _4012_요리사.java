import java.util.*;

public class _4012_요리사 {
	static int map[][];
	static int check[];
	static int N;
	static int min;

	static int calc(ArrayList<Integer> a) {
		int tmp = 0;
		for(int i = 0 ; i < N/2 ; i++) {
			for(int j = 0; j < N/2 ; j++) {
				if(i==j) continue;
				tmp += map[a.get(i)][a.get(j)];
			}
		}
		return tmp;
	}

	static void dfs(int v,int n) {
		check[v] = 1;
		if(n == N/2) {
			ArrayList<Integer> arr1 = new ArrayList<>();
			ArrayList<Integer> arr2 = new ArrayList<>();
			for(int i = 0 ; i < N ; i++) {
				if(check[i] == 1) arr1.add(i);
				else arr2.add(i);
			}
			int val = Math.abs(calc(arr1) - calc(arr2));
			min = Math.min(min, val);
		}else {
			for(int i = v+1 ; i < N ; i++) {
				dfs(i,n+1);
			}
		}
		check[v] = 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int x = 1 ; x <= tc ; x++) {
			N = sc.nextInt();
			map = new int[N][N];
			check = new int[N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			min = Integer.MAX_VALUE;
			dfs(0,1);
			System.out.println("#"+x + " " + min);
		}

	}

}
