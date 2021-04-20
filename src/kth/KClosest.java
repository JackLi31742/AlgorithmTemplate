package kth;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosest {

	
	public static void main(String[] args) {
		Point p1=new Point(4, 6);
		Point p2=new Point(4, 7);
		Point p3=new Point(4, 4);
		Point p4=new Point(2, 5);
		Point p5=new Point(1, 1);
		
		Point origin=new Point(0,0);
		
		Point[] points= {p1,p2,p3,p4,p5};
		KClosest kClosest=new KClosest();
		kClosest.kClosest(points, origin, 3);
	}
	/**
	 * 612. K个最近的点
		给定一些 points 和一个 origin，从 points 中找到 k 个离 origin 最近的点。
		按照距离由小到大返回。如果两个点有相同距离，则按照x值来排序；若x值也相同，就再按照y值排序。
	 * @param points
	 * @param origin
	 * @param k
	 * @return
	 */
	
	
	public Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
		
		Point[] result=new Point[k];
		
		if (points==null||origin==null) {
			return result;
		}
		
		
		
		Comparator<Point> comparator=new Comparator<Point>() {

			//大顶堆得2-1
			@Override
			public int compare(Point p1, Point p2) {
				// TODO Auto-generated method stub
				
				return compare(p1, p2, origin);
			}

			private int compare(Point p1, Point p2, Point origin) {
				// TODO Auto-generated method stub
				return -diff(p1, p2, origin);
			}
		};
		
		PriorityQueue<Point> maxHeap =new PriorityQueue<Point>(k,comparator);
		
		
		for (int i = 0; i < points.length; i++) {
			
			if (maxHeap.size()<k) {
				
				maxHeap.add(points[i]);
				continue;
			}
			
			int compare=diff(maxHeap.peek(),points[i], origin);
			
			if (compare>0) {
				maxHeap.poll();
				maxHeap.add(points[i]);
			}
		}
		
		for (int i = result.length-1; i >=0; i--) {
			result[i]=maxHeap.poll();
		}
		
		return result;
    }
	
	public int diff(Point p1, Point p2,Point origin) {
		
		double distance1=Math.pow(p1.x-origin.x,2)+Math.pow(p1.y-origin.y, 2);
		double distance2=Math.pow(p2.x-origin.x,2)+Math.pow(p2.y-origin.y, 2);
		
		double difDis=distance1-distance2;
		if (difDis!=0) {
			return (int)difDis;
		}else {
			int compareX=p1.x-p2.x;
			
			if (compareX!=0) {
				return compareX;
			}else {
				return p1.y-p2.y;
			}
		}
	}
	
	
	public double distance(Point origin,Point point) {
		
		return Math.pow(origin.x-point.x,2)+Math.pow(origin.y-point.y, 2);
	}
}

class Point {
	int x;
	int y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(int a, int b) {
		x = a;
		y = b;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
}
