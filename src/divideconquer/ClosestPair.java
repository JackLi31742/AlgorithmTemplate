package divideconquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 二维平面上有 n 个点，如何快速计算出两个距离最近的点对？
 *  LEETCODE 612
 *  
 */
public class ClosestPair {

	public static void main(String[] args) {
		Point p1=new Point(2, 3);
		Point p2=new Point(12, 30);
		Point p3=new Point(40, 50);
		Point p4=new Point(5, 1);
		Point p5=new Point(12, 10);
		Point p6=new Point(3, 4);
		Point[] points = {p1,p2,p3,p4,p5,p6}; 
		
		System.out.println(Math.sqrt(closestDistance(points)));;
	}
	
	public static double closestDistance(Point[] points) {
		Comparator<Point> comparatorX=new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				return o1.x-o2.x;
			}
		};
		Arrays.sort(points, comparatorX);
		
		return closestDistance(points, 0, points.length-1);
	}
	
	public static double closestDistance(Point[] points,int left,int right) {
		if (left<right) {
			int mid=left+(right-left)/2;
			//分
			double leftDis=closestDistance(points, left, mid);
			double rightDis=closestDistance(points, mid+1, right);
			
			
			double d=Math.min(leftDis, rightDis);
			
			List<Point> strip=new ArrayList<Point>();
			
			for (int i = 0; i < points.length; i++) {
				if (Math.abs(points[i].x-points[mid].x)<=d) {
					strip.add(points[i]);
				}
			}
			//治
			return getStripDis(strip, d);
		}
		//因为是求最小值，所以自身到自身的距离设置为最大，如果设置为0，那么最后就会返回0
		return Double.MAX_VALUE;
	}
	
	public static double getStripDis(List<Point> strip,double d) {
		
		double min=d;
		for (int i = 0; i < strip.size()-1; i++) {
			for (int j = i+1; j < strip.size(); j++) {
				double dis=getDis(strip.get(i), strip.get(j));
				if (dis<min) {
					min=dis;
				}
			}
		}
		
		return min;
	}
	
	/**
	 * 距离是最小，距离的平方也是最小的
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double getDis(Point p1,Point p2) {
		
		return Math.pow((p1.x-p2.x), 2)+Math.pow((p1.y-p2.y), 2);
	}
}

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
}