package kim.collection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 문제 이름(난이도) : 프로세스(LV2)
 * 시간 : 0.65ms
 * 메모리 : 76.2MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42587
 */

public class PRG_42587 {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Integer> queue = new LinkedList<>();

        // 1. 우선순위 큐를 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int priority : priorities) {
        pq.offer(priority);
        }

        // 2. 우선순위 큐가 비워질 때까지 반복
        while (!pq.isEmpty()) {

            // 3. 구성한 큐 순회
            for (int i = 0; i < priorities.length; i++) {

                if (priorities[i] == pq.peek()) {

                    // 4. 우선순위 큐에서 최상위 값 제거
                    pq.poll();
                    answer++;

                    // 5. 큐와 위치가 같으면 초기화
                    if (i == location) {
                        pq.clear();
                        break;
                    }
                }
            }
        }
    return answer;
    }
}