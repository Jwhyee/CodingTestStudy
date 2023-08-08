package lee.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 통나무 건너뛰기 (SIL1)
 * 시간 : 436 ms
 * 메모리: 48396 KB
 * 링크 : https://www.acmicpc.net/problem/11497
 * */
public class BOJ_11497 {
	static int N; // 통나무의 개수
	static int[] heightArr; // 통나무들 높이
	static int min; // 통나무의 높이 차 최솟값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			// 통나무 개수
			N = Integer.parseInt(br.readLine());
			// 통나무 높이 입력받기
			heightArr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				heightArr[i] = Integer.parseInt(st.nextToken());
			}//for
			min = 100000; // 1 ≤ Li ≤ 100,000
			// 통나무의 높이 차 최솟값 구하기
			min = Math.min(min, getLevel());
			ans.append(min).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int getLevel() {		
		// 통나무 높이 배열 정렬
		Arrays.sort(heightArr); 
		// 높이차가 최솟값이 되도록 정렬하기 위한 배열
		int[] sortArr = new int[N]; 
		int start = 0; // 시작 인덱스
		int end = N-1; // 끝 인덱스
		for(int i=0; i<N; i++) {
			// 1 3 5 4 2 <- 이런 형태로 정렬
			if(i%2 == 0) sortArr[start++] = heightArr[i];
			else sortArr[end--] = heightArr[i];
		}//for
		
		// 가장 첫 통나무와 가장 마지막 통나무 역시 인접해 있다.
		int max = sortArr[N-1] - sortArr[0];
		for(int i=1; i<N; i++) {
			// 난이도는 인접한 두 통나무 간의 높이의 차의 최댓값으로 결정
			max = Math.max(max, Math.abs(sortArr[i] - sortArr[i-1]));
		}//for		
		
		return max; // 난이도 최댓값 리턴
	}//getLevel

}//class
/*

3
7
13 10 12 11 10 11 12
5
2 4 5 7 9
8
6 6 6 6 6 6 6 6

*/