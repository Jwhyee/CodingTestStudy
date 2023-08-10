package ka.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제 이름(난이도) : 통나무 건너뛰기(실버1)
 * 시간 : 572 ms
 * 메모리 : 53088 KB
 * 링크 : https://www.acmicpc.net/problem/11497
 */
public class BOJ_11497 {
    static int[] array;
    static int N, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 수
        T = Integer.parseInt(br.readLine());

        // 반복문을 통해 테스트 케이스 입력 받음
        for (int i = 0; i < T; i++) {
            // 통나무 개수
            N = Integer.parseInt(br.readLine());

            // 통나무 높이 배열
            array = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // 배열 정렬
            Arrays.sort(array);

            sb.append(checkLevel()).append("\n");
        }

        br.close();

        System.out.println(sb);
    }

    private static int checkLevel() {
        // 배열 복사
        int[] copyArr = new int[N];

        // 앞, 뒤 쪽 인덱스를 조절할 변수
        int frontIdx = 0, endIdx = N - 1;

        // 앞 : 작은수, 뒤 : 큰수 반복하며 재정렬
        // 1 2 3 4 5 -> 1 3 5 4 2
        for (int i = 0; i < N; i ++) {
            if (i % 2 == 0) {
                copyArr[frontIdx++] = array[i];
            } else {
                copyArr[endIdx--] = array[i];
            }
        }

        // 마지막 통나무 -> 첫 통나무
        int max = copyArr[N - 1] - copyArr[0];
        for (int i = 1; i < N; i++) {
            max = Math.max(max, Math.abs(copyArr[i] - copyArr[i - 1]));
        }
        return max;
    }
}
