package list;

import java.util.Stack;

/**
 * 面试题06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
 * 
 * 方式1： 先将单链表进行反转操作，然后再遍历即可，这样的做的问题是会破坏原来的单链表的结构，不建议	
 * 方式2：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果.
举例演示栈的使用 Stack 

 * @author HP
 *
 */
public class ReversePrint {

	
	public int[] reversePrint(ListNode head) {
		int[]result=null;
		if (head==null) {
			//长度为0的数组
			result=new int [0];
			return result;
		}
		if (head.next==null) {
			//长度为1的数组
			result=new int [1];
			result[0]=head.val;
			return result;
		}
		
		Stack<Integer> stack=new Stack<Integer>();
		
		while(head!=null) {
			stack.push(head.val);
			head=head.next;
		}
		
		result=new int[stack.size()];
		
		for (int i = 0; i < result.length; i++) {
			result[i]=stack.pop();
		}
		return result;
	}
}
