package list;

import java.util.LinkedList;

/**
 * 已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围，
 * 从编号为k的人开始报数，数到m的那个人出列；他的下一个人又从1开始报数，
 * 数到m的那个人又出列；依此规律重复下去，直到圆桌周围的人只有一个没有出列
 * @author LANG
 *
 */
public class Joseph2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n=5;
		int k=1;
		int m=2;
		LinkedList<Integer> list=new LinkedList<Integer>();
		
		for (int i = 0; i < n; i++) {
			//编号从1开始
			list.add(i+1);
		}
		
		yuesefu(n, k, m, list);
	}
	
	/**
	 * 
	 * @param n
	 * @param k
	 * @param m
	 */
	public static void yuesefu(int n,int k, int m,LinkedList<Integer> list){
		if (list.isEmpty()||n<1||k<=0) {
			System.out.println("非法");
			return;
		}
		//自己就算一个
		int count=1;
		//先走到这个编号对应的人
		while(!list.isEmpty()&&count<k) {
			list.add(list.pop());
			count++;
		}
		count=1;
		
		while(!list.isEmpty()) {
			if (count<m) {
				list.add(list.pop());
				count++;
			}else {
				count=1;
				System.out.println(list.pop());
			}
		}
	}

}
