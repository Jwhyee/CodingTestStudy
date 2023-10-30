package ka.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 트리의 지름 (GOL2)
 * 시간 : 772 ms
 * 메모리 : 96928 KB
 * 링크 : https://www.acmicpc.net/problem/1167
 */
public class BOJ_1167 {
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int C, max, leaf;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        C = T;

        graph = new ArrayList[T + 1];
        visited = new boolean[T + 1];

        for (int i = 1; i <= T; i++) {
            graph[i] = new ArrayList<>();
        }

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child;
            while ((child = Integer.parseInt(st.nextToken())) != -1) {
                int point = Integer.parseInt(st.nextToken());
                graph[parent].add(new Node(child, point));
            }
        }
        dfs(1, 0);


        visited = new boolean[visited.length];
        max = 0;
        dfs(leaf, 0);

        System.out.println(max);
    }

    private static void dfs(int cur, int point) {
        visited[cur] = true;

        for (int i = 0; i < graph[cur].size(); i++) {
            Node node = graph[cur].get(i);
            if (!visited[node.num]) {
                dfs(node.num, point + node.point);
            }
        }

        if (max < point) {
            leaf = cur;
            max = point;
        }
    }

    static class Node {
        int num, point;

        public Node(int num, int point) {
            this.num = num;
            this.point = point;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", point=" + point +
                    '}';
        }
    }
}
