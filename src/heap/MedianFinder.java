package heap;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 
 * 
 * 题目只要中位数，而中位数左边和右边是否有序不重要。于是，我们需要用到这样的数据结构，大顶堆和小顶堆。
 */
public class MedianFinder {
	//保存中位数左边一半的值
	PriorityQueue<Integer> maxHeap;
	//保存中位数右边一半的值
	PriorityQueue<Integer> minHeap;
	Comparator<Integer> comparator;
	DecimalFormat df  = new DecimalFormat("###.00");
	 /** initialize your data structure here. */
    public MedianFinder() {
    	comparator=new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		};
		maxHeap=new PriorityQueue<Integer>(comparator);
    	minHeap=new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
    	int maxSize=maxHeap.size();
    	int minSize=minHeap.size();
    	if (maxHeap.isEmpty()) {
			maxHeap.add(num);
		}else {
			//num在左侧
			if (num<maxHeap.peek()) {
				
				if (maxSize>minSize) {
					minHeap.add(maxHeap.poll());
				}
				maxHeap.add(num);
				
			}else  {
				if (minHeap.isEmpty()) {
					minHeap.add(num);
				}else {
					//num在左右的中间
					if (num<minHeap.peek()) {
						if (maxSize<=minSize) {
							maxHeap.add(num);
						}else if (maxSize>minSize) {
							minHeap.add(num);
						}
						 
					}else {//num在右侧
						if (maxSize<=minSize) {
							maxHeap.add(minHeap.poll());
						}
						minHeap.add(num);
					}
				}
			}
		}
    }
    
    public double findMedian() {
    	if ((maxHeap.size()+minHeap.size())%2==0) {
    		return (double)(Math.round((maxHeap.peek()+minHeap.peek())*10/2)/10.0);
		}else {
			return maxHeap.peek();
		}
    }
    
    public static void main(String[] args) {
    	MedianFinder medianFinder=new MedianFinder();
    	medianFinder.addNum(40);
    	System.out.println(medianFinder.findMedian());
    	medianFinder.addNum(12);
    	System.out.println(medianFinder.findMedian());
    	medianFinder.addNum(16);
    	System.out.println(medianFinder.findMedian());
    	medianFinder.addNum(14);
    	System.out.println(medianFinder.findMedian());
    	medianFinder.addNum(35);
    	System.out.println(medianFinder.findMedian());
	}
}
