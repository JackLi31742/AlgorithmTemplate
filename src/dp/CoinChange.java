package dp;

import java.util.Arrays;

public class CoinChange {
	
	public static void main(String[] args) {
		int[]coins= {186,419,83,408};
		
		System.out.println(coinChange2(coins, 6249));;
	}

	/**
	 * 322. 零钱兑换
	 * 给定不同面额的硬币 coins 和一个总金额 amount。
	 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
	 * 如果没有任何一种硬币组合能组成总金额，返回 -1。

		说明:
		你可以认为每种硬币的数量是无限的。
	 * @param coins
	 * @param amount
	 * @return
	 */
	
	/**
	 * 使用dp
	 * F(i) 为组成金额 i 所需最少的硬币数量，dp数组的长度是amount+1，
	 * F(i)=F(i-ci)+1
	 * 也就是i代表对应的钱，ci对应第i个硬币的面值，由于要硬币数量最少，
	 * 所以 F(i) 为前面能转移过来的状态的最小值加上枚举的硬币数量 1 。
	 * @param coins
	 * @param amount
	 * @return
	 */
	public static int coinChange2(int[] coins, int amount) {
		
		int max=amount+1;
		int[]dp=new int[max];
		//为了最后好比较，返回-1的时候
		Arrays.fill(dp, max);
		dp[0]=0;
		
		for (int i = 1; i < dp.length; i++) {
			int min=max;
			for (int j = 0; j < coins.length; j++) {
				//比i大，就没必要，因为不可能是由比i大的值组合得到i
				if (i>=coins[j]) {
					
					int count=dp[i-coins[j]]+1;
					if (count<min) {
						min=count;
					}
				}
			}
			
			dp[i]=min;
		}
		
		return dp[amount]>amount?-1:dp[amount];
	}
	
}
