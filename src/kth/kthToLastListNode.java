package kth;

import list.ListNode;

public class kthToLastListNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode node1=new ListNode(1);
		ListNode node2=new ListNode(2);
		ListNode node3=new ListNode(3);
		ListNode node4=new ListNode(4);
		ListNode node5=new ListNode(5);
		
		node1.next=node2;
//		node2.next=node3;
//		node3.next=node4;
//		node4.next=node5;
		System.out.println(removeNthFromEnd(node1, 2));;

	}

	/**
	 * 
	 * 面试题 02.02. 返回倒数第 k 个节点
	 *       找出单向链表中倒数第 k 个节点,返回该节点的值。
	 *       
	 *  头结点是否有数据，不重要
	 *  解法1、暴力，求出链表的长度n，再移动n-k个节点
	 *  2、双指针，先用first指针正向移动k个节点，记录first位置，second指向头
	 *  	再用second指针和first同时移动，当first第一次为null时，second指向了倒数第k个
	 *  再同时移动时，必须指定计数器count，在first移动时，如果first在等于null时，count<k，说明不合法
	 * @param head
	 * @param k
	 * @return
	 */
	public int kthToLast(ListNode head, int k) {
		if(head==null||k<=0) {
			return Integer.MIN_VALUE;
		}
		ListNode first=head;
		ListNode second =head;
		int count=0;
		for (int i = 0; i < k; i++) {
			if (first!=null) {
				first=first.next;
				count++;
			}
		}
		
		if (count<k) {
			return Integer.MIN_VALUE;
		}
		while(first!=null) {
			first=first.next;
			second=second.next;
		}
		
		return second.val;
	}
	
	/**
	 * 19. 删除链表的倒数第N个节点
	 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head==null||n<=0) {
			return head;
		}
		ListNode first=head;
		ListNode second=head;
		int count=0;
		while(first!=null) {
			first=first.next;
			count++;
			if (count==n) {
				break;
			}
		}
		//当first为空的时候，说明要删除的是第一个节点，直接返回head.next即可
		if (first==null) {
			return head.next;
		}
		while(first!=null&&first.next!=null) {
			first=first.next;
			second=second.next;
		}
		if (second.next!=null) {
			
			second.next=second.next.next;
		}
		return head;
		
    }
}


