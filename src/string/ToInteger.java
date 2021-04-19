package string;

public class ToInteger {

	public static void main(String[] args) {
		System.out.println(Integer.MIN_VALUE+Math.pow(10, 10)*9);
		char ch=' ';
		System.out.println(ch<'0');
		System.out.println(Math.pow(10, 3));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.parseInt("-91283472332"));
		System.out.println(myAtoi("-91283472332"));
//		try {
//			System.out.println(parseInt("-91283472332",10));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	/**
	 * 8. 字符串转换整数 (atoi)
	 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

		如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
		假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
		该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
		注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
		
		在任何情况下，若函数不能进行有效的转换时，请返回 0 。
		
		提示：
		
		本题中的空白字符只包括空格字符 ' ' 。
		假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。
		如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。


	 */
	public static int myAtoi(String str) {
		if (str==null||str.length()==0||str.trim().length()==0) {
			return 0;
		}
		char[] arr=str.trim().toCharArray();
		//是否是负数
		boolean flag=false;
		int result=0;
		
		int start=0;
		int end=1;
//		while(start < arr.length) {
		if (arr[start]>'9') {
			return 0;
		}
		if (arr[start]<'0') {
			if (arr[start]=='-') {
				flag=true;
			}else if (arr[start]!='+') {
				return 0;
			}
			start++;
		}
			
			
//		}
		while(end<arr.length) {
			if (arr[end]>='0'&&arr[end]<='9') {
				end++;
			}else {
				break;
			}
		}
		int radix=10;
		int len=end-start;
		while(start < end) {
			int x=Character.digit(arr[start], radix);
			//所以当result<=Integer.MIN_VALUE时，就代表越界了
			//而且不能再得到result之后再比较，那个时候result就已经越界了
			if (result<=Integer.MIN_VALUE+Math.pow(10, len-1)*x) {
				if (flag) {
					return Integer.MIN_VALUE;
				}else {
					return Integer.MAX_VALUE;
				}
			}
			//由于int 的范围，在去掉正负号后，abs(负数)比正数大，所以用的是-=而不是+=
			result-=Math.pow(10, len-1)*x;
			start++;
			len--;
		}
		
		return flag?result:-result;
    }
	
	
	
}
