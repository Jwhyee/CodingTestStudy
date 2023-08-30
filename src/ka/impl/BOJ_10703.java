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
    static char[][] map, copy;
    static int R, S, meteorTotalCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new char[R][S];
        copy = new char[R][S];

        for (int i = 0; i < R; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < S; j++) {
                char c = arr[j];
                if (c == 'X') {
                    meteorTotalCnt++;
                }
                map[i][j] = c;
                copy[i][j] = c;
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

        int h = R;
        boolean flag = false;
        int cnt = 0;

        // 유성을 밑으로 한 칸씩 이동하는 반복문
        while (true) {
            // 밑에서부터 유성을 한 칸씩 밑으로 옮김
            outer : for (int i = R - 1; i >= 0; i--) {
                for (int j = S - 1; j >= 0; j--) {
                    char c = copy[i][j];
                    if (c == 'X') {
                        if (copy[i + 1][j] != '#') {
                            copy[i][j] = '.';
                            copy[i + 1][j] = c;
                        } else {
                            cnt--;
                            flag = true;
                            break outer;
                        }
                    }

                }
            }
            if (flag) {
                for (int z = 0; z <= cnt; z++) {
                    for (int i = R - 1; i >= 0; i--) {
                        for (int j = S - 1; j >= 0; j--) {
                            char c = map[i][j];
                            if (c == 'X') {
                                if (map[i + 1][j] != '#') {
                                    map[i][j] = '.';
                                    map[i + 1][j] = c;
                                }
                            }

                        }
                    }
                }
                break;
            } else {
                cnt++;
            }
        }
    }

}
