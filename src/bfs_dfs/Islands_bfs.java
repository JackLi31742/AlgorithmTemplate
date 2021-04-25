package bfs_dfs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * dfs容易Stack Overflow
 * 
 * 矩阵的BFS
 *
 */
public class Islands_bfs {
	
	public static void main(String[] args) {
		
		int[][] grid= {
						{1,1,0},
						{1,1,0},
						{0,0,1}
						};
		
		boolean[][]grid2= {
				
				{true,true,false,false,false},
				{false,true,false,false,true},
				{false,false,false,true,true},
				{false,false,false,false,false},
				{false,false,false,false,true},
		};
		
		Islands_bfs islands=new Islands_bfs();
		
		islands.numIslands(grid2);
		
		System.out.println(findCircleNum(grid));;

	}

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
	
	
	
	/**
	 * lintcode 1179. 朋友圈 
	 * 其实就是求岛屿的数量，本次使用BFS
		一个班中有N 个学生。他们中的一些是朋友，一些不是。他们的关系传递。例如，如果A是B的一个直接朋友，
		而B是C的一个直接朋友，那么A是C的一个间接朋友。我们定义朋友圈为一组直接和间接朋友。
		给出一个N*N 矩阵M表示一个班中学生的关系。如果m〔i〕〔J〕＝1，那么第i个学生和第j个学生是直接朋友，
		否则不是。你要输出朋友圈的数量。
	 * @param M
	 * @return
	 */
	public static int findCircleNum(int[][] grid) {
        // Write your code here
		if (grid==null||grid.length==0||grid[0].length==0) {
			return 0;
		}
		
		int row=grid.length;
		int col=grid[0].length;
		boolean[]visited=new boolean[row];
		int count=0;
		
		Queue<Integer> queue=new LinkedList<Integer>();
		
		for (int i = 0; i < row; i++) {
			
			if (!visited[i]) {
				
				queue.add(i);
				
				while(!queue.isEmpty()) {
					
					int node = queue.poll();
					visited[node]=true;
					
					for (int j = 0; j < col; j++) {
						if (grid[node][j]==1&&!visited[j]) {
							
							queue.add(j);
						}
					}
				}
				
				count++;
			}
			
		}
		
		return count;
    }
	 
	
	
	/**
	 * 695. 岛屿的最大面积
	 * @param grid
	 * @return
	 */
	public int maxAreaOfIsland(int[][] grid) {

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
