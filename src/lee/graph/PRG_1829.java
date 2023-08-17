package lee.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 문제 이름(난이도) : 2017 카카오코드 예선 > 카카오 프렌즈 컬러링북 (LV2)
 * 시간 : 19.09 ms
 * 메모리: 92.5 MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/1829
 * */
public class PRG_1829 {
	private boolean[][] checked; // 방문 체크
    private int max; // 가장 큰 영역 칸 수
    // 방향 탐색 (상 하 좌 우)
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int cnt = 0; // 그림에 있는 영역 개수
        max = 0; // 가장 큰 영역 칸 수
        checked = new boolean[m][n]; // 방문 체크
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
            	// 방문했었거나 색칠하지 않는 영역이라면 continue
                if(checked[i][j] || picture[i][j] == 0) continue;
                // 방문 체크
                checked[i][j] = true;
                bfs(i, j, picture, m, n); // 영역 탐색
                cnt++; // 그림의 영역 개수 증가
            }//for j
        }//for i
        
        // 그림에 몇 개의 영역이 있는지와 가장 큰 영역은 몇 칸으로 이루어져 있는지를 리턴한다.
        return new int[] {cnt, max};
    }//solution
    
    // 영역 탐색 메서드 (행, 열, 그림, 그림의 크기(m, n) )
    private void bfs(int r, int c, int[][] picture, int m, int n) {
		Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c}); // 현재 행, 열 담기
        int color = picture[r][c]; // 현재 색
        int cnt = 1; // 현재 색깔 영역의 칸 개수
        
        int[] cur;
        while(!q.isEmpty()){
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            // 상하좌우 탐색
            for(int i=0; i<4; i++) {
                int nr = r + dr[i]; // 다음 행
                int nc = c + dc[i]; // 다음 열
                // 범위 체크, 방문체크, 같은 색인지 체크
                if(rangeCheck(nr, nc, m, n) 
                   || checked[nr][nc] || picture[nr][nc] != color) continue;
                
                checked[nr][nc] = true; // 방문 체크
                cnt++; // 영역 칸 개수 증가
                q.offer(new int[] {nr, nc}); // 다음칸 담기
            }//for
        }//while
        // 가장 큰 영역의 칸 개수 비교
        max = Math.max(cnt, max);
	}//bfs
    
    // 범위 체크 메서드
    private boolean rangeCheck(int r, int c, int m, int n) {
        return r < 0 || r >= m || c < 0 || c >= n; 
    }//rangeCheck

}//class
