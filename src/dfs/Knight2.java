package dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * 骑士周游，输出骑士在棋盘上所有的位置
 * @author HP
 *
 */
public class Knight2 {
	
	public static void main(String[] args) {
		int n=6;
		int[][]board=new int[n][n];
		
		long start = System.currentTimeMillis();
		KnightTour(board, 2, 1, n);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]+"\t");
			}
			System.out.println();
		}
	}

	public static void KnightTour(int[][]board,int x,int y,int n) {
		int[] xarr= {1,2,2,1,-1,-2,-2,-1};
		int[] yarr= {-2,-1,1,2,2,1,-1,-2};
		
		int step=0;
		boolean[][]visited=new boolean[n][n];
		Point start=new Point(x, y);
		
		
		//标记已访问
		visited[start.x][start.y]=true;
		//传入步
		board[start.x][start.y]=step;
				
		Comparator<Point> comparator=new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				ArrayList<Point> list1=getNext(o1, xarr, yarr, n, visited);
				ArrayList<Point> list2=getNext(o2, xarr, yarr, n, visited);
				
				return list1.size()-list2.size();
			}
		};
		
		
		KnightTour(board, start, xarr, yarr, n,step,visited,comparator,start);
	}
	
	public static boolean KnightTour(int[][]board,Point p,
			int[] xarr,int[] yarr,int n,int step,boolean[][]visited,
			Comparator<Point> comparator,Point start) {
		
		//这里应该是做了减枝
		if (step==n*n-1) {
			System.out.println(step);
			ArrayList<Point> list1=getNext(p,xarr,yarr,n,visited);
			System.out.println(list1);
			if (list1.contains(start)) {
				
				return true;
			}else {
				return false;
			}
			
			
		}
		
				
		//找到下面所有可以走的位置
		ArrayList<Point> list=getNext(p,xarr,yarr,n,visited);
		//排序后，相当于用贪心算法找到了下一个最少的可能性，一旦回溯，次数也少
		Collections.sort(list,comparator);
//		System.out.println(list);
		
		while(!list.isEmpty()){
			//每次都取第一个，并移除
			Point next=list.remove(0);
			if (check(next, n,visited)) {
				//在进入下一个递归的时候赋值
				visited[next.x][next.y]=true;
				board[next.x][next.y]=step+1;
				if(KnightTour(board,next,xarr,yarr,n,step+1,visited,comparator,start)){
					return true;
				}else {
					visited[next.x][next.y]=false;
					board[next.x][next.y]=0;
				}
			}
		}
		
//		if (step==n*n-1) {
//			return true;
//		}else {
//			visited[p.x][p.y]=false;
//			board[p.x][p.y]=0;
//		}
		
		
		return false;
	}
	
	/**
	 * 得到该点的下一个位置
	 * @param p
	 * @param xarr
	 * @param yarr
	 * @param n
	 * @return
	 */
	public static ArrayList<Point> getNext(Point p,int[] xarr,int[] yarr,int n,boolean[][]visited){
		
		ArrayList<Point> list=new ArrayList<>();
		for (int i = 0; i < xarr.length; i++) {
			int nextX=p.x+xarr[i];
			int nextY=p.y+yarr[i];
			//这里重新new了对象，把之前的visited信息丢了
			Point next=new Point(nextX, nextY);
//			if (check(next, n,visited)) {
				list.add(next);
//			}
		}
		
		return list;
	}
	
	public static boolean check(Point p,int n,boolean[][]visited) {
		if (p.x>=0&&p.x<n&&p.y>=0&&p.y<n&&!visited[p.x][p.y]) {
			return true;
		}else {
			return false;
		}
	}
	
	
}

class Point{
	int x;
	int y;
//	boolean visited;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}