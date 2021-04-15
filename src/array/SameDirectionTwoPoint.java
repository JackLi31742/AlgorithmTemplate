package array;

import java.util.Arrays;

/**
 * 同向双指针
 * @author JackLi
 *
 */
public class SameDirectionTwoPoint {

	public static void main(String[] args) {
		
		int[] nums= {1,3,1,4,4,2};
		
		System.out.println(deduplication(nums));;
		
		System.out.println(Arrays.toString(nums));
	}
	
	/**
	 * 1870. number of substrings with all zeroes
	 * 给出一个只包含0或1的字符串str,请返回这个字符串中全为0的子字符串的个数
	 * 
	 * 问题转换为以0为起始，以0为结尾的子串长度有多长以及共有多少个这样的子串
	 * @param str
	 * @return
	 */
	 public int stringCount(String str) {
	        // Write your code here.
		 if (str==null||str.length()==0) {
			return 0;
		}
		 
		 char[] arr=str.toCharArray();
		 
		 int j=1;
		 int result=0;
		 
		 for (int i = 0; i < arr.length; i++) {
			
			 if (arr[i]!='0') {
				continue;
			}
			 
			 j=Math.max(j, i+1);
			 while(j<arr.length&&arr[j]=='0') {
				 j++;
			 }
			 
			 result+=j-i;
		}
		 
		 return result;
	 }
	 
	 /**
	  * lintcode 521. Remove Duplicate Numbers in Array
	  * 给一个整数数组，去除重复的元素。

		你应该做这些事
		
		1.在原数组上操作
		2.将去除重复之后的元素放在数组的开头
		3.返回去除重复元素之后的元素个数
	  * @param nums
	  * @return
	  */
	 public static int deduplication(int[] nums) {
	        // write your code here
		 
		 if (nums==null||nums.length==0) {
			
			 return 0;
		}
		 
		 Arrays.sort(nums);
		 
		 int j=1;
		 int i=0;
		 
		 for (; i < nums.length; ) {
			 
			while (j<nums.length&&nums[j]==nums[i]) {
				j++;
			}
			
			if (j>=nums.length) {
				break;
			}
			
			i++;
			nums[i]=nums[j];
			j++;
		}
		 
		 return i+1;
	 }
	 
	 
	 /**
	  * 最多有k个不同字符的最长子字符串
	  * 给定字符串S，找到最多有k个不同字符的最长子串T。
	  * @param s
	  * @param k
	  * @return
	  */
	 public int lengthOfLongestSubstringKDistinct(String s, int k) {
	        // write your code here
	    
	 }
}
