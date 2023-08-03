package kim.collection;

import java.util.Stack;

/**
 * 문제 이름(난이도) : 올바른 괄호(LV2)
 * 시간 : 18.23ms
 * 메모리 : 52.9MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12909
 */

public class PRG_12909 {
    class Solution {
        boolean solution(String s) {
            boolean answer = true;
            
            Stack<Character> stack = new Stack<>();

            // 1. 문자  순회
            for (char iItem : s.toCharArray()) {

                // 요소 값이 좌측 괄호인 경우 Stack에 저장
                if (iItem == '(') stack.push(iItem);

                // 요소 값이 우측 괄호이고, Stack에 값이 있는 경우 좌측 괄호 값 제거
                else {
                    if (stack.isEmpty()) answer = false;
                    else stack.pop();
                }
            }

            // 2. Stack의 값이 비어있지 않은 상태(짝이 이루어지지 않은 상태)라면 false 리턴
            if (!stack.isEmpty()) answer = false;

            // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
            System.out.println("Hello Java");
    
            return answer;
        }
    }
}