package matrix;

public class SearchMatrix {

	/**
	 * 面试题 10.09. 排序矩阵查找
	 * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
	 * 
	 * [
		  [1,   4,  7, 11, 15],
		  [2,   5,  8, 16, 19],
		  [3,   6,  9, 17, 22],
		  [10, 13, 14, 18, 24],
		  [18, 21, 23, 26, 30]
		]

	 * 从右上角15，如果15<target，往下走一行，（即使19的左边有比15大的，比如16，那也一定比19小，所以会走到左边）
	 * 如果15>target，往左走一行，说明target在左边
	 * 
	 * 从左下角，18<target，往右走
	 * 18>target，往上走
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {

		if (matrix==null||matrix.length<=0||matrix[0].length<=0) {
			return false;
		}
		
		int row = matrix.length;
		
		int col = matrix[0].length;
		
		int x=0;int y=col-1;
		
		while(x<row&&y>=0) {
			if (matrix[x][y]<target) {
				x++;
			}else if (matrix[x][y]>target) {
				y--;
			}else {
				return true;
			}
		}
		
		return false;
		
    }
}
