package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Combine {
	
	public static void main(String[] args) {
		
		char[][]ch= {
				{'d','o','a','f'},
				{'a','g','a','i'},
				{'d','c','a','n'}
		};
		
		List<String> words=Arrays.asList("babbab","b","a","ba");
		
		Combine combine=new Combine();
		
		combine.wordSearchII(ch, words);
				
	}

	/**
	 * 425. 电话号码的字母组合
	 * 给一个不包含0和1的数字字符串，每个数字代表一个字母，请返回其所有可能的字母组合。

下图的手机按键图，就表示了每个数字可以代表的字母。
	 * @param digits
	 * @return
	 */
	
	
	public List<String> letterCombinations(String digits) {
        // write your code here
		
		List<String> resultList=new ArrayList<String>();
		
		if (digits==null||digits.equals("")) {
			
			return resultList;
		}
		
		char[] array = digits.toCharArray();
		
		Map<Character,List<String>> map=new HashMap<Character, List<String>>();;
		
		map.put('1', Arrays.asList(""));
		map.put('2', Arrays.asList("a","b","c"));
		map.put('3', Arrays.asList("d","e","f"));
		map.put('4', Arrays.asList("g","h","i"));
		map.put('5', Arrays.asList("j","k","l"));
		map.put('6', Arrays.asList("m","n","o"));
		map.put('7', Arrays.asList("p","q","r","s"));
		map.put('8', Arrays.asList("t","u","v"));
		map.put('9', Arrays.asList("w","x","y","z"));
		
		dfs(array, map, 0, resultList, new StringBuilder());
		
		return resultList;
		
    }
	
	
	public void dfs(char[] array,
					Map<Character,List<String>> map,
					int startIndex,
					List<String> resultList,
					StringBuilder sb) {
		
		if (sb.length()==array.length) {
			resultList.add(new String(sb));
			return;
		}
		
		for (int i = startIndex; i < array.length; i++) {
			
			
			List<String> list = map.get(array[i]);
			
			for (int j = 0; j < list.size(); j++) {
				
				sb.append(list.get(j));
				
				dfs(array, map, i+1, resultList, sb);
				
				sb.deleteCharAt(sb.length()-1);
			}
		}
		
	}
	
	/**
	 * 132. 单词搜索 II
	 * 给出一个由小写字母组成的矩阵和一个字典。找出所有同时在字典和矩阵中出现的单词。
	 * 一个单词可以从矩阵中的任意位置开始，可以向左/右/上/下四个相邻方向移动。
	 * 一个字母在一个单词中只能被使用一次。且字典中不存在重复单词
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
