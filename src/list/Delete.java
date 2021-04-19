package list;


public class Delete {

	/**
	 * 面试题18. 删除链表的节点
	 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
		返回删除后的链表的头节点。	
	 *
	 */
	public ListNode deleteNode(ListNode head, int val) {

		if (head==null) {
			return head;
		}
		//如果头结点是要删除的值，返回头结点的下一个节点，不是返回头结点
		if (head.val==val) {
			return head.next;
		}
		ListNode pre=head;
		ListNode cur=pre.next;
		while(cur!=null) {
			if (cur.val==val) {
				pre.next=cur.next;
				return head;
			}
			pre=pre.next;
			cur=cur.next;
		}
		return head;
    }
}
