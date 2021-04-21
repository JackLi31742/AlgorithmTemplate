package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Traversal {

	public static void main(String[] args) {
		
		
		
	}
	/**
	 * 144. 二叉树的前序遍历
	 * 
	 */
	//递归
	public List<Integer> preorderTraversal(TreeNode root) {

		List<Integer> result=new ArrayList<Integer>();
		if (root==null) {
			return result;
		}
		
		result.add(root.val);
		preorderTraversal(root.left, result);
		preorderTraversal(root.right, result);
		return result;
    }
	
	public List<Integer> preorderTraversal(TreeNode node,List<Integer> result) {
		if (node==null) {
			return result;
		}
		result.add(node.val);
		preorderTraversal(node.left, result);
		preorderTraversal(node.right, result);
		return result;
		
	}
	
	//非递归需要借助栈
	public static List<Integer> preorderTraversal2(TreeNode root) {

		List<Integer> result=new ArrayList<Integer>();
		if (root==null) {
			return result;
		}
		
		
		TreeNode cur=root;
		Stack<TreeNode> stack=new Stack<TreeNode>();
		if(cur!=null) {
			result.add(cur.val);
			if (cur.right!=null) {
				stack.push(cur.right);
			}
			
			if (cur.left!=null) {
				stack.push(cur.left);
			}
			
			while(!stack.isEmpty()) {
				TreeNode node=stack.pop();
				result.add(node.val);
				if (node.right!=null) {
					stack.push(node.right);
				}
				
				if (node.left!=null) {
					stack.push(node.left);
				}
			}
			
		}
		
		return result;
    }
	
	//只放右节点
	public static List<Integer> preorderTraversal3(TreeNode root) {

		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		//第一次把root放进去，是为了好进第一个while循环，因为当节点走到树的右侧时，其实和左侧的套路一样
		stack.push(cur);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			while (node != null) {
				result.add(node.val);
				if (node.right != null) {
					stack.push(node.right);
				}
				node = node.left;
			}
		}

		return result;
	}
	
	/**
	 * 94 中序遍历
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		inorderTraversal(root,result);
		return result;
    }
	
	public static List<Integer> inorderTraversal(TreeNode root,List<Integer> result) {
		if (root==null) {
			return result;
		}
		inorderTraversal(root.left, result);
		result.add(root.val);
		inorderTraversal(root.right, result);
		return result;
	}
	
	//非递归
	public static List<Integer> inorderTraversal2(TreeNode root) {
		
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}

		TreeNode cur = root;
		//从根节点到当前节点的路径
		Stack<TreeNode> stack = new Stack<TreeNode>();

		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			TreeNode node = stack.pop();
			result.add(node.val);

			cur = node.right;
			//有右子树，所以找到右子树的最小值
			//没有右子树，下一个节点就是它的父节点，即栈里的顶点
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}

		}

		return result;
		
	}
	
	public static List<Integer> inorderTraversal3(TreeNode root) {
		
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}

		TreeNode cur = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();

		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}else {
				
				TreeNode node = stack.pop();
				result.add(node.val);
				
				cur = node.right;
			}
			

		}

		return result;
		
	}
	
	/**
	 * 145 后序遍历
	 */
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result=new ArrayList<Integer>();
		
		postorderTraversal(root, result);
		return result;
    }
	
	public static List<Integer> postorderTraversal(TreeNode root,List<Integer> result) {
		if (root==null) {
			return result;
		}
		postorderTraversal(root.left, result);
		postorderTraversal(root.right, result);
		result.add(root.val);
		return result;
	}
	
	//非递归
	public static  List<Integer> postorderTraversal2(TreeNode root) {
		List<Integer> result=new ArrayList<Integer>();
		if (root==null) {
			return result;
		}
		TreeNode cur=root;
		
		Stack<Node> stack=new Stack<Node>();
		
		while(cur!=null||!stack.isEmpty()) {
			
			//一直找到左子树的最左边
			while (cur!=null) {
				Node node=new Node(cur, false);
				stack.push(node);
				cur=cur.left;
			}
			
			if (!stack.isEmpty()) {
				if (!stack.peek().flag) {
					cur=stack.peek().treeNode.right;
					//标明从左边的子树返回到了根节点
					stack.peek().flag=true;
					
				}else {
					result.add(stack.pop().treeNode.val);
					
				}
			}
			
			
		}
		return result;
	}
	
	
	static class Node {

		public TreeNode treeNode;
		public boolean flag;
		
		public Node(TreeNode treeNode, boolean flag) {
			super();
			this.treeNode = treeNode;
			this.flag = flag;
		}

		@Override
		public String toString() {
			return "Node [treeNode=" + treeNode + ", flag=" + flag + "]";
		}
		
	}
	
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
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.add(root);
		int height=1;
		while(!queue.isEmpty()) {
			int size=queue.size();
			List<Integer> list=new ArrayList<Integer>();
			Stack<Integer> stack=new Stack<Integer>();
			for (int i = 0; i < size; i++) {
				TreeNode node=queue.poll();
				if (height%2==1) {
					//如果本行是奇数
					list.add(node.val);
				}else {
					//如果本行是偶数，就先放到栈里，然后再遍历到list中
					stack.add(node.val);
				}
				
				if (node.left!=null) {
					queue.add(node.left);
				}
				if (node.right!=null) {
					queue.add(node.right);
				}
				
			}
			if (height%2==0) {
				
				for (int i = 0; i < size; i++) {
					list.add(stack.pop());
				}
			}
			
			height++;
			result.add(list);
		}
		
		return result;

	}
	
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
