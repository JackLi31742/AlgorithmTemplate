package binarysearch;

public class Sqrt {

	/**
	 * 69. x 的平方根

	 * 实现 int sqrt(int x) 函数。

		计算并返回 x 的平方根，其中 x 是非负整数。
		
		由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
		
		
		其实这是在答案上进行二分，因为是从一个范围（0-x）进行不断缩小

	 * @param x
	 * @return
	 */
	public static int mySqrt(int x) {
		int left=0;
		int right=x;
		int mid=0;
		while(left<=right) {
			mid=(right-left)/2+left;
			if (mid==0) {
				if (x==0) {
					return mid;
				}else {
					left=mid+1;
				}
			}else {
				
				if (mid<x/mid) {
					left=mid+1;
				}else if (mid>x/mid) {
					right=mid-1;
				}else {
					return mid;
				}
			}
		}
//		System.out.println(left+","+mid+","+right);
		return right;
    }
	
	/**
	 * 对x开根II · Sqrt(x) II
	 * 精确度保持在小数点后12位。
	 * 一直二分直到 |number^2 - x| <= 1e-10
	 * 
	 * L/2^x<=10^-t
	 * 
	 * 2^x>=L/10^-t
	 * 
	 * x>=logL+log10*t
	 * @param x
	 * @return
	 */
	public double sqrt(double x) {
        // Write your code here
        double l = 0; 
        double r = Math.max(x, 1.0);
        double eps = 1e-12;
        
        while (l + eps < r) {
            double mid = l + (r - l) / 2;
            if (mid * mid < x) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        return l;
    }
	
	/**
	 * 保留6位小数
	 * @param x
	 * @return
	 */
	
	public static double mySqrt2(int x) {
		double left=0;
		double right=x;
		double mid=0;
		//介值定理，所以每次mid不能加1，减一，得加减range
		double range=0.000001;
		while(left<=right) {
			mid=(right-left)/2.0+left;
			if (mid==0) {
				if (x==0) {
					return mid;
				}else {
					left=mid+range;
				}
			}else {
				
				if (mid<x/mid) {
					left=mid+range;
				}else if (mid>x/mid) {
					right=mid-range;
				}else {
					return mid;
				}
			}
		}
//		System.out.println(left+","+mid+","+right);
		
		return (double)Math.round(right*1000000)/1000000;
    }
}
