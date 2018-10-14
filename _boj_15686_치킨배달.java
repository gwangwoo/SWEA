import java.util.*;

public class _boj_15686_치킨배달 {
	static int N,M,ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		ans = Integer.MAX_VALUE;
		ArrayList<Point> home = new ArrayList<>();
		ArrayList<Point> chic = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				int now = sc.nextInt();
				if(now == 0) continue;
				if(now != 0) {
					if(now == 1) home.add(new Point(i,j));
					else if(now == 2) chic.add(new Point(i,j));				}
			}
		}
		
		for(int i = 0 ; i < (1 << chic.size()) ; i++) {
			ArrayList<Integer> isUse = new ArrayList<>();
			for(int j = 0 ; j < chic.size() ; j++) {
				if((i & (1 << j)) != 0) {
					isUse.add(j);
				}
			}
			
			if(isUse.size() > M) continue;
			if(isUse.size() == 0) continue;
			int val = 0;
			for(int x = 0 ; x < home.size() ; x++) {
				int minValue = Integer.MAX_VALUE;
				for(int y = 0 ; y < isUse.size() ; y++) {
					int now = isUse.get(y);
					int distance = Math.abs(home.get(x).r - chic.get(now).r) + Math.abs(home.get(x).c - chic.get(now).c);
					minValue = Math.min(distance, minValue);
				}
				val += minValue;
			}
			ans = Math.min(val, ans);
		}
		System.out.println(ans);
		
	}
	static class Point {
		int r,c;
		Point(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
}
