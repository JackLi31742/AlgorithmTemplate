package sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[]arr={3, 9, -1, 1, 20,2,15,47};
		sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("swipCount:"+swipCount);
		System.out.println("compareCount:"+compareCount);
	}
	static int swipCount=0;
	static int compareCount=0;
	public static int[] sort(int[] nums) {
		if (nums==null||nums.length==0||nums.length==1) {
			return nums;
		}
		
		int[]temp=new int[nums.length];
		mergeSort(nums, 0, nums.length-1, temp);
		return nums;
	}
	
	public static void mergeSort(int[]arr,int left,int right,int[] temp){
		if (left<right) {
			int mid=(right-left)/2+left;
			//分
			System.out.println("left="+left+",mid="+mid+",right="+right);
			//向左递归进行分解
			mergeSort(arr, left, mid, temp);
			//向右递归进行分解
			mergeSort(arr, mid+1, right, temp);
			//执行了上边的分之后，由于不满足left<right的条件，就开始执行merge，这是递归的出口
			merge(arr, left, mid, right, temp);
			System.out.println("===============");
		}
	}
	/**
	 * 合并
	 * 类似于合并两个有序列表
	 */
	public static void merge(int[]arr,int left,int mid,int right,int[]temp) {
		System.out.println("merge:left="+left+",mid="+mid+",right="+right);
		//被分成两个数组后的左边起始
		int i=left;
		//被分成两个数组后的右边起始
		int j=mid+1;
		//temp的指针
		int t1=0;
		int t2=0;
		//
		//这里会重置temp数组
		//i到了mid，不是left
		while(i<=mid&&j<=right){
			//如果 A[p…q]和 A[q+1…r]之间有值相同的元素，
//			那我们可以像伪代码中那样，先把 A[p…q]中的元素放入 tmp 数组
			if (arr[i]<=arr[j]) {
				
				temp[t1]=arr[i];
				i++;
			}else {
				temp[t1]=arr[j];
				j++;
			}
			t1++;
		}
		//i到了mid，不是left
		while(i<=mid){
			temp[t1]=arr[i];
			t1++;
			i++;
		}
		
		while(j<=right){
			temp[t1]=arr[j];
			j++;
			t1++;
		}
		
		//复制回arr
		i=left;
		
		while(t2<t1){
			arr[i]=temp[t2];
			t2++;
			i++;
		}
		
		//复制回原素组
//        for (int i = left; i <=right; i++) 
//            a[i]=tmp[i];
		
	}
	
}
