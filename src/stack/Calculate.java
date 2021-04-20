package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * 224. 基本计算器
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。

	字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 * @author LANG
 *
 */
public class Calculate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculate pn=new Calculate();
		String expression = "1+((2+3)*4)-5";//注意表达式 
		List<String> infix = pn.toInfix(expression);
		System.out.println("中缀表达式对应的List=" + infix); 
		
		List<String> suffixExpreesionList = pn.toSuffix(infix);
		System.out.println("后缀表达式对应的List" + suffixExpreesionList);
		
		System.out.println(pn.calculate(suffixExpreesionList));;
	}

	
	public int calculate(String s) {
		List<String> infix = toInfix(s);
		List<String> suffix = toSuffix(infix);
		int result=calculate(suffix);
		return result;
    }


	/**
	 * 将字符串转为中缀
	 */
	public List<String> toInfix(String str) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < str.length();) {

			char ch = str.charAt(i);
			if (ch != ' ') {
				if (Character.isDigit(ch)) {

					int num = 0;
					while (i < str.length() && Character.isDigit(str.charAt(i))) {
						num = num * 10 + str.charAt(i) - '0';
						i++;
					}
					list.add(String.valueOf(num));
				} else {
					list.add(String.valueOf(ch));
					i++;
				}
			}else {
				i++;
			}

		}
		return list;
	}
	/**
	 * 将中缀转成后缀
	 */
	public List<String> toSuffix(List<String> infix) {
		Stack<String> s1 = new Stack<>();
		List<String> s2 = new ArrayList<>();
		for (String item : infix) {
			if (item.matches("\\d+")) {
				s2.add(item);
			} else if (item.equals("(")) {
				s1.push(item);
			} else if (item.equals(")")) {
				while (!s1.isEmpty() && !s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();//!!! 将 ( 弹出 s1栈， 消除小括号
			} else {

				while (!s1.isEmpty() && priority(item) <= priority(s1.peek())) {
					s2.add(s1.pop());
				}
				s1.push(item);
			}

		}

		while (!s1.isEmpty()) {
			s2.add(s1.pop());
		}
		return s2;
	}
	/**
	 * 优先级
	 * @param s
	 * @return
	 */
	public int priority(String s){
		if (s.equals("+")||s.equals("-")) {
			return 0;
		}else if (s.equals("*")||s.equals("/")) {
			return 1;
		}else {
			return -1; // 假定目前的表达式只有 +, - , * , /
		}
	}
	
	/**
	 * 计算逆波兰表达式
	 */
	
	public int calculate(List<String> suffixExpreesionList){
		Stack<Integer> stack=new Stack<>();
		for (String item : suffixExpreesionList) {
			if (item.matches("\\d+")) {
				stack.push(Integer.parseInt(item));
			}else {
				int num1=stack.pop();
				int num2=stack.pop();
				int result=calculate(num1, num2, item);
				stack.push(result);
			}
		}
		
		return stack.pop();
	}
	
	
	public int calculate(int num1,int num2,String oper) {
		int result=0;
		switch (oper) {
		case "+":
			result=num2+num1;
			break;
		case "-":
			result=num2-num1;
			break;
		case "*":
			result=num2*num1;
			break;
		case "/":
			result=num2/num1;
			break;
		default:
			break;
		}
		return result;
	}
}
