package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

import list.ListNode;

public class Merge {

	/**
	 * 23. 合并K个升序链表
	 * 给你一个链表数组，每个链表都已经按升序排列。

	请你将所有链表合并到一个升序链表中，返回合并后的链表。
	建堆的时间是O(k)logk，剩下n-k个点，最坏情况都要进来堆进行比较，每次比较是logk，所以总的是O(n)logk
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) {
			return null;
		}
        
        ListNode newHead=new ListNode(-1);
        ListNode p=newHead;
        Comparator<ListNode> comparator=new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				return o1.val-o2.val;
			}
		};
		
		PriorityQueue<ListNode> heap=new PriorityQueue<>(lists.length,comparator);
		
		for (int i = 0; i < lists.length; i++) {
			if (lists[i]!=null) {
				
				heap.add(lists[i]);
			}
		}
		
		while(!heap.isEmpty()) {
			ListNode node=heap.poll();
			if (node.next!=null) {
				
				heap.add(node.next);
			}
			p.next=node;
			p=node;
		}
		
		return newHead.next;
    }
	
	
}
