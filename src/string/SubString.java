package string;

import java.util.Arrays;

/**
 * 同向双指针
 * @author JackLi
 *
 */
public class SubString {

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
