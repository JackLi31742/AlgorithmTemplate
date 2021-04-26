package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import tree.TreeNode;

public class DanDiaoStack {

	
	/**
	 * 122 · 直方图最大矩形覆盖
	 * 给出的n个非负整数表示每个直方图的高度，每个直方图的宽均为1，在直方图中找到最大的矩形面积。
	 * 
	 * 最大矩形一定是某一个柱形往左往右直到不能前进，形成的矩形
		• 需要知道一个数字往左和往右第一个小于这个数字的位置
		• 单调递增栈
		– 压栈时弹出大于等于自己的值
		– 最后停下来时碰到的栈顶就是左边第一个比自己小的值
		– 一个数X被新来的值R弹出栈顶，那么R就是X右边第一个小于等于X的值
		• 如果有相同的数，那么最靠右的bar会求得最大面积
		• 最后插入-1 ，就可以把之前所有的元素全部出栈
		• 时间复杂度O(N)
		
		从而可以计算出这两个数中间的部分宽度 * 被pop出的数，
		就是以这个被pop出来的数为最低的那个直方向两边展开的最大矩阵面积。
		 因为要计算两个数中间的宽度，因此放在 stack 里的是每个数的下标。
	 * @param heights
	 * @return
	 */
	public int largestRectangleArea(int[] height) {
        // write your code here
		if (height == null || height.length == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<Integer>();  //维护单调递增
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int curt = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && curt <= height[stack.peek()]) {	//如果栈顶高度大于当前高度	            
                int h = height[stack.pop()];		//保存栈顶元素信息
//                int w = stack.isEmpty() ? i : i - stack.peek() - 1;		//如果栈已经为空，宽度为i，否则i-s.top()-1
               int left=stack.isEmpty()?0:(stack.peek()+1);
                int right=i-1;
                max = Math.max(max, h * (right-left+1));
            }
            stack.push(i);				//压入栈中
        }
        
        return max;
    }
	
	
	
	/**
	 * 126 · 最大树
	 * 给出一个没有重复数字的整数数组，在此数组上建立最大树，其定义如下：
		
		根是数组中最大的数
		左子树和右子树元素分别是被父节点元素切分开的子数组中的最大值
		利用给定的数组构造最大树。
		
		我们发现，每个值X的父亲一定是min{左边第一个比它大的值L，右边第一个比它大的值R}
		– ….., L, <X, …,<X, X, <X, …, <X, R,…
		– 如果L<R，[L, R]里一定R先做根。然后[L, R)里L先做根，然后就是X
		– 如果L>R， [L, R]里一定L先做根。然后(L, R]里R先做根，然后就是X
		• 如何找到每个值左右第一个比它大的值？
		– 单调递减栈
	 * @param A
	 * @return
	 */
	public TreeNode maxTree(int[] A) {
        // write your code here
		int n = A.length;
        int[] stack = new int[n + 2];
        int cnt = -1;
        List<TreeNode> pNodes = new ArrayList<>();
        for (int i = 0; i < n; i++){
            TreeNode treeNode = new TreeNode(A[i]);
            pNodes.add(treeNode);
            if (cnt != -1 && A[stack[cnt]] < A[i]) {
                // 如果栈中有元素且元素小于当前元素，则弹出栈内元素
                while (cnt > -1 && A[stack[cnt]] < A[i]) {
                    cnt--;
                }
                // 弹出的最后一个元素就是当前元素的左儿子
                pNodes.get(i).left = pNodes.get(stack[cnt + 1]);
            }
            if (cnt != -1 && A[stack[cnt]] > A[i]) {
                // 如果栈内有元素且栈顶元素大于当前元素
                // 那么当前元素是栈顶元素的右儿子
                pNodes.get(stack[cnt]).right = pNodes.get(i);
            }
            // 压入当前元素
            stack[++cnt] = i;
        }
        return pNodes.get(stack[0]);	
    }
}
