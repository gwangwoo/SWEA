import java.io.*;
import java.util.*;
public class _swea_8567 {
	
	static int arr[] = new int[100002];
	static int cnt[] = new int[100002];
	static void init() {
		Arrays.fill(cnt, 1);
		for(int i = 1 ; i <= 100000 ; i++) arr[i] = i;
		
		for(int i = 2 ; i <= 100000 ; i++) {
			for(int j = i ; j <= 100000; j+=i) {
				if(arr[j] % arr[i] == 0) {
					arr[j] /= arr[i];
					cnt[j]++;
				}
			}
		}
		int now = cnt[0];
		int idx = 0;
		for(int i = 1 ; i <= 100000 ; i++) {
			if(now > cnt[i]) {
				arr[i] = idx;
			}else {
				now = cnt[i];
				idx = i;
				arr[i] = idx;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		init();
		for(int tc = 1; tc <= T; tc++) {
			int cmd = Integer.parseInt(br.readLine());
			System.out.println("#"+tc + " " + arr[cmd]);
		}
		
	}
}
