package kth;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import tree.TreeNode;

public class KthSmallest_Closest_BST {

	/**
	 * 902 · BST中第K小的元素
	 * 给一棵二叉搜索树，写一个 KthSmallest 函数来找到其中第 K 小的元素。
	 * 
	 * 1 ≤ k ≤ 树的总节点数
	 * 
	 * 
	 * 时间复杂度分析
		O(k + h)
		当 k 是 1 的时候 ==> O(h)
		当 k 是 n 的时候 ==> O(n)
		k和h两者取大值


	 * 如果这棵 BST 经常会被修改(插入/删除操作)
	 * 并且你需要很快速的找到第 K 小的元素呢？你会如何优化这个 KthSmallest 函数？
	 * 
	 * 
	 * 在 TreeNode 中增加一个 counter，代表整个树的节点个数
		也可以用一个 HashMap<TreeNode, Integer> 来存储某个节点为代表的子树的节点个数
		在增删查改的过程中记录不断更新受影响节点的 counter
		在 kthSmallest 的实现中用类似 Quick Select 的算法去找到 kth smallest element
		时间复杂度为 O(h)，h 为树的高度。
	 * 
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallest(TreeNode root, int k) {
        // write your code here
		Stack<TreeNode> stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    
        for (int i = 0; i < k - 1; i++) {
            TreeNode node = stack.peek();
            
            if (node.right == null) {
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            } else {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        
        return stack.peek().val;
    }
	
	
	/**
	 * 900 · 二叉搜索树中最接近的值
	 * 给一棵非空二叉搜索树以及一个target值，找到在BST中最接近给定值的节点值
	 * 如果使用中序遍历，时间复杂度是多少？
如果使用 lowerBound / upperBound 的方法，时间复杂度是多少？

求出 lowerBound 和 upperBound。即 < target 的最大值和 >= target 的最小值。 然后在两者之中去比较谁更接近，然后返回即可。

时间复杂度为 O(h)，注意如果你使用 in-order traversal 的化，时间复杂度会是 
o(n) 并不是最优的。另外复杂度也不是 O(logn)
 因为BST 并不保证树高是 logn 的。
	 */
	
	public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0;
        }
        
        TreeNode lowerNode = lowerBound(root, target);
        TreeNode upperNode = upperBound(root, target);
        
        if (lowerNode == null) {
            return upperNode.val;
        }
        
        if (upperNode == null) {
            return lowerNode.val;
        }
        
        if (target - lowerNode.val > upperNode.val - target) {
            return upperNode.val;
        }
        
        return lowerNode.val;
    }
    
    // find the node with the largest value that smaller than target
    private TreeNode lowerBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        
        if (target <= root.val) {
            return lowerBound(root.left, target);
        }
        
        // root.val < target
        TreeNode lowerNode = lowerBound(root.right, target);
        if (lowerNode != null) {
            return lowerNode;
        }
        
        return root;
    }
    
    // find the node with the smallest value that larger than or equal to target
    private TreeNode upperBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        
        if (root.val < target) {
            return upperBound(root.right, target);
        }
        
        // root.val >= target
        TreeNode upperNode = upperBound(root.left, target);
        if (upperNode != null) {
            return upperNode;
        }
        
        return root;
    }
    
    /**
     * 901 · 二叉搜索树中最接近的值 II、
     * 
     * 给定一棵非空二叉搜索树以及一个target值，找到 BST 中最接近给定值的 k 个数。
     * 
     * 
     * 如果是用中序遍历得到从小到大的所有值，接下来的问题相当于求排序数组的最接近的k个值
     * 
     * 方法1 暴力做法
	先用 inorder traversal 求出中序遍历
	找到第一个 >= target 的位置 index
	从 index-1 和 index 出发，设置两根指针一左一右，获得最近的 k
	个整数
	
	
	
     */
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> values = new ArrayList<>();
        
        traverse(root, values);
        
        int i = 0, n = values.size();
        for (; i < n; i++) {
            if (values.get(i) >= target) {
                break;
            }
        }
        
        if (i >= n) {
            return values.subList(n - k, n);
        }
        
        int left = i - 1, right = i;
        List<Integer> result = new ArrayList<>();
        for (i = 0; i < k; i++) {
            if (left >= 0 && (right >= n || target - values.get(left) < values.get(right) - target)) {
                result.add(values.get(left));
                left--;
            } else {
                result.add(values.get(right));
                right++;
            }
        }
        
        return result;
    }
    
    private void traverse(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }
        
        traverse(root.left, values);
        values.add(root.val);
        traverse(root.right, values);
    }
    
    /**
     * 方法2 使用两个 Iterator 一个 iterator move forward
	另一个iterator move backward
	每次 i++ 的时候根据 stack，挪动到 next node
	每次i--的时候根据 stack, 挪动到 prev node
     */
    
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        List<Integer> values = new ArrayList<>();
        
        if (k == 0 || root == null) {
            return values;
        }
        
        Stack<TreeNode> lowerStack = getStack(root, target);
        Stack<TreeNode> upperStack = new Stack<>();
        upperStack.addAll(lowerStack);
        if (target < lowerStack.peek().val) {
            moveLower(lowerStack);
        } else {
            moveUpper(upperStack);
        }
        
        for (int i = 0; i < k; i++) {
            if (lowerStack.isEmpty() ||
                   !upperStack.isEmpty() && target - lowerStack.peek().val > upperStack.peek().val - target) {
                values.add(upperStack.peek().val);
                moveUpper(upperStack);
            } else {
                values.add(lowerStack.peek().val);
                moveLower(lowerStack);
            }
        }

        return values;
    }
    
    private Stack<TreeNode> getStack(TreeNode root, double target) {
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            
            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        return stack;
    }
    
    public void moveUpper(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.right == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
            return;
        }
        
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    public void moveLower(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.left == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().left == node) {
                node = stack.pop();
            }
            return;
        }
        
        node = node.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }
}
