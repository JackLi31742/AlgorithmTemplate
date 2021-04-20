package array;

import java.util.Arrays;

public class TriangleCount {

	/**
	 * lintcode 382. 三角形计数
	 * 给定一个整数数组，在该数组中，寻找三个数，分别代表三角形三条边的长度，问，可以寻找到多少组这样的三个数来组成三角形？
	 * 排序后，
	 * a<=b<=c充要条件
	 * a+b>c
	 * 同时如果a+b>c，那么(a到b之间的数)+b>c
	 * @param S
	 * @return
	 */
	public static int triangleCount(int[] S) {
        // write your code here
		if (S==null||S.length<3) {
			return 0;
		}
		Arrays.sort(S);
		int result=0;
		//控制最大值，比较不容易漏掉
		for (int i = S.length-1; i >=2; i--) {
//			if (i==0||S[i]>S[i-1]) {
				result+=triangleCount(S,i);
//			}
		}
		return result;
    }
	
	public static int triangleCount(int[] S,int i) {
		
		int left=0;
		int right=i-1;
		int count=0;
		while(left<right) {
			
			if (S[left]+S[right]>S[i]) {
				count+=right-left;
				right--;
			}else {
				left++;
			}
		}
		
		return count;
	}
}
