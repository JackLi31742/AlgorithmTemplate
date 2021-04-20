package sort;

import java.util.Arrays;

public class SortColors {
	
	public static void main(String[] args) {
		int[] nums= {2,0,2,1,1,0};
		
		sortColors(nums);
		
		System.out.println(Arrays.toString(nums));
	}

	/**
	 * 75. 颜色分类 
	 * lintcode 148
	 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
	 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

		此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

	 * @param nums
	 */
	
	//这不是两次partition
	public static void sortColors(int[] nums) {

		//代表0的右边界
		int p0=0;
		//代表当前
		int cur=0;
		//代表2的左边界
		int p2=nums.length-1;
		
		while(cur<=p2) {
			//需要在if里加指针的边界控制，否则会一直在正确的下标打转
			if (cur>=p0&&nums[cur]==0) {
				swip(nums, cur, p0);
				p0++;
			}else if (cur<=p2&&nums[cur]==2) {
				swip(nums, cur, p2);
				p2--;
			}else {
				//在交换之后不移动cur，因为不知道交换了之后的值是什么情况，所以需要再判断一次
				cur++;
			}
		}
		
    }
	
	/**
	 * 
	 * 两次partition，快速选择算法
	 * @param nums
	 */
	public void sortColors1(int[] nums) {
        // write your code here
		partitionArray(nums, 1);
		partitionArray(nums, 2);
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
	 * lintcode 143. Sort Colors II
	给定一个有n个对象（包括k种不同的颜色，并按照1到k进行编号）的数组，
	将对象进行分类使相同颜色的对象相邻，并按照1,2，...k的顺序进行排序。
	
	这是O(K*N)
	 * @param colors
	 * @param k
	 */
	public void sortColors2(int[] colors, int k) {
        // write your code here
		if (colors==null||colors.length==0) {
			return ;
		}
		
		
		for (int i = 1; i < k; i++) {
			partitionArray(colors, i+1);
		}
    }
	
	/**
	 * lintcode 143. Sort Colors II
	 * 本质还是对数组进行排序，所以是快排，但是由于数组是有范围的，所以在分治时，可以加速到O(logK)
	 * @param colors
	 * @param k
	 */
	public void sortColors22(int[] colors, int k) {
		
		if (colors==null||colors.length==0) {
			return;
		}
		
		quickSort(colors, 1, k,0,colors.length-1);
	}
	
	
	public void quickSort(int[] colors, int colorFrom, int colorTo, int start, int end) {

		// 不适用partition方式，需要在这里重新定义变量
		int left = start;
		int right = end;

		if (left >= right || colorFrom >= colorTo) {
			return;
		}

		int mid = (colorTo - colorFrom) / 2 + colorFrom;

		while (left <= right) {
			//因为mid 是向下取整的 所以等于的在左边 和 mid同侧
			// 这应该就是下标和值的不同
			while (left <= right && colors[left] <= mid) {
				left++;
			}
			while (left <= right && colors[right] > mid) {
				right--;
			}

			if (left <= right) {
				swip(colors, left, right);
				left++;
				right--;
			}
		}

		quickSort(colors, colorFrom, mid, start, right);

		//由于是向下取整，所以mid + 1
		quickSort(colors, mid + 1, colorTo, left, end);
	}
	
	public static void swip(int[]arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}
