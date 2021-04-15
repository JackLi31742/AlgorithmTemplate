package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 960. 数据流中第一个独特的数 II
void add(number) // 加一个新的数
int firstUnique() // 返回第一个独特的数

和lru的题目很像，需要把之前出现的有重复的数字删除，所以用链表
 *
 */
public class DataStream {
    
	//key 是num，value是链表的节点
	private Map<Integer, Node> map;
	
	//num是否重复出现
	private Set<Integer> set;
	
	Node headNode;
	Node tailNode;
	
    public DataStream(){
        // do intialization if necessary
    	map=new HashMap<Integer, Node>();
    	set=new HashSet<Integer>();
    	headNode=new Node(-1);
    	tailNode=new Node(-1);
    	headNode.next=tailNode;
    	tailNode.pre=headNode;
    }
    /**
     * @param num: next number in stream
     * @return: nothing
     */
    public void add(int num) {
        // write your code here
    	
    	if (set.contains(num)) {
    		Node node = map.remove(num);
    		//由于set包含了所有的，map已经把之前出现的删除了，所以要判断是不是null
    		if (node==null) {
				return;
			}
			node.pre.next=node.next;
			node.next.pre=node.pre;
			return;
		}
    	
    	set.add(num);
    	Node node = new Node(num);
    	
    	tailNode.pre.next=node;
    	node.pre=tailNode.pre;
    	node.next=tailNode;
    	tailNode.pre=node;
    	
    	map.put(num, node);
    }

    /**
     * @return: the first unique number in stream
     */
    public int firstUnique() {
        // write your code here
    	if (headNode.next!=null) {
			
    		return headNode.next.value;
		}
    	return -1;
    }
    
    static class Node {
    	
    	
    	int value;
    	Node pre;
    	Node next;
		public Node(int value) {
			super();
			this.value = value;
		}
		
    }
}
