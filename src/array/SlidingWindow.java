package array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class SlidingWindow {

	
	/**
	 * 239. 滑动窗口最大值
	 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
	 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

		返回滑动窗口中的最大值。

	输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
	输出: [3,3,5,5,6,7] 
	解释: 
	
	  滑动窗口的位置                最大值
	---------------               -----
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7

	 * 
	 * 类似单调栈，但是两端都有操作
		• 基本思想：如果A[i]<=A[j]，且i<j，那么A[i]就没有用了，即以后永远不会成为窗
		口最大值
		• 窗口向右移动，左端元素移出队首（如果仍在队列中），右端元素A[j]移进队尾，
		并删除所有<=A[j]的A[i]
		• 时间复杂度O(N)
	 */
	void inQueue(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) {
            deque.pollLast();
        }
        deque.offer(num);
    }
    
    void outQueue(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }
    
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here
    	ArrayList<Integer> ans = new ArrayList<Integer>();
        Deque<Integer> deque = new ArrayDeque<Integer>();
        if (nums.length == 0) {
            return ans;
        }
        for (int i = 0; i < k - 1; i++) {
            inQueue(deque, nums[i]);
        }
        //每次只要队列中的个数达到k，就把最左边的移除，时刻保证队列里是k-1
        //而不是k个，等到k+1的时候再删除
        for(int i = k - 1; i < nums.length; i++) {
            inQueue(deque, nums[i]);
            ans.add(deque.peekFirst());
            outQueue(deque, nums[i - k + 1]);
        }
        return ans;

    }
}
