package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
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
	
	
	/**
	 * 103. 二叉树的锯齿形层次遍历
	 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.add(root);
		int height=1;
		while(!queue.isEmpty()) {
			int size=queue.size();
			LinkedList<Integer> list=new LinkedList<Integer>();
			
			for (int i = 0; i < size; i++) {
				TreeNode node=queue.poll();
				if (height%2==1) {
					//如果本行是奇数
					list.add(node.val);
				}else {
					//如果本行是偶数，就采用头插法
					list.addFirst(node.val);
				}
				
				if (node.left!=null) {
					queue.add(node.left);
				}
				if (node.right!=null) {
					queue.add(node.right);
				}
				
			}
			
			height++;
			result.add(list);
		}
		
		return result;

	}
	
	/**
	 * 104. 二叉树的最大深度
	 * 给定一个二叉树，找出其最大深度。
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
		if (root==null) {
			return 0;
		}
		return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
	/**
	 * 非递归，用层次遍历
	 * @param root
	 * @return
	 */
	public int maxDepth2(TreeNode root) {
		if (root==null) {
			return 0;
		}

		int depth=0;
		Queue<TreeNode> queue=new ArrayDeque<TreeNode>();
		
		queue.add(root);
		while(!queue.isEmpty()) {
			int size=queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur=queue.poll();
				
				if (cur.left!=null) {
					queue.add(cur.left);
					
				}
				if (cur.right!=null) {
					queue.add(cur.right);
				}
			}
			depth++;
		}
		
		return depth;
    }
	
	/**
	 * 226. 翻转二叉树
	 * 
			 4
		   /   \
		  2     7
		 / \   / \
		1   3 6   9
		
		     4
		   /   \
		  7     2
		 / \   / \
		9   6 3   1
	 * @param root
	 * @return
	 */
	public static TreeNode invertTree(TreeNode root) {
		if (root==null) {
			return null;
		}
		//不能直接把root赋值给newRoot，会把原有的引用复制过去
		TreeNode newRoot=new TreeNode(root.val);
		newRoot.right=invertTree(root.left);
		newRoot.left=invertTree(root.right);
		return newRoot;
    }
	
	public static TreeNode invertTree2(TreeNode root) {
		if (root==null) {
			return null;
		}
		Queue<TreeNode> queue=new ArrayDeque<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode node=queue.poll();
			//交换
			TreeNode temp=node.left;
			node.left=node.right;
			node.right=temp;
			if (node.left!=null) {
				queue.add(node.left);
			}
			if (node.right!=null) {
				queue.add(node.right);
			}
		}
		return root;
	}
}
