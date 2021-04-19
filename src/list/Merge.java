package list;

/**
 * 21. 合并两个有序链表
 *将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Merge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		if (l1==null) {
			return l2;
		}
		if (l2==null) {
			return l1;
		}
		//辅助头结点，容易一点
		ListNode head=new ListNode(0);
		ListNode p=head;
		while(l1!=null&&l2!=null) {
			if (l1.val<l2.val) {
				p.next=l1;
				l1=l1.next;
			}else {
				p.next=l2;
				l2=l2.next;
			}
			p=p.next;
		}
		
		if (l1!=null) {
			p.next=l1;
		}
		if (l2!=null) {
			p.next=l2;
		}
		
		return head.next;
    }

}
