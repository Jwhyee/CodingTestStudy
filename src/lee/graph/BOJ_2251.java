package lee.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제 이름(난이도) : 물통 (GOL5)
 * 시간 : 80 ms
 * 메모리: 11616 KB
 * 링크 : https://www.acmicpc.net/problem/2251
 * */
public class BOJ_2251 {
	// 첫 번째 물통이 비어 있을 때, 세 번째 물통에 담겨있을 수 있는 물의 양 
	static PriorityQueue<Integer> cList;
	static int N; // 물통 개수
	static final int A=0, B=1, C=2;
	static int[] max; // 물통의 용량
	static int[] from = {A, A, B, B, C, C}; // 보내는 물통
	static int[] to = {B, C, A, C, A, B}; // 받는 물통

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = 3; // 물통 개수
		max = new int[N]; // 물통의 용량
		// 물통의 용량 입력
		for(int i=0; i<N; i++) {
			max[i] = Integer.parseInt(st.nextToken());
		}//for
		br.close();
		
		// 각 용량은 오름차순으로 정렬하기 위해 PriorityQueue 사용
		cList = new PriorityQueue<>();
		bfs(); // 물의 양 구하기
		
		// 첫 번째 물통이 비어 있을 때, 세 번째 물통에 담겨있을 수 있는 물의 양 모두 출력
		StringBuilder ans = new StringBuilder();
		while(!cList.isEmpty()) {
			ans.append(cList.poll()).append(" ");
		}
		System.out.print(ans);
	}//main

	private static void bfs() {
		// 방문 체크 (첫 번째A, 두 번째B 물통)
		boolean[][] visited = new boolean[max[A]+1][max[B]+1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0}); // 처음에는 앞의 두 물통(A, B)은 비어 있음
		visited[0][0] = true; 
		
		int[] cur;
		int a, b, c;
		while(!q.isEmpty()) {
			cur = q.poll(); // 현재 용량
			a = cur[A]; // A 용량
			b = cur[B]; // B 용량
			c = max[C] - (a + b); // C 용량
			
			if(a == 0) cList.add(c);

			for(int i=0; i<6; i++) {
				int[] next = {a, b, c};
				// 받는 물통에 보내는 물통의 물을 붓는다
				next[to[i]] += next[from[i]];
				// 보내는 물통을 비움.
				next[from[i]] = 0; 
				
				// 받는 물통의 양이 용량을 초과했다면
				if(next[to[i]] > max[to[i]]) {
					// 보내는 물통에 초과한 양 다시 돌려주고
					next[from[i]] = next[to[i]] - max[to[i]];
					// 받는 물통에는 용량만큼 채움.
					next[to[i]] = max[to[i]];
				}//if
				
				// 만약 이미 방문(동일한 양을 주고 받았던 적 있으면) 했으면 continue
				if(visited[next[A]][next[B]]) continue;
				
				// 방문 체크
				visited[next[A]][next[B]] = true;
				// 다음 탐색
				q.offer(next);
			}//for			
		}//while
		
	}//bfs


}//class
