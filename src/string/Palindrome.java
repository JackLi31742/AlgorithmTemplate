package string;

public class Palindrome {

	public static void main(String[] args) {
		
		String string="abc";
		
		System.out.println(validPalindrome(string));;
	}

	/**
	 * 125. 验证回文串
	 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

	说明：本题中，我们将空字符串定义为有效的回文串。
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome(String s) {
		if (s==null) {
			return false;
		}
		if (s.equals("")) {
			return true;
		}
		s=s.toLowerCase();
		int left=0;
		int right=s.length()-1;
		
		//双指针
		//由于要验证的是回文串，所以left不需要等于right，因为等于的时候肯定是回文串
		while(left<right) {
			//不能用if，因为可能连续好几个字符都不是字母和数字
			//同时由于left和right都在做加减操作，所以必须要小心是否越界
			while (left<right&&!check(s.charAt(left))) {
				left++;
			}
			while (left<right&&!check(s.charAt(right))) {
				right--;
			}
			if (left<right&&s.charAt(left)!=s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		
		return true;
    }
	
	/**
	 * 判断字符是不是字母或者数字
	 * @param ch
	 * @return
	 */
	public static boolean check(char ch) {
		if (Character.isDigit(ch)||Character.isLetter(ch)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 680. 验证回文字符串 Ⅱ
	 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
	 * 左右双指针，遇到不相等的，判断去掉其中一个字符后，剩下的是不是相等，有点像求最长回文子串时候的情况
	 * @param s
	 * @return
	 */
	public static boolean validPalindrome(String s) {
		if (s==null) {
			return false;
		}
		if (s.equals("")) {
			return true;
		}
		
		int left=0;
		int right=s.length()-1;
		
		int[] temp=validPalindrome(s, left, right);
		
		if (check(temp)) {
			return true;
		}
		int[] result=validPalindrome(s, temp[0]+1, temp[1]);
		if (check(result)) {
			return true;
		}
		result=validPalindrome(s, temp[0], temp[1]-1);
		
		if (check(result)) {
			return true;
		}
		return false;
    }
	
	public static int[] validPalindrome(String s,int left,int right) {
		while(left<right) {
			if (s.charAt(left)!=s.charAt(right)) {
				break;
			}
			left++;
			right--;
		}
		
		return new int[]{left,right};
	}
	
	public static boolean check(int[] result) {
		if (result[0]>=result[1]) {
			return true;
		}
		return false;
	}
}
