package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class ZimuYiTongWei {

	/**
	 * 242. 有效的字母异位词
	 * 字母异位词指字母相同，但排列不同的字符串。
	 * HASH
	 * 计算两个字符串中每个字母的出现次数并进行比较。
	 * 用一个计数器表计算 s 字母的频率，用 t减少计数器表中的每个字母的计数器，然后检查计数器是否回到零。
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram(String s, String t) {
		if (s==null||t==null||s.length()!=t.length()) {
			return false;
		}
		HashMap<Character, Integer> map=new HashMap<Character, Integer>();
		
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();
		
		for (int i = 0; i < sArr.length; i++) {
			
			map.put(sArr[i], map.getOrDefault(sArr[i], 0)+1);
		}
		
		for (int i = 0; i < tArr.length; i++) {
			if (!map.containsKey(tArr[i])) {
				return false;
			}
			
			map.put(tArr[i], map.get(tArr[i])-1);
		}
		
		Set<Entry<Character,Integer>> entrySet = map.entrySet();
		
		for (Entry<Character, Integer> entry : entrySet) {
			if (entry.getValue()>0) {
				return false;
			}
		}
		
		return true;
    }
	
	/**
	 * 438. 找到字符串中所有字母异位词
	给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
	
	字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
	
	说明：
	
	
	不考虑答案输出的顺序。

	 * @param s
	 * @param p
	 * @return
	 */
	public static List<Integer> findAnagrams(String s, String p) {

		List<Integer> result =new ArrayList<Integer>();
		if (s==null||p==null||s.length()<p.length()) {
			return result;
		}
		
		char[] sArr = s.toCharArray();
		char[] pArr = p.toCharArray();
		
		HashMap<Character, Integer> pMap=new HashMap<Character, Integer>();
		HashMap<Character, Integer> sMap=new HashMap<Character, Integer>();
		
		for (int i = 0; i < pArr.length; i++) {
			
			pMap.put(pArr[i], pMap.getOrDefault(pArr[i], 0)+1);
			sMap.put(sArr[i], sMap.getOrDefault(sArr[i], 0)+1);
		}
		
		if (sMap.equals(pMap)) {
			result.add(0);
		}
		
		for (int i = 1; i < sArr.length-pArr.length+1; i++) {
			
			if (sMap.get(sArr[i-1])==1) {
				sMap.remove(sArr[i-1]);
			}else {
				sMap.put(sArr[i-1], sMap.get(sArr[i-1])-1);
			}
			sMap.put(sArr[i+pArr.length-1], sMap.getOrDefault(sArr[i+pArr.length-1], 0)+1);
			
			if (sMap.equals(pMap)) {
				result.add(i);
			}
		}
		
		return result;
    }
}
