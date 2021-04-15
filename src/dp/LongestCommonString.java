package dp;

public class LongestCommonString {

	public static void main(String[] args) {
		
		String a="bedaacbade";
		String b="dccaeedbeb";
		
		System.out.println(longestCommonSubsequence(a, b));;
	}
	
	/**
	 * lintcode 79. Longest Common Substring
	 * 最大公共子串
	 * 假设有且仅有 1 个最大公共子串。比如，输入 a = "13452439"， b = "123456"。
	 * 由于字符串 "345" 同时在 a 和 b 中出现，且是同时出现在 a 和 b 中的最长子串。因此输出 "345"。
	 * 
	 * dp[i][j]代表以A[i]结尾的子串和B[j]结尾子串的最大公共子串的长度，
	 * 相等加一，否则为0
	 * 这样是为了能找到最大公共子串的结尾，否则后序的值都一样，找到最大公共子串的结尾就麻烦了
	 * 
	 */
	public static int longestCommonSubstring(String A, String B) {
		if (A==null||B==null||A.equals("")||B.equals("")) {
			return 0;
		}
		if (A.equals(B)) {
			return A.length();
		}
		int[][] dp=new int[A.length()][B.length()];
		
		char[] arrA=A.toCharArray();
		char[] arrB=B.toCharArray();
		int end=0;
		int max=0;
		
		//先把边界初始化
		for (int j = 0; j < dp[0].length; j++) {
			if (arrA[0]==arrB[j]) {
				dp[0][j]=1;
				if (dp[0][j]>max) {
					max=dp[0][j];
				}
			}
		}
		
		for (int i = 0; i < dp.length; i++) {
			if (arrB[0]==arrA[i]) {
				dp[i][0]=1;
				if (dp[i][0]>max) {
					max=dp[i][0];
					end=i;
				}
			}
		}
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if (arrA[i]==arrB[j]) {
					dp[i][j]=dp[i-1][j-1]+1;
					if (dp[i][j]>max) {
						max=dp[i][j];
						//end=j也行
						end=i;
					}
				}
			}
		}
		
		System.out.println(A.substring(end-max+1,end+1));
		return max;
		
    }
	
	//使用O(1)的空间复杂度
	/**
	 * 由于每次只需要左上角的值
	 * 按照反对角线的方式去计算
	 * @param A
	 * @param B
	 * @return
	 */
	public static int longestCommonSubstring2(String A, String B) {
		if (A==null||B==null||A.equals("")||B.equals("")) {
			return 0;
		}
		if (A.equals(B)) {
			return A.length();
		}
		
		int end=0;
		int max=0;
		
		int row=0;
		int col=B.length()-1;
		
		
		//外层循环 row和col 控制i和j的范围
		while(row>=0&&row<A.length()&&col>=0&&col<B.length()) {
			
			int i=0;
			int j=col;
			int temp=0;
			//内层循环i和j
			while(i>=0&&i<=row&&j>=col&&j<B.length()) {
				
				if (A.charAt(i)==B.charAt(j)) {
					temp+=1;
					if (temp>max) {
						max=temp;
						end=i;
					}
				}else {
					//由于只有一个temp变量，所以如果不相等，就得赋值0
					temp=0;
				}
				i++;
				j++;
			}
			
			row++;
			col--;
			
			
		}
		
		//说明对角线右上角遍历完了
		if (col<0) {
			row=1;
			col=B.length()-1;
			while(row>=0&&row<A.length()&&col>=0&&col<B.length()) {
				
				int i=row;
				int j=0;
				int temp=0;
				//内层循环i和j
				while(i>=row&&i<A.length()&&j>=0&&j<B.length()) {
					
					if (A.charAt(i)==B.charAt(j)) {
						temp+=1;
						if (temp>max) {
							max=temp;
							end=i;
						}
					}else {
						temp=0;
					}
					i++;
					j++;
				}
				
				row++;
				
			}
		}
		
		System.out.println(end);
		System.out.println(max);
		System.out.println(A.substring(end-max+1,end+1));
		return max;
	}
	
	
	
	/**
	 * lintcode 77. Longest Common Subsequence
	 * 给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。
	 * 最长公共子序列问题是在一组序列（通常2个）中找到最长公共子序列（注意：不同于子串，LCS不需要是连续的子串）。
	 * 该问题是典型的计算机科学问题，是文件差异比较程序的基础，在生物信息学中也有所应用。
	 * 
	 * dp[i][j]是A以i结尾，B以j结尾的到目前为止的子串的最大公共子序列
	 * 
	 */
	
	public static int longestCommonSubsequence(String A, String B) {
		if (A==null||B==null||A.equals("")||B.equals("")) {
			return 0;
		}
		if (A.equals(B)) {
			return A.length();
		}
		int[][]dp=new int[A.length()][B.length()];
		int len=A.length()>B.length()?A.length():B.length();
		
		char[]path=new char[len];
		int index=0;
		int max=0;
		//初始化边界
		if (A.charAt(0)==B.charAt(0)) {
			dp[0][0]=1;
			if (dp[0][0]>max) {
				max=dp[0][0];
				path[index]=A.charAt(0);
				index++;
			}
		}
		
		//下边的等于上边的
		for (int i = 1; i < dp.length; i++) {
			if (A.charAt(i)==B.charAt(0)) {
				dp[i][0]=1;
				if (dp[i][0]>max) {
					max=dp[i][0];
					path[index]=A.charAt(i);
					index++;
				}
				
			}else {
				dp[i][0]=dp[i-1][0];
			}
		}
		
		//右边的等于左边的
		for (int j = 1; j < dp[0].length; j++) {
			if (A.charAt(0)==B.charAt(j)) {
				dp[0][j]=1;
				if (dp[0][j]>max) {
					max=dp[0][j];
					
					path[index]=A.charAt(0);
					index++;
				}
				
			}else {
				dp[0][j]=dp[0][j-1];
			}
		}
		
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				//左上角+1
				if (A.charAt(i)==B.charAt(j)) {
					dp[i][j]=dp[i-1][j-1]+1;
					if (dp[i][j]>max) {
						max=dp[i][j];
						path[index]=A.charAt(i);
						index++;
					}
				}else {
					//左边和上边大的值
					dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		//只能求出一个最长子序列，求不出所有的
		System.out.println(String.valueOf(path));
		return dp[A.length()-1][B.length()-1];
	}
}
