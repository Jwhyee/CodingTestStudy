package lee.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 문제 이름(난이도) : 팰린드롬 만들기 (SIL3)
 * 시간 : 80 ms
 * 메모리: 11656 KB
 * 링크 : https://www.acmicpc.net/problem/1213
 * */
public class BOJ_1213 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] hansoo = br.readLine().toCharArray(); // 임한수의 영어 이름
		br.close();
		// 임한수의 영어 이름을 팰린드롬으로 바꾸자
		getPalindrome(hansoo);
	}//main

	// 임한수의 영어 이름을 팰린드롬으로 바꿔주는 메서드
	private static void getPalindrome(char[] hansoo) {		
		Queue<Character> tmp = new LinkedList<>(); // 짝이 안 맞는 알파벳 임시 저장
		Arrays.sort(hansoo); // 한수 영어이름 오름차순 정렬	
		for(char c : hansoo) {
			tmp.offer(c);
		}
		int len = hansoo.length; // 이름 길이
		char[] palindrome = new char[len]; // 팰린드롬 이름
		int start = 0, end = len-1; // 시작 끝
		
		char cur, next; // 현재, 다음 알파벳
		// AAABB(12345) -> 1,2 비교 3,4 비교 이런식으로 진행
		while(len>0) {
			cur = tmp.poll(); // 현재 알파벳
			if(tmp.isEmpty()) { // tmp가 비어있다면 cur이 마지막 알파벳이라는 뜻.
				palindrome[start] = cur; // 순서대로 대입
				break; // 종료
			}
			next = tmp.peek(); // 다음 알파벳
			if(cur == next) { // 현재와 다음이 같다면 펠린드롬 만들 수 있음
				palindrome[start++] = cur; // 현재는 시작부분부터 채우고
				palindrome[end--] = tmp.poll(); // 다음은 끝부분부터 채워나가기
				len-=2; // 길이 2만큼 감소
			}else { // 다르다면
				tmp.offer(cur); // 현재 알파벳 tmp에 다시 담기
				len--; // 다음 알파벳은 다다음 알파벳과 비교해야하므로 1만 감소
			}
		}//while
		
		StringBuilder ans = new StringBuilder();
		// tmp에 값이 남아 있다면
		if(!tmp.isEmpty()) {
			// 1개만 있을 경우 팰린드롬 성립
			if(tmp.size() == 1) {
				palindrome[start] = tmp.poll(); // 대입
			}else { // 2개 이상일 경우 팰린드롬 불가능
				System.out.println("I'm Sorry Hansoo");
				return; // 불가능 출력후 종료
			}
		}//if
		
		// 팰린드롬 완성
		for(char c : palindrome) {
			ans.append(c);
		}//for
		
		System.out.print(ans); // 출력
	}//getPalindrome

}//class
