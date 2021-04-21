package kth;

import java.util.PriorityQueue;

public class Kth {
	
	public static void main(String[] args) {
//		PriorityQueue<Integer> heap =new PriorityQueue<Integer>(2);
//		
//		heap.add(1);
//		heap.add(2);
//		heap.add(3);
//		
//		System.out.println(heap);
		
		
		int[]arr= {1,3,5,7,2,4,6,8};
		
		
		
		
		int[] nums= {1,1,1,2,2,3};
		
		
	}
	
	
	/**
	 * 找中位数 k=n/2
	 * 
	 * lintcode 5. 第k大元素
		在数组中找到第 k 大的元素。
	 * 215. 数组中的第K个最大元素
	 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
	 * 
	 * 
		要求时间复杂度为O(n)，空间复杂度为O(1)。
	 */
	
	public int kthLargestElement(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length){
            return -1;
        }
        
        //注意传入的是nums.length - k
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int partition(int[] nums, int start, int end, int k) {
       
    	//说明找到了，start==end==k
    	if (start >= end) {
            return nums[start];
        }
        
        int left = start, right = end;
        int pivot = nums[(start + end) / 2];
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        
        if (k <= right) {
            return partition(nums, start, right, k);
        }
        if (k >= left) {
            return partition(nums, left, end, k);
        }
        return nums[k];
    }    
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
	
	
	//堆
	public int findKthLargest(int[] nums, int k) {
		if (nums==null||k==0) {
			return 0;
		}
		
		PriorityQueue<Integer> heap=new PriorityQueue<Integer>(k);
		
		for (int i = 0; i < k; i++) {
			
			heap.add(nums[i]);
		}
		for (int i = k; i < nums.length; i++) {
			if (heap.peek()<=nums[i]) {
				heap.poll();
				heap.add(nums[i]);
			}
		}
		return heap.peek();
    }
	
	
	/**
	 * LintCode 543
	 * N数组第K大元素
	 */
	public int KthInArrays(int[][] arrays, int k) {
        
        int len = arrays.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i = 0; i < len; i++){
            if(arrays[i] == null || arrays[i].length == 0){
                continue;
            }
            
            for(int j = 0; j < arrays[i].length; j++ ){
                pq.offer(arrays[i][j]);
                if(pq.size() > k){
                    pq.poll();
                }
            }
        }
       
        return pq.poll();
    }
	
	
	
}
