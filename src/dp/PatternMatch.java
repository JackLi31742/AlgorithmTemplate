package dp;

public class PatternMatch {

	/**
	 * 10. 正则表达式匹配
	 * 154. 正则表达式匹配
		实现支持'.'和'*'的正则表达式匹配。

		'.'匹配任意一个字母。
		
		'*'匹配零个或者多个前面的元素。
		
		匹配应该覆盖整个输入字符串，而不仅仅是一部分。
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
        // write your code here
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
	public boolean isMatch2(String s, String p) {
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
	
	
	/**
	 * 829. 字模式 II
		给定一个pattern和一个字符串str，查找str是否遵循相同的模式。
	这里遵循的意思是一个完整的匹配，在一个字母的模式和一个非空的单词str之间有一个双向连接的模式对应。
	(如果a对应s，那么b不对应s。例如，给定的模式= "ab"， str = "ss"，返回false）。
	
	输入:
	pattern = "abab"
	str = "redblueredblue"
	输出: true
	说明: "a"->"red","b"->"blue"
	 * @param pattern
	 * @param str
	 * @return
	 */
	public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
    }
}
