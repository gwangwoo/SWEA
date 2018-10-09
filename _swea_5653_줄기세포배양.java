import java.util.*;

public class _swea_5653_줄기세포배양 {
	static int N,M,K;
	static int map[][] = new int[1001][1001];
	static int check[][] = new int[1001][1001];
	static int dead[][] = new int[1001][1001];
	static final int dy[] = {-1,0,1,0};
	static final int dx[] = {0,1,0,-1};
	static int res;
	static PriorityQueue<Point> q = new PriorityQueue<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1 ; tc <= T ; tc++) {
			q.clear();
			res = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			// init
			for(int i = 0 ; i <= 1000 ; i++) {
				Arrays.fill(map[i], 0);
				Arrays.fill(check[i], 0);
				Arrays.fill(dead[i], 0);
			}
			// input
			for(int i = 500 ; i < 500+N ; i++) {
				for(int j = 500 ; j < 500+M ; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] >= 1) {
						q.add(new Point(i,j,map[i][j] , 2, map[i][j],-1));
						check[i][j] = 1;
					}
				}
			}
			int time = 0;
			int cnt = 0;
			PriorityQueue<Point> new_q = new PriorityQueue<>();
			while(!q.isEmpty()) {
				if(time == K+1) break;
				int q_size = q.size();
				while(q_size-- > 0) {
					Point p = q.poll();
					int y = p.y;
					int x = p.x;
					int life = p.life;
					int state = p.state;
					int power = p.power;
					int dead_time = p.dead_time;
					
					if(life == time) {
						if(state == 2) {
							new_q.add(new Point(y,x,-1,1,power,life+map[y][x]));
							continue;
						}
					}
					else if(state == 1) {
							for(int k = 0 ; k < 4 ; k++) {
								int ny = y + dy[k];
								int nx = x + dx[k];
								if(ny < 0 || ny >= 1001 || nx < 0 || nx >= 1001) continue;
								if(check[ny][nx] == 0) {
									check[ny][nx] = 1;
									map[ny][nx] = map[y][x];
									new_q.add(new Point(ny,nx,map[y][x]+time,2,map[y][x],-1));
								}
							}
						}
					if(dead_time == time) {
						cnt++;
						dead[y][x] = 1;
						continue;
					}
					new_q.add(new Point(y,x,life,state,power,dead_time));
				}
				time++;
				q.addAll(new_q);
				new_q.clear();
//				System.out.println("time" + " " + (time-1));
//				for(int i = 480 ; i < 520; i++) {
//					for(int j = 480 ; j < 520 ; j++) {
//						System.out.print(map[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println("------------------------------");
			}
			
			System.out.println("time" + " " + time);
			for(int i = 480 ; i < 520; i++) {
				for(int j = 480 ; j < 520 ; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.out.println("------------------------------");
			System.out.println("------------------------------");
			
			for(int i = 480 ; i < 520; i++) {
				for(int j = 480 ; j < 520 ; j++) {
					System.out.print(dead[i][j]);
				}
				System.out.println();
			}
			
			
			int res = 0;
			for(int i = 0 ; i <1001 ; i++) {
				for(int j = 0 ; j < 1001 ; j++) {
					if(map[i][j] != 0) {
						res+=1;
					}
				}
			}
			System.out.println("#"+tc + " " + (res-cnt));
		}
	}
	static class Point implements Comparable<Point>{
		int y,x,life,power;
		int state;	// 0: dead   1: active   2: inactive
		int dead_time = 0;
		Point(int y,int x,int life,int state,int power,int dead_time) {
			this.y = y;
			this.x = x;
			this.life = life;
			this.state = state;
			this.power = power;
			this.dead_time = dead_time;
			
		}
		@Override
		public int compareTo(Point target) {
			if(this.power > target.power) return -1;
			else if(this.power < target.power) return 1;
			return 0;
		}
	}
}
