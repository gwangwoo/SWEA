

import java.util.*;

public class _4014_활주로건설 {
	static int arr[][];
	static int brr[][];
	static int N,X;

	static int check(int a[]) {
		int first = a[0];
		boolean visited[] = new boolean[N];
		for(int i = 1 ; i < N ; i++) {
			if(first == a[i]) continue;
			if(Math.abs(a[i] - first) > 1) return 0;
			// 자신보다 높은 숫자가 나온 경우
			if(first < a[i]) {
				for(int j = i-1 ; j >= i - X ; j--) {
					if(j < 0) return 0;
					if(first != a[j] || visited[j]) return 0;
					visited[j] = true;
				}
			// 자신보다 낮은 숫자가 나온 경우
			}else {
				for(int j = i ; j < i + X ; j++) {
					if(j >= N) return 0;
					if(a[i] != a[j] || visited[j]) return 0;
					visited[j] = true;
				}
				i = i + X-1;
			}
			first = a[i];
		}
		return 1;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int x = 1; x <= tc ; x++) {
			N = sc.nextInt();
			X = sc.nextInt();
			arr = new int[N][N];
			brr = new int[N][N];

			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					arr[i][j] =	sc.nextInt();
					brr[j][i] = arr[i][j];
				}
			}
			int ans = 0;
			for(int i = 0 ; i < N ; i++) {
				ans += check(arr[i]);
				ans += check(brr[i]);
			}
			System.out.println("#"+x + " " +ans);

		}
	}

}
