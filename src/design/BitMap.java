package design;

import java.util.Arrays;
import java.util.BitSet;

public class BitMap { 
	// Java中char类型占16bit，也即是2个字节
	private int[] bytes;
	private int nbits;

	public BitMap(int nbits) {
		this.nbits = nbits;
		this.bytes = new int[nbits / 16 + 1];
	}

	public void set(int k) {
		if (k > nbits)
			return;
		int byteIndex = k / 16;
		int bitIndex = k % 16;
		bytes[byteIndex] |= (1 << bitIndex);
	}

	public boolean get(int k) {
		if (k > nbits)
			return false;
		int byteIndex = k / 16;
		int bitIndex = k % 16;
		return (bytes[byteIndex] & (1 << bitIndex)) != 0;
	}

	public static void main(String[] args) {
		int a = 10;
		System.out.println(1 << a);
		
		BitMap bitMap=new BitMap(128);
		bitMap.set(64);
		System.out.println(Arrays.toString(bitMap.bytes));
		
		BitSet bitSet=new BitSet(64);
		
		bitSet.set(16);
		
		System.out.println(bitSet.get(20));
	}
}