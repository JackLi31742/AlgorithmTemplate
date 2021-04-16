package dp;

public class WildCardMatch {
	
	
	public static void main(String[] args) {
		WildCardMatch patternMatch=new WildCardMatch();
		System.out.println(patternMatch.isMatch("aa", "*"));;
		
//		String aString="";
//		System.out.println(aString.length());
//		System.out.println(aString.charAt(0)==' ');
		
	}

	/**
	 * 
	 * 192. 通配符匹配
	 * 判断两个可能包含通配符“？”和“*”的字符串是否匹配。匹配规则如下：

	'?' 可以匹配任何单个字符。
	'*' 可以匹配任意字符串（包括空字符串）。
	两个串完全匹配才算匹配成功。
	
	1<=|s|, |p| <= 1000
s仅包含小写英文字母
p包含小写英文字母，？和 *
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
        // write your code here
		
		if (s==null||p==null) {
			return false;
		}

		
		return dp(s, p);
    }
	
	
	
	/**
	 * dfs是从当下的点向后进行递归计算
	 * dp不能是前边的依赖后边的，因为后边还没有计算，所以需要换一个角度，当前的点是从之前的点来的
	 * 所以dp需要初始化
	 * 
	 * dp[i][j]数组的含义代表s到i为止，p到j为止，是否匹配
	 * @param s
	 * @param sIndex
	 * @param p
	 * @param pIndex
	 * @return
	 */
	public boolean dp(String s, String p) {

		int sLen=s.length();
		int pLen=p.length();
		
		boolean[][] dp = new boolean[sLen+1][pLen+1];

		dp[0][0]=true;
		
		//这是行
		for (int j = 1; j <= pLen; j++) {
			
			dp[0][j]=dp[0][j-1]&&p.charAt(j-1)=='*';
		}
		
		//列除了第一个，都初始化为false，因为代表了s有字符，而p是""
		
		
		for (int i = 1; i <= sLen; i++) {
			
			for (int j = 1; j <= pLen; j++) {
				
				if (p.charAt(j-1) == '?' || s.charAt(i-1) == p.charAt(j-1)) {
					
					dp[i][j] = dp[i-1][j-1];
					
				} else if (p.charAt(j-1) == '*') {
					
					dp[i][j] = dp[i-1][j] || dp[i][j-1];
				}
			}
			
		}
				


		return dp[sLen][pLen];

	}
}
