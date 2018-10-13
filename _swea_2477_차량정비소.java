import java.util.*;

public class _swea_2477_��������� {
	static int N,M,K,A,B;

	public static void main(String[] main) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1 ; tc <= T ; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			A = sc.nextInt();
			B = sc.nextInt();

			Center first_center[] = new Center[N];
			Center second_center[] = new Center[M];
			Person person[] = new Person[K];
			for(int i = 0 ; i < N ; i++) {
				first_center[i] = new Center(i+1,sc.nextInt(),0,false,0);
			}
			for(int i = 0 ; i < M ; i++) {
				second_center[i] = new Center(i+1,sc.nextInt(),0,false,0);
			}
			for(int i = 0 ; i < K ; i++) {
				person[i] = new Person(i+1,sc.nextInt(),0,0);
			}

			Queue<Pair> arr = new LinkedList<>();
			for(int i = 0 ; i < K ; i++) {
				arr.add(new Pair(person[i].startTime,i+1));
			}

			PriorityQueue<Pair2> arr2 = new PriorityQueue<>();

			int time = 0;
			NEXT:
				while(true) {
					// ����â�� ����.
					for(int i = 0 ; i < N ; i++) {
						if(!first_center[i].isWork) continue;
						if(first_center[i].finish_time == time) {
							first_center[i].isWork = false;
							arr2.add(new Pair2(first_center[i].whoisUse , time, i+1));
							first_center[i].whoisUse = 0;
						}
					}
					// ����â���� �߰� ���ֱ�.
					for(int i = 0 ; i < N ; i++) {
						if(!first_center[i].isWork) {
							if(arr.isEmpty()) break;
							Pair p = arr.peek();
							if(p.time > time) continue;
							arr.poll();

							first_center[i].isWork = true;
							person[p.index-1].first = i+1;
							first_center[i].whoisUse = p.index;
							first_center[i].finish_time = time+first_center[i].spend;
						}
					}
					// ����â�� ����.
					for(int i = 0 ; i < M ; i++) {
						if(!second_center[i].isWork) continue;
						if(second_center[i].finish_time == time) {
							second_center[i].isWork = false;
							second_center[i].whoisUse = 0;
						}
					}
					// ����â���� �߰� ���ֱ�.
					for(int i = 0 ; i < M ; i++) {
						if(!second_center[i].isWork) {
							if(arr2.isEmpty()) break;
							Pair2 p = arr2.poll();

							second_center[i].isWork = true;
							person[p.index-1].second = i+1;
							second_center[i].whoisUse = p.index;
							second_center[i].finish_time = time + second_center[i].spend;

						}
					}
					time++;
					if(arr.isEmpty() && arr2.isEmpty()) {
						for(int i = 0 ; i < N ; i++) {
							if(first_center[i].whoisUse != 0) continue NEXT;
						}
						for(int i = 0 ; i < M ; i++) {
							if(second_center[i].whoisUse != 0) continue NEXT;
						}
						break;
					}
				}

			int res = 0;
			for(int i = 0 ; i < K ; i++) {
				if(person[i].first == A && person[i].second == B) res+=(i+1);
				//System.out.println(person[i].first + " " + person[i].second);
			}
			if(res == 0) res = -1;
			System.out.println("#" + tc + " " + res);
		}
	}
	static class Center {
		int index,spend,finish_time,whoisUse;	// ���͹�ȣ , ������ �ӹ�ó���ð� , ��ó���� ������ �ð�, ��������ϴ���;
		boolean isWork;							// �� ���͸� �̿������� �ƴ����� �Ǻ��ϴ� boolean ����
		Center(int index,int spend,int finish_time , boolean isWork, int whoisUse) {
			this.index = index;
			this.spend = spend;
			this.finish_time = finish_time;
			this.isWork = isWork;
			this.whoisUse = whoisUse;
		}
	}
	static class Person {
		int index,startTime,first,second;	// �����ȣ , �׻���� �½ð� , ����â����ȣ, ����â�� ��ȣ;
		Person(int index,int startTime, int first, int second) {
			this.index = index;
			this.startTime = startTime;
			this.first = first;
			this.second = second;
		}
	}
	static class Pair {
		int time,index;
		Pair(int time, int index) {						// ����â�� �����ѽð� , �̿����� ��ȣ 
			this.time = time;
			this.index = index;
		}
	}
	static class Pair2 implements Comparable<Pair2>{		// �켱����ť�� �̿��ϱ� ���� ���� �̳�Ŭ����.
		int index,time,recep_num;							// �̿����� ��ȣ , ����â�� �����ѽð� , ��� ����â�� �̿��ϰ�Դ���
		Pair2(int index, int time, int recep_num) {
			this.index = index;
			this.time = time;
			this.recep_num = recep_num;
		}
		@Override
		public int compareTo(Pair2 target) {
			if(this.time == target.time) {
				if(this.recep_num < target.recep_num) return -1;
				else return 1;
			}else if(this.time < target.time) return -1;
			else return 1;
		}
	}
}
