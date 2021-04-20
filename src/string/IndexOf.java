package string;

import java.util.HashMap;
import java.util.Map;

public class IndexOf {

	
	/**
	 * 28 暴力破解
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr1(String haystack, String needle) {
		
		if (needle.equals("")) {
			return 0;
		}
		int i=0;//遍历haystack
		int j=0;//遍历needle
		
		int hlen=haystack.length();
		int nlen=needle.length();
		
		while(i<hlen&&j<nlen){
			if (haystack.charAt(i)==needle.charAt(j)) {
				i++;
				j++;
			}else {
				i=i-j+1;
				j=0;
				
			}
			//i和j都再次加了一次
			if (j==nlen) {
				return i-j;
			}
		}
		
		return -1;
    }
	
	/**
	 * 另一种暴力破解
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr2(String haystack, String needle) {
		if (haystack==null||needle==null) {
			return -1;
		}
		if (needle.equals("")) {
			return 0;
		}
		
		int hlen=haystack.length();
		int nlen=needle.length();
		
		//可以有等于号，或者+1
		for (int i = 0; i < hlen-nlen+1; i++) {
			//每次都得重新设置
			boolean notEqual=false;

			for (int j = 0; j < nlen; j++) {
				//随着j增加，i+j也在增加
				if (haystack.charAt(i+j)!=needle.charAt(j)) {
					notEqual=true;
					break;
				}
			}
			//走完一次，如果都相等
			if (!notEqual) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * RK算法，将字符串变成hashcode
	 * 
	 * hash函数可以将字符串转换为一个整数，则hash结果不同的字符串肯定不同，但hash结果相同的字符串则很有可能相同
	 * @param haystack
	 * @param needle
	 * @return
	 */
	
	private static final int HASHSIZE=Integer.MAX_VALUE;
	private static final int PRIME=31;
	
	public static int strStr3(String haystack, String needle) {
		
		int hLen=haystack.length();
		int nLen=needle.length();
		
		char[] hArr=haystack.toCharArray();
		char[] nArr = needle.toCharArray();
		
		int nHash=0;
		
		for (int i = 0; i < nLen; i++) {
			nHash+=nArr[i]*PRIME%HASHSIZE;
		}
		
		for (int i = 0; i < hLen-nLen+1; i++) {
			int hHash=0;
			for (int j = i; j < i+nLen; j++) {
				hHash+=hArr[j]*PRIME%HASHSIZE;
			}
			
			if (hHash==nHash&&compare(hArr, nArr, i, 0)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr4(String haystack, String needle) {
		
		if (haystack==null||needle==null||haystack.length()<needle.length()) {
			return -1;
		}
		if (needle.equals("")) {
			return 0;
		}
		
		int hLen=haystack.length();
		int nLen=needle.length();
		
		char[] hArr=haystack.toCharArray();
		char[] nArr = needle.toCharArray();
		
		int nHash=0;
		int hHash=0;
		//计算从0到小字符串的长度的hash
		for (int i = 0; i < nLen; i++) {
			nHash=(nHash*PRIME+nArr[i])%HASHSIZE;
			hHash=(hHash*PRIME+hArr[i])%HASHSIZE;
			
		}
		

		
		if (hHash==nHash&&compare(hArr, nArr, 0, 0)) {
			return 0;
		}
		//计算Math.pow(PRIME, nLen)
		int base=1;
		
		for (int i = 0; i < nLen; i++) {
			base=(base*PRIME)%HASHSIZE;
		}
		
		//从下标1开始滑动窗口计算hash
		for (int i = 1; i < hLen-nLen+1; i++) {
//			System.out.println(hArr[i+nLen-1]+","+hArr[i-1]);
			hHash=(hHash*PRIME+hArr[i+nLen-1]-hArr[i-1]*base)%HASHSIZE;

			if (hHash==nHash&&compare(hArr, nArr, i, 0)) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	
	public static boolean compare(char[] hArr,char[] nArr,int i,int j) {
		
		while(i<hArr.length&&j<nArr.length) {
			if (hArr[i]!=nArr[j]) {
				return false;
			}
			i++;
			j++;
		}
		return true;
	}
	
	
	
	/**
	 * 28. 实现 strStr()
	 *  Java的 indexOf() 
	 *  用sunday算法
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr(String haystack, String needle) {
		if (haystack==null||needle==null) {
			return -1;
		}
		if (needle.equals("")) {
			return 0;
		}
		
		int i=0,j=0;
		int len1=haystack.length();
		int len2=needle.length();
		
		HashMap<Character, Integer> map=new HashMap<Character, Integer>();
		getLastIndex(needle, map);
		
		while(i<=(len1-len2)&&j<len2) {
			if (haystack.charAt(i+j)==needle.charAt(j)) {
				j++;
				//j走完最后一个匹配的会再加一次，退出循环，所以是等于len2
				if (j==len2) {
					return i;
				}
			}else {
//				System.out.println(map.get(haystack.charAt(i+len2)));
				if (i+len2>=len1) {
					return -1;
				}
				if (map.get(haystack.charAt(i+len2))==null) {
					i=i+len2+1;
				}else {

					i=i+len2-map.get(haystack.charAt(i+len2));

					
				}
				j=0;
			}
			
		}
		
		
		return -1;
	
		
    }
	/**
	 * 预处理needle，得到每个字符最后出现的位置
	 * @param needle
	 * @param map
	 */
	public static void getLastIndex(String needle,Map<Character, Integer> map) {
		for (int i = 0; i < needle.length(); i++) {
			//map会自动覆盖相同的key
			map.put(needle.charAt(i), i);
		}
		
	}
}
