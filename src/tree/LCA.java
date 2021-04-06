package tree;

import java.util.HashSet;

import tree.TreeNode;

public class LCA {

	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(1);
		TreeNode node2=new TreeNode(2);
		TreeNode node3=new TreeNode(3);
		TreeNode node4=new TreeNode(4);
		TreeNode node5=new TreeNode(5);
		node1.right=node2;
		node2.right=node3;
		node3.right=node4;
		node4.right=node5;
		LCA lca=new LCA();
		System.out.println(lca.lowestCommonAncestor3(node1, node2, new TreeNode(8)));;
	}
	/**
	 * 88. 最近公共祖先
	 * 给定一棵二叉树，找到两个节点的最近公共父节点(LCA)。

	最近公共祖先是两个节点的公共的祖先节点且具有最大深度。

	假设给出的两个节点都在树中存在
	
	
	使用分治法，递归方式
	
	A和B要么分别位于LCA的左右，要么LCA就是其中的一个点，而另一个点再左子树或者右子树中
	
	定义方法f，返回值为A和B是否在子树中
	 * @param root
	 * @param A
	 * @param B
	 * @return
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
		
		Result2 result = isContain(root, A, B);
		
		return result.node;
		
    }
	
	public Result2 isContain(TreeNode root, TreeNode A, TreeNode B) {
		
		if (root==null) {
			return new Result2(root, false);
		}
		
		if (root==A||root==B) {
			return new Result2(root, true);
		}
		
		Result2 left = isContain(root.left, A, B);
		
		Result2 right = isContain(root.right, A, B);
		
		if (left.isContain&&right.isContain) {
			return new Result2(root, true);
		}
		
		if (left.isContain) {
			return new Result2(left.node, true);
		}
		
		if (right.isContain) {
			
			return new Result2(right.node, true);
		}
		
		return new Result2(null, false);
	}
	
	/**
	 * 474. 最近公共祖先 II
	 * 每个节点除了左右儿子指针以外，还包含一个父亲指针parent，指向自己的父亲。
	 * 
	 * 使用 HashSet 记录从 A 到根的所有点
访问从 B 到根的所有点，第一个出现在 HashSet 中的就是
	 */
	
	public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here
		
		if (root==null) {
			return null;
		}
		
		HashSet<ParentTreeNode> aSet=new HashSet<ParentTreeNode>();
		
		ParentTreeNode curNode=A;
		
		while(curNode!=null) {
			
			aSet.add(curNode);
			curNode=curNode.parent;
		}
		
		curNode=B;
		
		while(curNode!=null) {
			if (aSet.contains(curNode)) {
				return curNode;
			}
			curNode=curNode.parent;
		}
		
		return null;
    }
	
	/**
	 * 578. 最近公共祖先 III
		
		这两个节点未必都在这棵树上出现。
		
	 * @param root
	 * @param A
	 * @param B
	 * @return
	 */
	public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
		
		Result2 result2 = isContain3(root, A, B);
		
		if (result2.isAContain&&result2.isBContain) {
			return result2.node;
		}
		return null;
    }
	
	
	public Result2 isContain3(TreeNode root, TreeNode A, TreeNode B) {
		
		if (root==null) {
			return new Result2(root, false,false);
		}
		//在这会导致提前return，
		
//		if (root==A) {
//			return new Result2(root, true, false);
//		}
//		
//		if (root==B) {
//			
//			return new Result2(root, false, true);
//		}
		
		Result2 left = isContain3(root.left, A, B);
		
		Result2 right = isContain3(root.right, A, B);
		
		boolean isAContain=left.isAContain||right.isAContain||root==A;
		boolean isBContain=left.isBContain||right.isBContain||root==B;
		
		
		if (root==A||root==B) {
			return new Result2(root, isAContain, isBContain);
		}
		//由于isAContain和isBContain都需要判断，所以不能直接return
		//如果用isAContain和isBContain判断情况就太多了，而且返回值无法确定到底返回哪个节点
		//
		if (left.node!=null&&right.node!=null) {
			return new Result2(root, isAContain, isBContain);
		}
		
		if (left.node!=null) {
			return new Result2(left.node, isAContain, isBContain);
					
		}
		
		if (right.node!=null) {
			
			return new Result2(right.node, isAContain, isBContain);
		}
		
		
		
		return new Result2(null, false,false);
	}
}

class ParentTreeNode {
	public ParentTreeNode parent, left, right;
}

class Result2{
	TreeNode node;
	boolean isContain;
	
	boolean isAContain;
	boolean isBContain;
	public Result2(TreeNode node, boolean isContain) {
		super();
		this.node = node;
		this.isContain = isContain;
	}
	public Result2(TreeNode node, boolean isAContain, boolean isBContain) {
		super();
		this.node = node;
		this.isAContain = isAContain;
		this.isBContain = isBContain;
	}
	@Override
	public String toString() {
		return "Result2 [node=" + node + ", isContain=" + isContain + ", isAContain=" + isAContain + ", isBContain="
				+ isBContain + "]";
	}
	
	
	
}