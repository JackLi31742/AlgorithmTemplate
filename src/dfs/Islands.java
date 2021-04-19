package dfs;

public class Islands {

	
	/**
	 * 200. 岛屿数量
	 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

	 * @param grid
	 * @return
	 */
	public static int numIslands(char[][] grid) {
		if (grid==null||grid.length==0||grid[0].length==0) {
			return 0;
		}
		int row=grid.length;
		int col=grid[0].length;
		boolean[][]visited=new boolean[row][col];
		int count=0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j]=='1'&&!visited[i][j]) {
					dfs(grid, visited, row, col, i, j);
					count++;
				}
			}
		}
		return count;
    }
	
	public static void dfs(char[][] grid,boolean[][]visited,int row,int col,int i,int j) {
		
		if (check(grid, visited, row, col, i, j)) {
			
//			System.out.println(grid[i][j]+":"+visited[i][j]);
			visited[i][j]=true;
			dfs(grid, visited, row, col, i+1, j);
			dfs(grid, visited, row, col, i-1, j);
			dfs(grid, visited, row, col, i, j+1);
			dfs(grid, visited, row, col, i, j-1);
			
			
		}
	}
	
	public static boolean check(char[][] grid,boolean[][]visited,int row,int col,int i,int j) {
		if (i<0||i>=row||j<0||j>=col||grid[i][j]=='0'||visited[i][j]) {
			return false;
		}
		return true;
		
	}
	
	/**
	 * 695. 岛屿的最大面积
	 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
	 * @param grid
	 * @return
	 */
	public static int maxAreaOfIsland(int[][] grid) {
		if (grid==null||grid.length==0||grid[0].length==0) {
			return 0;
		}
		int row=grid.length;
		int col=grid[0].length;
		boolean[][]visited=new boolean[row][col];
		int count=0;
		int max=0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j]==1&&!visited[i][j]) {
					count=dfs(grid, visited, row, col, i, j);
					if (count>max) {
						max=count;
					}
				}
			}
		}
		return max;
    }
	
	public static int dfs(int[][] grid,boolean[][]visited,int row,int col,int i,int j) {
		
		int count=0;
		if (check(grid, visited, row, col, i, j)) {
			count++;
//			System.out.println(grid[i][j]+":"+visited[i][j]);
			visited[i][j]=true;
			count+=dfs(grid, visited, row, col, i+1, j);
			count+=dfs(grid, visited, row, col, i-1, j);
			count+=dfs(grid, visited, row, col, i, j+1);
			count+=dfs(grid, visited, row, col, i, j-1);
			
			
		}
		return count;
	}
	
	public static boolean check(int[][] grid,boolean[][]visited,int row,int col,int i,int j) {
		if (i<0||i>=row||j<0||j>=col||grid[i][j]==0||visited[i][j]) {
			return false;
		}
		return true;
		
	}
}
