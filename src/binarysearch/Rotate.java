package binarysearch;

public class Rotate {

	/**
	  * 33. 搜索旋转排序数组
	  * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

		( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
		
		搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
		
		你可以假设数组中不存在重复的元素。
	  */
	 
	 public static int search(int[] nums, int target) {
		 int left=0;
		 int right=nums.length-1;
		 return search41(nums, target, left, right);
		 
	 }
	 
	 public static int search41(int[] nums, int target,int left,int right) {
		 
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
					 return search41(nums, target, left, mid-1);
				 }else {//target在右侧
					 return search41(nums, target, mid+1, right);
				 }
			 }else {//右侧有序
				 //target在右侧
				 if (nums[mid]<=target&&target<=nums[right]) {
					 return search41(nums, target, mid+1, right);
				 }else {//target在左侧
					 return search41(nums, target, left, mid-1);
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
	  * 非递归
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
				
					
					if (target<=A[mid]&&A[mid]<=A[right]) {
						right=mid;
					}else {
						left=mid;
					}
					
				}else {

					
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
//			 if (nums[right]==target) {
//				return right;
//			}
			 int mid=(right-left)/2+left;
//			 if (nums[mid]==target) {
//				 return mid;
//			 }
//			 System.out.println(left+":"+nums[left]+","+mid+":"+nums[mid]+","+right+":"+nums[right]);
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
}
