package tree;

/**
 * 
 * 后序遍历中用到的保存到栈里的节点
 */
public class Node {

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
