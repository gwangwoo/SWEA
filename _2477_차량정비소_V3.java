import java.util.*;

public class _2477_차량정비소_V3 {
	static int N,M,K,A,B;
	static final int original_time = 0, spair_time = 1, num = 2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc ; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			A = sc.nextInt();
			B = sc.nextInt();
			
			// 0 :원래시간, 1 :남은시간, 2: 사람번호 
			int an[][] = new int[N][3];
			int bn[][] = new int[M][3];
			
			// 고객 도착 시간, 그리고 고객번호는 순서대로
			Queue<Pair> q1 = new LinkedList<>();
			Queue<Pair> q2 = new LinkedList<>();
			for(int i = 0 ; i < N ; i++) an[i][original_time] = sc.nextInt();
			for(int i = 0 ; i < M ; i++) bn[i][original_time] = sc.nextInt();
			for(int i = 0 ; i < K ;i++) q1.add(new Pair(sc.nextInt(), i+1));
					
			ArrayList<Integer> recep = new ArrayList<>();
			ArrayList<Integer> repair = new ArrayList<>();
			
			int time = 0;
			HOME:
			while(true) {
				// 접수 : 0 인곳에 넣어주기.
				for(int i = 0 ; i < N ; i++) {
					if(an[i][spair_time] == 0) {
						if(q1.isEmpty()) break;
						Pair p = q1.peek();
						if(p.time > time) continue;
						
						q1.poll();
						an[i][num] = p.num;
						an[i][spair_time] = an[i][original_time];
						if(i == A-1) recep.add(p.num);
					}
				}
				// 접수 : 시간 감소하기
				for(int i = 0 ; i < N ; i++) {
					if(an[i][spair_time] == 1) {
						q2.add(new Pair(time, an[i][num]));
					}
					if(an[i][spair_time] != 0) {
						an[i][spair_time]--;
					}
				}
				
				// 정비 : 0인곳에 넣어주기.
				for(int i = 0 ; i < M ; i++) {
					if(bn[i][spair_time] == 0) {
						if(q2.isEmpty()) break;
						Pair p = q2.poll();
						bn[i][num] = p.num;
						bn[i][spair_time] = bn[i][original_time];
						if(i == B-1) repair.add(p.num);
					}
				}
				
				// 정비: 시간 감소하기
				for(int i = 0 ; i < M ; i++) {
					if(bn[i][spair_time] != 0) {
						bn[i][spair_time]--;
					}
				}
				time++;
				if(q1.isEmpty() && q2.isEmpty()) {
					for(int i = 0 ; i < N ; i++) {
						if(an[i][spair_time] != 0) continue HOME; 
					}
					for(int i = 0 ; i < M ; i++) {
						if(bn[i][spair_time] != 0) continue HOME; 
					}
					break;
				}
			}
			int sum = 0;
			for(int tmp : recep) {
				if(repair.contains(tmp)) {
					sum += tmp;
				}
			}
			sum = sum==0?-1:sum;
			System.out.println("#"+t+ " "+ sum);
		}
	}
	static class Pair{
		int time, num;
		Pair(int time, int num) {
			this.time = time;
			this.num = num;
		}
	}
}
