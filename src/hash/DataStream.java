package hash;

import java.util.HashMap;

public class DataStream {

	/**
	 * 685. 数据流中第一个唯一的数字
	 * 给一个连续的数据流,写一个函数返回终止数字到达时的第一个唯一数字（包括终止数字）,如果找不到这个终止数字, 返回 -1.
	 * @param nums
	 * @param number
	 * @return
	 */
	public int firstUniqueNumber(int[] nums, int number) {
        // Write your code here
		
		if (nums==null) {
			return -1;
		}
		
		HashMap<Integer, Integer> numToCount=new HashMap<Integer, Integer>();
		
		for (int i = 0; i < nums.length; i++) {
			
			numToCount.put(nums[i], numToCount.getOrDefault(nums[i], 0)+1);
			//不能走到结束，否则有些数字出现在number的后边，也会导致增加数量
			if (number==nums[i]) {
				break;
			}
		}
		
		if (!numToCount.containsKey(number)) {
			return -1;
		}
		
		int result=nums[0];
		for (int i = 0; i < nums.length; i++) {
			
			
			if (numToCount.get(nums[i])==1) {
				result=nums[i];
				break;
			}
			//for循环上的if已经判断number必然在数组中，
			//同时如果number就是数组的第一个数字，而这个数字在数组中有很多次，
			//所以要将result初始化为nums[0]
			if (nums[i]==number) {
				break;
			}
		}
		
		return result;
    }
}
