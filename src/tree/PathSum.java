package tree;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

public class PathSum {

	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(5);
		TreeNode node2=new TreeNode(4);
		TreeNode node3=new TreeNode(8);
		TreeNode node4=new TreeNode(11);
		TreeNode node5=new TreeNode(13);
		TreeNode node6=new TreeNode(4);
		TreeNode node7=new TreeNode(7);
		TreeNode node8=new TreeNode(2);
		TreeNode node9=new TreeNode(5);
		TreeNode node10=new TreeNode(1);
		
		node1.left=node2;
		node1.right=node3;
		node2.left=node4;
		node3.left=node5;
		node3.right=node6;
		node4.left=node7;
		node4.right=node8;
		node6.left=node9;
		node6.right=node10;
//		System.out.println(postorderTraversal2(node1));;
//		System.out.println(zigzagLevelOrder(node1));
		
		
		System.out.println(pathSum(node1, 22));;
	}
	/**
	 * 112. 路径总和
	 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
	 * @param root
	 * @param sum
	 * @return
	 */
	public static boolean hasPathSum(TreeNode root, int sum) {
		if (root==null) {
			return false;
		}
		int result=0;
		return hasPathSum(root, sum,result);
    }
	
	public static boolean hasPathSum(TreeNode root, int sum,int result) {
		if (root==null) {
			return false;
		}
		result+=root.val;
		if (root.left==null&&root.right==null&&result==sum) {
			return true;
		}
		
		boolean left=hasPathSum(root.left, sum, result);
		//相当于剪枝
		if (left) {
			return true;
		}
		
		boolean right=hasPathSum(root.right, sum, result);
		if (right) {
			return true;
		}
//		return left||right;
		return false;
	}
	
	/**
	 * 113. 路径总和 II
	 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
	 * @param root
	 * @param sum
	 * @return
	 */
	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> list=new ArrayList<List<Integer>>();
		if (root==null) {
			return list;
		}
		
		int result=0;
		List<Integer> eachList=new ArrayList<Integer>();
		pathSum(root, sum,list,result,eachList);
		
		return list;
    }
	
	public static void pathSum(TreeNode root, int sum,
			List<List<Integer>> list,int result,List<Integer> eachList) {
		if (root!=null) {
			result+=root.val;
			eachList.add(root.val);
			if (root.left==null&&root.right==null&&result==sum) {
				list.add(new ArrayList<Integer>(eachList));
				eachList.remove(eachList.size()-1);
				return ;
			}
			
			pathSum(root.left, sum, list, result, eachList);
			pathSum(root.right, sum, list, result, eachList);
			
			eachList.remove(eachList.size()-1);
		}
	}
	
	/**
	 * 437. 路径总和 III
	 * 找出路径和等于给定数值的路径总数。

		路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
	 * @param root
	 * @param sum
	 * @return
	 */
	public int pathSum3(TreeNode root, int sum) {
		
		if (root==null) {
			return 0;
		}
		int pathSum=0;
		int count=0;
		
		TreeNode cur=root;
		while(cur!=null) {
			
		}
//		return count;
        
    }
	
	/**
	 * 124. 二叉树中的最大路径和
	 * 给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

	 * @param root
	 * @return
	 */
	public int maxPathSum(TreeNode root) {

    }
}
