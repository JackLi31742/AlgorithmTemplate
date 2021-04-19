package string;

public class Reverse {

	
	public static void main(String[] args) {
		String s="  hello world!  ";
		System.out.println(reverseWords2(s));
	}
	
	/**
	 * 344. 反转字符串
	 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
	 * @param s
	 */
	public void reverseString(char[] s) {

		int i=0;
		int j=s.length-1;
		while(i<s.length/2&&j>=0) {
			char temp=s[i];
			s[i]=s[j];
			s[j]=temp;
			i++;
			j--;
		}
    }
	
	/**
	 * 剑指 Offer 58 - I. 翻转单词顺序
	 * 151. 翻转字符串里的单词
	 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
	 * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

	 * @param s
	 * @return
	 */
	public static String reverseWords(String s) {
		String[]arr=s.trim().split(" ");
		
		int i=0;
		int j=arr.length-1;
		while(i<arr.length/2&&j>=0) {
					
					String temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;

					i++;
					j--;
		}
		String result="";
		for (int j2 = 0; j2 < arr.length-1; j2++) {
			if (!arr[j2].trim().equals("")) {
				
				result+=arr[j2]+" ";
			}
		}
		result+=arr[arr.length-1].trim();
		return result;
				
    }
	
	public static String reverseWords2(String s) {
		//把两边的空格先去除
		String[]arr=s.trim().split(" ");
		StringBuilder sb=new StringBuilder();
		for (int j=arr.length-1;j>0;j--) {
			if (!arr[j].trim().equals("")) {
				sb.append(arr[j].trim());
				sb.append(" ");
			}
		}
		sb.append(arr[0].trim());
		
		return sb.toString();
	}
}
