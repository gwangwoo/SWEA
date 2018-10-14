import java.util.*;

public class _boj_15685_드래곤커브 {
	static final int dr[] = {0,-1,0,1};
	static final int dc[] = {1,0,-1,0};
	static int map[][] = new int[101][101];
	static int y,x,d,g;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while(T-->0) {
			x = sc.nextInt();
			y = sc.nextInt();
			d = sc.nextInt();
			g = sc.nextInt();

			ArrayList<Integer> dir_list = new ArrayList<>();
			dir_list.add(d);
			for(int i = 0 ; i < g ; i++) {
				int size = dir_list.size();
				for(int j = size-1 ; j >= 0 ; j--) {
					dir_list.add((dir_list.get(j)+1)%4);
				}
			}
			map[y][x] = 1;
			for(int i = 0 ; i < dir_list.size() ; i++) {
				int dir = dir_list.get(i);
				int nr = y + dr[dir];
				int nc = x + dc[dir];
				map[nr][nc] = 1;
				y = nr;
				x = nc;
			}
		}

		int cnt = 0;
		for(int i = 0 ; i < 100 ; i++) {
			for(int j = 0 ; j < 100 ; j++) {
				if(map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
