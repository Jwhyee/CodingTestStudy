package ka.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 안전 영역(SIL1)
 * 시간 : 244 ms
 * 메모리 : 18544 KB
 * 링크 : https://www.acmicpc.net/problem/2468
 */
public class BOJ_2468 {
    static int[][] map;
    static boolean[][] visited;
    static int N, max, cnt;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max = 0;

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxValue = getMax();

        for (int i = 0; i <= maxValue; i++) {
            visited = new boolean[N][N];
            int areaCnt = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (!visited[j][k] && map[j][k] > i) {
                        areaCnt += dfs(i, j, k);
                    }
                }
            }
            // 총 영역
            max = Math.max(max, areaCnt);
        }
        System.out.println(max);
    }

    private static int getMax() {
        int max = Integer.MIN_VALUE;
        for (int[] ints : map) {
            for (int anInt : ints) {
                if (anInt > max) {
                    max = anInt;
                }
            }
        }
        return max;
    }

    private static int dfs(int waterHeight, int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (!visited[ny][nx] && map[ny][nx] > waterHeight) {
                    dfs(waterHeight, ny, nx);
                }
            }
        }
        return 1;
    }
}
