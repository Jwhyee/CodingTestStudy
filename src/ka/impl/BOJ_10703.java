package ka.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
11 7
XXX.XXX
X.XXX.X
X..X..X
X.....X
.......
.#.....
.#...#.
.#...#.
.##.##.
.#####.
#######
*/
/**
 * 문제 이름(난이도) : 유성(SIL1)
 * 시간 : 시간 초과 ms
 * 메모리 : ... KB
 * 링크 : https://www.acmicpc.net/problem/10703
 */
public class BOJ_10703 {
    static char[][] map;
    static int R, S, xCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new char[R][S];

        for (int i = 0; i < R; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < S; j++) {
                char c = arr[j];
                if (c == 'X') {
                    xCnt++;
                }
                map[i][j] = c;
            }
        }

        meteor();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < S; j++) {
                bw.append(map[i][j]);
            }
            bw.append("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void meteor() {
        int m = S;
        while (m-- > 0) {
            // 유성을 밑으로 한 칸 이동
            int tempCnt = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < S; j++) {
                    char c = map[i][j];
                    if (c == 'X') {
                        if (map[i + 1][j] != '#') {
                            tempCnt++;
                        }
                    }
                }
            }

            if (tempCnt == xCnt) {
                for (int i = R - 1; i >= 0; i--) {
                    for (int j = S - 1; j >= 0; j--) {
                        char c = map[i][j];
                        if (c == 'X') {
                            map[i][j] = '.';
                            map[i + 1][j] = c;
                        }

                    }
                }
            } else {
                break;
            }
        }
    }

}
