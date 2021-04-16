package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestPath {
	
	final static int N = 10000;// 表示不可以连接
	
	static List<String> list;
	static int count=0;
	public static void main(String[] args) {
		
		int[][] m = { 
				{ 0, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 3, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 8, 7, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 6, 8, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 3, 5, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 8, 4, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 5, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 2, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3 } };
		
		
		list=new ArrayList<String>();
		
		list.add("A");
		list.add("B1");
		list.add("B2");
		list.add("C1");
		list.add("C2");
		list.add("C3");
		list.add("C4");
		list.add("D1");
		list.add("D2");
		list.add("D3");
		list.add("E1");
		list.add("E2");
		list.add("E3");
		list.add("F1");
		list.add("F2");
		list.add("G");
		
		
		int matrix[][] = {
			      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
			/*A*/ { N,   5,   7,   N,   N,   N,   2},
			/*B*/ { 5,   N,   N,   9,   N,   N,   3},
			/*C*/ { 7,   N,   N,   N,   8,   N,   N},
			/*D*/ { N,   9,   N,   N,   N,   4,   N},
			/*E*/ { N,   N,   8,   N,   N,   5,   4},
			/*F*/ { N,   N,   N,   4,   5,   N,   6},
			/*G*/ { 2,   3,   N,   N,   4,   6,   N}}; 

//		        System.out.println(minPath(m));;
//		        
//		        System.out.println(count);
		        
		        
		        
		        int[][]grid= {
		                       {1,3,1},
		                       {1,5,1},
		                       {4,2,1}
		                     };
		        
		        
//		        System.out.println(minPathSum(grid));;
		        
		        
		        int[][]grid1= {
	                       {1,0}
	                       
	                     };
//		        System.out.println(uniquePaths2(7, 3));
//		        System.out.println(count);
		        
//		        System.out.println(uniquePathsWithObstacles(grid1));
		        
		        
		        List<List<Integer>> triangle=new ArrayList<List<Integer>>();
		        
		        List<Integer> list1=Arrays.asList(2);
		        List<Integer> list2=Arrays.asList(3,4);
		        List<Integer> list3=Arrays.asList(6,5,7);
		        List<Integer> list4=Arrays.asList(4,1,8,3);
		        
		        triangle.add(list1);
		        triangle.add(list2);
		        triangle.add(list3);
		        triangle.add(list4);
		        
		        System.out.println(minimumTotal(triangle));;
	}
	/***
	 * 图的最短路径
	 * 从后向前递推，从后向前决策
	 * 这种解法应该不是动态规划，拉勾上边讲错了
	 * @param m
	 */
	public static int minPath(int[][] m) {
		
		return minPath(m, m[0].length-1);
	}
	
	/**
	 * 
	 * @param m
	 * @param end 到达的节点
	 * @return distance的返回值给了minPath，加上m[i][end]赋值给了tempDis
	 */
	public static int minPath(int[][] m,int end) {
		if (end==0) {
			return 0;
		}
		
		int distance=N;
		
		//由于每一轮都要找到前一轮，所以i不能是小于m[0].length，那样的话，之前的决策就没用了
		for (int i = 0; i < end; i++) {
			if (m[i][end]!=0) {
				count++;
				System.out.println(list.get(i)+"到"+list.get(end)+"的矩阵距离是："+m[i][end]);
				int tempDis=m[i][end]+minPath(m,i);
				//递归回溯相加后，就不是i到end的距离了，是一直累加的距离
				System.out.println("经过"+list.get(i)+"到"+list.get(end)+"的距离是："+tempDis);
				if (tempDis<distance) {
					distance=tempDis;
				}
			}
		}
		
		return distance;
	} 
	
	
	/**
	 * 64. 最小路径和
	 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
	 * @param grid
	 * @return
	 */
	public static int minPathSum(int[][] grid) {

		if (grid==null||grid.length<=0||grid[0].length<=0) {
			return -1;
		}
		
		int m=grid.length;
		int n=grid[0].length;
		
		return minPathSum2(grid, 0, 0, m-1, n-1);
    }
	
	
	
	
	public static int minPathSum2(int[][] grid,int startX,int StartY,int endX,int endY) {

		int[][] distance=new int[grid.length][grid[0].length];
		
		//将边界进行单独处理，省去了在循环中进行判断
		distance[0][0]=grid[0][0];
		
		for (int i = startX+1; i <= endX; i++) {
			distance[i][0]=distance[i-1][0]+grid[i][0];
		}
		
		for (int j = StartY+1; j <= endY; j++) {
			distance[0][j]=distance[0][j-1]+grid[0][j];
		}
		
		for (int i = startX+1; i <= endX; i++) {
			for (int j = StartY+1; j <= endY; j++) {
				
				
				int top=distance[i-1][j];
			
				int left=distance[i][j-1];
					
					
				distance[i][j]=Math.min(left, top)+grid[i][j];
				
			}
		}
		
		return distance[endX][endY];
    }
	
	/**
	 * 62. 不同路径
	 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

		机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
		
		问总共有多少条不同的路径？

		经过递推，f(m,n)=f(m-1,n)+f(m,n-1)
	 * @param m
	 * @param n
	 * @return
	 */
	//超时
	public static int uniquePaths(int m, int n) {
		if (m<=0||n<=0) {
			return 0;
		}
		if (m==1||n==1) {
			count++;
			return 1;
		}
		
		return uniquePaths(m-1, n)+uniquePaths(m, n-1);
    }
	
	/**
	 * 使用dp，dp[i][j]代表从起点走到该点的走法
	 * @param m
	 * @param n
	 * @return
	 */
	public static int uniquePaths2(int m, int n) {
		
		if (m<=0||n<=0) {
			return 0;
		}
		if (m==1||n==1) {
//			count++;
			return 1;
		}
		
		int[][]dp=new int[m][n];
		
		dp[0][0]=1;
		
		for (int i = 0; i < m; i++) {
			dp[i][0]=1;
		}
		
		for (int j = 0; j < n; j++) {
			dp[0][j]=1;
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j]=dp[i-1][j]+dp[i][j-1];
			}
		}
		
		return dp[m-1][n-1];
	}
	
	/**
	 * 63. 不同路径 II
	 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
	 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
	 * @param obstacleGrid
	 * @return
	 */
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid==null||obstacleGrid.length<=0||obstacleGrid[0].length<=0) {
			return 0;
		}
		
		int m=obstacleGrid.length;
		int n=obstacleGrid[0].length;
		
		int[][]dp=new int[m][n];
		
		if (obstacleGrid[0][0]!=1) {
			dp[0][0]=1;
		}
		
		
		for (int i = 0; i < m; i++) {
			if (obstacleGrid[i][0]!=1) {
				
				dp[i][0]=1;
			}else {
				break;
			}
		}
		
		for (int j = 0; j < n; j++) {
			
			if (obstacleGrid[0][j]!=1) {
				
				dp[0][j]=1;
			}else {
				break;
			}
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j]!=1) {
					
					dp[i][j]=dp[i-1][j]+dp[i][j-1];
				}
			}
		}
		
		return dp[m-1][n-1];
		
    }
	
	/**
	 * 62最后形成的树，很像杨辉三角
	 * 118. 杨辉三角
	 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
	 * @param numRows
	 * @return
	 */
	public List<List<Integer>> generate(int numRows) {
		

    }
	
	/**
	 * 120. 三角形最小路径和
	 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

		dp[i][j]:代表从顶到i行j列的元素的最小路径和
		dp[i][j]=min(dp[i-1][j],dp[i-1][j-1])+triangle.get(i).get(j)
		dp[0][0]=triangle.get(0).get(0)
	 * @param triangle
	 * @return
	 */
	public static int minimumTotal(List<List<Integer>> triangle) {
		if (triangle==null||triangle.size()==0) {
			return 0;
		}
		
		int iLen=triangle.size();
//		int jLen=triangle.get(iLen-1).size();
		
		int[][] dp=new int[iLen][];
		
		for (int i = 0; i < iLen; i++) {
			dp[i]=new int[triangle.get(i).size()];
		}
		
		dp[0][0]=triangle.get(0).get(0);
		
		for (int i = 1; i < iLen; i++) {
			dp[i][0]+=dp[i-1][0]+triangle.get(i).get(0);
		}
		
		
		for (int i = 1; i < iLen; i++) {
			for (int j = 1; j < triangle.get(i).size(); j++) {
//				System.out.println(dp[i-1][j]);
//				System.out.println(dp[i-1][j-1]);
//				System.out.println(triangle.get(i).get(j));
				
				//这有两个可能越界的地方，一个是如果dp的列按照jLen设置，triangle.get(i).get(j)会越界
				//如果dp按照triangle每一行的列的大小设置，dp[i-1][j]会越界
				if (j>=triangle.get(i-1).size()) {
					dp[i][j]=dp[i-1][j-1]+triangle.get(i).get(j);
				}else {
					
					dp[i][j]=Math.min(dp[i-1][j], dp[i-1][j-1])+triangle.get(i).get(j);
				}
			}
		}
		
		int min=dp[iLen-1][0];
		for (int j = 1; j < triangle.get(iLen-1).size(); j++) {
			if (dp[iLen-1][j]<min) {
				min=dp[iLen-1][j];
			}
		}
		
		return min;
    }
	
}
