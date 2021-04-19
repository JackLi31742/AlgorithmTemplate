package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement {
	
	public static void main(String[] args) {
		int nums[]= {1,2,1};
		
		int[]result=nextGreaterElements2(nums);
		
		System.out.println(Arrays.toString(result));
		
		
		System.out.println(nextGreaterElement(12443322));;
		
		
		System.out.println(Integer.MAX_VALUE);
	}
	
	/**
	 * 496. 下一个更大元素 I
	 * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
	 * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。

	 nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

	 这里用到的就是单调递增栈
	 */
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		int[]result=new int[nums1.length];
		if (nums2==null||nums2.length==0) {
			return result;
		}
		Stack<Integer> stack=new Stack<Integer>();
		
		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
		
		stack.push(nums2[0]);
		for (int i = 1; i < nums2.length; i++) {
			//说明找到了一个元素，比栈里的大
			while (!stack.isEmpty()&&nums2[i]>stack.peek()) {
				//key是栈里的元素，value是比key大的
				map.put(stack.pop(), nums2[i]);
			}
			//比栈顶的元素小，就入栈，等待下一次比较
			stack.push(nums2[i]);
		}
		
		//栈里不为空，说明里边还有元素，而且元素都是没有找到下一个最大值的
		while(!stack.isEmpty()) {
			map.put(stack.pop(), -1);
		}
		
		for (int i = 0; i < nums1.length; i++) {
			result[i]=map.get(nums1[i]);
		}
		return result;
    }
	
	/**
	 * 503. 下一个更大元素 II
	 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），
	 * 输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
	 * 这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
	 * @param nums
	 * @return
	 */
	public static int[] nextGreaterElements(int[] nums) {
		int[]result=new int[nums.length];
		if (nums==null||nums.length==0) {
			return result;
		}
		Stack<Num> stack=new Stack<Num>();
		
		HashMap<Num, Num> map=new HashMap<Num, Num>();
		
		stack.push(new Num(0, nums[0]));
		int len=nums.length;
		//相当于用数组实现的循环队列
		for (int i = 1; i < 2*len; i++) {
			
			while(!stack.isEmpty()&&nums[i%len]>stack.peek().value) {
				map.put(stack.pop(), new Num(i, nums[i%len]));
			}
			
			stack.push(new Num(i, nums[i%len]));
		}
		
		while(!stack.isEmpty()) {
			map.put(stack.pop(), new Num(-1, -1));
		}
		
		for (int i = 0; i < nums.length; i++) {
			result[i]=map.get(new Num(i, nums[i])).value;
		}
		return result;
    }
	
	static class Num{
		int index;
		int value;
		public Num(int index, int value) {
			super();
			this.index = index;
			this.value = value;
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + index;
			result = prime * result + value;
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Num other = (Num) obj;
			if (index != other.index)
				return false;
			if (value != other.value)
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "Num [index=" + index + ", value=" + value + "]";
		}
		
	}
	
	/**
	 *  入栈的时候用index，不用值
	 * @param nums
	 * @return
	 */
	public static int[] nextGreaterElements2(int[] nums) {
		int[]result=new int[nums.length];
		if (nums==null||nums.length==0) {
			return result;
		}
		
		Arrays.fill(result, -1);
		Stack<Integer> stack=new Stack<Integer>();
		
		stack.push(0);
		int len=nums.length;
		
		for (int i = 1; i < 2*len; i++) {
			while(!stack.isEmpty()&&nums[i%len]>nums[stack.peek()]) {
				//找到就更新
				result[stack.pop()]=nums[i%len];
			}
				
			stack.push(i%len);

		}
		
		return result;
	}
	
	/**
	 * 556. 下一个更大元素 III
	 * 给定一个32位正整数 n，你需要找到最小的32位整数，
	 * 其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
	 * 
	 * 感觉是重新排序n的位，找到第一个比n大的值
	 * 
	 * 从后先前，找到第一个比相邻右侧小的值a[i]，
	 * 然后再向右寻找第一个比这个值大的值a[j]，交换，然后再把i右侧的按照从小到大排序
	 * @param n
	 * @return
	 */
	public static int nextGreaterElement(int n) {
		char[]arr=String.valueOf(n).toCharArray();
		int index=0;
		for (int i = arr.length-1; i >=1; i--) {
			if (arr[i-1]<arr[i]) {
				index=i-1;
				break;
			}
		}
		int j=0;
//		找到第一个比arr[index]大的值，从后向前找更好，因为从前向后找，找到后，还得继续找，看有没有第一个比它大 的
		for (int i = arr.length-1; i>=index+1; i--) {
			if (arr[i]>arr[index]) {
				j=i;
				break;
			}
		}
		
		if (index>=j) {
			return -1;
		}
		swip(arr, index, j);
		
		//包含头，不包含尾
		Arrays.sort(arr, index+1, arr.length);
		try {
			//修改后可能超过了Integer.MAX_VALUE
			return Integer.parseInt(String.valueOf(arr));
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
    }
	
	public static void swip(char[]arr,int i,int j) {
		char temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}
