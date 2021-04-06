package array;

import java.util.Arrays;
import java.util.HashMap;

public class NSum {

	
	/**
	 * 1. 两数之和
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

		你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

		由于要返回下标，所以用哈希的方法更好，否则可以先对数组进行排序，再用双指针
		判断nums[left]+nums[right]>target，那么说明需要right--，因为nums[right]加上最小值还要比target大，所以right下标
		的值肯定是不符合要求的，
		
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
        	//遍历到后边的时候，如果有值等于target-nums[i]，那肯定已经在map中了
        	//否则就是还没有遍历到，继续遍历即可
			if (map.get(target-nums[i])==null) {
				map.put(nums[i], i);
			}else {
				return new int[] {map.get(target-nums[i]),i};
			}
		}
        return null;
    }
	
	/**
	 * 双指针
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum12(int[] nums, int target) {
		
		if (nums==null||nums.length<2) {
			return null;
		}
		
		int[]temp=Arrays.copyOf(nums, nums.length);
		
		Arrays.sort(temp);
		int[] result=new int[2];
		
		int left=0;
		int right=nums.length-1;
		int i=left;
		int j=right;
		while(left<right) {
			
			if (temp[left]+temp[right]<target) {
				left++;
			}else if (temp[left]+temp[right]>target) {
				right--;
			}else {
				i=left;
				j=right;
				break;
			}
		}
		
		for (int k = 0; k < nums.length; k++) {
			if (nums[k]==temp[i]) {
				result[0]=k;
				break;
			}
		}
		for (int k = 0; k < nums.length; k++) {
			if (nums[k]==temp[j]&&k!=result[0]) {
				result[1]=k;
				break;
			}
		}
		
		return result;
	}
}
