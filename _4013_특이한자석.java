import java.util.*;
import java.io.*;

public class _4013_특이한자석 {
	static int arr[][] = new int[4][8];
	static int N;

	static void change(int d, int [] a) {
		int tmp = 0;
		switch(d) {
		case 1:
			tmp = a[7];
			for(int i = 7 ; i > 0 ; i--) {
				a[i] = a[i-1];
			}
			a[0] = tmp;
			break;
		case -1:
			tmp = a[0];
			for(int i = 0 ; i < 7 ; i++) {
				a[i] = a[i+1];
			}
			a[7] = tmp;
			break;
		}
	}

	static int opp(int n) {
		return n == 1?-1:1;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t = 1 ; t <= tc ; t++) {
			N = sc.nextInt();
			for(int i = 0 ; i < 4 ; i++) {
				for(int j = 0 ; j < 8 ; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			while(N-->0) {
				int direct[] = new int[4];

				int num = sc.nextInt()-1;
				int dir = sc.nextInt();
				direct[num] = dir;

				int next = num -1;
				while(true) {
					if(next < 0) break;
					if(arr[next][2] == arr[next+1][6]) break;
					direct[next] = opp(direct[next+1]);
					next--;
				}
				next = num + 1;
				while(true) {
					if(next > 3) break;
					if(arr[next][6] == arr[next-1][2]) break;
					direct[next] = opp(direct[next-1]);
					next++;
				}
				for(int i = 0 ; i < 4 ; i++) {
					if(direct[i] == 0) continue;
					change(direct[i], arr[i]);
				}
				
			}
			int ans = arr[0][0] + arr[1][0] * 2 + arr[2][0] * 4 + arr[3][0]* 8;
			System.out.println("#"+t + " " + ans);
		}

	}

}
