package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permute {

	public static void main(String[] args) {
		
		int[]nums= {1,2,2,2};
		Permute permute=new Permute();
		System.out.println(permute.permuteUnique(nums));;
	}
	
	/**
	 * 15. 全排列
	 * 给定一个数字列表，返回其所有可能的排列。
	 * 
	 * 点：每个数为一个点
边：任意两两点之间都有连边，且为无向边
路径：= 排列 = 从任意点出发到任意点结束经过每个点一次且仅一次的路径
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute(int[] nums) {
        // write your code here
		
		List<List<Integer>> resultList=new ArrayList<List<Integer>>();
		
		if (nums==null) {
			
			return resultList;
		}
		
		List<Integer> list=new ArrayList<Integer>();
		
		boolean[] visited=new boolean[nums.length];
		dfs(nums,visited, resultList, list);
		
		return resultList;
    }
	
	public void dfs(int[] nums,
					boolean[] visited,
					List<List<Integer>> resultList,
					List<Integer> list) {
		
		if (list.size()==nums.length) {
			resultList.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < visited.length; i++) {
			
			if (visited[i]) {
				continue;
			}
			list.add(nums[i]);
			visited[i]=true;
			dfs(nums, visited, resultList, list);
			list.remove(list.size()-1);
			visited[i]=false;
		}
		
	}
	
	/**
	 * 16. 带重复元素的排列
	 * 给出一个具有重复数字的列表，找出列表所有不同的排列。
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here
		
		List<List<Integer>> resultList=new ArrayList<List<Integer>>();
		
		if (nums==null) {
			
			return resultList;
		}
		
		List<Integer> list=new ArrayList<Integer>();
		
		boolean[] visited=new boolean[nums.length];
		
		Arrays.sort(nums);
		
		dfs2(nums,visited, resultList, list);
		
		return resultList;
		
    }
	
	public void dfs2(int[] nums,
			boolean[] visited,
			List<List<Integer>> resultList,
			List<Integer> list) {
		
		if (list.size()==nums.length) {
			resultList.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < visited.length; i++) {
			
			if (visited[i]) {
				continue;
			}
			//要从重复的里只挑一个,visited[i-1]==true，会把重复的这一段数字从最后开始加
			//[1,2,2,2]会从1,第三个2，第二个2，第一个2，倒着把结果加到list中，
			//从前添加的时候，i走到最后list.size()<nums.length，所以不会把1，第一个2，第二个2，第3个二加到结果中
			//要么加的时候，再重复的2中，总会visited[i-1]为true
			
			//!visited[i-1]==true更好理解，这样是把当nums[i]==nums[i-1]时，如果i-1为false，说明i-1已经回溯使用过了
			//因为for循环是一直向前走的，如果某个下标回溯时用过了，visited[i-1]==false
			if (i>0&&nums[i]==nums[i-1]&&!visited[i-1]) {
				continue;
			}
			list.add(nums[i]);
			visited[i]=true;
			dfs2(nums, visited, resultList, list);
			list.remove(list.size()-1);
			visited[i]=false;
		}
	}
	
	/**
	 * 10. 字符串的不同排列
	 * 给出一个字符串，找到它的所有排列，注意同一个字符串不要打印两次。
	 *        输入："abb"
		输出：
		["abb", "bab", "bba"]

	 * 搜索去重的诀窍 —— 选代表
错误方法：把所有方案都放在 HashSet 里去重
正确方法：在每组重复方案中选出代表方案
	 * @param str
	 * @return
	 */
	public List<String> stringPermutation2(String str) {
        // write your code here
		List<String> resultList=new ArrayList<String>();
		if (str==null) {
			return resultList;
		}
		
		char[] array = str.toCharArray();
		
		//将所有重复的放在一起
		Arrays.sort(array);
		
		StringBuilder sb=new StringBuilder();
		
		boolean[]visited=new boolean[array.length];
		dfs3(array, visited, resultList, sb);
		return resultList;
    }
	
	public void dfs3(char[] nums,
			boolean[] visited,
			List<String> resultList,
			StringBuilder sb) {
		
		if (sb.length()==nums.length) {
			resultList.add(new String(sb));
			return;
		}
		
		for (int i = 0; i < visited.length; i++) {
			
			if (visited[i]) {
				continue;
			}
			
			//判断是否选中
			if (i>0&&nums[i]==nums[i-1]&&!visited[i-1]) {
				continue;
			}
			
			sb.append(nums[i]);
			visited[i]=true;
			dfs3(nums, visited, resultList, sb);
			visited[i]=false;
			sb.deleteCharAt(sb.length()-1);
			
		}
	}
}
