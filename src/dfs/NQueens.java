package dfs;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

	public static void main(String[] args) {
		NQueens nQueens=new NQueens();

		
		System.out.println(nQueens.solveNQueens(4));;
		
		
	}
	
	int count=0;
	//定义一个max表示共有多少个皇后
	int max=0;
	//定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3} 
	int [] arr=null;
	
	
	List<List<String>> result=null;
	/**
	 * 51. N皇后
	 * 面试题 08.12. 八皇后
	 * @param n
	 * @return
	 */
	public List<List<String>> solveNQueens(int n) {
		max=n;
		arr=new int[max];
		result=new ArrayList<List<String>>();
		check(0);
		
		return result;
	}
	
	/**
	 * 52. N皇后 II
	 * 不用dp，用的是dfs
	 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量
	 */
	public int totalNQueens(int n) {
		
		max=n;

		arr=new int[max];
		check(0);
		return count;
		
    }
	
	//特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
	public void check(int n){
		if (n==max) {//n = 8 , 其实8个皇后就既然放好
			count++;
//			print();
				
			getRestlt();
			
			return ;
		}
		//依次放入皇后，并判断是否冲突
		for (int i = 0; i < max; i++) {
			//先把当前这个皇后 n , 放到该行的第1列
			arr[n]=i;
			//判断当放置第n个皇后到i列时，是否冲突
			if (judge(n)) {// 不冲突
				//接着放n+1个皇后,即开始递归
				check(n+1);
			}
			//如果冲突，就继续执行 array[n] = i+1; 即将第n个皇后，放置在本行的后移的一个位置
		}
		
	}
	
	//查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
	public boolean judge(int n){
		for (int i = 0; i < n; i++) {
			//1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
			//2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一 斜线
			// n = 1  放置第 2列 1 n = 1 array[1] = 1
			// Math.abs(1-0) == 1  Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
			//3. 判断是否在同一行, 没有必要，因为用下标代表行，n 每次都在递增
			if (arr[i]==arr[n]||Math.abs(n-i)==Math.abs(arr[i]-arr[n])) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 要求的结果中，result的每一个row的每一行，都是一个字符串，".Q.."
	 * arr中的i代表了行，arr[i]代表了列，而要求的结果中Q出现的位置，也是列
	 */
	public void getRestlt() {
		List<String> row = new ArrayList<String>();
		
		for (int i = 0; i < max; i++) {
			StringBuilder sb=new StringBuilder();
			for (int j = 0; j < arr[i]; j++) {
				sb.append(".");
			}

			sb.append("Q");
			for (int j = arr[i]+1; j < max; j++) {
				sb.append(".");
			}
			row.add(sb.toString());
		}
		
		result.add(row);
	}
	
	//写一个方法，可以将皇后摆放的位置输出
		private void print() {
			count++;
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
}
