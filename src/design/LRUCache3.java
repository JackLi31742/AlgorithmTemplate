package design;

import java.util.HashMap;

/**
 * 146. LRU缓存机制
 * 
 * 	自己实现linkedhashmap
 * 
 * 

 */
public class LRUCache3 {

	
	public static void main(String[] args) {

		
		
		LRUCache3 cache = new LRUCache3( 2 /* 缓存容量 */ );

		cache.put(2, 1);
		cache.put(1, 1);
//		System.out.println(cache.get(1));;       // 返回  1
		cache.put(2, 3);    // 该操作会使得关键字 2 作废
//		System.out.println(cache.get(2));;       // 返回 -1 (未找到)
		cache.put(4, 1);    // 该操作会使得关键字 1 作废
		;       // 返回 -1 (未找到)
		;       // 返回  3
		; 

		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
//		System.out.println(cache.get(4));
		
	}
	//双链表的头结点
	Node head;
	//双链表的尾节点
	Node tail;
	HashMap<Integer, Node> map;
	int capacity;
	public LRUCache3(int capacity) {
		map=new HashMap<Integer, Node>(capacity);
		this.capacity=capacity;
		head=new Node(-1,-1);
		tail=new Node(-1,-1);
		head.after=tail;
		tail.before=head;
    }
    
    public int get(int key) {

    	return get(key,null,false);
    }
    
    public int get(int key,Integer value,boolean isUpdate) {
    	if (!map.containsKey(key)) {
			return -1;
		}
    	Node result=map.get(key);
    	//也可以删除后再次加入新节点
    	if (isUpdate) {
			result.value=value;
		}
    	//把该节点的前后连起来
    	result.before.after=result.after;
    	result.after.before=result.before;
    	
    	//将该节点放到最后
		
		refreshNode(result);
		
    	return result.value;
    }

    /**
     * 添加数据到缓存稍微有点麻烦，我们需要先看这个数据是否已经在缓存中。
     * 如果已经在其中，需要将其移动到双向链表的尾部；
     * 如果不在其中，还要看缓存有没有满。
     * 如果满了，则将双向链表头部的结点删除，然后再将数据放到链表的尾部；如果没有满，就直接将数据放到链表的尾部
     * @param key
     * @param value
     */
    public void put(int key, int value) {
    	//如果key存在，需要更新值，同时，也要相当于get操作，要重新改节点的位置
    	if (map.containsKey(key)) {
    		
    		get(key,value,true);
		}else {
			
			Node node=new Node(key,value);
			map.put(key, node);
			if (map.size()>capacity) {
				
				map.remove(head.after.key);
				//删除head之后的节点，这个节点是最老的
				head.after=head.after.after;
				head.after.before=head;
			}
			//不管是map.size()是否大于capacity，在操作完map，删除了head之后的节点后，
			//都要把新的节点插入到双链表中
			
			refreshNode(node);
		}
    	
    	
    	
    }
    //将节点放到最后
    public void refreshNode(Node node) {
    	node.after=tail;
		
		tail.before.after=node;
		
		node.before=tail.before;
		
		tail.before=node;
    }
    
    //双链表的节点
    private static class Node{
    	int key;
    	int value;
    	Node before;
    	Node after;
		Node(int key, int value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		
		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + ", before=" + before + ", after=" + after + "]";
		}

    }

    
}