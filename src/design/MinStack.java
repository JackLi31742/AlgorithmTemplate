package design;

import java.util.Stack;

/**
 * 12 · 带最小值操作的栈
 *	实现一个栈, 支持以下操作:

push(val) 将 val 压入栈
pop() 将栈顶元素弹出, 并返回这个弹出的元素
min() 返回栈中元素的最小值
要求 O(1) 开销.


使用两个仅支持 pop 和 push 的栈就可以完成, stack 储存压入的数据, minStack 储存最小值.

push 直接把元素压入 stack, 对于 minStack, 如果它为空则直接压入, 反之压入当前元素与 minStack 栈顶的最小值
pop 两个栈都弹出一个元素, 返回 stack 弹出的元素
min 返回 minStack 的栈顶
 */
public class MinStack {

	private Stack<Integer> stack;
    private Stack<Integer> minStack;
    
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int number) {
        stack.push(number);
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {
            minStack.push(Math.min(number, minStack.peek()));
        }
    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    public int min() {
        return minStack.peek();
    }
}
