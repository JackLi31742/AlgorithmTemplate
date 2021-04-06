package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class Islands {

	
	/**
	 * 433. 岛屿的个数
	 * @param grid
	 * @return
	 */
	public int numIslands(boolean[][] grid) {
        // write your code here
		
		if (grid==null||grid.length==0||grid[0].length==0) {
			return 0;
		}
		
		int row=grid.length;
		int col=grid[0].length;
		boolean[][] visited=new boolean[row][col];
		
		int[] xArr= {1,0,0,-1};
		int[] yArr= {0,1,-1,0};
		
		int num=0;
		
		for (int i = 0; i < row; i++) {
			
			for (int j = 0; j < col; j++) {
				
				if (grid[i][j]&&isValid(grid,visited, row, col, i, j)) {
					
					bfs(grid, visited, row, col, i, j, xArr, yArr);
					
					num++;
				}
			}
		}
		
		return num;
		
		
    }
	
	
	public void bfs(boolean[][] grid,boolean[][] visited,int row,int col,
			int i ,int j,int[] xArr,int[] yArr) {
		
		Queue<Position> queue=new ArrayDeque<Position>();
		
		queue.add(new Position(i, j));
		visited[i][j]=true;
		
		while(!queue.isEmpty()) {
			
			Position position = queue.poll();
			
			for (int k = 0; k < yArr.length; k++) {
				
				int nextX=position.i+xArr[k];
				int nextY=position.j+yArr[k];
				
				if (isValid(grid,visited, row, col, nextX, nextY)) {
					
					queue.add(new Position(nextX, nextY));
					
					visited[nextX][nextY]=true;
				}
				
			}
					
		}
	}
	
	public boolean isValid(boolean[][] grid,boolean[][] visited,int row,int col,int i ,int j) {
		
		if (i<0||i>=row||j<0||j>=col||!grid[i][j]||visited[i][j]) {
			return false;
		}
		
		return true;
	}
}

class Position{
	int i;
	int j;
	public Position(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
	@Override
	public String toString() {
		return "Position [i=" + i + ", j=" + j + "]";
	}
	
	
	
}
