package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Combine {
	
	/**
	 * 77. 组合
	 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
	 * @param n
	 * @param k
	 * @return
	 */
	 public static List<List<Integer>> combine(int n, int k) {
		 List<List<Integer>> result=new ArrayList<List<Integer>>();
			if (n==0||k==0) {
				return result;
			}
			List<Integer> path=new ArrayList<Integer>();
			boolean[] visited=new boolean[n];
			int index=1;
			combine(n,k,result,1,path,visited,index);
			return result;
	 }
	 
	 public static void combine(int n, int k,List<List<Integer>> result,
			 int count,List<Integer> path,boolean[] visited,int index) {
		 
		 if (count<=k) {
			
			 for (int i = index; i <= n; i++) {
				 if (!visited[i-1]) {
					 visited[i-1]=true;
					 path.add(i);
					 count++;
					 combine(n, k, result, count, path, visited,i+1);
					 
					 visited[i-1]=false;
					 path.remove(path.size()-1);
					 count--;
				}
			 }
		}else {
			
			result.add(new ArrayList<Integer>(path));
		}
		
	 }

	/**
	 * 425. 电话号码的字母组合
	 * 给一个不包含0和1的数字字符串，每个数字代表一个字母，请返回其所有可能的字母组合。

下图的手机按键图，就表示了每个数字可以代表的字母。

		输入: "23"
		输出: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
		解释: 
		'2' 可以是 'a', 'b' 或 'c'
		'3' 可以是 'd', 'e' 或 'f'
		
		Follow up
		如果有一个词典（Dictionary）
		要求组成的单词都是词典里的
		如何优化？

		把数字存在字典树中，而不是把字母存在字典树里，会快一点
	 * @param digits
	 * @return
	 */
	
	
	public List<String> letterCombinations(String digits) {
        // write your code here
		
		List<String> resultList=new ArrayList<String>();
		
		if (digits==null||digits.equals("")) {
			
			return resultList;
		}
		
		char[] array = digits.toCharArray();
		
		Map<Character,List<String>> map=new HashMap<Character, List<String>>();;
		
		map.put('1', Arrays.asList(""));
		map.put('2', Arrays.asList("a","b","c"));
		map.put('3', Arrays.asList("d","e","f"));
		map.put('4', Arrays.asList("g","h","i"));
		map.put('5', Arrays.asList("j","k","l"));
		map.put('6', Arrays.asList("m","n","o"));
		map.put('7', Arrays.asList("p","q","r","s"));
		map.put('8', Arrays.asList("t","u","v"));
		map.put('9', Arrays.asList("w","x","y","z"));
		
		dfs(array, map, 0, resultList, new StringBuilder());
		
		return resultList;
		
    }
	
	
	public void dfs(char[] array,
					Map<Character,List<String>> map,
					int startIndex,
					List<String> resultList,
					StringBuilder sb) {
		
		if (sb.length()==array.length) {
			resultList.add(new String(sb));
			return;
		}
		
		for (int i = startIndex; i < array.length; i++) {
			
			
			List<String> list = map.get(array[i]);
			
			for (int j = 0; j < list.size(); j++) {
				
				sb.append(list.get(j));
				
				dfs(array, map, i+1, resultList, sb);
				
				sb.deleteCharAt(sb.length()-1);
			}
		}
		
	}
	
	
	
}
