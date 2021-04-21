package kth;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import list.ListNode;

public class Merge {

	/**
	 * 88. 合并两个有序数组
	 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {

		int p1=0;
		int p2=0;
		int[] temp=new int[m+n];
		int index=0;
		while(p1<m&&p2<n) {
			if (nums1[p1]<nums2[p2]) {
				temp[index]=nums1[p1];
				p1++;
			}else {
				temp[index]=nums2[p2];
				p2++;
			}
			index++;
		}
		
		while(p1<m) {
			temp[index]=nums1[p1];
			index++;
			p1++;
		}
		
		while(p2<n) {
			temp[index]=nums2[p2];
			index++;
			p2++;
		}
		
		for (int i = 0; i < temp.length; i++) {
			nums1[i]=temp[i];
		}
    }
	
	
	
	/**
	 * 21. 合并两个有序链表
	 *将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
	 */
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

	
	
	/**
	 * 486 · 合并k个排序数组
	 * 将 k 个有序数组合并为一个大的有序数组。
	 * @param arrays
	 * @return
	 */
	public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
		
		if (arrays == null) {
            return new int[0];
        }
        //总数
        int total_size = 0;
        PriorityQueue<Element> heap = new PriorityQueue<Element>(arrays.length, ElementComparator);
            
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                Element elem = new Element(i, 0, arrays[i][0]);
                heap.add(elem);
                total_size += arrays[i].length;
            }
        }
        
        int[] result = new int[total_size];
        
        int index = 0;
        
        while (!heap.isEmpty()) {
            Element elem = heap.poll();
            result[index++] = elem.val;
            if (elem.col + 1 < arrays[elem.row].length) {
            	//直接赋值，没有创建新的Element
                elem.col += 1;
                elem.val = arrays[elem.row][elem.col];
                heap.add(elem);
            }
        }
        
        return result;
    }
	
	
	private Comparator<Element> ElementComparator = new Comparator<Element>() {
        public int compare(Element left, Element right) {
            return left.val - right.val;
        }
    };
    
    
    /**
     * 104 · 合并k个排序链表
     * 合并k个排序链表，并且返回合并后的排序链表。
     * @param lists
     * @return
     */
    public ListNode mergeKLists(List<ListNode> lists) {  
        // write your code here
    	if (lists == null || lists.size() == 0) {
            return null;
        }
        
    	PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                heap.add(lists.get(i));
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if (head.next != null) {
                heap.add(head.next);
            }
        }
        return dummy.next;
    }
    
    
    private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
        public int compare(ListNode left, ListNode right) {
            return left.val - right.val;
        }
    };
    
    
    //两两合并，每个链表合并O(logK)所以总的是O(NlogK)
    //这是从下到上
    public ListNode mergeKLists2(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        while (lists.size() > 1) {
            List<ListNode> new_lists = new ArrayList<ListNode>();
            for (int i = 0; i + 1 < lists.size(); i += 2) {
            	//21. 合并两个有序链表
                ListNode merged_list = mergeTwoLists(lists.get(i), lists.get(i+1));
                new_lists.add(merged_list);
            }
            if (lists.size() % 2 == 1) {
                new_lists.add(lists.get(lists.size() - 1));
            }
            lists = new_lists;
        }
        
        return lists.get(0);
    }
    
    
    
    //归并排序，把k个链表当成从0到k-1的数，把这k个数进行排序
    public ListNode mergeKLists3(List<ListNode> lists) {
        if (lists.size() == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.size() - 1);
    }
    
    private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }
        
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }
}


class Element {
    public int row, col, val;
    Element(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }
}
