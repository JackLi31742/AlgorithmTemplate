package hash.bf;

/**
 * 555. 计数型布隆过滤器
 * add(string). 往布隆过滤器中加入一个字符串.
contains(string). 检查一个字符串是否在布隆过滤器中.
remove(string). 从布隆计数器中删除一个字符串.
 *
 */
public class CountingBloomFilter {

	
	int k;
	
	int capacity;
	
	HashFunction[] hashFunctions;
	
	int[] bits;
	/*
	    * @param k: An integer
	    */public CountingBloomFilter(int k) {
	        // do intialization if necessary
	    	
	    	this.k=k;
	    	this.capacity=1000000+k;
	    	
	    	hashFunctions=new HashFunction[k];
	    	
	    	for (int i = 0; i < k; i++) {
				hashFunctions[i]=new HashFunction(31+2*i, this.capacity);
			}
	    	
	    	bits=new int[this.capacity];
	    }

	    /*
	     * @param word: A string
	     * @return: nothing
	     */
	    public void add(String word) {
	        // write your code here
	    	
	    	for (int i = 0; i < k; i++) {
				
	    		int index=hashFunctions[i].hash(word);
	    		bits[index]++;
			}
	    }

	    /*
	     * @param word: A string
	     * @return: nothing
	     */
	    public void remove(String word) {
	        // write your code here
	    	for (int i = 0; i < k; i++) {
				
	    		int index=hashFunctions[i].hash(word);
	    		bits[index]--;
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
	    		if (bits[index]<=0) {
					return false;
				}
			}
	    	
	    	return true;
	    }
}
