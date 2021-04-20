package binarysearch;

import java.util.List;

public class FindPeak {


		

		
		/**
		 * 75. 寻找峰值
		
			你给出一个整数数组(size为n)，其具有以下特点：

			相邻位置的数字是不同的
			A[0] < A[1] 并且 A[n - 2] > A[n - 1]
			假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，
			返回数组中任意一个峰值的位置。
			
			
			要想有峰值，必须是先上升后下降
		 * @param A
		 * @return
		 */
		public int findPeak(int[] A) {
	        // write your code here
			if (A==null||A.length==0) {
				return -1;
			}
			
			int left=0;
			int right=A.length-1;
			
			while(left+1<right) {
				
				int mid=left+(right-left)/2;
				
				if (mid==0) {
					left=mid;
				}else if (mid==A.length-1) {
					right=mid;
				}else {
					if (A[mid]>A[mid-1]&&A[mid]>A[mid+1]) {
						return mid;
					}else if (A[mid]>A[mid-1]&&A[mid+1]>A[mid]) {//上坡
						left=mid;
					}else if (A[mid]<A[mid-1]&&A[mid+1]<A[mid]) {//下坡
						right=mid;
					}else {
						//由于在谷底的时候左右都可以，所以上边的判断其实可以简化不需要用&&
						left=mid;
//						right=mid;
					}
				}
			}
			
			return A[left]>A[right]?left:right;
	    }
		
		/**
		 * 390. 找峰值 II

		 * @param A
		 * @return
		 */
		public List<Integer> findPeakII(int[][] A) {
	        // write your code here
	    }
		
		
		
		
		
}
