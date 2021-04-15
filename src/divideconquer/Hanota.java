package divideconquer;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.06. 汉诺塔问题
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
(1) 每次只能移动一个盘子;
(2) 盘子只能从柱子顶端滑出移到下一根柱子;
(3) 盘子只能叠在比它大的盘子上。

请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。

你需要原地修改栈。

 * @author LANG
 *
 */
public class Hanota {
	
	public static void main(String[] args) {
		List<Integer> A=new ArrayList<Integer>();
		A.add(0);
		A.add(1);
		A.add(2);
		List<Integer> B=new ArrayList<Integer>();
		List<Integer> C=new ArrayList<Integer>();
		
		hanota(A, B, C);
	}
	
	public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
		if (A==null||B==null||C==null||A.size()<=0) {
			return;
		}
		
		hanota(A.size(), A, B, C);
    }
	
	/**
	 * 递归
	 * @param n
	 * @param A
	 * @param B
	 * @param C
	 */
	public static void hanota(int n,List<Integer> A, List<Integer> B, List<Integer> C) {
		if (n==1) {
			C.add(A.remove(A.size()-1));
		}else {
			//把A经过辅助C放到B上
			hanota(n - 1, A, C, B);
            //把A放到C上
            C.add(A.remove(A.size() - 1));
            //把B经过辅助A放到C上
            hanota(n - 1, B, A, C);

		}
	}

}
