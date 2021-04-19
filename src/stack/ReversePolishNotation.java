package stack;

import java.util.Stack;

public class ReversePolishNotation {

	
	public static void main(String[] args) {
		String[] tokens= {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
		
		System.out.println(evalRPN(tokens));;
	}
	/**
	 * 150. 逆波兰表达式求值
	 * 根据 逆波兰表示法，求表达式的值。

	有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
	
	说明：

		整数除法只保留整数部分。
		给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
		
		逆波兰表达式主要有以下两个优点：

去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中

	 * @param tokens
	 * @return
	 */
	public static int evalRPN(String[] tokens) {
		Stack<Integer> numStack=new Stack<Integer>();
		int first=0;
		int second=0;
		int result=0;
		for (int i = 0; i < tokens.length; i++) {
			switch (tokens[i]) {
				case "+":
					first=numStack.pop();
					second=numStack.pop();
					result=first+second;
					numStack.push(result);
					break;
				case "-":
					first=numStack.pop();
					second=numStack.pop();
					result=second-first;
					numStack.push(result);
					break;
				case "*":
					first=numStack.pop();
					second=numStack.pop();
					result=first*second;
					numStack.push(result);
					break;
				case "/":
					first=numStack.pop();
					second=numStack.pop();
					result=second/first;
					numStack.push(result);
					break;
				default:
					//数字的正则表达式太复杂，所以用这种方式
					numStack.push(Integer.parseInt(tokens[i]));
					break;
			}
			
		}
		
		return numStack.pop();
    }
	
	
}
