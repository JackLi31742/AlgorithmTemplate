package design;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * lintcode 607. 两数之和 III-数据结构设计
 * 
	 * 设计b并实现一个 TwoSum 类。他需要支持以下操作:add 和 find。
	add -把这个数添加到内部的数据结构。
	find -是否存在任意一对数字之和等于这个值
	
	数据结构选择：
	（1）ArrayList 可以二分查找到add的位置，但是要想排序好，挪动元素是O(n)
	（2）LinkedList add是O(1)，但是查找add的位置是O(n)
	（3）heap 只适合找最值
	（4）BST 最坏情况是O(n)
	（5）平衡BST可以，比如TreeSet，但是不支持重复值
	（6）HashMap，key是值，value是出现次数，解决重复值
 */
public class TwoSum {

	HashMap<Integer, Integer> map;
	
	public TwoSum() {
		map = new HashMap<Integer, Integer>();
	}
	 /**
     * @param number: An integer
     * @return: nothing
     */
    public void add(int number) {
        // write your code here
    	
    	map.put(number, map.getOrDefault(number, 0)+1);
    	
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
    	
    	Set<Entry<Integer,Integer>> entrySet = map.entrySet();
    	
    	for (Entry<Integer, Integer> entry : entrySet) {
			
    		Integer a1 = entry.getKey();
    		
    		if (map.containsKey(value-a1)) {
    			//处理相等的情况
				if (value-a1==a1&&entry.getValue()==1) {
//					Integer count = entry.getValue();
//					if (count==1) {
						continue;
//					}
				}else {
					
					return true;
				}
			}
		}
    	
    	return false;
    }
}
