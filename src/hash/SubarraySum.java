package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 */
public class SubarraySum {

	
	/**
	 * 138. 子数组之和
	 * 给定一个整数数组，找到和为 0 的子数组。你的代码应该返回满足要求的子数组的起始位置和结束位置
	 * @param nums
	 * @return
	 */
	 public List<Integer> subarraySum(int[] nums) {
	        // write your code here
		 
		 ArrayList<Integer> resultList=new ArrayList<Integer>();
		 
		 //key是前缀和，value是下标
		 Map<Integer, Integer> sumToIndex=new HashMap<Integer, Integer>();
		 
		 int sum=0;
		 sumToIndex.put(sum, -1);
		 
		 for (int i = 0; i < nums.length; i++) {
			
			 sum+=nums[i];
			 
			 if (sumToIndex.containsKey(sum)) {
				
				 resultList.add(sumToIndex.get(sum)+1);
				 resultList.add(i);
				 break;
			}
			 
			 sumToIndex.put(sum, i);
		}
		 
		 return resultList;
		 
	 }
	 
	 
	 /**
	  * 404. 子数组求和 II
		给定一个正整数数组 A 和一个区间. 返回和在给定区间范围内的子数组数量.
	  * @param A
	  * @param start
	  * @param end
	  * @return
	  */
	 public int subarraySumII(int[] A, int start, int end) {
	        // write your code here
		 
		 
	 }
}
