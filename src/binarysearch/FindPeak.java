package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
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
		 * 
		 * 给定一个整数矩阵 A, 它有如下特性:

		相邻的整数不同
		矩阵有 n 行 m 列，n和m不会小于3。
		对于所有的 i < n, 都有 A[i][0] < A[i][1] && A[i][m - 2] > A[i][m - 1]
		对于所有的 j < m, 都有 A[0][j] < A[1][j] && A[n - 2][j] > A[n - 1][j]
		我们定义一个位置 [i,j] 是峰值, 当且仅当它满足:
		
		  A[i][j] > A[i + 1][j] && A[i][j] > A[i - 1][j] && 
		  A[i][j] > A[i][j + 1] && A[i][j] > A[i][j - 1]
		  
		  找到一个比周围都大的峰值
		  
		  我们对行二分，每次二分时将该行的最大值位置求出来，并与同一列的上下行数值作比较，
		  若比上一行和下一行都大，那么该位置即答案，直接返回，否则移动上下边界，
		  向比它大的的一侧移动（若两侧都比它大，向任意一个移动皆可）。
		  
		  
		  设置上边界为0，下边界为N - 1

		在mid找到最大值位置index（有多个最大值返回任意一个均可），
		若A[mid][index] > A[mid + 1][index]且A[mid][index] > A[mid - 1][index]，
		则该位置就是峰值，直接返回答案；否则若A[mid][index] < A[mid + 1][index]，
		那么上边界等于mid，若A[mid][index] 《 A[mid - 1][index]，下边界等于mid

		重复2，直到上下边界距离小于1
		
		比较上下边界所在行的最大值，返回最大值较大的那个位置
		
		N表示行数，M表示列数
		空间复杂度：O(1)
		时间复杂度：O(MlogN)

		 * @param A
		 * @return
		 */
		public List<Integer> findPeakII(int[][] A) {
	        // write your code here
			int n = A.length;

	        int up = 0, bottom = n - 1;

	        

	        while(up + 1 < bottom) {

	            int mid = up + (bottom - up) / 2;

	            int index = find_maxCol(A, mid);

	            //若上一行位置比当前位置值大，则下边界上移

	            if (A[mid][index] < A[mid - 1][index]) {

	                bottom = mid;

	            }

	            //若下一行位置比当前位置值大，则上边界下移

	            else if (A[mid][index] < A[mid + 1][index]) {

	                up = mid;

	            }

	            //否则该位置为峰值，直接返回答案

	            else {

	                return new ArrayList<Integer>(Arrays.asList(mid, index));

	            }

	        }

	        //比较上下边界上最大值，取较大的位置返回答案

	        int bottom_index = find_maxCol(A, bottom);

	        int up_index = find_maxCol(A, up);

	        if (A[up][up_index] < A[bottom][bottom_index]) {

	            return new ArrayList<Integer>(Arrays.asList(bottom, bottom_index));

	        }else {

	            return new ArrayList<Integer>(Arrays.asList(up, up_index));

	        }

	    }

	    

	    //find_maxCol用于找到当前行最大值所在列

	    private int find_maxCol(int[][] A, int row) {

	        int col = 0;

	        int m = A[0].length;

	        for (int i = 1; i < m; i++) {

	            if (A[row][col] < A[row][i]) col = i;

	        }

	        return col;

	    }
		
		
		
		
		
}
