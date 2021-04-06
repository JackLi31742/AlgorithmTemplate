package array;

public class Merge {

	/**
	 * 88. 合并两个有序数组
	 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {

		int p1=0;
		int p2=0;
		int[] temp=new int[m+n];
		int index=0;
		while(p1<m&&p2<n) {
			if (nums1[p1]<nums2[p2]) {
				temp[index]=nums1[p1];
				p1++;
			}else {
				temp[index]=nums2[p2];
				p2++;
			}
			index++;
		}
		
		while(p1<m) {
			temp[index]=nums1[p1];
			index++;
			p1++;
		}
		
		while(p2<n) {
			temp[index]=nums2[p2];
			index++;
			p2++;
		}
		
		for (int i = 0; i < temp.length; i++) {
			nums1[i]=temp[i];
		}
    }
}
