package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSearch {

	
public static void main(String[] args) {
		
		char[][]ch= {
				{'d','o','a','f'},
				{'a','g','a','i'},
				{'d','c','a','n'}
		};
		
		List<String> words=Arrays.asList("babbab","b","a","ba");
		
		WordSearch wordSearch=new WordSearch();
		
		wordSearch.wordSearchII(ch, words);
				
	}
	
	/**
	 * 132. 单词搜索 II
	 * 给出一个由小写字母组成的矩阵和一个字典。找出所有同时在字典和矩阵中出现的单词。
	 * 一个单词可以从矩阵中的任意位置开始，可以向左/右/上/下四个相邻方向移动。
	 * 一个字母在一个单词中只能被使用一次。且字典中不存在重复单词
	 * 
	 * 输入：["doaf","agai","dcan"]，["dog","dad","dgdg","can","again"]
输出：["again","can","dad","dog"]
解释：
  d o a f
  a g a i
  d c a n
矩阵中查找，返回 ["again","can","dad","dog"]。

使用哪种搜索策略  B策略更好
A: for 词典里的每个单词 { check 单词是否在矩阵里 }
B: for 矩阵的每个单词 { check 单词是否在词典里}


	 * @param board
	 * @param words
	 * @return
	 */
	public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
		
		
		if (board==null||words==null||board.length==0||board[0].length==0) {
			
			return new ArrayList<String>();
		}
		
		int[] xArr= {1,0,0,-1};
		int[] yArr= {0,1,-1,0};
		
		//光用单词进行加速是不够的
//		Set<String> wordsSet=new HashSet<String>(words);
		
		Map<String, Boolean> map = getPreWords(words);
		
		boolean[][]visited=new boolean[board.length][board[0].length];
		
		StringBuilder sb=new StringBuilder();
		
		Set<String> resultSet=new HashSet<String>();
		
		for (int i = 0; i < board.length; i++) {
			
			for (int j = 0; j < board[i].length; j++) {
				
//				if (board[i][j]=='a') {
//					System.out.println("a");
//				}
				//回溯得跟着dfs，否则会过滤掉一些词
				visited[i][j]=true;
				sb.append(board[i][j]);
				dfs(board, map, i, j, visited, resultSet, sb, xArr, yArr);
				visited[i][j]=false;
				sb.deleteCharAt(sb.length()-1);
			}
		}
		return new ArrayList<String>(resultSet);
    }
	
	
	public void dfs(char[][] board, 
//			Set<String> wordsSet,
			Map<String, Boolean> map,
			int i,int j,
			boolean[][]visited,
			Set<String> resultSet,
			StringBuilder sb,
			int[] xArr,
			int[] yArr) {
		
		
//		visited[i][j]=true;
//		sb.append(board[i][j]);
		
		if (!map.containsKey(sb.toString())) {
			return;
		}
		
		//要在加了sb之后判断，而不是先contain，后sb添加
		if (map.get(sb.toString())) {
			
			resultSet.add(sb.toString());
		}
		

		for (int k = 0; k < yArr.length; k++) {
			
			int nextX=i+xArr[k];
			int nextY=j+yArr[k];
			
			if (check(board, nextX, nextY, visited)) {
				continue;
			}
			visited[nextX][nextY]=true;
			sb.append(board[nextX][nextY]);
			dfs(board, map, nextX, nextY, visited, resultSet, sb, xArr, yArr);
			visited[nextX][nextY]=false;
			sb.deleteCharAt(sb.length()-1);
		}
		
//		visited[i][j]=false;
//		sb.deleteCharAt(sb.length()-1);
				
	}
	
	/**
	 * 检查坐标i,j是否合法，合法返回false，非法返回true
	 * @param board
	 * @param x
	 * @param y
	 * @param visited
	 */
	public boolean check(char[][] board,int x,int y,boolean[][]visited) {
		
		if (x<0||x>=board.length||y<0||y>=board[0].length||visited[x][y]) {
			return true;
		}
		
		return false;
	}
	
	//用来加速
	/**
	 * 使用 hashset 来做前缀查询？
wordSet = {“hello”,“world”}
prefixSet = {“h”,“he”,“hel”,“hell”,“hello”,“w”,“wo”,“wor”,“worl”,“world”}
	 * @param words
	 * @return
	 */
	public Map<String, Boolean> getPreWords(List<String> words){
		
		//单词的前缀
		Map<String, Boolean> map=new HashMap<String, Boolean>();
		
		for (String word : words) {
			
			for (int i = 0; i < word.length()-1; i++) {
				
				String pre = word.substring(0, i+1);
				
				//if是用来判断可能某个前缀也是单词，直接put会覆盖
				if (!map.containsKey(pre)) {
					
					//用key来判断前缀
					map.put(pre, false);
				}
				
			}
			//用value加result
			map.put(word, true);
		}
		
		return map;
	}
}
