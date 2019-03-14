import java.io.*;
import java.util.*;

public class _swea_7206_숫자게임 {
	static int N;
	static int res;
	static void go(int num ,int cnt) {
		if(num < 10 ) {
			res = Math.max(res, cnt);
			return;
		}
		String sb = Integer.toString(num);
		int len = sb.length()-1;
		int sum = sb.charAt(0)-'0';
		int tsum = 1;
		for(int i = 1 ; i < (1 << len) ; i++) {
			for(int j = 0 ; j < len ; j++) {
				if((i & (1 << j)) != 0) {
					tsum = tsum * sum;
					sum = (sb.charAt(j+1)-'0');
				}else {
					sum = sum*10+(sb.charAt(j+1)-'0');
				}
			}
			int val = tsum * sum;

			go(val, cnt+1);
			sum = sb.charAt(0)-'0';
			tsum = 1;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++) {
			res = 0;
			N = Integer.parseInt(br.readLine());
			if(N < 10) {
				System.out.println("#"+tc + " " + "0");
				continue;
			}
			go(N,0);
			System.out.println("#"+tc + " " + res);
		}
	}
}
