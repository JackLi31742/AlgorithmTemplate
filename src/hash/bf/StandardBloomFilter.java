package hash.bf;

/**
 * 556. 标准型布隆过滤器
 *	实现一个标准型布隆过滤器, 支持一下方法:

StandardBloomFilter(k) 构造方法, 你需要创建k个hash方法
add(string) 往布隆过滤器中加入一个字符串.
contains(string) 检查字符串是否在过滤器中
 */
public class StandardBloomFilter {

		HashFunction[] hashFunctions;
		
		boolean[] bits;
		
		int k;
		
		int capacity;
	 /*
	    * @param k: An integer
	    */public StandardBloomFilter(int k) {
	        // do intialization if necessary
	    	this.k=k;
	    	
	    	this.capacity=1000000+k;
	    	
	    	hashFunctions=new HashFunction[k];
	    	for (int i = 0; i < hashFunctions.length; i++) {
				hashFunctions[i]=new HashFunction(31+2*i, this.capacity);
			}
	    	
	    	bits=new boolean[this.capacity];
	    }

	    /*
	     * @param word: A string
	     * @return: nothing
	     */
	    public void add(String word) {
	        // write your code here
	    	
	    	for (int i = 0; i < k; i++) {
				
	    		int index=hashFunctions[i].hash(word);
	    		
	    		bits[index]=true;
			}
	    }

	    /*
	     * @param word: A string
	     * @return: True if contains word
	     */
	    public boolean contains(String word) {
	        // write your code here
	    	
	    	for (int i = 0; i < k; i++) {
	    		
	    		int index=hashFunctions[i].hash(word);
	    		
	    		if (!bits[index]) {
					return false;
				}
	    	}
	    	
	    	return true;
	    }
}
