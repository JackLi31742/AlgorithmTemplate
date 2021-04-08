package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
	
	public static void main(String[] args) {
		int[]nums= {1,2};
		Subsets subsets=new Subsets();
		subsets.subsets(nums);
	}
	/**
	 * 17. 子集
	 * 给定一个含不同整数的集合，返回其所有的子集。
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets(int[] nums) {
        // write your code here
		
		List<List<Integer>> results=new ArrayList<List<Integer>>();
		//[]和[[]]不一样
		if (nums==null) {
			return results;
		}
		
		//先排序
		
		Arrays.sort(nums);
		
		dfs(nums, 0, new ArrayList<Integer>(), results);
		
		return results;
    }

	
	public void dfs(int[] nums,int startIndex,List<Integer> list,List<List<Integer>> results) {
		
		//由于是求子集，所以不一定非要startINdex=nums.length-1才结束
		
		results.add(new ArrayList<Integer>(list));
		
		for (int i = startIndex; i < nums.length; i++) {
			
			list.add(nums[i]);
			dfs(nums, i+1, list, results);
			list.remove(list.size()-1);
		}
	}
	
	/**
	 * 18. 子集 II
	 * 给定一个可能具有重复数字的列表，返回其所有可能的子集。
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
		List<List<Integer>> results=new ArrayList<List<Integer>>();
		//[]和[[]]不一样
		if (nums==null) {
			return results;
		}
		
		//先排序
		
		Arrays.sort(nums);
		
		dfs2(nums, 0, new ArrayList<Integer>(), results);
		
		return results;
    }
	
	
	public void dfs2(int[] nums,int startIndex,List<Integer> list,List<List<Integer>> results) {
		
		//由于是求子集，所以不一定非要startINdex=nums.length-1才结束
		
		results.add(new ArrayList<Integer>(list));
		
		for (int i = startIndex; i < nums.length; i++) {
			
			//不能直接nums[i]==nums[i-1]，否则就会把重复的跳过
			//比如[1,2,2,2,3]
			//而且由于有重复的，为了过滤重复，要把startIndex-1,startIndex+1中间不能有间隔
			//即不能选择
//			[]
//			/
//		    1
//		    /
//		   1,2
//		    / \
//		1,2,2 1,2,2 
		    if (i>0&&nums[i]==nums[i-1]&&i>startIndex) {
				continue;
			}
			
			list.add(nums[i]);
			dfs2(nums, i+1, list, results);
			list.remove(list.size()-1);
		}
	}
}
