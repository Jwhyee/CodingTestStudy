package lee.two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 용액 (GOL5)
 * 시간 : 300 ms
 * 메모리: 31516 KB
 * 링크 : https://www.acmicpc.net/problem/2467
 * */
public class BOJ_2467 {
	static int N; // 전체 용액의 수 N
	static int[] solution; // 용액의 특성값을 나타내는 N개의 정수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 전체 용액의 수 N
		solution = new int[N]; // 용액의 특성값을 나타내는 N개의 정수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			solution[i] = Integer.parseInt(st.nextToken());
		}//for
		br.close();

		mix();
	}//main

	private static void mix() {
		int start = 0; // 시작 인덱스
		int end = N-1; // 끝 인덱스
		int l = solution[start]; // 작은 용액
		int r = solution[start+1]; // 큰 용액
		int min = Math.abs(l + r); // 0에 가까운 값(최솟값)
		int sum = 0; // 두 용액의 혼합값
				
		while(start < end) {
			// start 인덱스의 용액과 end 인덱스의 용액 혼합
			sum = Math.abs(solution[start] + solution[end]);
			
			// 혼합값이 0이라면
			if(sum == 0) {
				l = solution[start]; // 작은 용액
				r = solution[end]; // 큰 용액
				break; // 종료
			}//if
			
			// 혼합값이 min 값보다 더 0에 가깝다면
			if(sum < min) {
				l = solution[start]; // 작은 용액
				r = solution[end]; // 큰 용액
				min = sum; // 최솟값 갱신
			}//if
			
			// (시작 인덱스+1의 용액과 끝 인덱스의 혼합값)이  (시작 인덱스 용액과 끝 인덱스-1의 혼합값)보다 작거나 같다면
			if(Math.abs(solution[start+1] + solution[end]) <= Math.abs(solution[start] + solution[end-1])) {
				start++; // 시작 인덱스 증가
			}else end--; // 아니라면 끝 인덱스 감소
		}//while
		
		StringBuilder ans = new StringBuilder();
		//특성값의 오름차순으로 출력한다.
		ans.append(l + " ").append(r);
		System.out.print(ans);
	}//mix

}//class
