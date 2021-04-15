package binarysearch;

public class BinarySearch {

	
	/**
	 * lintcode 457. 经典二分查找问题
	 * 在一个排序数组中找一个数，返回该数出现的任意位置，如果不存在，返回 -1。
	 * 通过 while 循环，将区间范围从 n 缩小到 2 （只有 start 和 end 两个点）。
		在 start 和 end 中判断是否有解。
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        // 要点1: start + 1 < end
        while (start + 1 < end) {
     // 要点2：start + (end - start) / 2
            int mid = start + (end - start) / 2;
            // 要点3：=, <, > 分开讨论，mid 不+1也不-1
            if (nums[mid] == target) {
                return mid;
                //大部分时候，mid 是可以 +1 和 -1 的。在一些特殊情况下，比如寻找目标的最后一次出现的位置时，
//                当 target 与 nums[mid] 相等的时候，是不能够使用 mid + 1 或者 mid - 1 的。
//                因为会导致漏掉解。那么为了节省脑力，统一写成 start = mid / end = mid 并不会造成任何解的丢失，
//                并且也不会损失效率——log(n) 和 log(n+1) 没有区别。
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        // 要点4: 循环结束后，单独处理start和end
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
	
	/**
	 * lintcode 458. 目标最后位置
	 * @param nums
	 * @param target
	 * @return
	 */
	public int lastPosition(int[] nums, int target) {
        // write your code here
		if (nums==null||nums.length==0) {
			return -1;
		}
		
		int left=0;
		int right=nums.length-1;
		
		while(left +1 < right) {
			
			int mid=left+(right-left)/2;
			
			if (nums[mid]==target) {
				left=mid;
			}else if (nums[mid]<target) {
				left=mid;
			}else {
				right=mid;
			}
		}
		
		if (nums[right]==target) {
			return right;
		}
		if (nums[left]==target) {
			return left;
		}
		
		return -1;
    }
	
	
		/**
		 * lintcode 14. 二分查找
		 * 用O(logn)的时间查找到target第一次出现的下标（从0开始）
		 * @param nums
		 * @param target
		 * @return
		 */
	 public int binarySearch(int[] nums, int target) {
	        // write your code here
		 
		 if (nums == null || nums.length ==0) {
			return -1;
		}
		 
		 int left=0;
		 int right=nums.length-1;
		 
		 while(left +1 <right) {
			 
			 int mid =left+(right-left)/2;
			 
			 if (nums[mid]==target) {
				right=mid;
			}else if (nums[mid]<target) {
				left=mid;
			}else {
				right=mid;
			}
		 }
		 
		 if (nums[left]==target) {
			return left;
		}
		 if (nums[right]==target) {
			return right;
		}
		 return -1;
	 }
	 
	 /**
	  * 447. 在大数组中查找
	  * 给一个按照升序排序的非负整数数组。这个数组很大以至于你只能通过固定的接口 ArrayReader.get(k) 
	  * 来访问第k个数(或者C++里是ArrayReader->get(k))，并且你也没有办法得知这个数组有多大。

		找到给出的整数target第一次出现的位置。你的算法需要在O(logk)的时间复杂度内完成，k为target第一次出现的位置的下标。

		如果找不到target，返回-1。
		
		
		本题既然无非得知数组的右边界，所以需要想办法得到右边界，需要使用倍增的思想去得到右边界
		
		每次倍增，那么当走到k时，其实用的是logK的时间走到的，然后再二分
	  * @param reader
	  * @param target
	  * @return
	  */
//	 public int searchBigSortedArray(ArrayReader reader, int target) {
//	        // write your code here
//		 
//		 int right=getNumLargerThanTarget(reader, target);
//		 
//		 int left=0;
//		 
//		 while(left+1<right) {
//			 
//			 int mid=left+(right-left)/2;
//			 
//			 if (reader.get(mid)<target) {
//				left=mid;
//			}else  {
//				//找第一个位置，向左找
//				right=mid;
//			
//			}
//		 }
//		 
//		 if (reader.get(left)==target) {
//			return left;
//		}
//		 
//		 if (reader.get(right)==target) {
//			return right;
//		}
//		 
//		 return -1;
//	 }
//	 
//	 public int getNumLargerThanTarget(ArrayReader reader, int target) {
//		 
//		 int index=1;
//		 while(reader.get(index)<=target) {
//			 index=index*2;
//		 }
//		 
//		 return index;
//	 }
	 
	 /**
	  * 585. 山脉序列中的最大值
	  * 给 n 个整数的山脉数组，即先增后减的序列，找到山顶（最大值）
	  * @param nums
	  * @return
	  */
	 public int mountainSequence(int[] nums) {

		 if (nums==null||nums.length==0) {
			return -1;
		}
		 
		 int left=0;
		 int right=nums.length-1;
		 
		 while(left+1<right) {
			 
			 //由于是向下取整，所以mid+1不会越界，但mid-1可能会
			 int mid=left+(right-left)/2;
			 //山顶
			 if (nums[mid]>nums[mid-1]&&nums[mid]>nums[mid+1]) {
				return nums[mid];
			 }else if (nums[mid]<nums[mid-1]) {//下坡
				right=mid;
			}else  {//上坡
				left=mid;
			}
		 }
		 
		 return Math.max(nums[left], nums[right]);
	 }
	 
	 /**
	  * //由于是向下取整，所以mid+1不会越界，但mid-1可能会
	  * @param nums
	  * @return
	  */
	public int mountainSequence2(int[] nums) {

		if (nums == null || nums.length == 0) {
			return -1;
		}

		int left = 0;
		int right = nums.length - 1;

		while (left + 1 < right) {

			 int mid=left+(right-left)/2;
			 
			 if (nums[mid]>nums[mid+1]) {//下坡
				right=mid;
			}else {//上坡
				left=mid;
			}
		}
		//即使在while循环中不return，无非多走了几次，不影响时间复杂度，
		//会使left和right最后趋近于山顶两侧
		return Math.max(nums[left], nums[right]);
	}
	
	/**
	 * 460. 在排序数组中找最接近的K个数
	 * 给一个目标数 target, 一个非负整数 k, 一个按照升序排列的数组 A。在A中找与target最接近的k个整数。
	 * 返回这k个数并按照与target的接近程度从小到大排序，如果接近程度相当，那么小的数排在前面。
	 * 
	 * 
	 * 找到target在A的位置，将A一分为二，接下来跟合并两个排序数组很像
	 * @param A
	 * @param target
	 * @param k
	 * @return
	 */
	public static int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
		
		if (A==null||A.length==0) {
			return new int[] {};
		}
		
		
		int pos=findInsert(A, target);
		
		
		int left=pos-1;
		int right=pos;
		
		int[] result=new int[k];
		int index=0;
		
		while(left>=0&&right<=A.length-1&&index<k) {
			
			int leftDif=(target-A[left]);
			int rightDif=(A[right]-target);
			
			if (leftDif<=rightDif) {
				result[index]=A[left];
				left--;
			} else {
				result[index]=A[right];
				right++;
			}
			index++;
		}
		
		while(left>=0&&index<k) {
			
			result[index]=A[left];
			left--;
			index++;
		}
		
		while(right<=A.length-1&&index<k) {
			
			result[index]=A[right];
			right++;
			index++;
		}
		
		return result;
		
    }
	
	/**
	 * 搜索插入的位置，得到的下标偏右
	 * @param A
	 * @param target
	 * @return
	 */
	public static int findInsert(int[] A, int target) {
		
		int left=0;
		int right=A.length-1;
		
		while(left<=right) {
			
			int mid=left+(right-left)/2;
			
			if (A[mid]>target) {
				right=mid-1;
			}else if (A[mid]<target) {
				left=mid+1;
			}else {
				return mid;
			}
		}
		
		return left;
	}
	
	
	/**
	 * 35. 搜索插入位置
	 * Arrays中的二分查找，如果没找到，返回的是-(left+1)
	 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
	 * @param nums
	 * @param target
	 * @return
	 */
	 public static int searchInsert(int[] nums, int target) {
		 int left=0;
		 int right=nums.length-1;
		 
		 while(left<=right) {
			 int mid=(right-left)/2+left;
			 if (nums[mid]>target) {
				right=mid-1;
			}else if (nums[mid]<target) {
				left=mid+1;
			}else {
				return mid;
			}
		 }
		 
		 return left;
	 }
	 
	 
	 /**
	  * 34. 在排序数组中查找元素的第一个和最后一个位置
	  * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

			你的算法时间复杂度必须是 O(log n) 级别。

			如果数组中不存在目标值，返回 [-1, -1]。

	  * @param nums
	  * @param target
	  * @return
	  */
	 public static int[] searchRange(int[] nums, int target) {
		 int left=0;
		 int right=nums.length-1;
		 int[] result= {-1,-1};
		 while(left<=right) {
			 int mid=(right-left)/2+left;
			 if (nums[mid]>target) {
				right=mid-1;
				
			}else if (nums[mid]<target) {
				left=mid+1;
			}else {
				int tempLeft=mid;
				while(tempLeft>=0&&nums[tempLeft]==target) {
					tempLeft--;
				}
				if (tempLeft>=0&&nums[tempLeft]==target) {
					
					result[0]=tempLeft;
				}else {
					result[0]=tempLeft+1;
				}
				
				int tempRight=mid;
				while(tempRight<nums.length&&nums[tempRight]==target) {
					tempRight++;
				}
				
				if (tempRight<nums.length&&nums[tempRight]==target) {
					result[1]=tempRight;
					
				}else {
					result[1]=tempRight-1;
				}
				//这里要return，或者break
				return result;
			}
		 }
		 
		 return result;
	 }
}
