package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class GcdOfStrings {

	public static void main(String[] args) {
//		System.out.println(gcd(4,8));;
		
		String hayString="cbaebabacd";
		String llString="abc";
		
		
//		String hayString="hello";
//		String llString="ll";
//		System.out.println(hayString.indexOf(llString));
//		System.out.println((int)'a');
//		System.out.println((int)'b');
//		System.out.println((int)'c');
//		System.out.println((int)'o');
//		System.out.println(strStr4(hayString, llString));
//		findAnagrams(hayString, llString);
	}
	
	
	
	
	/**
	 * 1071. 字符串的最大公因子
	 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。

		返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。

		数学理论，如果str1+str2=str2+str1，那么最大公因子就是两个字符串长度的gcd，然后取前缀，否则就是""
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String gcdOfStrings(String str1, String str2) {
		if ((str1+str2).equals(str2+str1)) {
			int len=gcd(str1.length(),str2.length());
			return str1.substring(0, len);
		}else {
			return "";
		}
		
    }
	
	/**
	 * 辗转相除
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a,int b) {
		int temp=0;
		while(b>0) {
			temp=a%b;
			a=b;
			b=temp;
		}
		return a;
	}
	
	
	
	
}
