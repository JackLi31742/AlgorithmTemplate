package tree;

public class Flatten {

	
	/**
	 * 453 · 将二叉树拆成链表
	 * 将一棵二叉树按照前序遍历拆解成为一个 假链表。
	 * 所谓的假链表是说，用二叉树的 right 指针，来表示链表中的 next 指针。
	 * 
	 * 
	 * 尽可能避免使用全局变量
		容易写出 BUG
		可以把需要修改的变量作为参数传入到函数里
		或者是放在 return value 里
	 * @param root
	 */
	public void flatten(TreeNode root) {
        // write your code here
		
		helper(root); 
    }
	
	// flatten root and return the last node
	private TreeNode helper(TreeNode root) {
	    if (root == null) {
	        return null;
	    }
	    
	    TreeNode leftLast = helper(root.left);
	    TreeNode rightLast = helper(root.right);
	    
	    // connect leftLast to root.right
	    if (leftLast != null) {
	        leftLast.right = root.right;
	        root.right = root.left;
	        root.left = null;
	    }
	    
	    //返回的时候是用右左根的思路做
	    if (rightLast != null) {
	        return rightLast;
	    }
	    
	    if (leftLast != null) {
	        return leftLast;
	    }
	    
	    return root;
	}
}
