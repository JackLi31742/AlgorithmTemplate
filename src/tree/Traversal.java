package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import tree.TreeNode;

public class Traversal {

	
	/**
	 * 102. 二叉树的层序遍历
	 * 
	 *  3
	   / \
	  9  20
	    /  \
	   15   7
	   
	   [
		  [3],
		  [9,20],
		  [15,7]
		]
	 */
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		if (root==null) {
			return result;
		}
		Queue<TreeNode> queue=new ArrayDeque<TreeNode>();
		queue.add(root);
//		List<Integer> first=new ArrayList<Integer>();
//		first.add(root.val);
//		result.add(first);
		while(!queue.isEmpty()) {
			int size=queue.size();
			List<Integer> list=new ArrayList<Integer>();
			//由于一层的要打印到一个list里，所以加了for循环
			for (int i = 0; i < size; i++) {
				TreeNode node=queue.poll();
				list.add(node.val);
//			System.out.print(node.val+" ");
				if (node.left!=null) {
//					list.add(node.left.val);
					queue.add(node.left);
				}
				if (node.right!=null) {
//					list.add(node.right.val);
					queue.add(node.right);
				}
				
			}
//			if (list.size()>0) {
				
				result.add(list);
//			}
		}
		
		return result;
    }
	
}
