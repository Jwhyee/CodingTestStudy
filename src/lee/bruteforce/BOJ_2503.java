package lee.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 문제 이름(난이도) : 숫자 야구 (SIL3)
 * 시간 : 84 ms
 * 메모리: 11632 KB
 * 링크 : https://www.acmicpc.net/problem/2503
 * */
public class BOJ_2503 {
	static Number[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 질문 횟수
		
		StringTokenizer st;
		numbers = new Number[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] number = st.nextToken().toCharArray(); // 민혁이가 질문한 세 자리 수
			int S = Integer.parseInt(st.nextToken());  // 스트라이크 개수
			int B = Integer.parseInt(st.nextToken());  // 볼의 개수
			numbers[i] = new Number(number, S, B);
		}//for
		br.close();
		// 영수가 생각하고 있을 가능성이 있는 답의 총 개수를 출력한다.
		int cnt = getCnt();
		System.out.print(cnt);
	}//main
	
	// 가능성이 있는 답의 총 개수를 반환해주는 메서드
	private static int getCnt() {
		int cnt = 0; // 답의 총 개수
		
		char[] num;
		// 1 ~ 9까지 서로 다른 숫자 세 개로 구성 됐으므로 123~987까지 탐색
		for(int n=123; n<=987; n++) {
			num = getNumArr(n); // 숫자를 char[]로 변환
			if(num == null || wrong(num)) continue; // 잘못된 답인지 확인
			cnt++; // 개수 증가
		}//for
		
		return cnt; // 답의 총 개수 반환
	}//getCnt

	// 숫자를 char[]로 변환해주는 메서드
	private static char[] getNumArr(int n) {
		int first = n / 100; // 첫 번째 (100)
		n %= 100;
		int second = n / 10; // 두 번째 (10)
		int third = n % 10; // 세 번째 (1)
		
		// 0이거나 중복되는 숫자라면 null 반환
		if(first == 0 || second == 0 || third == 0) return null;
		if(first == second || first == third || second == third) return null;
		
		// 조건을 통과했다면 배열 생성 후 반환
		char[] numArr = new char[3];
		numArr[0] = (char) (first + '0');
		numArr[1] = (char) (second + '0');
		numArr[2] = (char) (third + '0');
		return numArr;
	}//getNumArr

	// 잘못된 답인지 확인하는 메서드 (가능성이 있으면 false, 없으면 true)
	private static boolean wrong(char[] num) {
		int s, b;  // 스트라이크 개수, 볼 개수	
		char[] cur; // 민혁이가 물어본 숫자
		
		// 민혁이의 물음들과 각각의 물음에 대한 영수의 답과 비교
		for(Number number : numbers) {
			s = b = 0; // 0으로 초기화
			cur = number.number; // 민혁이가 물어본 숫자 초기화
			// 세 자리 비교
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					if(cur[j] != num[i]) continue; // 수가 다르다면 continue
					
					if(i == j) s++; // 동일한 자리라면 스트라이크 증가
					else b++; // 아니면 볼 증가
				}//for j
			}//for i
			// 스트라이크 개수와 볼 개수가 다르다면 잘못된 답이므로 true 반환
			if(number.S != s || number.B != b) return true;
		}//for number
		
		return false;
	}//wrong

	static class Number {
		char[] number; // 민혁이가 질문한 세 자리 수
		int S; // 스트라이크 개수
		int B; // 볼의 개수
		public Number(char[] number, int S, int B) {
			this.number = number;
			this.S = S;
			this.B = B;
		}
		@Override
		public String toString() {
			return "[number=" + Arrays.toString(number) + ", S=" + S + ", B=" + B + "]";
		}
		
	}//Number

}//class
