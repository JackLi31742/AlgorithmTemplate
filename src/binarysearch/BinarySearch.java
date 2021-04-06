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
}
