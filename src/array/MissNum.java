package array;

import java.util.Arrays;
import java.util.HashSet;

public class MissNum {
	
	
	public static void main(String[] args) {
		int[]nums= {0,1,2};
		
		System.out.println(firstMissingPositive4(nums));;
	}

	/**
	 * 41. 缺失的第一个正数
	 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
	 * 
	 * 将数组排序后进行下标讨论，情况太多太复杂
	 * 可以排序后使用二分查找，查找[1,N]的数是否在nums中出现过
	 * @param nums
	 * @return
	 */
	
	/**
	 * 采用哈希
	 * 对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1, N+1] 中
	 * @param nums
	 * @return
	 */
	public static int firstMissingPositive2(int[] nums) {
		
		HashSet<Integer> set=new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}
		int result=0;
		int index=0;
		while(index<set.size()) {
			//从1开始遍历
			if (!set.contains(index+1)) {
				result=index+1;
				break;
			}
			index++;
		}
		if (result==0) {
			result=index+1;
		}
		return result;
	}
	
	/**
	 * 排序后使用二分
	 * @param nums
	 * @return
	 */
	public static int firstMissingPositive3(int[] nums) {
		if (nums==null||nums.length==0) {
			return 1;
		}
		Arrays.sort(nums);
		int result=0;
		for (int i = 1; i <= nums.length; i++) {
			int index = Arrays.binarySearch(nums, i);
			if (index<0) {
				result=i;
				break;
			}
		}
		
		if (result==0) {
			result=nums.length+1;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param nums
	 * @return
	 */
	public static int firstMissingPositive4(int[] nums) {
		if (nums==null||nums.length==0) {
			return 1;
		}
		int index=0;
		int len=nums.length;
		while(index<len) {
			//赋值给x后，写判断时，逻辑会好懂
			int x=nums[index];
			//由于是下标，所以用的nums[x-1]
			if (x>=1&&x<=len&&x!=nums[x-1]) {
				swip(nums, index, x-1);
			}else {
				index++;
			}
		}
		
		int result=0;
		for (int i = 0; i < len; i++) {
			int x=nums[i];
			//x!=nums[x-1]容易数组越界
			if (i+1!=x) {
				result=i+1;
				break;
			}
			
		}
		//说明走完了nums数组，没有缺失
		if (result==0) {
			result=len+1;
		}
		return result;
	}
	
	public static void swip(int[] nums,int i,int j) {
		int temp=nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
}
