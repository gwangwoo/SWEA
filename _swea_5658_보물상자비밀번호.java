import java.util.*;

public class _swea_5658_보물상자비밀번호 {
	static int N,K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1 ; tc <= T ; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();

			String str = sc.next();
			String t_str = "";
			HashSet<String> hs = new HashSet<>();

			for(int k = 0 ; k < N/4 ; k++) {
				for(int i = 1 ; i <= N ; i++) {
					t_str += str.charAt(i-1);
					if(i % (N/4) == 0) {
						hs.add(t_str);
						t_str = "";
					}
				}
				str = str.substring(N-1) + str.substring(0, N-1);
				t_str = "";
			}
			ArrayList<String> arr = new ArrayList<>(hs);
			ArrayList<Integer> int_arr = new ArrayList<>();
			for(int i = 0 ; i < arr.size() ; i++) {
				String num = arr.get(i);
				int val  = 0;
				for(int j = 0 ; j < num.length() ; j++) {
					char now = num.charAt(j);
					if('0' <= now && now <= '9') {
						val += (now-'0')*Math.pow(16, num.length()-j-1);
					}else if('A' <= now && now <= 'F') {
						val += (now-55) *Math.pow(16, num.length()-j-1);
					}
				}
				int_arr.add(-val);
			}
			Collections.sort(int_arr);
			System.out.println("#"+tc + " " + -int_arr.get(K-1));

		}
	}
}

