package array;

public class Majority {

	public static void main(String[] args) {
		
		int [] nums= {10,9,9,9,10};
		
		System.out.println(majorityElement(nums));
	}
	
	 /**
	  * 169. 多数元素
	  * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

		你可以假设数组是非空的，并且给定的数组总是存在多数元素。
		
		“一个碰一个”的思想，那就是如果相等则加 1，如果不等则减 1。
		假设现在三国交战，其中 A 国的兵力比 B 国和 C 国的总和还多。
		那么人们就常常会说，哪怕是 A 国士兵“一个碰一个”地和另外两国打消耗战，都能取得最后的胜利。

	  * @param nums
	  * @return
	  */
	 public static int majorityElement(int[] nums) {
		 int result=nums[0];
		 int count=1;
		 for (int i = 1; i < nums.length; i++) {
			 if (count==0) {
				result=nums[i];
				count++;
			}else {
				
				if (nums[i]==result) {
					count++;
				}else {
					count--;
				}
			}
		}
		 
		 return result;
	 }
	 
	 
}
