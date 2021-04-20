package dp;

public class SubArray {

	
	/**
	 * 53. 最大子序和
	 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {

    }
	
	
	/**
	 * 152. 乘积最大子数组
	 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
	 * 并返回该子数组所对应的乘积。

 		dp[i]:代表到下标i的最大乘积
 		
 		dp[i]=max(dp[i-1],max)
 		其中max就是从nums[i]开始依次乘以i之前的数，一直乘到下标0
	 * @param nums
	 * @return
	 */
	public int maxProduct(int[] nums) {
		if (nums==null||nums.length==0) {
			return 0;
		}
		
		int len=nums.length;
		int[]dp =new int[len];
		
		dp[0]=nums[0];
		
		for (int i = 1; i < len; i++) {
			
			int max=nums[i];
			int temp=nums[i];
			for (int j = i-1; j >= 0; j--) {
				temp=temp*nums[j];
				if (temp>max) {
					max=temp;
				}
			}
			
			dp[i]=Math.max(dp[i-1], max);
		}
		
		return dp[len-1];
    }
}
