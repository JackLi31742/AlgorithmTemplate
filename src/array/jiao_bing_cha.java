package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class jiao_bing_cha {
	
	
	public static void main(String[] args) {
        Integer[] m = { 1, 2, 3, 4, 5 };
        Integer[] n = { 3, 4, 6 };

        System.out.println("----------并集------------");
        Integer[] b = getB(m, n);
        System.out.println(Arrays.toString(b));

        

        System.out.println("----------差集------------");
        Integer[] c = getC(m, n);
        System.out.println(Arrays.toString(c));

    }

	
	/**
	 * 547 · 两数组的交集
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null) {
            return null;
        }
        
        HashSet<Integer> hash = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            hash.add(nums1[i]);
        }
        
        HashSet<Integer> resultHash = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
        	//在1里，而且不在交集中
            if (hash.contains(nums2[i]) && !resultHash.contains(nums2[i])) {
                resultHash.add(nums2[i]);
            }
        }
        
        int size = resultHash.size();
        int[] result = new int[size];
        int index = 0;
        for (Integer num : resultHash) {
            result[index++] = num;
        }
        
        return result;
    }
	
	
	// sort & binary search
	public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        Arrays.sort(nums1);
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                continue;
            }
            if (binarySearch(nums1, nums2[i])) {
                set.add(nums2[i]);
            }
        }
        
        int[] result = new int[set.size()];
        int index = 0;
        for (Integer num : set) {
            result[index++] = num;
        }
        
        return result;
    }
    
    private boolean binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }
        
        return false;
    }
	
	
	/**
     * 求并集
     */
    private static Integer[] getB(Integer[] m, Integer[] n) {
        // 将数组转换为set集合
        Set<Integer> set1 = new HashSet<Integer>(Arrays.asList(m));
        Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(n));
        // 合并两个集合
        set1.addAll(set2);
        Integer[] arr = {};
        return set1.toArray(arr);
    }

    /**
     * 求差集
     */
    private static Integer[] getC(Integer[] m, Integer[] n) {
        // 将较长的数组转换为set
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(m.length > n.length ? m : n));
        // 遍历较短的数组，实现最少循环
        for (Integer i : m.length > n.length ? n : m) {
            // 如果集合里有相同的就删掉，如果没有就将值添加到集合
            if (set.contains(i)) {
                set.remove(i);
            } else {
                set.add(i);
            }
        }
        Integer[] arr = {};
        return set.toArray(arr);
    }
    
}
