package array;

public class SubArray {

	
	/**
	 * LintCode 406 和大于S的最小子数组
	 * 给定一个由 n 个正整数组成的数组和一个正整数 s ，请找出该数组中满足其和 ≥ s 的最小长度子数组。如果无解，则返回 -1。
	 * 
	 * 输入: [2,3,1,2,4,3], s = 7
		输出: 2
		解释: 子数组 [4,3] 是该条件下的最小长度子数组。
		
		s[j]代表到j为止的前缀和
		两个指针，那么a[i]+...+a[j]=s[j]-s[i-1]
		固定右指针j，那么i作为距离j最近的满足条件的下标，在j向右移动的时候，i是不能向左移动的，只能向右移动，
		
		所以右指针j是for循环，左指针while循环，一直到和小于给定的s，就是此时j对应的最接近j是i
	 */
	
	public int minimumSize(int[] nums, int s) {
        // write your code here
		int j = 0, i = 0;
        int sum =0;
        int ans = Integer.MAX_VALUE;
        for(i = 0; i < nums.length; i++) {
            while(j < nums.length && sum < s) {
                sum += nums[j];
                j ++;
            }
            if(sum >=s) {
                ans = Math.min(ans, j - i);
            }
            sum -= nums[i];
        }
        if(ans == Integer.MAX_VALUE)
            ans = -1;
        return ans;
    }
	
	/**
	 * lintcode 617 · 子数组的最大平均值 II
	 * 给出一个整数数组，有正有负。找到这样一个子数组，他的长度大于等于 k，且平均值最大。
	 * 
	 * 如果要求和最大，可以用前缀和数组。但是平均值最大不好求
• 那么如果最大平均值是T，那么我们的目标是找到
– (A[left] + … + A[right]) / (right – left + 1) >= T，且right – left + 1>= k
– 即(A[left]-T) + … + (A[right]-T) >= 0
• 换句话说，对于一个T，把每个元素A[i]减去T得到B[i]
• 希望找到最大的B[left] + … + B[right] >= 0，且right – left + 1>= k
• 这可以通过前缀和实现
• 如果找不到这样的(left, right)，说明答案小于T，继续往下找；如果找到了T，那么继续往大了找
	 */
	
	//是否能找到的B[left] + … + B[right] >= 0，且right – left + 1>= k
	private boolean canFind(int[] A, int K, double avg) {
        int i;
        double rightSum = 0, leftSum = 0, minLeftSum = 0;
        for (i = 0; i < K; ++i) {
            rightSum += A[i] - avg;  //S[j]+=B[i]，右端点 前缀和
        }
        
        //划线从下标K，走到最后
        for (i = K; i <= A.length; ++i) {
            if (rightSum - minLeftSum >= 0) {
                return true;
            }
            
            if (i < A.length) {
                rightSum += A[i] - avg;
                leftSum += A[i - K] - avg;//S[j-k] 左端点的前缀和
                minLeftSum = Math.min(minLeftSum, leftSum);//s[0]到s[j-k]中最小的，只有它最小，从j-k到j才会最大
            }
        }
        
        return false;
    } 
     
    public double maxAverage(int[] A, int K) {
        int i;
        double start, stop, mid;
        start = stop = A[0];
        for (i = 0; i < A.length; ++i) {
            start = Math.min(A[i], start);
            stop = Math.max(A[i], stop);
        }
        
        while (start + 1e-5 < stop) {
            mid = (start + stop) / 2;// mid 就是T
            if (canFind(A, K, mid)) {
                start = mid;
            }
            else {
                stop = mid;
            }
        }
        
        return start;
    }
}
