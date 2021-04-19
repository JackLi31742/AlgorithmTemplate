package list;

public class Cycle {

	
	public static void main(String[] args) {
		ListNode node1=new ListNode(1);
		ListNode node2=new ListNode(2);
		
		node1.next=node2;
		node2.next=node1;
		
		System.out.println(detectCycle(node1));;
	}
	 
     /**
      * 141. 环形链表
                *  给定一个链表，判断链表中是否有环。

		为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 
		如果 pos 是 -1，则在该链表中没有环。
      * @param head
      * @return
      */
	 public boolean hasCycle(ListNode head) {
	      ListNode fast =head;
	      ListNode slow =head;
	      while(fast!=null&&fast.next!=null) {
	    	  fast=fast.next.next;
	    	  slow=slow.next;
	    	  if (fast==slow) {
				return true;
			}
	      }
	      
	      return false;
	 }
	 
	 
	 /**
	  * 142. 环形链表 II
	  * https://blog.csdn.net/willduan1/article/details/84771729
	  * 
	  * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
		
		为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
		 如果 pos 是 -1，则在该链表中没有环。

		
		快慢指针，快走两步，慢走1步，用于检测是否有环
		在有环的情况下，慢指针指向头节点，与快指针同时移动，当两者相遇，就是环的入口
		
	  */
	 public static ListNode detectCycle(ListNode head) {
		  ListNode fast =head;
	      ListNode slow =head;
	      boolean flag=false;
	      while(fast!=null&&fast.next!=null) {
	    	  fast=fast.next.next;
	    	  slow=slow.next;
	    	  if (fast==slow) {
	    		  flag=true;
	    		  break;
			}
	      }
	      //在有环的情况下
	      if (flag&&fast==slow) {
			
	    	  slow=head;
	    	  while(fast!=null) {
	    		  //先判断，可以避免[1,2]，1指向2,2指向1
	    		  if (fast==slow) {
	    			  return fast;
	    		  }
	    		  fast=fast.next;
	    		  slow=slow.next;
	    	  }
		}
	      return null;
	 }
	 
	 /**
	  * 876. 链表的中间结点
	  * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。

	如果有两个中间结点，则返回第二个中间结点。
	  */
	 public ListNode middleNode(ListNode head) {
		  ListNode fast =head;
	      ListNode slow =head;
	      while(fast!=null&&fast.next!=null) {
	    	  fast=fast.next.next;
	    	  slow=slow.next;
	      }
	      
	      return slow;
	 }
}
