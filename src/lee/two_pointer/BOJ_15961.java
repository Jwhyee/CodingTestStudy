package lee.two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 회전 초밥 (GOL4)
 * 시간 : 532 ms
 * 메모리: 168616 KB
 * 링크 : https://www.acmicpc.net/problem/15961
 * */
public class BOJ_15961 {
	// 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
	static int N, D, K, C;
	static int max, cnt;
	static int[] plates;
	static int[] types;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 접시의 수
		D = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		C = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		plates = new int[N]; // 벨트 위의 초밥
		types = new int[D+1]; // 초밥 종류
		
		max = 0; // 먹을 수 있는 초밥의 가짓수의 최댓값
		cnt = 0; // 먹을 수 있는 초밥의 가짓수
		// k개 먼저 입력
		for(int i=0; i<K; i++) {
			plates[i] = Integer.parseInt(br.readLine());
			if(types[plates[i]] == 0) cnt++; // 먹은적 없는 초밥이면 가짓수 증가
			types[plates[i]]++;	// 먹은 초밥 종류 개수 증가		
		}//for
		
		// 나머지 초밥 입력
		for(int i=K; i<N; i++) {
			plates[i] = Integer.parseInt(br.readLine());
		}//for
		br.close();
		
		if(types[C] > 0) max = cnt; // 쿠폰으로 받은 초밥 먹었으면 현재 먹은 가짓수가 최댓값
		else max = cnt + 1; // 쿠폰으로 받은 초밥 안 먹었으면 쿠폰 초밥 포함
		
		rotation(0, K, N);	// 앞부분부터 탐색
		rotation(N-K, 0, K-1); // 뒷부분부터 탐색
		
		// 먹을 수 있는 초밥의 가짓수의 최댓값을 출력.
		System.out.print(max);
	}//main
	// 1 1 2 3 5 4 2
	private static void rotation(int prev, int cur, int len) {
		// 현재 인덱스가 len 보다 작으면 반복
		while(cur < len) {
			types[plates[prev]]--; // 이전 초밥 종류 먹은 개수 감소
			if(types[plates[prev++]] == 0) cnt--; // 이전에 먹은 초밥 뱉었는데 그 종류가 0개라면 가짓수 감소
			if(types[plates[cur]] == 0) cnt++; // 현재 먹을 초밥의 종류 개수가 0개라면 가짓수 증가
			types[plates[cur++]]++; // 현재 초밥 종류 먹은 개수 증가
			
			if(cnt >= max) { // 가짓수가 최댓값보다 크거나 같다면
				if(types[C] > 0) max = cnt; // 쿠폰 초밥 포함해서 먹었으면 최댓값에 현재 가짓수 대입
				else max = cnt+1; // 쿠폰 초밥 안 먹었으면 쿠폰 초밥 포함시키기
			}//if
		}//while
		
	}//rotation

}//class
