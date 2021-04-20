package sort;

import java.util.Arrays;

public class Partition {

	public static void main(String[] args) {
		int[]nums= {9,3,2,4,8};
		
//		partitionArray(nums, 2);
		
		Partition partition=new Partition();
		
//		System.out.println(partition.kthLargestElement(3, nums));;
//		
//		System.out.println(Arrays.toString(nums));
		
		int[][] matrix = {{1,5,7},
							{3,7,8},
							{4,8,9}};
		
//		System.out.println(partition.kthSmallest(matrix, 1));;
	}
	
	/**
	 * 912. 排序数组
	 * 给你一个整数数组 nums，请你将该数组升序排列。
	 * @param nums
	 * @return
	 */
	public int[] sortArray(int[] nums) {
		
		if (nums==null||nums.length==0) {
			return nums;
		}
		
		sort(nums, 0, nums.length-1);
		
		return nums;
		
    }
	
	public void sort(int[] nums,int left,int right) {
		//这里必须要有等号，否则会Stack Overflow
		if (left>=right) {
			return ;
		}
		int partition=partition(nums, left, right);
		sort(nums, partition, right);
		sort(nums, left, partition-1);
	}
	
	
	
	public int partition(int[] nums,int left,int right) {
		
		int k=nums[(right-left)/2+left];
		
		while(left<=right) {
			//和partitionArray不同，这里不能有=
			while(left<=right&&nums[left]<k) {
				left++;
			}
			
			while(left<=right&&nums[right]>k) {
				right--;
			}
			
			
			if (left<=right) {
				swip(nums, left, right);
				
				left++;
				right--;
			}
			
		}
		
		return left;
	}
	
	/**
	 * lintcode 31. 数组划分
	 * 给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：

		所有小于k的元素移到左边
		所有大于等于k的元素移到右边
		
		返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k。
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int partitionArray(int[] nums, int k) {
        // write your code here
		if (nums==null||nums.length==0) {
			return 0;
		}
		
		
		int left=0;
		int right=nums.length-1;
		
		//当left==right时，肯定会走下边两个while循环中的一个，会导致不满足循环条件，从而退出循环
		//当left在right右边时，left肯定是第一个大于等于k的
		while(left<=right) {
			//一直走这个循环，知道nums[left]>=k
			while(left<=right&&nums[left]<k) {
				left++;
			}
			
			while(left<=right&&nums[right]>=k) {
				right--;
			}
			
			//指针在上边的两个while循环走完后可能right已经在left左边，这时就不能继续走了
			if (left<=right) {
				swip(nums, left, right);
				
				left++;
				right--;
			}
			
		}
		
		return left;
    }
	
	
	/**
	 * 144. 交错正负数
	 * 给出一个含有正整数和负整数的数组，重新排列成一个正负数交错的数组。
	 * @param A
	 */
	
	public void rerange(int[] A) {
        // write your code here
		
		if (A==null||A.length==0) {
			return ;
		}
		
		//lintcode 31. 数组划分的方法
		int index=partitionArray(A, 0);
		
		int negCount=index;
		int posCount=A.length-index;
		
		swipNegPos(A,negCount, posCount,index);
		
    }
	
	//交换正负数
	public void swipNegPos(int[]nums,int negCount,int posCount,int index) {
		
		
		
		//负数比正数少，负数i从下标0，正数j从nums.length-2,开始交换，i每次加2，j每次-2
		//相等的情况，负数i从下标1，正数j从nums.length-2,开始交换，i每次加2，j每次-2
		//负数比正数多，负数i从下标1，正数j从nums.length-1，开始交换，i每次加2，j每次-2
		
		int i=0;int j=0;
		if (negCount<posCount) {
			i=0;
			j=nums.length-2;
		}else if (negCount==posCount) {
			i=1;
			j=nums.length-2;
		}else {
			i=1;
			j=nums.length-1;
		}
		
		while(i<index) {
			swip(nums, i, j);
			i=i+2;
			j=j-2;
		}
		
		
	}
	
	/**
	 * 373. 奇偶分割数组
	 * 分割一个整数数组，使得奇数在前偶数在后。
	 * @param nums
	 */
	public void partitionArray(int[] nums) {
        // write your code here
		if (nums==null||nums.length==0) {
			return ;
		}
		
		int left=0;
		int right=nums.length-1;
		while(left<=right) {
			
			while(left<=right&&nums[left]%2!=0) {
				left++;
			}
			
			while(left<=right&&nums[right]%2==0) {
				right--;
			}
			
			if (left<=right) {
				swip(nums, left, right);
				left++;
				right--;
			}
		}
		
    }
	
	/**
	 * 49. 字符大小写排序
		给定一个只包含字母的字符串，按照先小写字母后大写字母的顺序进行排序
	 * @param chars
	 */
	public void sortLetters(char[] chars) {
        // write your code here
		
		if (chars==null||chars.length==0) {
			return;
		}
		
		int left=0;
		int right=chars.length-1;
		
		while(left<=right) {
			while(left<=right&&chars[left]>'Z') {
				left++;
			}
			while(left<=right&&chars[right]<='Z') {
				right--;
			}
			
			if (left<=right) {
				swip(chars, left, right);
				left++;
				right--;
			}
		}
    }
	
	
	
	
	
	public static void swip(char[]nums,int i,int j) {
		char temp=nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
	
	public static void swip(int[]nums,int i,int j) {
		int temp=nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
	
	
	

	
}
