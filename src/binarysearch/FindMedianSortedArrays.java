package binarysearch;

public class FindMedianSortedArrays {

	
	/**
	  * 4. 寻找两个正序数组的中位数
	  * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。

		请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
		
		你可以假设 nums1 和 nums2 不会同时为空。

		 
		方法一：合并两个数组
	  */
	 public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 if (nums1==null) {
			 return getMedian(nums2);
		 }
		 if (nums2==null) {
			 return getMedian(nums1);
		 }
		 int[] merge=new int[nums1.length+nums2.length];
		 
		 //按照长度划分不对，因为长的不一定是大的
//		 if (nums1.length>=nums2.length) {
//			int[]temp=nums1;
//			nums1=nums2;
//			nums2=temp;
//		}
		 int i=0;
		 int j=0;
		 int index=0;
		 while(i<nums1.length&&j<nums2.length) {
			 if (nums1[i]<=nums2[j]) {
				 merge[index]=nums1[i];
				 i++;
			 }else {
				 merge[index]=nums2[j];
				 j++;
			 }
			 index++;
			 
		 }
		
		 for (int i2 = i; i2 < nums1.length; i2++) {
			merge[index]=nums1[i2];
			index++;
		}
		 for (int j2 = j; j2 < nums2.length; j2++) {
			merge[index]=nums2[j2];
			index++;
		}
		 
		 return getMedian(merge);
	 }
	 
	 
	 /**
	  * 方法二：用两个指针，不合并数组
	  * @param nums1
	  * @param nums2
	  * @return
	  */
	 public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
		 if (nums1==null) {
			 return getMedian(nums2);
		 }
		 if (nums2==null) {
			 return getMedian(nums1);
		 }
		 
		 int i=0;
		 int j=0;
		 
		 int len1=nums1.length;
		 int len2=nums2.length;
		 
		 int len=len1+len2;
		 int left=0;
		 int right=0;
//		 int[] merge=new int[nums1.length+nums2.length];
//		 int index=0;
		 while((i+j)<=len/2) {
			 //需要用两个指针记录，否则出循环的时候，i和j很可能会数组越界
			 //而且right其实一直就是记录merge[index]的值，最后返回的len/2的
			 //那么left就可以记录上一次right的值，即len/2-1的值，那么如果len是偶数，就可以拿到值了
			 left=right;
			 //当i<nums1.length时，可以走merge[index]=nums1[i];
			 //当i>=nums1.length时，说明nums1走完了，所以走的是merge[index]=nums2[j];
			 //当j>=nums2.length时，就不能走merge[index]=nums2[j];走的也是上边的
			 if (i<nums1.length&&(j>=nums2.length||nums1[i]<=nums2[j])) {
//				 merge[index]=nums1[i];
				 right=nums1[i];
				 i++;
			 }else {
//				 merge[index]=nums2[j];
				 right=nums2[j];
				 j++;
			 }
//			 index++;
		 }
		 
//		 System.out.println(i+","+left);
//		 System.out.println(j+","+right);
//		 System.out.println(Arrays.toString(merge));
		 if (len%2==0) {
			return (left+right)/2.0;
		 }else {
			return right;
		}
	 }
	 
	 
	 public static double getMedian(int[] nums) {
			if (nums.length%2==0) {
				int left=nums[nums.length/2-1];
				int right=nums[nums.length/2];
				return (left+right)/2.0;
			}else {
				return nums[nums.length/2];
			}
		}
	
	 
	 /**
	  * 
	  * 二分法
	  * 
	  * 将两个数组切割后，要找到中位数，就是要找到切割后，
	  * 第一个数组左边的与第二个数组左边的数量和=第一个数组右边的与第二个数组右边的数量和
	  * 和方法2里的i+j=len/2的效果是一样的
	  * 二分法就是想通过二分的思想，加快扫描的过程
	  * 
	  * 由于两个数组有序，所以LMax1<RMin1,LMax2<RMin2
	  * 
	  * 如果能找到c1和c2，使得LMax2<RMin1,LMax1<RMin2，
	  * 
	  * 那么将两个数组合并后，在左边的还在左边，在右边的还在右边，不会混到另一个数组的另一侧
	  * 
	  * 这样，中位数就会出现在Max(LMax1,LMax2)和Min(RMin1,RMin2)中
	  * 
	  * 这里涉及到了len最后是奇数还是偶数的问题，对两个数组虚拟加入‘#’，
	  * 让len1转换成2len1+1 ，len2转换成2len2+1, 两数之和就变成了2(len1+len2)+2，恒为偶数
	  * 
	  * 加长后的偶数的中位数就是len1+len2+1和len1+len2
	  * 
	  * 原来的中位数就是在(len1+len2+1)/2和(len1+len2)/2的位置
	  * 
	  * 对于奇数，取(len1+len2)/2
	  * 对于偶数，(len1+len2+1)/2和(len1+len2)/2相等，取(len1+len2)/2和(len1+len2)/2-1的值
	  */
	 public static double findMedianSortedArrays4(int[] nums1, int[] nums2) {
		 
		 int len1=nums1.length;
		 int len2=nums2.length;
		 
		 int len=len1+len2;
		 //由于二分涉及到数组长短的比较，这里调用自身，少了额外的temp数组
		 //和解法1中涉及的长度划分不一样
		 if (len1>len2) {
			return findMedianSortedArrays4(nums2, nums1);
		 }
		 
		 //第1个数组切割后左边的最大值，右边的最小值
		 int LMax1=0,RMin1=0;
		//第2个数组切割后左边的最大值，右边的最小值
		 int LMax2=0,RMin2=0;
		 //c1代表i，c2代表j
		 //(c1+c2)<=len/2
		 int c1=0,c2=0;
		 
		 int low=0;
		 //由于虚拟加入了‘#’,所以长度是2len1+1，由于这里是下标，所以是2len1
		 int high=2*len1;
		 
		 while(low<=high) {
			 c1=(high-low)/2+low;
			 
			 c2=len-c1;
				 
			 if (c1==0) {
				//说明数组1的左边没有值了，为了方便比较，要设LMax1是一个不影响Max函数的值
				LMax1=Integer.MIN_VALUE;
			 }else {
				 LMax1=nums1[(c1-1)/2];
			}
			 
			 if (c1==2*len1) {
				//说明数组1的右边没有值了，为了方便比较，要设RMin1是一个不影响Min函数的值
				 RMin1=Integer.MAX_VALUE;
			 }else {
				 RMin1=nums1[c1/2];
			}
			 
			 
			 if (c2==0) {
					//说明数组2的左边没有值了，为了方便比较，要设LMax2是一个不影响Max函数的值
					LMax2=Integer.MIN_VALUE;
			  }else {
				  LMax2=nums2[(c2-1)/2];
			}
			 
			 if (c2==2*len2) {
				//说明数组2的右边没有值了，为了方便比较，要设RMin2是一个不影响Min函数的值
				 RMin2=Integer.MAX_VALUE;
			 }else {
				 RMin2=nums2[c2/2];
			}
			 
			 if (LMax1<=RMin2&&LMax2<=RMin1) {
				break;
			 }
			 
			 //说明数组1左边的元素太多了，
			 if (LMax1>RMin2) {
				//c1小了，c2就大了
				high=c1-1;
			 }  
			 else if (LMax2>RMin1) {
				 //c2需要向左移动，c1向右移动
				low=c1+1;
			}
		}
		 
		if (len%2==0) {
			return (Math.max(LMax1, LMax2)+Math.min(RMin1, RMin2))/2.0;
		}else {
			return Math.min(RMin1, RMin2);
		}
		 
		 
		 
	 }
	
}
