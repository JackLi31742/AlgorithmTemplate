package binarysearch;

import java.util.List;

public class FindDuplicate {

	/**
	 * 442. 数组中重复的数据
	 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。

		找到所有出现两次的元素。

		输入:
		[4,3,2,7,8,2,3,1]
		
		输出:
		[2,3]
	 * @param nums
	 * @return
	 */
	public List<Integer> findDuplicates(int[] nums) {

    }
	
	/**
	 *lintcode  633 · 寻找重复的数
	 * 给出一个数组 nums 包含 n + 1 个整数，
	 * 每个整数是从 1 到 n (包括边界)，保证至少存在一个重复的整数
	 * 。假设只有一个重复的整数，找出这个重复的数。
	 * 
	 * 跟array包里的RemoveElement不一样，这里的数组是没有排序的
	 * 
	 * 1.不能修改数组(假设数组只能读)
		2.只能用额外的O(1)的空间
		3.时间复杂度小于O(n^2)
		4.数组中只有一个重复的数，但可能重复超过一次
		
		
		假设答案是S
		• 数组一定是（假设排好序）：[1,3,…,S,...,S,S+1,…,n]
		• 那么其中<=S的数大于S
		• 而且对于所有T>=S, <=T的个数大于T
		• 而对于所有T<S, <=T的个数小于等于T
		• 二分法, O(nlog2n)
	 */
	
	public int findDuplicate(int[] nums) {
        // Write your code here
        int left = 1;
        int right = nums.length - 1;  // n
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (count(nums, mid) <= mid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (count(nums, left) <= left) {
            return right;
        }
        return left;
    }
    //寻找比mid（S）小的数量
    private int count(int[] nums, int S) {
        int count = 0;
        for (int item : nums) {
            if (item <= S) {
                count++;
            }
        }
        return count;
    }
}
