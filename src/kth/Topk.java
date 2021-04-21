package kth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public class Topk {

	/**
	 * 544 · 前K大数
	 * 在一个数组中找到前K大的数
	 * @param nums
	 * @param k
	 * @return
	 */
	public int[] topk(int[] nums, int k) {
        // write your code here
    }
	
	/**
	 * 面试题 17.14. 最小K个数
	 * 找出数组中最小的k个数。以任意顺序返回这k个数均可。
	 */
	
	//第一种办法：先排序
	public static int[] smallestK1(int[] arr, int k) {
		if (arr==null||arr.length==0) {
			return new int[k];
		}
		
		if (arr.length<=k) {
			return arr;
		}
		int[] result=new int[k];
		
//		Arrays.sort(arr);
		quickSort(arr,0,arr.length-1,k);
		
		for (int i = 0; i < result.length; i++) {
			result[i]=arr[i];
		}
		
		return result;
    }
	
	public static void quickSort(int[]arr,int start,int end,int k) {
		if (start>=end) {
			return;
		}
		
		//优化快排
		if (start>=k) {
			return;
		}
		
		int index=start+(end-start)/2;
		//比较的是值，pivot不能是下标，因为下标会变
		int pivot=arr[index];
		
		int left=start;
		int right=end;
		
		
		while(left<=right) {
			
			//交换跟先交换的左右顺序没关系
			while(left<=right&&arr[left]<pivot) {
				left++;
			}
			
			while(left<=right&&arr[right]>pivot) {
				right--;
			}
			
			
			if (left<=right) {
				swip(arr,left,right);
				//由于已经比较了当前的，所以直接移动指针比较下一个
				left++;
				right--;
			}
		}
		
		//由于选取的是中点，所以不需要进行出循环后再次交换
//		swip(arr, index, left);
		
		quickSort(arr, left, end,k);
		
		quickSort(arr, start, right,k);
	}
	
	
	public static void swip(int[] arr, int left,int right) {
		int temp=arr[left];
		arr[left]=arr[right];
		arr[right]=temp;
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
	 * 347. 前 K 个高频元素
	 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
	      
	       
	       小顶堆
	 */

	public static int[] topKFrequent(int[] nums, int k) {
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
