package array;

import java.util.Arrays;

public class RemoveElement {

	
	public static void main(String[] args) {
		int[]nums= {0,0,1,1,1,1,2,3,3};
//		removeElement(nums, 0);;
		System.out.println(removeDuplicates2(nums));;
		System.out.println(Arrays.toString(nums));
	}

	/**
	 * 27. 移除元素
	 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
	 * @param nums
	 * @param val
	 * @return
	 */
	public static int removeElement(int[] nums, int val) {
		int i = 0,j=nums.length-1;
		while(i<nums.length&&j>=0) {
			
			if (nums[i]==val) {
				while (j>=0&&nums[j]==val) {
					j--;
				}
				if (i<j) {
					
					swip(nums, i, j);
				}
			}
			i++;
		}
		
//		System.out.println(i);
//		System.out.println(j);
		return j+1;
		
    }
	
	public static void swip(int[] nums,int i,int j) {
		int temp=nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
	
	/**
	 * 26. 删除排序数组中的重复项
	 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates(int[] nums) {
		int i = 0,j=i+1;
		while(i<nums.length&&j<nums.length) {
			while (j<nums.length&&nums[i]==nums[j]) {
				j++;
			}
			if (j<nums.length) {
				
				nums[i+1]=nums[j];
				//赋值了，i才移动
				i++;
			}
			j++;
		}
//		System.out.println(i);
//		System.out.println(j);
		return i+1;
    }
	
	/**
	 * 26. 删除排序数组中的重复项
	 * 因为数组有序，因此只需要去对比 nums[i] 和当前去重数组的最大值是否相等即可。我们用一个 temp 变量保存去重数组的最大值。

		如果二者不等，则说明是一个新的数据。我们就需要把这个新数据放到去重数组的最后，
		并且修改 temp 变量的值，再修改当前去重数组的长度变量 len。直到遍历完，就得到了结果。
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates2(int[] nums) {
		if (nums==null||nums.length==0) {
			return 0;
		}
		int temp=nums[0];
		int len=0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i]!=temp) {
				temp=nums[i];
				len++;
				nums[len]=nums[i];
				
			}
		}
		return len+1;
	}
	/**
	 * 80. 删除排序数组中的重复项 II
	 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates80(int[] nums) {
		int i = 0,j=i+2;
		while(i<nums.length&&j<nums.length) {
			while (j<nums.length&&nums[i]==nums[j]) {
				j++;
			}
			if (j<nums.length) {
				
				nums[i+2]=nums[j];
				//赋值了，i才移动
				i++;
			}
			j++;
		}
		System.out.println(i);
		System.out.println(j);
		return i+2;
    }
	
}
