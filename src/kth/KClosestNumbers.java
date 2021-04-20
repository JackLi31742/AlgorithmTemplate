package kth;

public class KClosestNumbers {

	
	/**
	 * 460. 在排序数组中找最接近的K个数
	 * 给一个目标数 target, 一个非负整数 k, 一个按照升序排列的数组 A。在A中找与target最接近的k个整数。
	 * 返回这k个数并按照与target的接近程度从小到大排序，如果接近程度相当，那么小的数排在前面。
	 * 
	 * 
	 * 找到target在A的位置，将A一分为二，接下来跟合并两个排序数组很像
	 * @param A
	 * @param target
	 * @param k
	 * @return
	 */
	public static int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
		
		if (A==null||A.length==0) {
			return new int[] {};
		}
		
		
		int pos=findInsert(A, target);
		
		
		int left=pos-1;
		int right=pos;
		
		int[] result=new int[k];
		int index=0;
		
		while(left>=0&&right<=A.length-1&&index<k) {
			
			int leftDif=(target-A[left]);
			int rightDif=(A[right]-target);
			
			if (leftDif<=rightDif) {
				result[index]=A[left];
				left--;
			} else {
				result[index]=A[right];
				right++;
			}
			index++;
		}
		
		while(left>=0&&index<k) {
			
			result[index]=A[left];
			left--;
			index++;
		}
		
		while(right<=A.length-1&&index<k) {
			
			result[index]=A[right];
			right++;
			index++;
		}
		
		return result;
		
    }
	
	/**
	 * 搜索插入的位置，得到的下标偏右
	 * @param A
	 * @param target
	 * @return
	 */
	public static int findInsert(int[] A, int target) {
		
		int left=0;
		int right=A.length-1;
		
		while(left<=right) {
			
			int mid=left+(right-left)/2;
			
			if (A[mid]>target) {
				right=mid-1;
			}else if (A[mid]<target) {
				left=mid+1;
			}else {
				return mid;
			}
		}
		
		return left;
	}
}
