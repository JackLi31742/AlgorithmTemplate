package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ValidTree {

	
	
	/**
	 * 95. 验证二叉查找树
	 * 给定一个二叉树，判断它是否是合法的二叉查找树(BST)

	一棵BST定义为：
	
	节点的左子树中的值要严格小于该节点的值。
	节点的右子树中的值要严格大于该节点的值。
	左右子树也必须是二叉查找树。
	一个节点的树也是二叉查找树。
	
	
	不能仅仅判断节点和它的左右子节点，因为在左右子树中，有可能左子树的最大值，右子树的最小值会导致非法
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
        // write your code here
		Result3 result3 = isValid(root);
		return result3.isValid;
    }
	
	public Result3 isValid(TreeNode root) {
		
		if (root==null) {
			return new Result3(true);
		}
		
		Result3 left = isValid(root.left);
		
		Result3 right = isValid(root.right);
		
		//先判断非法的，符合合法的条件比较多
		
		if (!left.isValid||!right.isValid) {
			return new Result3(false);
		}
		
		if (left.maxNode!=null&&left.maxNode.val>=root.val) {
			return new Result3(false);
		}
		
		if (right.minNode!=null&&right.minNode.val<=root.val) {
			return new Result3(false);
		}
		
		//以整体看待这三个节点
		Result3 result3=new Result3(true);
		
		//right.maxNode 和right.minNode是同时存在的，所以不需要上述的多个if else 判断
		result3.minNode = left.minNode != null ? left.minNode : root;
        result3.maxNode = right.maxNode != null ? right.maxNode : root;
		return result3;
	}
	
	/**
	 * 178 · 图是否是树
	 * 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 写一个函数去判断这张｀无向｀图是否是一棵树
	 * @param n
	 * @param edges
	 * @return
	 */
	public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        
        if (edges.length != n - 1) {
            return false;
        }
        
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        
        // bfs
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();
        
        queue.offer(0);
        hash.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (hash.contains(neighbor)) {
                    continue;
                }
                hash.add(neighbor);
                queue.offer(neighbor);
            }
        }
        
        return (hash.size() == n);
    }
    
    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        return graph;
    }
}

class Result3{
	
	boolean isValid;
	//用是否为null判断，比用isLeft,LeftMax这样的boolean和int要好
	TreeNode maxNode;
	TreeNode minNode;
	
	public Result3(boolean isValid) {
		this(isValid,null,null);
	}
	
	
	public Result3(boolean isValid, TreeNode maxNode, TreeNode minNode) {
		super();
		this.isValid = isValid;
		this.maxNode = maxNode;
		this.minNode = minNode;
	}


	@Override
	public String toString() {
		return "Result3 [isValid=" + isValid + ", maxNode=" + maxNode + ", minNode=" + minNode + "]";
	}
	
}
