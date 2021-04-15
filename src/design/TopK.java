package design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 545. 前K大数 II
 * 1.add(number) 添加一个元素
2.topk() 返回前K大的数
 *
 */
public class TopK {
	
	private PriorityQueue<Integer> minHeap =null;
	
	private int capacity=0;
	/*
	    * @param k: An integer
	    */public TopK(int k) {
	        // do intialization if necessary
	    	
	    	minHeap=new PriorityQueue<Integer>(k);
	    	
	    	this.capacity=k;
	    }

	    /*
	     * @param num: Number to be added
	     * @return: nothing
	     */
	    public void add(int num) {
	        // write your code here
	    	
	    	if (minHeap.size()<capacity) {
				minHeap.add(num);
				return;
			}
	    	
	    	if (minHeap.peek()<num) {
				minHeap.poll();
				minHeap.add(num);
			}
	    	
	    	
	    }

	    /*
	     * @return: Top k element
	     */
	    public List<Integer> topk() {
	        // write your code here
	    	
	    	//不能把heap中的数据都poll出来，因为后边还要用，所以要用迭代器
	    	
	    	List<Integer> result=new ArrayList<Integer>();
	    	
	    	Iterator<Integer> iterator = minHeap.iterator();
	    	
	    	while(iterator.hasNext()) {
	    		result.add(iterator.next());
	    	}
	    	
	    	Collections.sort(result,Collections.reverseOrder());
	    	return result;
	    }

}
