import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 감시(GOL4)
 * 시간 : 1 ms
 * 메모리 : 1 KB
 * 링크 : https://www.acmicpc.net/problem/15683
 */
public class Main {
    static int H, W, cnt = 0, result = Integer.MAX_VALUE;
    static int emptyAreaCnt = 0, objectAreaCnt = 0;
    static int[][] map, originCopy;
    static boolean[][] visited;
    static int[] caseDir = {4, 2, 4, 4, 1}, dirCnt = {1, 2, 2, 3, 4};
    static Node[] pos;
    static boolean[] posVisited;
    static List<Node> cctv = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        // 지도 및 방문 배열 초기화
        map = new int[H][W];
        visited = new boolean[H][W];

        // 경우의 수를 확인할 때마다 배열을 다시 돌려줘야하기 때문에 원본을 저장할 배열 초기화
        originCopy = new int[H][W];

        // 입력 받기
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                int n = Integer.parseInt(st.nextToken());
                // CCTV의 좌표를 리스트에 저장
                if (n != 0 && n != 6) {
                    cctv.add(new Node(j, i, n, 0));
                }
                // 비어있는 공간의 크기 구하기
                if (n == 0) {
                    emptyAreaCnt++;
                }
                map[i][j] = n;
            }
        }

        objectAreaCnt = (H * W) - emptyAreaCnt;

        originCopy = copyArray(map);

        // CCTV의 좌표를 저장하는 배열
        pos = new Node[cctv.size()];
        posVisited = new boolean[cctv.size()];

        // 리스트를 배열로 변환
        for (int i = 0; i < pos.length; i++) {
            pos[i] = cctv.get(i);
        }

        // 메모리 해제
        cctv = null;

        backDfs(0);

        System.out.println(result);
    }

    public static int[][] copyArray(int[][] original) {
        if (original == null) return null;

        int numRows = original.length;
        int[][] copy = new int[numRows][];

        for (int i = 0; i < numRows; i++) {
            int numCols = original[i].length;
            copy[i] = new int[numCols];
            System.arraycopy(original[i], 0, copy[i], 0, numCols);
        }

        return copy;
    }

    static int[][][] dirPos = {
            {
                    {0}, {1},
                    {0}, {-1},
                    {-1}, {0},
                    {1}, {0}
            },
            {
                    {0, 0}, {1, -1},
                    {-1, 1}, {0, 0}
            },
            {
                    {-1, 0}, {0, 1},
                    {1, 0}, {0, 1},
                    {1, 0}, {0, -1},
                    {-1, 0}, {0, -1}
            },
            {
                    {-1, 0, 0}, {0, 1, -1},
                    {-1, 0, 1}, {0, 1, 0},
                    {1, 0, 0}, {0, 1, -1},
                    {-1, 0, 1}, {0, -1, 0},
            },
            {
                    {0, 0, -1, 1}, {1, -1, 0, 0}
            }
    };


    private static void backDfs(int depth) {
        // 깊이가 CCTV 개수랑 같아질 경우
        if (depth == pos.length) {
            // CCTV 공간 감시지대 탐색
            map = copyArray(originCopy);
            cnt = 0;
            for (Node node : pos) {
                int cctvNum = node.num;

                for (int i = 0; i < dirCnt[cctvNum - 1]; i++) {
                    fillDfs(node.y, node.x, node.num, node.dir, i);
                }

            }

            int totalArea = (H * W);
            totalArea -= (cnt + objectAreaCnt);
            result = Math.min(result, totalArea);

            return;
        }

        // 방향을 돌면서 진행
        Node cur = pos[depth];
        for (int i = 0; i < caseDir[cur.num - 1]; i++) {
            // 현재 깊이의 cur
            if (!posVisited[depth]) {
                pos[depth].dir = i;
                posVisited[depth] = true;
                backDfs(depth + 1);
                posVisited[depth] = false;
            }
        }
    }

    private static void fillDfs(int y, int x, int cctvNum, int dir, int dirCnt) {
        map[y][x] = -1;

        // 총 돌아야할 방향 수
        int ny = y + dirPos[cctvNum - 1][dir * 2][dirCnt];
        int nx = x + dirPos[cctvNum - 1][(dir * 2) + 1][dirCnt];

        if (ny >= 0 && ny < H && nx >= 0 && nx < W) {
            if (map[ny][nx] != 6) {
                if (map[ny][nx] == 0) {
                    cnt++;
                }
                fillDfs(ny, nx, cctvNum, dir, dirCnt);
            }
        }
    }

    static class Node {
        int x, y, num, dir;

        public Node(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }
    }
}
