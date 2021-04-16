package array;

import java.util.HashMap;

public class Sudo {

	
	public static void main(String[] args) {
		String[][] board= {
		  {"5","3",".",".","7",".",".",".","."},
		  {"6",".",".","1","9","5",".",".","."},
		  {".","9","8",".",".",".",".","6","."},
		  {"8",".",".",".","6",".",".",".","3"},
		  {"4",".",".","8",".","3",".",".","1"},
		  {"7",".",".",".","2",".",".",".","6"},
		  {".","6",".",".",".",".","2","8","."},
		  {".",".",".","4","1","9",".",".","5"},
		  {".",".",".",".","8",".",".","7","9"}
		};

//		System.out.println(isValidSudoku(board));
	}
	/**
	 * 36. 有效的数独
	 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

	数字 1-9 在每一行只能出现一次。
	数字 1-9 在每一列只能出现一次。
	数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

	 * @param board
	 * @return
	 */
	public static boolean isValidSudoku(char[][] board) {
		if (board==null||board.length==0||board[0].length==0) {
			return false;
		}
		int row=board.length;
		int col=board[0].length;
		HashMap<Character, Integer> map=new HashMap<Character, Integer>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j]!='.'&&map.containsKey(board[i][j])) {
					return false;
				}
				map.put(board[i][j], 1);
			}
//			System.out.println(map);
			map.clear();
		}
		
//		System.out.println("--------------");
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				if (board[j][i]!='.'&&map.containsKey(board[j][i])) {
					return false;
				}
				map.put(board[j][i], 1);
			}
//			System.out.println(map);
			map.clear();
		}
//		System.out.println("----------------");
		int per=3;
		for (int m = 1; m <= row/per; m++) {
			for (int n = 1; n <= col/per; n++) {
				
				for (int i = (m-1)*per; i < row/per*m; i++) {
					for (int j = (n-1)*per; j < col/per*n; j++) {
						if (board[i][j]!='.'&&map.containsKey(board[i][j])) {
							return false;
						}
						map.put(board[i][j], 1);
					}
				}
//				System.out.println(map);
				map.clear();
			}
		}
		
		
		
		
		return true;
    }
	
	/**
	 * 一次遍历
	 * @param board
	 * @return
	 */
	public static boolean isValidSudoku2(char[][] board) {
		if (board==null||board.length==0||board[0].length==0) {
			return false;
		}
		int row=board.length;
		int col=board[0].length;
		HashMap<Character, Integer>[] rowMaps=new HashMap[row];
		HashMap<Character, Integer>[] colMaps=new HashMap[row];
		HashMap<Character, Integer>[] boxMaps=new HashMap[row];
		
		for (int i = 0; i < row; i++) {
			rowMaps[i]=new HashMap<Character, Integer>();
			colMaps[i]=new HashMap<Character, Integer>();
			boxMaps[i]=new HashMap<Character, Integer>();
		}
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j]!='.') {
					
					int boxIndex = (i / 3 ) * 3 + j / 3;
					char ch=board[i][j];
					rowMaps[i].put(ch, rowMaps[i].getOrDefault(ch, 0)+1);
					colMaps[j].put(ch, colMaps[j].getOrDefault(ch, 0)+1);
					boxMaps[boxIndex].put(ch, boxMaps[boxIndex].getOrDefault(ch, 0)+1);
				
					if (rowMaps[i].get(ch)>1||colMaps[j].get(ch)>1||boxMaps[boxIndex].get(ch)>1) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
