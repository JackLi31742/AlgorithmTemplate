package design;

import java.util.LinkedHashMap;

/**
 * 146. LRU缓存机制
 * 
 * 	不使用继承

 */
public class LRUCache2 {

	
	public static void main(String[] args) {

		
	}
	
	LinkedHashMap<Integer, Integer> linkedHashMap;
	int capacity;
	public LRUCache2(int capacity) {
		linkedHashMap=new LinkedHashMap<Integer, Integer>(capacity,0.75f,true);
		this.capacity=capacity;
    }
    
    public int get(int key) {
    	if (!linkedHashMap.containsKey(key)) {
			return -1;
		}
    	Integer result=linkedHashMap.get(key);
    	return result;
    }
    //此方法是淘汰策略的核心，但是由于不是继承，这里将不会自动调用
//    public boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
//        return linkedHashMap.size()>this.capacity;
//    }
    
    public void put(int key, int value) {
    	
    	linkedHashMap.put(key, value);
    	if (linkedHashMap.size()>capacity) {
    		linkedHashMap.remove(linkedHashMap.entrySet().iterator().next().getKey());
			
		}
    }
    
    
    
}