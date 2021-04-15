package binarysearch;

import java.util.List;

public class Rotate {

	/**
	  * 33. 搜索旋转排序数组
	  * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

		( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
		
		搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
		
		你可以假设数组中不存在重复的元素。
	  */
	 
	 public static int search4(int[] nums, int target) {
		 int left=0;
		 int right=nums.length-1;
		 return search4(nums, target, left, right);
		 
	 }
	 
	 public static int search4(int[] nums, int target,int left,int right) {
		 
		 if (left<=right) {
			
			 if (nums[left]==target) {
				return left;
			 }
			 if (nums[right]==target) {
				return right;
			}
			 int mid=(right-left)/2+left;
			 if (nums[mid]==target) {
				 return mid;
			 }
			 //左侧有序
			 if (nums[left]<=nums[mid]) {
				 //target在左侧
				 if (nums[left]<=target&&target<=nums[mid]) {
					 return search4(nums, target, left, mid-1);
				 }else {//target在右侧
					 return search4(nums, target, mid+1, right);
				 }
			 }else {//右侧有序
				 //target在右侧
				 if (nums[mid]<=target&&target<=nums[right]) {
					 return search4(nums, target, mid+1, right);
				 }else {//target在左侧
					 return search4(nums, target, left, mid-1);
				 }
			 }
		}else {
//			if (nums[left]==target) {
//				return left;
//			}
			return -1;
		}
		 
	 }
	 
	 /**
	  * 33. 搜索旋转排序数组
	  * 非递归,替换search4(int[] nums, int target,int left,int right)
	  * @param nums
	  * @param target
	  * @param left
	  * @param right
	  * @return
	  */
	 public static int search42(int[] nums, int target,int left,int right) {
		 
		 while (left<=right) {
			
			 if (nums[left]==target) {
				return left;
			 }
			 if (nums[right]==target) {
				return right;
			}
			 int mid=(right-left)/2+left;
			 if (nums[mid]==target) {
				 return mid;
			 }
			 //左侧有序
			 if (nums[left]<=nums[mid]) {
				 //target在左侧
				 if (nums[left]<=target&&target<=nums[mid]) {
					 right=mid-1;
				 }else {//target在右侧
					 left=mid+1;
				 }
			 }else {//右侧有序
				 //target在右侧
				 if (nums[mid]<=target&&target<=nums[right]) {
					 left=mid+1;
				 }else {//target在左侧
					 right=mid-1;
				 }
			 }
		}
		 
//		 else {
//			if (nums[left]==target) {
//				return left;
//			}
			return -1;
//		}
		 
	 }
	 
	 
	 
	 /**
		 * 62. 搜索旋转排序数组 和上边的33一样
		 * 假设有一个排序的按未知的旋转轴旋转的数组
		 * (比如，0 1 2 4 5 6 7 可能成为4 5 6 7 0 1 2)。给定一个目标值进行搜索，
		 * 如果在数组中找到目标值返回数组中的索引位置，否则返回-1。你可以假设数组中不存在重复的元素。
		 * 
		 * 根据target的位置调用二分法
		 * @param A
		 * @param target
		 * @return
		 */
		public static int search(int[] A, int target) {
			
			if (A==null||A.length==0) {
				return -1;
			}
			
			int minIndex=findMinIndex(A);
			
			//还是按照最右边的数进行区分target的区间
			if (target<=A[A.length-1]) {
				
				return binarySearch(A, target, minIndex, A.length-1);
			}else {
				
				return binarySearch(A, target, 0, minIndex-1);
			}
		}
		
		public static int findMinIndex(int[] nums) {
	        // write your code here
			
			if (nums==null||nums.length==0) {
				return Integer.MIN_VALUE;
			}
			
			int left=0;
			int right=nums.length-1;
//			int target=nums[right];
			
			while(left+1<right) {
				
				int mid = left + (right-left)/2;
				//当right指针变化时，为了加快搜索速度，可以将target的值不写死
				if (nums[mid]<=nums[right]) {
					right=mid;
				}else {
					left=mid;
				}
			}
			
			return nums[left]>nums[right]?right:left;
	    }
		
		/**
		 * 
		 * @param A
		 * @param target
		 * @param left
		 * @param right
		 * @return
		 */
		public static int binarySearch(int[] A, int target ,int left,int right) {
			
			while(left + 1 <right) {
				
				int mid=left+(right-left)/2;
				if (A[mid]==target) {
					return mid;
				}else if (A[mid]<target) {
					left=mid;
				}else {
					right=mid;
				}
				
			}
			
			if (A[left]==target) {
				return left;
			}
			
			if (A[right]==target) {
				return right;
			}
			
			return -1;
		}
		
		/**
		 * 旋转数组在二分后，本质还是旋转数组
		 * 调用一次二分法
		 * @param A
		 * @param target
		 * @return
		 */
		public static int search2(int[] A, int target) {
	        // write your code here
			
			if (A==null||A.length==0) {
				return -1;
			}
			
			int left=0;
			int right=A.length-1;
			
			while(left+1<right) {
				
				int mid=left+(right-left)/2;
				
				if (target<=A[right]) {
					
//					if (A[mid]>A[right]) {
//						left=mid;
//					}else {
//						if (target>A[mid]) {
//							left=mid;
//						}else {
//							right=mid;
//						}
//					}
					
					if (target<=A[mid]&&A[mid]<=A[right]) {
						right=mid;
					}else {
						left=mid;
					}
					
				}else {
//					if (A[mid]>A[right]) {
//						if (target<A[mid]) {
//							right=mid;
//						}else {
//							left=mid;
//						}
//					}else {
//						right=mid;
//					}
					
					if (target>=A[mid]&&A[mid]>A[right]) {
						left=mid;
					}else {
						right=mid;
					}
					
				}
				
			}
			
			if (A[left]==target) {
				return left;
			}
			
			if (A[right]==target) {
				
				return right;
			}
			
			return -1;
					
	    }
		
		
		/**
		  * 面试题 10.03. 搜索旋转数组 33是特例，只旋转了一次
		  * 搜索旋转数组。给定一个排序后的数组，包含n个整数，
		  * 但这个数组已被旋转过很多次了，次数不详。
		  * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。
		  * 若有多个相同元素，返回索引值最小的一个。
		  * @param arr
		  * @param target
		  * @return
		  */
		 public static int search5(int[] arr, int target) {

			 int left=0;
			 int right=arr.length-1;
			 return search5(arr, target, left, right);
		 }
		 
		 public static int search5(int[] nums, int target,int left,int right) {
			 
			 while (left<=right) {
				
				 //为了找到索引最小的，一直让left去匹配
				 if (nums[left]==target) {
					return left;
				 }
//				 if (nums[right]==target) {
//					return right;
//				}
				 int mid=(right-left)/2+left;
//				 if (nums[mid]==target) {
//					 return mid;
//				 }
//				 System.out.println(left+":"+nums[left]+","+mid+":"+nums[mid]+","+right+":"+nums[right]);
				 //左侧有序
				 if (nums[left]<nums[mid]) {
					 
					//target在左侧
					 if (nums[left]<=target&&target<=nums[mid]) {
						 //为了搞定重复的，不能用+1或者-1
						 right=mid;
					 }else {//target在右侧
						 left=mid;
					 }
				 }else if(nums[left]>nums[mid]){//右侧有序

					 if (nums[mid]<=target&&target<=nums[right]) {
						 left=mid;
					 }else {//target在左侧
						 right=mid;
					 }
				 }else {
					left++;
				}
			}
			 

			return -1;

			 
		 }
		
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
		
		
		/**
		 * 183. 木材加工
		 * 有一些原木，现在想把这些木头切割成一些长度相同的小段木头，
		 * 需要得到的小段的数目至少为 k。当然，我们希望得到的小段越长越好，
		 * 你需要计算能够得到的小段木头的最大长度。
		 * 
		 * 无法切出要求至少 k 段的,则返回 0 即可。
		 * 
		 * 这是在答案范围上进行二分，即长度是有范围的，就是1到Max(L中的最大值,L中的sum/k)
		 * 
		 * 最后的长度，当超过这个长度时，会导致除以这个长度后的值<k
		 * @param L
		 * @param k
		 * @return
		 */
		public int woodCut(int[] L, int k) {
	        // write your code here
			
			if (L==null||L.length==0) {
				return 0;
			}
//			System.out.println(L.length);
			long left=1;
			long right=getMax(L, k);
			
			while(left+1 < right) {
				
				long mid=left+(right-left)/2;
				
				long result=getResult(L, mid);
				
				if (result<k) {//说明mid大了，导致分割的段少了
					
					right=mid;
				}else if (result>=k) {
					
					left=mid;
				}
//				else {
//					return (int)mid;
//				}

			}
			
			long max=0;
			
			if (getResult(L, left)>=k) {
				
				max=left;
			}
			
			if (getResult(L, right)>=k) {
				
				if (right>max) {
					max=right;
				}
			}
			
			return (int)max;
	    }
		
		
		public long getResult(int[] L,long len) {
			
			long result=0;
			
			for (int i = 0; i < L.length; i++) {
				result+=L[i]/len;
			}
			
//			System.out.println(result);
			return result;
		}
		
		
		public long getMax(int[] L,int k) {
			
			int max=L[0];
			long sum=L[0];
			
			for (int i = 1; i < L.length; i++) {
				
				if (L[i]>max) {
					max=L[i];
				}
				sum+=L[i];
			}
//			System.out.println(max);
//			System.out.println(sum);
			return Math.max(max, sum/k);
		}
		
		/**
		 * 面试题 10.09. 排序矩阵查找
		 * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
		 * 
		 * [
			  [1,   4,  7, 11, 15],
			  [2,   5,  8, 16, 19],
			  [3,   6,  9, 17, 22],
			  [10, 13, 14, 18, 24],
			  [18, 21, 23, 26, 30]
			]

		 * 从右上角15，如果15<target，往下走一行，（即使19的左边有比15大的，比如16，那也一定比19小，所以会走到左边）
		 * 如果15>target，往左走一行，说明target在左边
		 * 
		 * 从左下角，18<target，往右走
		 * 18>target，往上走
		 * @param matrix
		 * @param target
		 * @return
		 */
		public boolean searchMatrix(int[][] matrix, int target) {

			if (matrix==null||matrix.length<=0||matrix[0].length<=0) {
				return false;
			}
			
			int row = matrix.length;
			
			int col = matrix[0].length;
			
			int x=0;int y=col-1;
			
			while(x<row&&y>=0) {
				if (matrix[x][y]<target) {
					x++;
				}else if (matrix[x][y]>target) {
					y--;
				}else {
					return true;
				}
			}
			
			return false;
			
	    }
}
