package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

public class KSum {

	
	public static void main(String[] args) {
		int[]nums= {2,2,3};
		
		KSum kSum=new KSum();
		
//		kSum.combinationSum(nums, 5);
		
//		kSum.letterCombinations("23");
	}
	/**
	 * 135. 数字组合
	 * 给定一个候选数字的集合 candidates 和一个目标值 target. 找到 candidates 中所有的和为 target 的组合.

在同一个组合中, candidates 中的某个数字不限次数地出现.
	
		所有数值 (包括 target ) 都是正整数.
		返回的每一个组合内的数字必须是非降序的.
		返回的所有组合之间可以是任意顺序.
		解集不能包含重复的组合.
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
		
		List<List<Integer>> resultList=new ArrayList<List<Integer>>();
		
		if (candidates==null) {
			
			return resultList;
		}
		
		Arrays.sort(candidates);
		
		dfs(candidates, target, resultList, 0, new ArrayList<Integer>());
		
		return resultList;
    }
	
	
	public void dfs(int[] candidates, 
			int target,
			List<List<Integer>> resultList,
			int startIndex,
			List<Integer> list) {
		
		if (target<0) {
			return;
		}
		
		//用list的和加到一起是否等于target比较慢，每次把target-当前的值，判断是否等于0快
		if (target==0) {
			resultList.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = startIndex; i < candidates.length; i++) {
			
			if (i>0&&candidates[i]==candidates[i-1]) {
				continue;
			}
			list.add(candidates[i]);
			dfs(candidates, target-candidates[i], resultList, i, list);
			list.remove(list.size()-1);
		}
	}
	
	/**
	 * 90. k数和 II
		给定n个不同的正整数，整数k（1<= k <= n）以及一个目标数字。　　　　

在这n个数里面找出K个数，使得这K个数的和等于目标数字，你需要找出所有满足要求的方案。
	 * @param A
	 * @param k
	 * @param target
	 * @return
	 */
	public List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
		
		List<List<Integer>> resultList=new ArrayList<List<Integer>>();
		
		if (A==null) {
			
			return resultList;
		}
		
		Arrays.sort(A);
		
		dfs2(A, k, target, 0, resultList, new ArrayList<Integer>());
		
		return resultList;
    }
	
	public void dfs2(int[] A, 
					int k, 
					int target,
					int startIndex,
					List<List<Integer>> resultList,
					List<Integer> list) {
		
		if (target<0||list.size()>k) {
			return;
		}
		if (k==list.size()&&target==0) {
			
			
			resultList.add(new ArrayList<Integer>(list));
			return;
		}
		
		
		for (int i = startIndex; i < A.length; i++) {
			
			list.add(A[i]);
			
			dfs2(A, k, target-A[i],i+1, resultList, list);
			
			list.remove(list.size()-1);
		}
		
	}
	
	
}
