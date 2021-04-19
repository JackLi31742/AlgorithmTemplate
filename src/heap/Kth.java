package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

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
		
		int[]result=smallestK3(arr, 4);
		
		System.out.println(Arrays.toString(result));
		
		
		int[] nums= {1,1,1,2,2,3};
		
		topKFrequent(nums, 2);
		
	}
	
	/**
	 * 面试题 17.14. 最小K个数
	 * 找出数组中最小的k个数。以任意顺序返回这k个数均可。
	
		第一种办法：先排序
	 */
	public static int[] smallestK1(int[] arr, int k) {
		if (arr==null||arr.length==0) {
			return new int[k];
		}
		
		if (arr.length<=k) {
			return arr;
		}
		int[] result=new int[k];
		
		Arrays.sort(arr);
		
		for (int i = 0; i < result.length; i++) {
			result[i]=arr[i];
		}
		
		return result;
    }
	
	//第二种，用堆
	public static int[] smallestK2(int[] arr, int k) {
		
		if (arr==null||arr.length==0||k==0) {
			return new int[k];
		}
		
		if (arr.length<=k) {
			return arr;
		}
		Comparator<Integer> comparator=new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		};
		
		//得用大顶堆
		PriorityQueue<Integer> heap =new PriorityQueue<Integer>(k,comparator);
		
		
		for (int i = 0; i < k; i++) {
			heap.add(arr[i]);
		}
		
		
		int[]result=new int[k];
		
		
		for (int i = k; i < arr.length; i++) {
			if (arr[i]<heap.peek()) {
				heap.poll();
				heap.add(arr[i]);
			}
		}
		
		int i=0;
		while(!heap.isEmpty()) {
			result[i]=heap.poll();
			i++;
		}
		
		return result;
	}
	
	
	/**
	 * 快排思想
	 * 快速选择算法
	 * @param arr
	 * @param k
	 * @return
	 */
	public static int[] smallestK3(int[] arr, int k) {
		if (arr==null||arr.length==0||k==0) {
			return new int[k];
		}
		
		if (arr.length<=k) {
			return arr;
		}
		
		int left=0;
		int right=arr.length-1;
		
		return smallestK3(arr, k, left, right);
		
		
		
	}
	
	public static int[] smallestK3(int[] arr, int k,int left,int right) {
		if (left>=right) {
			return arr;
		}
		int partition=partition(arr, left, right);
		//当递归调用时，是不需要，也不可以用while循环
//		while((partition+1)!=k) {
			
//			partition是下标，所以需要加1
			if ((partition+1)<k) {
				smallestK3(arr, k, partition+1, right);
			}else if ((partition+1)>k) {
				
				smallestK3(arr, k,left,partition-1);
			}
//		}
		
		int[]result=new int[k];
		for (int i = 0; i < result.length; i++) {
			result[i]=arr[i];
		}
		
		
		return result;
	}
	
	public static int partition(int[] arr, int left,int right) {
		int index=(right-left)/2+left;
		swip(arr, left, index);
		int pivot=arr[left];
		while(left<right) {
			while(left<right&&arr[right]>=pivot) {
				right--;
			}
			swip(arr, left, right);
			while(left<right&&arr[left]<=pivot) {
				left++;
			}
			swip(arr, left, right);;
		}
		return left;
	}
	
	
	public static void swip(int[] arr, int left,int right) {
		int temp=arr[left];
		arr[left]=arr[right];
		arr[right]=temp;
	}
	
	/**
	 * 215. 数组中的第K个最大元素
	 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
	 * @param nums
	 * @param k
	 * @return
	 */
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
	 * 347. 前 K 个高频元素
	 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] topKFrequent(int[] nums, int k) {
		
		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			
			if (map.containsKey(nums[i])) {
				map.put(nums[i],map.get(nums[i])+1);
			}else {
				map.put(nums[i],1);
			}
			
		}
		
		if (map.size()<k) {
			return new int[k];
		}
		
		//从大到小排序
		Comparator<Map.Entry<Integer, Integer>> comparator=new Comparator<Map.Entry<Integer,Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue()-o1.getValue();
			}
		};
		
		List<Map.Entry<Integer,Integer>> list=new ArrayList<>(map.entrySet());
		
		Collections.sort(list, comparator);
		
		int[]result=new int[k];
		
		for (int i = 0; i < result.length; i++) {
			result[i]=list.get(i).getKey();
		}
		
		return result;
    }
	
	/**
	 * 小顶堆
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] topKFrequent2(int[] nums, int k) {
		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			
			map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
			
		}
		
		if (map.size()<k) {
			return new int[k];
		}
		
		
		//小顶堆
		Comparator<Map.Entry<Integer, Integer>> comparator=new Comparator<Map.Entry<Integer,Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getValue()-o2.getValue();
			}
		};
		
		PriorityQueue<Map.Entry<Integer, Integer>> heap 
				=new PriorityQueue<Map.Entry<Integer, Integer>>(k,comparator);
		
		
		Iterator<Entry<Integer, Integer>> iterator = map.entrySet().iterator();
		
		int i=0;
		
		while(i<k) {
			Entry<Integer, Integer> entry=iterator.next();
			heap.add(entry);
			i++;
		}
		
		while(iterator.hasNext()) {
			Entry<Integer, Integer> entry=iterator.next();
			if (heap.peek().getValue()<entry.getValue()) {
				heap.poll();
				heap.add(entry);
			}
		}
		
		int[]result=new int[k];
		
		for (int j = 0; j < result.length; j++) {
			result[j]=heap.poll().getKey();
		}
		
		return result;
		
	}
	
	
}
