package lee.two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 블로그 (SIL3)
 * 시간 : 288 ms
 * 메모리: 38296 KB
 * 링크 : https://www.acmicpc.net/problem/21921
 * */
public class BOJ_21921 {
	// 블로그를 시작하고 지난 일수  N, X일 동안 가장 많이 들어온 방문자 수를 출력
	static int N, X;
	static int[] visitors; // 방문자 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 블로그를 시작하고 지난 일수  N
		X = Integer.parseInt(st.nextToken()); // X일 동안 가장 많이 들어온 방문자 수를 출력
		
		visitors = new int[N+1]; // N일 동안 방문자 수
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) { // 누적합
			visitors[i] = Integer.parseInt(st.nextToken()) + visitors[i-1];
		}//for
		br.close();
		
		getVisitorCnt();
	}//main

	private static void getVisitorCnt() {
		int cnt = 0; // 최대 방문 기간 수
		int max = 0; // 최대 방문자 수
		int start = 0; // 시작일
		int end = X; // 종료일
		int sum = 0; // 합
		
		while(end <= N) {
			sum = visitors[end++] - visitors[start++];
			if(sum >= max) { // 최대 방문자 수와 동일하거나 같다면
				if(sum > max) cnt = 1; // 최대 방문자 수보다 크다면 기간 수 1로 초기화
				else cnt++; // 같다면 기간수 증가
				max = sum; 
			}//if
		}//while
		
		// 만약 최대 방문자 수가 0명이라면 SAD를 출력한다.
		if(max == 0) System.out.print("SAD");
		else {
			// 만약 최대 방문자 수가 0명이 아닌 경우 둘째 줄에 기간이 몇 개 있는지 출력한다.
			StringBuilder ans = new StringBuilder();
			ans.append(max).append("\n");
			ans.append(cnt);
			System.out.print(ans);
		}//else
		
	}//getVisitorCnt

}//class
