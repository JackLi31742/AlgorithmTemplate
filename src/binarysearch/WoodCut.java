package binarysearch;

public class WoodCut {

	/**
	 * 183. 木材加工
	 * 有一些原木，现在想把这些木头切割成一些长度相同的小段木头，
	 * 需要得到的小段的数目至少为 k。当然，我们希望得到的小段越长越好，
	 * 你需要计算能够得到的小段木头的最大长度。
	 * 
	 * 无法切出要求至少 k 段的,则返回 0 即可。
	 * 
	 * 这是在答案范围上进行二分，即长度是有范围的，就是1到Max(L中的最大值,L中的sum/k)
	 * 
	 * 最后的长度，当超过这个长度时，会导致除以这个长度后的值<k
	 * @param L
	 * @param k
	 * @return
	 */
	public int woodCut(int[] L, int k) {
        // write your code here
		
		if (L==null||L.length==0) {
			return 0;
		}
//		System.out.println(L.length);
		long left=1;
		long right=getMax(L, k);
		
		while(left+1 < right) {
			
			long mid=left+(right-left)/2;
			
			long result=getResult(L, mid);
			
			if (result<k) {//说明mid大了，导致分割的段少了
				
				right=mid;
			}else if (result>=k) {
				
				left=mid;
			}
//			else {
//				return (int)mid;
//			}

		}
		
		long max=0;
		
		if (getResult(L, left)>=k) {
			
			max=left;
		}
		
		if (getResult(L, right)>=k) {
			
			if (right>max) {
				max=right;
			}
		}
		
		return (int)max;
    }
	
	
	public long getResult(int[] L,long len) {
		
		long result=0;
		
		for (int i = 0; i < L.length; i++) {
			result+=L[i]/len;
		}
		
//		System.out.println(result);
		return result;
	}
	
	
	public long getMax(int[] L,int k) {
		
		int max=L[0];
		long sum=L[0];
		
		for (int i = 1; i < L.length; i++) {
			
			if (L[i]>max) {
				max=L[i];
			}
			sum+=L[i];
		}
//		System.out.println(max);
//		System.out.println(sum);
		return Math.max(max, sum/k);
	}
}
