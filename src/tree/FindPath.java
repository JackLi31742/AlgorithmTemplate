package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tree.TreeNode;


public class FindPath {
	
	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(1);
		TreeNode node2=new TreeNode(2);
		TreeNode node3=new TreeNode(3);
		TreeNode node4=new TreeNode(5);
		
		node1.left=node2;
		node1.right=node3;
		
		node2.right=node4;
		
		System.out.println(binaryTreePaths(node1));;
	}
	
	/**
	 * lintcode 480. 二叉树的所有路径
	 * 给一棵二叉树，找出从根节点到叶子节点的所有路径。
	 * @param root
	 * @return
	 */
	 public static List<String> binaryTreePaths(TreeNode root) {
	        // write your code here
		 
		 List<String> result=new ArrayList<>();
		 
		 
		 if (root==null) {
			return result;
		}
		 LinkedList<Integer> temp=new LinkedList<Integer>();
		 
		 findPath(root, temp,result);
		 
		 
		 return result;
	 }
	 
	 public static void findPath(TreeNode root,LinkedList<Integer> temp,List<String> result) {
		 
		 if (root!=null) {
			 
			temp.add(root.val);
			
			findPath(root.left, temp,result);
			
			findPath(root.right, temp,result);
			
			//到达叶子节点，再把temp加到result里，其余时候不加，结果就不会多
			if (root.left==null&&root.right==null) {
				
				getResult(temp, result);
			}
			
			temp.removeLast();
		}
		 
	 }

	 
	public static void getResult(LinkedList<Integer> temp, List<String> result) {

		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < temp.size() - 1; i++) {
			sb.append(temp.get(i) + "->");
		}
		
		sb.append(String.valueOf(temp.getLast()));
		
		result.add(sb.toString());
	}
}
