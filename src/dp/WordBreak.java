package dp;

import java.util.List;
import java.util.Set;

public class WordBreak {

	/**
	 * 107. 单词拆分 I

		给定字符串 s 和单词字典 dict，确定 s 是否可以分成一个或多个以空格分隔的子串，并且这些子串都在字典中存在。
因为我们已经使用了更强大的数据，所以普通的DFS方法已经无法通过此题。
	 * @param s
	 * @param wordSet
	 * @return
	 */
	public boolean wordBreak(String s, Set<String> wordSet) {
        // write your code here
    }
	
	
	/**
	 * 582. 单词拆分II
		给一字串s和单词的字典dict,在字串中增加空格来构建一个句子，并且所有单词都来自字典。
		返回所有有可能的句子。
		
		输入："lintcode"，["de","ding","co","code","lint"]
		输出：["lint code", "lint co de"]
		解释：
		插入一个空格是"lint code"，插入两个空格是 "lint co de"
		
		不适用场景：求出所有具体方案而非方案总数
		但是可以使用动态规划进行优化
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public List<String> wordBreak2(String s, Set<String> wordDict) {
        // write your code here
    }
	
	
	/**
	 * 683. 单词拆分 III
	 * 给出一个单词表和一条去掉所有空格的句子，
	 * 根据给出的单词表添加空格, 返回可以构成的句子的数量, 
	 * 保证构成的句子中所有的单词都可以在单词表中找到.
	 * @param s
	 * @param dict
	 * @return
	 */
	public int wordBreak3(String s, Set<String> dict) {
        // Write your code here
    }
}
