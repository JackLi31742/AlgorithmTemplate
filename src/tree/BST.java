package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BST {
	
	public static void main(String[] args) {
		
		TreeNode node1=new TreeNode(2);
		TreeNode node2=new TreeNode(1);
		TreeNode node3=new TreeNode(3);
		TreeNode node4=new TreeNode(11);
		TreeNode node5=new TreeNode(20);
		TreeNode node6=new TreeNode(6);
		node1.left=node2;
		node1.right=node3;
		
//		node3.left=node4;
//		node3.right=node5;
//		node5.right=node6;
		
		BST bst=new BST();
//		bst.inorderPredecessor(node1, node2);
		
	}
	
	/**
	 * 85. 在二叉查找树中插入节点
	 * 给定一棵二叉查找树和一个新的树节点，将节点插入到树中。

	你需要保证该树仍然是一棵二叉查找树。
	 * @param root
	 * @param node
	 * @return
	 */
	public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
		if (root==null) {
			return node;
		}
		
		TreeNode cur=root;
		
		TreeNode pre=null;
		
		while(cur!=null) {
			
			pre=cur;
			
			if (cur.val>node.val) {
				cur=cur.left;
			}else {
				cur=cur.right;
			}
		}
		
		if (pre.val>node.val) {
			pre.left=node;
		}else {
			pre.right=node;
		}
		
		return root;
    }
	
	/**
	 * 87. 删除二叉查找树的节点
		给定一棵具有不同节点值的二叉查找树，删除树中与给定值相同的节点。
		如果树中没有相同值的节点，就不做任何处理。你应该保证处理之后的树仍是二叉查找树。
	 * @param root
	 * @param value
	 * @return
	 */

	/**
	 * dummy 
	 * @param root
	 * @param value
	 * @return
	 */
	public TreeNode removeNode2(TreeNode root, int value) {
		
		if (root==null) {
			return null;
		}
		
		TreeNode dummy=new TreeNode(-1);
		
		dummy.left=root;
		TreeNode cur=root;
		
		removeNode2(cur, dummy, value);
		
		return dummy.left;
	}
	
	public void removeNode2(TreeNode cur, TreeNode parent,int value) {
		
		if (cur==null) {
			return ;
		}
		
		if (parent.left==cur) {
			if (cur.val==value) {
				
				parent.left=remove(cur, value);
				return;
			}
		}
		
		if(parent.right==cur){
			if (cur.val==value) {
				parent.right=remove(cur, value);;
				return;
			}
		}
		
		if (cur.val>value) {
			removeNode2(cur.left, cur,value);
			return;
		}

		if (cur.val<value) {
			removeNode2(cur.right,cur, value);
			return ;
		}
	}
	
	public TreeNode remove(TreeNode cur, int value) {
		
		if (cur.left!=null&&cur.right!=null) {
			if (cur.right.left!=null) {
				
				cur.left.right=cur.right;
				cur=cur.left;
			}else {
				
				cur.right.left=cur.left;
				cur=cur.right;
			}
			
		}else if (cur.left!=null) {
			cur=cur.left;
		}else {
			cur=cur.right;
		}
		
		return cur;
	}
	
	
	

	
	
	
    
    
    
    
	/**
	 * 11. 二叉查找树中搜索区间
		给定一个二叉查找树和范围[k1, k2]。按照升序返回给定范围内的节点值。
	 * @param root
	 * @param k1
	 * @param k2
	 * @return
	 */
	public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
		
		List<Integer> result =new ArrayList<Integer>();
		
		TreeNode cur =root;
		
		searchRange(cur, k1, k2, result);
		
		return result;
    }
	
	public void searchRange(TreeNode root, int k1, int k2,List<Integer> result) {
		
		if (root==null) {
			
			return ;
		}
		
		if (root.val>k2) {
			
			searchRange(root.left, k1, k2, result);
			return ;
		}
		
		if (root.val<k1) {
			
			searchRange(root.right, k1, k2, result);
			return ;
		}
		
		result.add(root.val);
		searchRange(root.left, k1, k2, result);
		searchRange(root.right, k1, k2, result);
	}
	
	/**
	 * 689. 两数之和 - BST版本
	 * 给一棵二叉搜索树以及一个整数 n, 在树中找到和为 n 的两个数字
	 * @param root
	 * @param n
	 * @return
	 */
	public int[] twoSum(TreeNode root, int n) {
        // write your code here
    }
	
	/**
	 * 701. 修剪二叉搜索树
	 * 给定一个有根的二分搜索树和两个数字min和max，
	 * 修整这个树使得所有的数字在这个新的树种都是在min和max之间（包括min和max）。
	 * 然后这个所得的树仍然是合法的二分搜索树。
	 * @param root
	 * @param minimum
	 * @param maximum
	 * @return
	 */
	public TreeNode trimBST(TreeNode root, int minimum, int maximum) {
        // write your code here
    }
	
	
	/**
	 * 915. BST的中序前驱节点
	 * 给出一棵二叉搜索树以及其中的一个节点，找到这个节点在这棵树中的中序前驱节点。
	 * @param root
	 * @param p
	 * @return
	 */
	public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
	        // write your code here
		if (root==null||p==null) {
			return null;
		}
		
		Stack<TreeNode> stack=new Stack<TreeNode>();
		
		TreeNode cur=root;
		
		TreeNode pre=null;
		
		while(cur!=null||!stack.isEmpty()) {
			
			if(cur!=null) {
				stack.push(cur);
				cur=cur.left;
			}else {
				TreeNode node = stack.pop();
				//在pop的时候才是正在的中序，push的时候不是
				if (node==p) {
					break;
				}else {
					pre=node;
				}
				cur=node.right;
			}
			
			
		}
		
		return pre;
	}
    
}




