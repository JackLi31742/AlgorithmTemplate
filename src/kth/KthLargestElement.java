package kth;

public class KthLargestElement {

	/**
	 * 5. 第k大元素
		在数组中找到第 k 大的元素。
		要求时间复杂度为O(n)，空间复杂度为O(1)。
	 * @param n
	 * @param nums
	 * @return
	 */
	public int kthLargestElement(int k, int[] nums) {
        // write your code here
		if (nums==null||nums.length==0|| k < 1 || k > nums.length) {
			return -1;
		}
		
		int partition=sort(k, nums, 0, nums.length-1);
		
		return nums[partition];
    }
	
	public int sort(int k, int[] nums,int left,int right) {
		
		if (left>=right) {
			return left;
		}
		
		int partition=partition(k,nums,left,right);
		
		if (partition<nums.length-k) {
			//这是递归，要返回值，否则partition进了递归再出来，没有任何改变
			return sort(k,nums,partition,right);
		}else if(partition>nums.length-k){
			return sort(k,nums,left,partition-1);
		}
		return partition;
	}
	
	public int partition(int k, int[] nums,int left,int right) {
		
		
		
		int mid=left+(right-left)/2;
		int pivot=nums[mid];
		
		while(left<=right) {
			
			while(left<=right&&nums[left]<pivot) {
				left++;
			}
			
			while(left<=right&&nums[right]>pivot) {
				right--;
			}
			
			if (left<=right) {
				int temp=nums[left];
				nums[left]=nums[right];
				nums[right]=temp;
				left++;
				right--;
			}
		}
		
		return left;
	}
}
