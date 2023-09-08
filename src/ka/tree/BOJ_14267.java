package ka.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 회사 문화1(GOL4)
 * 시간 : 1 ms
 * 메모리 : 메모리 초과 KB
 * 링크 : https://www.acmicpc.net/problem/14267
 */
public class BOJ_14267 {
    static int N, M;
    static int[][] graph;
    static int[] dp;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 회사 직원 수
        N = Integer.parseInt(st.nextToken());

        // 최초의 칭찬 횟수
        M = Integer.parseInt(st.nextToken());

        // 직원 그래프 초기화
        graph = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            // 직속 상사 번호
            int boss = Integer.parseInt(st.nextToken());
            if (boss == -1) {
                continue;
            }
            graph[i][boss] = 1;
            graph[boss][i] = 1;
        }

        // 칭찬 누적 배열
        dp = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int employee = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            dp[employee] = cnt;
        }

        visited = new boolean[N + 1];
        dfs(1);

        for (int i = 1; i <= N; i++) {
            bw.append(dp[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static void dfs(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Integer cur = stack.pop();

            if (!visited[cur]) {

                visited[cur] = true;

                for (int i = 1; i <= N; i++) {
                    if (!visited[i] && graph[cur][i] == 1) {
                        dp[i] += dp[cur];
                        stack.push(i);
                    }
                }
            }

        }


    }
}
