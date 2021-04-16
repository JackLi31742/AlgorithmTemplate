package dp;

public class LongestPalindrome {
	
	public static void main(String[] args) {
		String string="abaabaa";
		
		System.out.println(longestPalindromeSubseq(string));;
//		System.out.println(string.length());
	}
	
	/**
	 * 5. 最长回文子串
	 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {

		if (s==null||s.length()<2) {
			return s;
		}
//		String result="";
		int max=0;
		int begin=0;
		char[] arr = s.toCharArray();
		int len=arr.length;
		for (int i = 0; i < len; i++) {
//			for (int j = i+1; j <= s.length(); j++) {
			//由于求最长，所以，从最长的开始检测，而且由于不是substring，所以要注意边界
			for (int j = len-1; j >= i; j--) {
				//先判断是不是比他大，因为这个判断比较快，如果小的话，就没有必要检查是否是回文串了
				if ((j-i+1)>max&&checkPalindrome(arr,i,j)) {
					max=j-i+1;
					//不用每次都求出结果，可以最后一次拿到
//					result=String.valueOf(arr, i, max);
					begin=i;
					
				}
			}
		}
//		System.out.println(max);
		return String.valueOf(arr, begin, max);
    }
	
	
	//substring和charAt每次都会进行边界检查
	public static boolean checkPalindrome(char[] arr,int left,int right) {
		while(left<right) {
			if (arr[left]!=arr[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
	
	/**
	 * dp[i][j]从i到j是否是回文串
	 * dp[i][j]=dp[i+1][j-1]&&s.charAt(i)==s.charAt(j)
	 * 
	 * 对于s来说，如果s的长度是0和1，那么肯定是回文串
	 * 由于是从i到i+len的形式，那么不存在s为0的情况，s为1，就是从i到i，dp[i][i]=true
	 * 当s的长度是2和3，那么只需要比较s的左右两端
	 * 反映到i到i+len的形式，就是len是1或者2，比较s.charAt(i)==s.charAt(i+len)
	 * @param s
	 * @return
	 */
	public static String longestPalindrome2(String s) {
		
		if (s==null||s.length()<2) {
			return s;
		}
		
		char[] arr = s.toCharArray();
		int len = s.length();
		boolean[][] dp = new boolean[len][len];
		
		int begin=0;
		//单个字符也是回文，所以最短都是1,max代表距离begin的距离
		int max=0;
		
		//l为0的情况
		for (int i = 0; i < len; i++) {
			dp[i][i]=true;
		}
		//l代表从i出发到j的距离
		for (int l = 1; l < 3; l++) {
			//i代表起点 ，由于j是由i+l得到，需要在i这个循环进行控制
			for (int i = 0; i+l < len; i++) {
				//j代表终点
				int j=i+l;
				
				if (arr[i]==arr[j]) {
					dp[i][j]=true;
					begin=i;
					//长度需要加上i本身
					max=l;
				}
			}
		}
		
		//当长度大于3的时候，才需要状态转移
		for (int l = 3; l < len; l++) {
			
			for (int i = 0; i+l < len; i++) {
				//这样填表才能填对顺序
				int j=i+l;
				dp[i][j]=dp[i+1][j-1]&&arr[i]==arr[j];
				
				if (dp[i][j]&&l>max) {
					begin=i;
					max=l;
				}
			}
		}
		
		
		return String.valueOf(arr, begin, max+1);
	}
	
	
	/**
	 * 中心扩散，选择一个字符，从这个字符开始进行左右扩散
	 * @param s
	 * @return
	 */
	public static String longestPalindrome3(String s) {
		
		if (s==null||s.length()<2) {
			return s;
		}
		
		char[] arr = s.toCharArray();
		int len = arr.length;
		//begin count
		int begin=0;
		int count=1;
		
		for (int i = 0; i < len; i++) {
			
			//奇数和偶数，这两初始值不一样，由于不知道要传入奇数还是偶数，所以需要都传一次，看哪个大
			int[] odd =spread(arr, len, new int[2] ,i-1,i+1);
			int[] even =spread(arr, len, new int[2], i-1, i);
			
			int[] result=odd[1]>even[1]?odd:even;
			
			if (result[1]>count) {
				count=result[1];
				begin=result[0];
			}
		}
		
		return String.valueOf(arr, begin, count);
	}
	
	public static int[] spread(char[]arr,int len,int[]result,int left,int right) {
		
		
//		int left=mid-1;
//		int right=mid+1;
		
		while(left>=0&&right<len) {
			if (arr[left]==arr[right]) {
				left--;
				right++;
			}else {
				break;
			}
		}
		
		result[0]=left+1;
		result[1]=right-left-1;
		
		return result;
	}
	
	/**
	 * 516. 最长回文子序列
	 * dp[i][j]：代表从i到j最长的公共子序列的长度，谨记是从i到j，
	 * 别因为是子序列，就延长到i的左边，j的右边，那些不是dp[i][j]的职责范围，这是dp不是中心扩散
	 * 
	 * 如果arr[i]==arr[j]，dp[i][j]=dp[i+1][j-1]+2
	 * 如果arr[i]!=arr[j],，说明i和j这两个字符不能同时出现在dp[i][j]中，所以要比较dp[i+1][j]和dp[i][j-1]的大小
	 * 也就是要比较dp[i][j]左边和下边的值的大小
	 */
	public static int longestPalindromeSubseq(String s) {

		if (s==null||s.length()==0) {
			return 0;
		}
		
		if (s.length()==1) {
			return 1;
		}
		
		char[] arr = s.toCharArray();
		int len = arr.length;
		
		int[][] dp = new int[len][len];
		
		for (int i = 0; i < len; i++) {
			dp[i][i]=1;
		}
		for (int i = 1; i < len; i++) {
			if (arr[i]==arr[i-1]) {
				dp[i-1][i]=2;
			}else {
				//和最长回文子串不同，需要赋值1，不然加的时候就成0了
				dp[i-1][i]=1;
			}
		}
		
		
		for (int l = 2; l < len; l++) {
			for (int i = 0; i+l < len; i++) {
				int j=i+l;
				if (arr[i]==arr[j]) {
					dp[i][j]=dp[i+1][j-1]+2;
				}else {
//					if (arr[i+1]==arr[j]||arr[i]==arr[j-1]) {
						//不能是dp[i+1][j]+1，或者dp[i][j-1]+1，
					//因为dp[i+1][j]右边虽然加了j，但左边也少了一个字符，相当于不加不减
					
						dp[i][j]=Math.max(dp[i+1][j], dp[i][j-1]);
//					}else {
						//即使上边的if两项都不相等，也不能等于dp[i+1][j-1]
//						dp[i][j]=dp[i+1][j-1];
//					}
				}
			}
		}
		
		return dp[0][len-1];
    }
	
	
	/**
	 * 730. 统计不同回文子序列
	 * @param S
	 * @return
	 */
	public int countPalindromicSubsequences(String S) {

    }
	
}
