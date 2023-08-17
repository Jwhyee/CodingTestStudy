package lee.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
/**
 * 문제 이름(난이도) : 안전 영역 (SIL1)
 * 시간 : 196 ms
 * 메모리: 15916 KB
 * 링크 : https://www.acmicpc.net/problem/2468
 * */
public class BOJ_2468 {
	static int N; // 지도의 크기
	static int[][] map; // 지도
	// 방향 탐색 ( 상 하 좌 우 )
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		// 영역의 높이를 담을 Set (중복을 없애기 위함)
		Set<Integer> hSet = new TreeSet<>();
		
		int maxH = 0; // 최고 높이
		map = new int[N][N]; 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, map[i][j]); // 최고 높이 갱신
				hSet.add(map[i][j]); // 높이 담아줌
			}//for j
		}//for i
		hSet.remove(maxH); // 최고높이면 다 잠기므로 필요 없음.
		
		int max = 1; // 안전 영역 최대 개수 (아무 지역도 물에 잠기지 않을 수도 있다.)
		Iterator<Integer> it = hSet.iterator();
		while(it.hasNext()) {
			int height = it.next(); // 비의 양
			int cnt = 0; // 안전 영역 개수
			boolean[][] visited = new boolean[N][N]; // 방문 체크
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 방문한적 없고 비에 안잠기는 영역이면
					if(!visited[i][j] && map[i][j] > height) {
						dfs(i, j, visited, height); // 탐색
						cnt++; // 안전 영역 개수 증가
					}//if
				}//for j
			}//for i
			
			max = Math.max(max, cnt); // 안전 영역 최대 개수 갱신
		}//while
		
		// 안전한 영역의 최대 개수를 출력한다.
		System.out.print(max);
	}//main

	// 영역 탐색 메서드
	private static void dfs(int r, int c, boolean[][] visited, int height) {
		visited[r][c] = true;
		
		// 상하좌우 탐색
		for(int i=0; i<4; i++) {
			int nr = r + dr[i]; // 다음 행
			int nc = c + dc[i]; // 다음 열 
			// 범위 벗어나는지 확인
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			// 방문 확인, 물에 잠기는 영역인지 확인
			if(visited[nr][nc] || map[nr][nc] <= height) continue;
			dfs(nr, nc, visited, height); // 다음  영역 탐색
		}//for
		
	}//dfs

}//class
