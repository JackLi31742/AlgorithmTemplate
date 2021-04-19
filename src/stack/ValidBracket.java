package stack;

import java.util.HashMap;
import java.util.Stack;

public class ValidBracket {

	public static void main(String[] args) {
		String s=")(){}";
		System.out.println(isValid(s));;
	}
	/**
	 * 20. 有效的括号
	 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	 * @param s
	 * @return
	 */
	public static boolean isValid(String s) {
		if (s==null||s.length()<=1) {
			return false;
		}
		Stack<Character> stack=new Stack<Character>();
		
		HashMap<Character, Character> map=new HashMap<Character, Character>();
		
		map.put(')', '(');
		map.put('}','{');
		map.put(']','[');
		
		char[]arr=s.toCharArray();
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]=='('||arr[i]=='['||arr[i]=='{') {
				stack.push(arr[i]);
			}else {
				if(!stack.isEmpty()) {
					//不能直接用stack.pop()比较，会把栈里的元素直接弹出
					if (map.get(arr[i])!=stack.peek()) {
						
						return false;
					}else {
						stack.pop();
					}
				}else {
					return false;
				}
			}
		}
		if (!stack.isEmpty()) {
			return false;
		}
		return true;
    }
}
