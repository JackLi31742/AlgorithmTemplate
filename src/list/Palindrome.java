package list;

import java.util.Stack;
public class Palindrome {

	
	public static void main(String[] args) {
		ListNode node1=new ListNode(1);
		ListNode node2=new ListNode(0);
		ListNode node3=new ListNode(1);
		ListNode node4=new ListNode(1);
		ListNode node5=new ListNode(5);
		
		node1.next=node2;
		node2.next=node3;
//		node3.next=node4;
//		node4.next=node5;
		
		System.out.println(isPalindrome(node1));
	}
	
	/**
	 * 234. 回文链表
	 * 请判断一个链表是否为回文链表。
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome(ListNode head) {
		if (head==null||head.next==null) {
			return true;
		}
		ListNode fast=head;
		ListNode slow=head;
		Stack<ListNode> stack=new Stack<>();
		while(fast!=null&&fast.next!=null) {
			fast=fast.next.next;
			stack.push(slow);
			slow=slow.next;
		}
		//链表长度是奇数
		if (fast!=null) {
			//走到下一个位置再去判断
			slow=slow.next;
		}
		while(!stack.isEmpty()&&slow!=null) {
			if (stack.pop().val!=slow.val) {
				return false;
			}
			slow=slow.next;
		}
		
		if (!stack.isEmpty()||slow!=null) {
			return false;
		}
		return true;
    }
}
