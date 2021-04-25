package heap;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindMedian {

	/**
	 *    lintcode 81 数 据流中位数 
	 * @param nums
	 * @return
	 */
	public int[] medianII(int[] nums) {
        // write your code here
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] results = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
			
        	if (maxHeap.size()==0|| nums[i] <=maxHeap.peek()) {
        		
        		maxHeap.offer(nums[i]);
        	}else {
        		
        		minHeap.offer(nums[i]);
        	}
        	
        	balance(maxHeap,minHeap);
        	
        	//maxheap 的大小 =minheap的大小或者size +1
        	
        	results[i]=maxHeap.peek();
		}
        
        
        return results;
    }
	
	public void balance(PriorityQueue<Integer> maxHeap,PriorityQueue<Integer> minHeap) {
		
		while(maxHeap.size()< minHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
		
		while(minHeap.size() < maxHeap.size()-1) {
			minHeap.offer(maxHeap.poll());
		}
		
		
	}
	/**
	 * 360 · 滑动窗口的中位数
	 * 给定一个包含 n 个整数的数组，和一个大小为 k 的滑动窗口,从左到右在数组中滑动这个窗口，
	 * 找到数组中每个窗口内的中位数。(如果数组个数是偶数，则在该窗口排序数字后，返回第 N/2 个数字。)
	 * 
	 * 存下标
	 * @param nums
	 * @param k
	 * @return
	 */
	public List<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
    }
}
