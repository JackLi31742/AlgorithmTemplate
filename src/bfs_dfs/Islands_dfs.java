package bfs_dfs;

import java.util.List;

import dfs.Point;

public class Islands_dfs {

	
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
	
	
	
	/**
	 * 434 · 岛屿的个数II
	 * 给定 n, m, 分别代表一个二维矩阵的行数和列数, 并给定一个大小为 k 的二元数组A. 
	 * 初始二维矩阵全0. 二元数组A内的k个元素代表k次操作, 设第i个元素为 (A[i].x, A[i].y), 
	 * 表示把二维矩阵中下标为A[i].x行A[i].y列的元素由海洋变为岛屿. 问在每次操作之后, 二维矩阵中岛屿的数量. 你需要返回一个大小为k的数组.

设定0表示海洋, 1代表岛屿, 并且上下左右相邻的1为同一个岛屿.

定义一个矩阵记录地图状态, 还需要定义初始含 n×m
 个集合的并查集, 以及一个计数器, 记录当前岛屿的个数.

对于每一次操作(x, y), 如果(x, y)的上下左右都是0, 那么计数器加一; 如果不全为0, 则:

并查集查询其四周的1所属的集合, 假设它们属于 k 个不同的集合
计数器减去 k-1
将这 k 个集合, 连同 (x, y), 合并

并查集的实现可以利用Point结构体, 也可以将二维坐标转换成一维.

使用并查集，每个格子作为一个节点。当一个格子变成岛屿，和它的四个邻居依次连
接，相当于在图中加四条边
	 * @param n
	 * @param m
	 * @param operators
	 * @return
	 */
	public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // write your code here
    }
}
