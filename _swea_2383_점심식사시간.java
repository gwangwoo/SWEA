import java.util.*;

public class _swea_2383_점심식사시간 {
	static int map[][];
	static int N;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T ; tc++) {
			N = sc.nextInt();
			ans = Integer.MAX_VALUE;
			ArrayList<Person> arr = new ArrayList<>();
			Stair stair1 = new Stair(-1,-1,-1);
			Stair stair2 = new Stair(-1,-1,-1);
			boolean flag = false;
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					int val = sc.nextInt();
					if(val != 0) {
						if(val == 1) arr.add(new Person(i,j,-1,-1));
						else {
							if(!flag) {
								flag = true;
								stair1 = new Stair(i,j,val);
							}else {
								stair2 = new Stair(i,j,val);
							}
						}
					}
				}
			}
			for(int i = 0 ; i < (1 << arr.size()) ; i++) {
				ArrayList<Integer> use_first = new ArrayList<>();
				ArrayList<Integer> use_second = new ArrayList<>();
//				use_second.add(0); use_second.add(1); use_second.add(2); use_second.add(3);
				for(int j = 0 ; j < arr.size(); j++) {
					if((i & (1 << j)) != 0) {
						use_first.add(j);
					}else {
						use_second.add(j);
					}
				}

				// 거리위치 갱신화.
				PriorityQueue<People> q1 = new PriorityQueue<>();
				PriorityQueue<People> q2 = new PriorityQueue<>();
				for(int x = 0 ; x < use_first.size() ; x++) {
					int index = use_first.get(x);
					int time = Math.abs(arr.get(index).r - stair1.r) + Math.abs(arr.get(index).c - stair1.c);
					q1.add(new People(index,time));
				}
				for(int x = 0 ; x < use_second.size() ; x++) {
					int index = use_second.get(x);
					int time = Math.abs(arr.get(index).r - stair2.r) + Math.abs(arr.get(index).c - stair2.c);
					q2.add(new People(index,time));
//					System.out.print(index + " " + time);
				}

				int time = 0;
				int res = 0;
				Step step1[] = new Step[3];
				Step step2[] = new Step[3];
				for(int x = 0 ; x < 3 ; x++) {
					step1[x] = new Step(-1,false);
					step2[x] = new Step(-1,false);
				}
				NEXT:
				while(true) {
					time++;
					// 첫 번째 계단 빼주기.
					for(int x = 0 ; x < 3 ; x++) {
						if(!step1[x].isUse) continue;
						if(step1[x].finish_time == time) {
							step1[x].isUse = false;
							step1[x].finish_time = 0;
						}
					}
					// 두 번째 계단 빼주기.
					for(int x = 0 ; x < 3 ; x++) {
						if(!step2[x].isUse) continue;
						if(step2[x].finish_time == time) {
							step2[x].isUse = false;
							step2[x].finish_time = 0;
						}
					}
					// 첫 번째 계단 넣어주기.
					for(int x = 0 ; x < 3 ; x++) {
						if(q1.isEmpty()) break;
						if(step1[x].isUse) continue;
						People p = q1.peek();
						if(time > p.distance) {
							q1.poll();
							step1[x].isUse = true;
							step1[x].finish_time = time + stair1.spend;
						}
					}
					// 두 번째 계단 넣어주기.
					for(int x = 0 ; x < 3 ; x++) {
						if(q2.isEmpty()) break;
						if(step2[x].isUse) continue;
						People p = q2.peek();
						if(time > p.distance) {
							q2.poll();
							step2[x].isUse = true;
//							System.out.println("time " + time + " " + p.index);
							step2[x].finish_time = time + stair2.spend;
						}
					}
					if(q1.isEmpty() && q2.isEmpty()) {
						for(int x = 0 ; x < 3 ; x++) {
							if(step1[x].isUse) continue NEXT;
						}
						for(int x = 0 ; x < 3 ; x++) {
							if(step2[x].isUse) continue NEXT;
						}
						res = time;
						break;
					}
				}
				ans = Math.min(res, ans);
			}
			System.out.println("#"+tc+ " " + ans);
		}
	}
	static class Person {
		int r,c,time,whereisUse;
		Person(int r,int c,int time,int whereisUse) {
			this.r = r;
			this.c = c;
			this.time = time;
			this.whereisUse = whereisUse;
		}
	}

	static class Stair {
		int r,c,spend;
		Stair(int r,int c,int spend) {
			this.r = r;
			this.c = c;
			this.spend = spend;
		}
	}

	static class People implements Comparable<People>{
		int index,distance;
		People(int index,int distance) {
			this.index = index;
			this.distance = distance;
		}
		@Override
		public int compareTo(People target) {
			if(this.distance < target.distance) return -1;
			else if(this.distance > target.distance) return 1;
			return 0;
		}
	}

	static class Step {
		int finish_time;
		boolean isUse;
		Step(int finish_time, boolean isUse) {
			this.finish_time = finish_time;
			this.isUse = isUse;
		}
	}
}
