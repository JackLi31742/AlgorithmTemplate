package list;

import java.util.ArrayList;
import java.util.List;

public class Insert {

	
	/**
	 * 头插法
	 * LANG
	 */
	public void headInsert(){
		ListNode node1=new ListNode(4);
		ListNode node2=new ListNode(5);
		ListNode node3=new ListNode(1);
		ListNode node4=new ListNode(9);
		
		List<ListNode> list=new ArrayList<>();
		list.add(node1);
		list.add(node2);
		list.add(node3);
		list.add(node4);
		ListNode head=new ListNode();
//		System.out.println(head.val+","+head.next);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).next=head.next;
			head.next=list.get(i);
		}
		
		System.out.println(head.next);
	}
	
	/**
	 * 尾插法
	 * LANG
	 */
	public void tailInsert(){
		ListNode node1=new ListNode(4);
		ListNode node2=new ListNode(5);
		ListNode node3=new ListNode(1);
		ListNode node4=new ListNode(9);
		
		List<ListNode> list=new ArrayList<>();
		list.add(node1);
		list.add(node2);
		list.add(node3);
		list.add(node4);
		
		ListNode head=new ListNode();
		ListNode tail=head;
		for (int i = 0; i < list.size(); i++) {
			
			tail.next=list.get(i);
			tail=list.get(i);
			
		}
		
		System.out.println(head.next);
	}
}
