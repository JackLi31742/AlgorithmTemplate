package list;

import java.util.Stack;

/**
 * leetcod 206
 *
 */

public class Reverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ListNode node1=new ListNode(1);
		ListNode node2=new ListNode(2);
		ListNode node3=new ListNode(3);
		ListNode node4=new ListNode(4);
		ListNode node5=new ListNode(5);
		
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node5;
		
		Reverse reverse=new Reverse();
		System.out.println(reverse.reverseList3(node1));;
	}

	/**
	 * 206. 反转链表
	 * 反转一个单链表。
	 * 解法1、迭代法
	 * @param head
	 * @return
	 */
	public ListNode reverseList1(ListNode head) {
		if (head==null||head.next==null) {
			return head;
		}
		ListNode first=head;
		ListNode second=first.next;
		ListNode third=second.next;
		head.next=null;
		while(second!=null) {
			second.next=first;
			//得先把second赋值给first，否则second指针所指的节点就移动了
			//first节点的位置也就跟着移动了
			first=second;
			second=third;
			if (third!=null) {
				third=third.next;
			}
		}
		return first;
    }
	
	/**
	 * 解法2、新建一个头，作为新链表的头，使用头插法，将原有链表的头不停的插到newHead的后一个位置
	 * 
	 * 
	 * @param head
	 * @return
	 */
	public ListNode reverseList2(ListNode head) {
		if (head==null||head.next==null) {
			return head;
		}
		ListNode newHead=new ListNode(0);
		ListNode first=head;
		while(first!=null){
			ListNode temp=first.next;
			first.next=newHead.next;
			newHead.next=first;
			first=temp;
		}
		
		return newHead.next;
				
	}
	
	/**
	 * 解法3 递归 没有理解
	 * @param head
	 * @return
	 */
	public ListNode reverseList3(ListNode head) {
		if (head==null||head.next==null) {
			return head;
		}
		//这才是递归的真正用法，遍历到最后一个得到了新的头
		ListNode newHead=reverseList3(head.next);
		
//		newHead.next=head;
		head.next.next=head;
		head.next=null;
		
		return newHead;
	}
	
	
	
	/**
	 * 92. 反转链表 II
	 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
	 * 1 ≤ m ≤ n ≤ 链表长度。
	 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
		输出: 1->4->3->2->5->NULL
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode cur = head;
		ListNode pre = null;
		ListNode next = null;
		int count = 1;
		while (cur != null && count < m) {
			next = cur.next;
			pre = cur;
			cur = next;
			count++;

		}

		ListNode preNew = null;
		ListNode tail=cur;
		while (cur != null && count <= n) {
			next = cur.next;
			cur.next = preNew;
			preNew = cur;
			cur = next;
			count++;
		}

		tail.next = cur;
		//如果只有1个节点，pre是null
		if (pre!=null) {
			
			pre.next = preNew;
		}
		//当tail==head时，说明是从头开始反转的
		if (tail==head) {
			return preNew;
		}
		return head;
		
    }
	
	/**
	 * 25. K 个一组翻转链表
	 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

		k 是一个正整数，它的值小于或等于链表的长度。

		如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
		
		给你这个链表：1->2->3->4->5

		当 k = 2 时，应当返回: 2->1->4->3->5
		
		当 k = 3 时，应当返回: 3->2->1->4->5
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode reverseKGroup(ListNode head, int k) {
		if (head==null||head.next==null) {
			return head;
		}
		ListNode newHead=new ListNode(0);
		int count=1;
		//pre会走，但是newHead没走，作为0的节点，连接到了后边
		ListNode pre=newHead;
		//cur会走
		ListNode cur=head;
		//k组的开始
		ListNode start=cur;
		ListNode next=null;
		
		while(cur!=null){
			//指针一直向后走
			if (count<k) {
				next=cur.next;
				cur=next;
				count++;
			}else {
				//指向下一组的头
				next=cur.next;
				//断开连接
				cur.next=null;
				pre.next=reverse(start);
				//start在反转后变为尾，重新连接到下一组
				start.next=next;
				//重新恢复初始
				count=1;
				//cur指向下一组的头
				cur=next;
				//pre指向上一组的尾，下一组头的前驱
				pre=start;
				//start指向下一组的头
				start=cur;
			}
		}

		return newHead.next;
		
    }
	
	public static ListNode reverse(ListNode head){
		ListNode pre=null;
		ListNode cur=head;
		ListNode next=null;
		
		while(cur!=null){
			next=cur.next;
			cur.next=pre;
			pre=cur;
			cur=next;
		}
		//出循环后，head变为了尾节点，但不是最后的null节点
//		System.out.println(head);
		return pre;
	}
	
	/**
	 *  栈
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode reverseKGroup2(ListNode head, int k) {
		if (head==null||head.next==null) {
			return head;
		}
		
		Stack<ListNode> stack=new Stack<>();
		
		ListNode cur=head;
		ListNode newHead=new ListNode(0);
		ListNode temp=newHead;
		int count=1;
		while(cur!=null) {
			if (count<=k) {
				
				stack.push(cur);
				cur=cur.next;
				count++;
			}
			//这里如果用else 当count>k，
			//cur如果为null的时候，就不走下边的代码，直接退出循环了
			if (count>k) {
				
				while(!stack.isEmpty()) {
					temp.next=stack.pop();
					temp=temp.next;
				}
				temp.next=cur;
				count=1;
			}
		
				
		}
		
		
		return newHead.next;
	}
	
	
}
