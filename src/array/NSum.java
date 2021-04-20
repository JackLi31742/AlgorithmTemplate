package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NSum {
	public static void main(String[] args) {
		int[] nums= {0,1,2,2};
		System.out.println(twoSum7(nums, 0));;
		
		
//		System.out.println(threeSum3(nums));
	}
	
	/**
	 * 1. 两数之和
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

		你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

		由于要返回下标，所以用哈希的方法更好，否则可以先对数组进行排序，再用双指针
		判断nums[left]+nums[right]>target，那么说明需要right--，因为nums[right]加上最小值还要比target大，所以right下标
		的值肯定是不符合要求的，
		
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
        	//遍历到后边的时候，如果有值等于target-nums[i]，那肯定已经在map中了
        	//否则就是还没有遍历到，继续遍历即可
			if (map.get(target-nums[i])==null) {
				map.put(nums[i], i);
			}else {
				return new int[] {map.get(target-nums[i]),i};
			}
		}
        return null;
    }
	
	/**
	 * 双指针
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum12(int[] nums, int target) {
		
		if (nums==null||nums.length<2) {
			return null;
		}
		
		int[]temp=Arrays.copyOf(nums, nums.length);
		
		Arrays.sort(temp);
		int[] result=new int[2];
		
		int left=0;
		int right=nums.length-1;
		int i=left;
		int j=right;
		while(left<right) {
			
			if (temp[left]+temp[right]<target) {
				left++;
			}else if (temp[left]+temp[right]>target) {
				right--;
			}else {
				i=left;
				j=right;
				break;
			}
		}
		
		for (int k = 0; k < nums.length; k++) {
			if (nums[k]==temp[i]) {
				result[0]=k;
				break;
			}
		}
		for (int k = 0; k < nums.length; k++) {
			if (nums[k]==temp[j]&&k!=result[0]) {
				result[1]=k;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * lintcode 609. 两数和-小于或等于目标值
	 * 给定一个整数数组，找出这个数组中有多少对的和是小于或等于目标值。返回对数
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int twoSum5(int[] nums, int target) {
        if (nums==null||nums.length<2) {
			return 0;
		}
        
		Arrays.sort(nums);
		
		int count=0;
		
		for (int i = 0; i < nums.length; i++) {
			for (int j = nums.length-1; j >i; j--) {
				if (nums[i]+nums[j]<=target) {
					count++;
				}
			}
		}
		
		return count;
    }

	/**
	 * lintcode 609. 双指针
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int twoSum52(int[] nums, int target) {
		
		if (nums==null||nums.length<2) {
			return 0;
		}
        
		Arrays.sort(nums);
		
		int count=0;
		
		int left=0;
		int right=nums.length-1;
		while(left<right) {
			while (left<right&&nums[left]+nums[right]>target) {
				right--;
			}
			count+=right-left;
			left++;
		}
		
		
		return count;
	}
	
	/**
	 * lintcode 443. 两数之和 II
	 * 	给一组整数，问能找出多少对整数，他们的和大于一个给定的目标值。
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int twoSum2(int[] nums, int target) {
        // write your code here
		
		if (nums==null||nums.length<2) {
			return 0;
		}
        
		Arrays.sort(nums);
		
		int count=0;
		
		int left=0;
		int right=nums.length-1;
		while(left<right) {
			while (left<right&&nums[left]+nums[right]<=target) {
				left++;
			}
			count+=right-left;
			right--;
		}
		
		return count;
    }
	
	/**
	 * 610. Two Sum - Difference equals to target
		给定一个排序后的整数数组，找到两个数的 差 等于目标值。
		你需要返回一个包含两个数字的列表[num1, num2], 使得num1与num2的差为target，同时num1必须小于num2。
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum7(int[] nums, int target) {
        // write your code here
		
		if (nums==null||nums.length==0) {
			return new int[] {-1,-1};
		}
		
		int j=1;
		
		target=Math.abs(target);
		//使用for循环，是i在增加后，还要继续进入下边的while循环，而不是if else 的关系
		for (int i = 0; i < nums.length; i++) {
			
			//为了保持j在i的右边，否则有可能j的指针不走
			j=Math.max(j, i+1);
			//而且之后j不用从i+1开始，因为在出了while循环之后的j的坐标之前的值减去nums[i]
			//比target小，那么在i的指针向后移动之后，相减更会小于target，所以从当下的j向后就行
			while(i<j&&j<nums.length&&nums[j]-nums[i]<target) {
				j++;
			}
			
			if (j>=nums.length) {
				break;
			}
			
			if (i<j&&j<nums.length&&nums[j]-nums[i]==target) {
				
				return new int[] {nums[i],nums[j]};
			}
		}
			
		return new int[] {-1,-1};
    }
	
	
	/**
	 * 15. 三数之和
	 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
	 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。


	我们枚举的三元组 (a,b,c) 满足 a≤b≤c，保证了只有 (a, b, c) 这个顺序会被枚举到，
	 而 (b,a,c)、(c,b,a) 等等这些不会，这样就减少了重复。

	同时，对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复。
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum(int[] nums) {

		List<List<Integer>> result=new ArrayList<List<Integer>>();
		
		if (nums==null||nums.length<3) {
			return result;
		}
		
		//由于threeSum的时间复杂度在O(n^2)，所以不用担心排序的开销
		Arrays.sort(nums);
		for (int i = 0; i < nums.length-2; i++) {
			if (i==0||nums[i]>nums[i-1]) {
				
				twoSum4threeSum2(nums, i, result);
			}
		}
		
		return result;
    }
	
	
	
	/**
	 * 双指针
	 * @param nums
	 * @param i
	 * @param result
	 */
	public static void twoSum4threeSum2(int[] nums,int i,List<List<Integer>> result) {
		
		int left=i+1;
		int right=nums.length-1;
		
		while(left<right) {
			if (nums[left]+nums[right]+nums[i]<0) {
				left++;
			}else if (nums[left]+nums[right]+nums[i]>0) {
				right--;
			}else {
				List<Integer> list=new ArrayList<>();
				list.add(nums[i]);
				list.add(nums[left]);
				list.add(nums[right]);
				result.add(list);
				
				//left指针走了，right指针必须要走，否则就不可能等于0
				left++;
				right--;
				
				while(left<right&&nums[left]==nums[left-1]) {
					left++;
				}
				
				while(left<right&&nums[right]==nums[right+1]) {
					right--;
				}
			}
		}
		
	}
	
	/**
	 * 双指针
	 * 当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，那么就可以使用双指针的方法
	 * 将枚举的时间复杂度从 O(N^2) 减少至 O(N)。为什么是 O(N) 呢？这是因为在枚举的过程每一步中，
	 * 「左指针」会向右移动一个位置（也就是题目中的 b），而「右指针」会向左移动若干个位置，
	 * 这个与数组的元素有关，但我们知道它一共会移动的位置数为 O(N)，均摊下来，每次也向左移动一个位置，因此时间复杂度为 O(N)。

	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum2(int[] nums) {
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		
		if (nums==null||nums.length<3) {
			return result;
		}
		
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			//if(i!=0&&nums[i]==nums[i-1])
//				continue
			if (i==0||nums[i]>nums[i-1]) {
				//
				int j=i+1;
				int k=nums.length-1;
				while(j<nums.length&&k>0&&j<k) {
					if (j==i+1||nums[j]>nums[j-1]) {
						
						//由于k在第一个while的外边，k不会被重置为nums.length-1，会接着上一次的继续递减
						//所以形成了双指针
						//同时，必须要大于0，否则k容易走到j的左边
						while(k>0&&j<k&&nums[i]+nums[j]+nums[k]>0) {
							k--;
						}
						if (j==k) {
							break;
						}
						if (nums[i]+nums[j]+nums[k]==0) {
							List<Integer> list=new ArrayList<>();
							list.add(nums[i]);
							list.add(nums[j]);
							list.add(nums[k]);
							result.add(list);
						}
					}
					j++;
				}
			}
		}
		
		return result;
	}
	
	
	
	
	/**
	 * lintcode 58. 四数之和
	给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。
	四元组(a, b, c, d)中，需要满足a <= b <= c <= d

	答案中不可以包含重复的四元组。

		把a，b当成target-a-b的target，c d用twosum 
	 * @param numbers
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] numbers, int target) {
        // write your code here
		List<List<Integer>> result=new ArrayList<>();
		if (numbers==null||numbers.length<4) {
			return result;
		}
		
		Arrays.sort(numbers);
		
		
		for (int i = 0; i < numbers.length-3; i++) {
			
			if (i==0||numbers[i]>numbers[i-1]) {
				
				for (int j = i+1; j < numbers.length-2; j++) {
					
					if (j==i+1||numbers[j]>numbers[j-1]) {
						
						int sum=numbers[i]+numbers[j];
						
						twoSum(numbers, target-sum, result,i ,j);
						
					}
				}
			}
		}
		
		return result;
    }
	
	public void twoSum(int[] numbers, int target,List<List<Integer>> result,int i, int j) {
		
		int left=j+1;
		int right=numbers.length-1;
		
		while(left<right) {
			
			if (numbers[left]+numbers[right]>target) {
				
				right--;
			}else if (numbers[left]+numbers[right]<target) {
				
				left++;
			}else {
				
				List<Integer> list=new ArrayList<>();
				list.add(numbers[i]);
				list.add(numbers[j]);
				list.add(numbers[left]);
				list.add(numbers[right]);
				result.add(list);
				
				left++;
				right--;
				
				while(left<right&&numbers[left]==numbers[left-1]) {
					left++;
				}
				while(left<right&&numbers[right]==numbers[right+1]) {
					right--;
				}
			}
		}
		
	}
	
	
	/**
	 * lintcode 976. 4数和 II
	 * 给出 A, B, C, D 四个整数列表，
	 * 计算有多少的tuple (i, j, k, l)满足A[i] + B[j] + C[k] + D[l]为 0。

	为了简化问题，A, B, C, D 具有相同的长度，且长度N满足 0 ≤ N ≤ 500。
	所有的整数都在范围(-2^28, 2^28 - 1)内以及保证结果最多为2^31 - 1
	
	
	分成两组，A和B，C和D，分别记录两组的和以及对应的个数，用hashmap保存，然后加起来
	 * @param A
	 * @param B
	 * @param C
	 * @param D
	 * @return
	 */
	 public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
	        // Write your code here

		 //key是和，value是和的个数
		 HashMap<Integer, Integer> map=new HashMap<>();
		 
		 for (int i = 0; i < A.length; i++) {
			
			 for (int j = 0; j < B.length; j++) {
				
				 int sum=A[i]+B[j];
				 map.put(sum, map.getOrDefault(sum, 0)+1);
			}
		}
		 
		 int result=0;
		 for (int i = 0; i < C.length; i++) {
			
			 for (int j = 0; j < D.length; j++) {
				
				 int sum=C[i]+D[j];
				 
				 if (map.containsKey(-sum)) {
					
					result+=map.get(-sum); 
				}
			}
		}
		 
		 return result;
	 }
}
