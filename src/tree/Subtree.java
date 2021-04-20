package tree;

public class Subtree {

	public static void main(String[] args) {
		
		TreeNode node1=new TreeNode(-1);
		TreeNode node2=new TreeNode(-2);
		TreeNode node3=new TreeNode(-3);
		TreeNode node4=new TreeNode(-4);
		TreeNode node5=new TreeNode(-5);
		TreeNode node6=new TreeNode(-6);
		TreeNode node7=new TreeNode(-7);
		TreeNode node8=new TreeNode(-8);
		TreeNode node9=new TreeNode(-9);
		TreeNode node10=new TreeNode(-10);
		TreeNode node11=new TreeNode(-11);
		TreeNode node12=new TreeNode(-12);
		TreeNode node13=new TreeNode(-13);
		TreeNode node14=new TreeNode(-14);
		TreeNode node15=new TreeNode(-15);
		TreeNode node16=new TreeNode(-16);
		
		
		node1.left=node2;
		node1.right=node3;
		
		node2.left=node4;
		node2.right=node5;
		
		node3.left=node6;
		node3.right=node7;
		
		node4.left=node8;
		node4.right=node9;
		
		node5.left=node10;
		node5.right=node11;
		
		node6.left=node12;
		node6.right=node13;
		
		node7.left=node14;
		node7.right=node15;
		
		node8.left=node16;
		
		Subtree tree=new Subtree();
		
		System.out.println(tree.findSubtree2(node1));;
		
	}
	
	
	/**
	 * 93. 平衡二叉树
	 * 给定一个二叉树,确定它是高度平衡的。对于这个问题,一棵高度平衡的二叉树的定义是：一棵二叉树中每个节点的两个子树的深度相差不会超过1。 
	 * @param root
	 * @return
	 */
	 public static boolean isBalanced(TreeNode root) {
	        // write your code here
		 
		 if (root==null) {
			return true;
		}
		 
		 return getHeight(root).isBalanced;
	 }
	 
	 //由于要同时返回高度和是否平衡，所以定义了额外的类
	 public static Result getHeight(TreeNode root) {
		 
		 if (root==null) {
			return new Result(0, true);
		}
		 
		 Result left = getHeight(root.left);
		 Result right = getHeight(root.right);
		 
		 if (Math.abs(left.height-right.height)<=1&&(left.isBalanced&&right.isBalanced)) {
			return new Result(Math.max(left.height,right.height)+1,true);
		}else {
			return new Result(Math.max(left.height,right.height)+1, false);
		}
		 
	 }

	 
	 /**
	  * 628. 最大子树
	  * 给你一棵二叉树，找二叉树中的一棵子树，他的所有节点之和最大。
	  * @param root
	  * @return
	  */
	 Result result=null;
	 public TreeNode findSubtree(TreeNode root) {
	        // write your code here
		 
		 if (root==null) {
			return root;
		}
		 result=new Result(Integer.MIN_VALUE, root);
		 getSum(root);
		 
		 return result.node;
	 }
	 
	
	 public Result getSum(TreeNode root) {
		 
		 if (root==null) {
			return new Result(0, root);
		}
		 
		 Result left = getSum(root.left);
		 Result right = getSum(root.right);
		 
		 Result parent=new Result(left.sum+right.sum+root.val, root);
		 
		 if (parent.sum>result.sum) {
			result.sum=parent.sum;
			result.node=parent.node;
		}
		 return parent;
	 }
	/**
	 * 596. 最小子树
	 *    给一棵二叉树, 找到和为最小的子树, 返回其根节点。输入输出数据范围都在int内。
	 * @param root
	 * @return
	 */
	public TreeNode findSubtree2(TreeNode root) {
        // write your code here
		
		Result result = getMinSum(root);
		
		return result.subTreeNode;
    }
	
	/**
	 * 把叶子节点也当做有左右子树的节点，只不过左右子树是null
	 * @param root
	 * @return
	 */
	public Result getMinSum(TreeNode root) {
		
		if(root==null) {
			return new Result(root, 0 ,root,Integer.MAX_VALUE);
		}
		//找到左子树的和
		Result left=getMinSum(root.left);
		//右子树的和
		Result right=getMinSum(root.right);
		//加上根之后的和
		Result rootResult=new Result(root, left.sum+right.sum+root.val ,
				root,left.sum+right.sum+root.val);
		
		//将子树的最小sum和节点记录到rootResult中
		//如果不比左右子树的minSum大，要求的节点正好就是自己，要使用的minSum也是自己的sum
		
		if (rootResult.minSum>left.minSum) {
			rootResult.minSum=left.minSum;
			rootResult.subTreeNode=left.subTreeNode;
		}
		//经过和左节点的比较，rootResult.minSum的值可能已经改变，不再是sum，所以不能用sum进行比较
		if (rootResult.minSum>right.minSum) {
			rootResult.minSum=right.minSum;
			rootResult.subTreeNode=right.subTreeNode;
		}
		
		return rootResult;
	}
	
	/**
	 * 597. 具有最大平均数的子树
	 * 给一棵二叉树，找到有最大平均值的子树。返回子树的根结点。
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode findSubtree2(TreeNode root) {
        // write your code here
		
		Result result = getAvg(root);
		
		return result.subTreeNode;
    }
	
	public Result getAvg(TreeNode root) {
		
		if (root==null) {
			
			return new Result(root, 0,0,root);
		}
		
		
		Result left=getAvg(root.left);
		
		Result right=getAvg(root.right);
				
		Result rootResult=new Result(root, left.sum+right.sum+root.val,
				left.num+right.num+1,root);
		
		if (rootResult.maxAvg<left.maxAvg) {
			rootResult.maxAvg=left.maxAvg;
			rootResult.subTreeNode=left.subTreeNode;
		}
		
		if (rootResult.maxAvg<right.maxAvg) {
			rootResult.maxAvg=right.maxAvg;
			rootResult.subTreeNode=right.subTreeNode;
		}
		
		return rootResult;
	}
	
	
}

/**
 * 
 * 不能仅仅代表自身的属性，那样还得需要全局变量去记录minSum和MaxAvg
 * 
 * 同时省略get set方法
 *
 */
class Result{
	//自身
	TreeNode node;
	//要 求的 子树节点
	TreeNode subTreeNode;
	//算上自身的和
	int sum;
	//最小和
	int minSum;
	//算上自身的节点数
	int num;
	//算上自身的平均值
	double avg;
	//最大平均值
	double maxAvg;
	
	int height;
	boolean isBalanced;
	
	public Result(int sum, TreeNode node) {
		super();
		this.sum = sum;
		this.node = node;
	}
	
	public Result(int height, boolean isBalanced) {
		super();
		this.height = height;
		this.isBalanced = isBalanced;
	}
	
	
	public Result(TreeNode node,int sum,TreeNode subTreeNode,int minSum) {
		// TODO Auto-generated constructor stub
		this.node=node;
		this.sum=sum;
		//最开始初始化为自身
		this.subTreeNode=node;
		//不能直接初始化为最大值，否则在记录rootResult时，如果不比左右子树的minSum大，要使用自己的sum
		//初始化最大值，这个值就不会变化了
		this.minSum=minSum;
		
	}
	
	public Result(TreeNode node,int sum,int count,TreeNode subTreeNode) {
		// TODO Auto-generated constructor stub
		this.node=node;
		this.sum=sum;
		this.num=count;
		
		this.subTreeNode=node;
		
		if (node==null) {
			//求最大初始化为最小值
			this.maxAvg=Integer.MIN_VALUE;
			this.avg=Integer.MIN_VALUE;
		}else {
			this.avg=(double)Math.round(this.sum*100/this.num)/100;
			this.maxAvg=(double)Math.round(this.sum*100/this.num)/100;
		}
	}

	@Override
	public String toString() {
		return "Result [node=" + node + ", subTreeNode=" + subTreeNode + ", sum=" + sum + ", minSum=" + minSum
				+ ", num=" + num + ", avg=" + avg + ", maxAvg=" + maxAvg + "]";
	}
	
	
	
}
