package ka.back_tracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 알파벳(GOL4)
 * 시간 : 1028 ms
 * 메모리 : 12624 KB
 * 링크 : https://www.acmicpc.net/problem/1987
 */
public class BOJ_1987 {
    static int W, H, max;
    static char[][] map;
    static int ALPHA_A = 65;
    static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];

        // 알파벳 개수
        visited = new boolean[26];

        for (int y = 0; y < H; y++) {
            char[] charArray = br.readLine().toCharArray();
            for (int x = 0; x < W; x++) {
                map[y][x] = charArray[x];
            }
        }

        backTracking(0, 0, 1);

        System.out.println(max);

    }

    private static void backTracking(int x, int y, int cnt) {
        visited[map[y][x] - ALPHA_A] = true;

        boolean isPossible = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < W && ny < H) {
                if (!visited[map[ny][nx] - ALPHA_A]) {
                    isPossible = true;
                    backTracking(nx, ny, cnt + 1);
                    visited[map[ny][nx] - ALPHA_A] = false;
                }

            }
        }

        if (!isPossible) {
            max = Math.max(max, cnt);
        }

    }
}
