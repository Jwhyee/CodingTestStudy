package ka.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 회사 문화1(GOL4)
 * 시간 : 588 ms
 * 메모리 : 87808 KB
 * 링크 : https://www.acmicpc.net/problem/14267
 */
public class BOJ_14267 {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 회사 직원 수
        N = Integer.parseInt(st.nextToken());

        // 그래프 초기화

        // 최초의 칭찬 횟수
        M = Integer.parseInt(st.nextToken());

        // 그래프 값 초기화
        st = new StringTokenizer(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            // 직속 상사 번호
            int boss = Integer.parseInt(st.nextToken());

            // 사장의 경우 상사가 없기 때문에 제외
            if (boss == -1) {
                continue;
            }

            // 상사의 부하 직원 저장
            graph[boss].add(i);
        }

        // 칭찬 누적 배열 초기화
        dp = new int[N + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int employee = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            // 한 사원이 다른 상사에게도 칭찬 받을 수 있음
            dp[employee] += cnt;
        }

        // 사장부터 시작
        dfs(1);

        for (int i = 1; i <= N; i++) {
            bw.append(dp[i] + " ");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    private static void dfs(int employee) {
        // 직속 상사가 employee인 부하 직원의 칭찬 횟수 구하기
        for (Integer i : graph[employee]) {
            dp[i] += dp[employee];
            dfs(i);
        }
    }
}
