package sort;

import java.util.Arrays;

public class QuickSort {
	
	
	public static void main(String[] args) {
		
		int[]arr={5,2,3,1};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	
	public static void sort(int[]nums) {
		
		if (nums==null||nums.length<2) {
			return ;
		}
		quickSort(nums,0,nums.length-1);
		
		
	}
	
	public static void quickSort(int[]arr,int start,int end) {
		if (start>=end) {
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
		
		quickSort(arr, left, end);
		
		quickSort(arr, start, right);
	}
	
	
	public static void swip(int[]arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	
	/**
	 * 交换指针法 partition
	 * @param arr
	 * @param start
	 * @param end
	 */
	public static void sort(int[]arr,int start,int end) {
		
		if (start>=end) {
			return;
		}
		
		int partiton=partition(arr, start, end);
		
		sort(arr, start, partiton-1);
		sort(arr, partiton, end);
	}
	
	public static int partition(int[]arr,int start,int end) {
		
		int left=start;
		int right=end;
		int index=start+(end-start)/2;
		int pivot=arr[index];
		
		while(left<=right) {
			
			while(left<=right&&arr[right]>pivot) {
				right--;
			}
			
			while(left<=right&&arr[left]<pivot) {
				left++;
			}
			
			if (left<=right) {
				swip(arr,left,right);
				//由于已经比较了当前的，所以直接移动指针比较下一个
				left++;
				right--;
			}
		}
		
		return left;
	}
}
