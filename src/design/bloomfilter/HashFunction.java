package design.bloomfilter;

public class HashFunction {

	int seed;
	int capacity;
	public HashFunction(int seed, int capacity) {
		super();
		this.seed = seed;
		this.capacity = capacity;
	}
	
	
	public int hash(String word) {
		
		char[] arr = word.toCharArray();
		
		long result = 1;
		
		for (int i = 0; i < arr.length; i++) {
			
			result=(result*seed+(int)arr[i])%capacity;
			
		}
		
		return (int)result;
	}
	
	
}
