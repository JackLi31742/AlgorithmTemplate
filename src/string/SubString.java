package string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 同向双指针
 * @author JackLi
 *
 */
public class SubString {

	public static void main(String[] args) {
		
		int[] nums= {1,3,1,4,4,2};
		
		String string="abcabcbb";
		
		System.out.println(lengthOfLongestSubstring(string));;
		
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
	  * 384 · 最长无重复字符的子串
	  * 给定一个字符串，请找出其中无重复字符的最长子字符串。
	  * 
	  * 双指针
	  * 固定左指针，向右移动右指针，一直移动到不满足条件为止
	  * 然后左指针向右移动，右指针也会跟着往右移动
	  * 左指针是for，右指针是while
	  * 
	  * 在两个指针内的窗口，开一个数组或者hash判断是否重复
	  * @param s
	  * @return
	  */
	 public static int lengthOfLongestSubstring(String s) {
	        // write your code here
		if (s == null||s.length()==0) {
			return 0;
		}

		char[] arr = s.toCharArray();
		int maxSize = Integer.MIN_VALUE;
		Set<Character> set = new HashSet<>();

		int right=0;
		
		for (int left = 0; left < arr.length; left++) {
			
			while(right<arr.length) {
				
				if (!set.contains(arr[right])) {
					set.add(arr[right]);
					right++;
				}else {
					break;
				}
					
			}
			maxSize=Math.max(maxSize, set.size());
			set.remove(arr[left]);
		}
		
		
		return maxSize ;
	 }
	 
	 
	 /**
	  * 386 ·最多有k个不同字符的最长子字符串
	  * 给定字符串S，找到最多有k个不同字符的最长子串T。
	  * 
	  * 算法：同向双指针 + 哈希表
通过使用同向双指针的算法，我们可以做到一次遍历字符串就得到答案。 在字符串上移动滑动窗口，
保证窗口内有不超过 k 个不同字符，同时在每一步更新最大子串长度。

如果字符串为空或者 k 是零的话返回 0。
设置指针为字符串开头 left = 0 和 right = 0，初始化最大子串长度 maxLen = 1。
当 right < N 时： - 将当前字符 s[right] 加入哈希表并且向右移动 right 指针。 -
 如果哈希表包含 k + 1 个不同字符，在哈希表中移去最左出现的字符(s[left])，
 右移动 left 指针使得滑动窗口只包含 k 个不同字符。 - 更新 maxLen = max(maxLen, right - left)。
 
复杂度分析
时间复杂度O(n) - 只用扫描一遍字符串，复杂度即字符串的长度
空间复杂度O(kind) - 只用开一个数组记录各种不同的字符
	  * @param s
	  * @param k
	  * @return
	  */
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		// write your code here
		if (s.length() == 0 || k == 0) {
			return 0;
		}

		int left = 0, right = 0, cnt = 0;
		int charSet[] = new int[256];
		int ans = 0;

		while (right < s.length()) {
			// 统计right指向的字符
			// 当字符在窗口内第一次出现时，字符种类数+1，该字符出现次数+1
			if (charSet[s.charAt(right)] == 0) {
				cnt++;
			}
			charSet[s.charAt(right)]++;
			right++;

			// 向右移动left，保持窗口内只有k种不同的字符
			while (cnt > k) {
				charSet[s.charAt(left)]--;
				// 当该字符在本窗口不再出现时，字符种类数-1
				if (charSet[s.charAt(left)] == 0) {
					cnt--;
				}
				left++;
			}

			// 更新答案
			ans = Math.max(ans, right - left);
		}
		return ans;
	}
	 
	 
	 /**
	  * 32 · 最小子串覆盖
	  * 给定两个字符串 source 和 target. 求 source 中最短的包含 target 中每一个字符的子串.
	  * 
	  * 输入：

		source = "adobecodebanc"
		target = "abc"
		输出：
		
		"banc"
		
		
		
		
		解题思路
本题采用滑窗法，滑窗法是双指针技巧，指针left和right分别指向窗口两端，
从左向右滑动，实施维护这个窗口。我们的目标是找到source中涵盖target全部字母的最小窗口，即为最小覆盖子串。
算法流程
变量定义：
	哈希表counter_s和counter_t表示数组source和target中有效字符的出现次数，其中，我们将有效字符定义为target中出现的字符。
	start是最小覆盖子串的起始索引，minlen是最小覆盖子串的长度（从0计）。
	valid表示source的窗口中满足counter_t出现次数要求的字符的个数。注意，相同的字符只计算一次。
	left和right分别指向滑窗两端。
算法流程：
	初始化：left指针和right指针都指向s[0]。
	移动窗口右边界：将right指针右移，如果指向的字符ch是有效字符，
		那么counter_s[ch] += 1。如果字符ch的出现次数达标，那么valid += 1。
	当我们得到一个可行窗口，即包含target的全部字母的窗口时:
	判断此时的子串是否长度更小。如果是，更新子串的起始位置start和长度minlen。
	移动窗口左边界：将left右移，如果离开的字符left_ch是有效字符，
		那么counter_s[left_ch] -= 1。如果字符left_ch的出现次数不再达标，那么valid -= 1。
	如果窗口依然可行，循环步骤3。否则，跳转至步骤2。
复杂度分析：
时间复杂度：
O(n)，n为字符串source的长度。
空间复杂度：O(n)
。
	  */
	
	
	public String minWindow(String source, String target) {
		// 初始化counter_s和counter_t
		Map<Character, Integer> counter_t = new HashMap<Character, Integer>();
		Map<Character, Integer> counter_s = new HashMap<Character, Integer>();
		for (int i = 0; i < target.length(); i++) {
			int count = counter_t.getOrDefault(target.charAt(i), 0);
			counter_t.put(target.charAt(i), count + 1);
		}

		int left = 0, valid = 0;
		// 记录最小覆盖子串的起始索引及长度
		int start = -1, minlen = Integer.MAX_VALUE;
		for (int right = 0; right < source.length(); right++) {
			// 移动右边界, ch 是将移入窗口的字符
			char ch = source.charAt(right);
			if (counter_t.containsKey(ch)) {
				counter_s.put(ch, counter_s.getOrDefault(ch, 0) + 1);
				if (counter_s.get(ch).compareTo(counter_t.get(ch)) == 0) {
					valid++;
				}
			}
			// 判断左侧窗口是否要收缩
			while (valid == counter_t.size()) {
				// 更新最小覆盖子串
				if (right - left < minlen) {
					start = left;
					minlen = right - left;
				}
				// left_ch 是将移出窗口的字符
				char left_ch = source.charAt(left);
				// 左移窗口
				left++;
				// 进行窗口内数据的一系列更新
				if (counter_t.containsKey(left_ch)) {
					if (counter_t.get(left_ch).equals(counter_s.get(left_ch))) {
						valid--;
					}
					counter_s.put(left_ch, counter_s.getOrDefault(left_ch, 0) - 1);
				}
			}
		}
		// 返回最小覆盖子串
		return start == -1 ? "" : source.substring(start, start + minlen + 1);
	}
}
