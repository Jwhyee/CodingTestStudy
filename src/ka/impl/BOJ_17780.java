package ka.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 제목 : 새로운 게임 [골드2]
 * 시간 : . ms
 * 메모리 : . KB
 * 링크 : https://www.acmicpc.net/problem/17780
 */
public class BOJ_17780 {
    static int N, K, gameCnt = 0;
    static int[][] map;
    static Node[] players;
    static LinkedList<Integer>[][] playerMap;

    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, -1, 0};
    static int[] reverseDir = {1, 0, 3, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        players = new Node[K];
        playerMap = new LinkedList[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                playerMap[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            // 1 :동 / 2 : 서 / 3 : 북 / 4 : 남
            int dir = Integer.parseInt(st.nextToken()) - 1;
            players[i] = new Node(x, y, dir);
            playerMap[y][x].add(i);
        }

        System.out.println(move());

    }

    private static int move() {
        boolean flag = true;

        while (flag) {
            gameCnt++;
            if (gameCnt > 1000) break;

            int i = 0;
            for (Node player : players) {
                int x = player.x;
                int y = player.y;

                // 현재 말이 가장 밑에 있지 않으면 다음 말 탐색
                if (playerMap[y][x].get(0) != i++) continue;

                int nx = x + dx[player.dir];
                int ny = y + dy[player.dir];

                // 다음 칸이 파란색이거나 체스판을 벗어나려는 경우
                // - A번 말의 이동 방향을 반대로 한다.
                if (!isPossible(nx, ny) || map[ny][nx] == 2) {
                    // 방향 반전
                    player.dir = reverseDir[player.dir];
                    nx = x + dx[player.dir];
                    ny = y + dy[player.dir];

                }

                // 만약 반대 방향으로 이동하려는 칸이 파란색 혹은 체스판 밖일 경우 이동하지 않고 방향만 바꾼다.
                if (!isPossible(nx, ny) || map[ny][nx] == 2) {
                    continue;
                }
                // 다음 칸이 빨간색인 경우
                else if (map[ny][nx] == 1) {
                    // - 이동할 칸(next)에 말이 없을 경우 현재 말 탑의 순서를 바꾼다 -> A, B, C -> C, B, A
                    // - 이동할 칸(next)에 말이 있을 경우 현재 말 탑의 순서를 바꾼다 -> A, B, C -> D, H, C, B, A
                    for (int j = playerMap[y][x].size() - 1; j >= 0; j--) {
                        int temp = playerMap[y][x].get(j);
                        playerMap[ny][nx].add(temp);
                        players[temp].x = nx;
                        players[temp].y = ny;
                    }
                    // 현재 위치의 말 탑 초기화
                    playerMap[y][x].clear();
                }
                // 다음 칸이 흰색인 경우
                else {
                    // - 이동할 칸(next)에 말이 있을 경우 현재 말들을 next 말 위에 올린다.
                    for (int j = 0; j < playerMap[y][x].size(); j++) {
                        int temp = playerMap[y][x].get(j);
                        playerMap[ny][nx].add(temp);
                        players[temp].x = nx;
                        players[temp].y = ny;
                    }
                    playerMap[y][x].clear();
                }

                if (playerMap[ny][nx].size() >= 4) {
                    flag = false;
                    break;
                }
            }
        }
        return flag ? -1 : gameCnt;
    }

    private static boolean isPossible(int x, int y) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    static class Node {
        int x, y, dir;

        public Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
