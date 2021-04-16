package hash;

public class HashFunction {

	public static void main(String[] args) {
		
		String aString="abcd";
		char[] key= aString.toCharArray();
		
		hashCode(key, 100000000);
	}
	/**
	 * 128. 哈希函数

	 * @param key
	 * @param HASH_SIZE
	 * @return
	 */
	public static int hashCode(char[] key, int HASH_SIZE) {
        // write your code here
		long result=0;
		for (int i = 0; i < key.length; i++) {
			result=(result*33+(int)key[i])%HASH_SIZE;
		}
		return (int)result;
    }
}
