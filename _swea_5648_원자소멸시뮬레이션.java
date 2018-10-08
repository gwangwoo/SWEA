import java.util.*;

public class _swea_5648_¿øÀÚ¼Ò¸ê½Ã¹Ä·¹ÀÌ¼Ç {
	static int N,res;
	static final int dx[] = {0,0,-1,1};
	static final int dy[] = {1,-1,0,0};
	static boolean erase[];
	static int check[][] = new int[4004][4004];
	static Atom arr[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1 ; tc <= T ; tc++) {
			res = 0;
			N = sc.nextInt();
			arr = new Atom[N];
			erase = new boolean[N];
			for(int i = 0 ; i < 4004 ; i++) {
				for(int j = 0 ; j < 4004 ; j++) {
					check[i][j] = 0;
				}
			}
			for(int i = 0 ; i < N ; i++) {
				arr[i] = new Atom((sc.nextInt()+1000)*2,(sc.nextInt()+1000)*2,sc.nextInt(),sc.nextInt());
				check[arr[i].x][arr[i].y] = 1;
			}
			int count = N;
			int time = 0;
			while(time < 4001) {
				if(count == 0) break;
				time++;
				// move
				for(int i = 0 ; i < N ; i++) {
					if(erase[i]) continue;
					int dir = arr[i].dir;
					int nx = arr[i].x + dx[dir];
					int ny = arr[i].y + dy[dir];
					if(nx < 0 || nx >= 4001 || ny < 0 || ny >= 4001) {
						erase[i] = true;
						count--;
						check[arr[i].x][arr[i].y] -=1;
						continue;
					}
					check[nx][ny]+=1;
					check[arr[i].x][arr[i].y]-=1;
					arr[i].x = nx; arr[i].y = ny;
				}
				// burn
				ArrayList<Integer> destroy = new ArrayList<>();
				for(int i = 0 ; i < N ; i++) {
					if(erase[i]) continue;
					Atom now = arr[i];
					if(check[now.x][now.y] >=2) {
						res += now.K;
						erase[i] = true;
						count--;
						destroy.add(i);
					}
				}
				for(int i = 0 ; i < destroy.size() ; i++) {
					int index = destroy.get(i);
					check[arr[index].x][arr[index].y] -= 1;
				}

			}
			System.out.println("#"+tc + " " + res);
		}
	}
	static class Atom {
		int x,y,dir,K;
		Atom(int x,int y,int dir,int K) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.K = K;
		}
	}
}
