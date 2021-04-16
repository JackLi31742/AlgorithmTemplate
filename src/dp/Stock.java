package dp;

public class Stock {

	public static void main(String[] args) {
		int[]prices= {1,3,2,8,4,9};
		
		System.out.println(maxProfit(prices,2));;
	}
	/**
	 * 121. 买卖股票的最佳时机
	 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

注意：你不能在买入股票前卖出股票。

	 * @param prices
	 * @return
	 */
	
	//暴力会超时
	public int maxProfit(int[] prices) {
		int max=0;
		for (int i = 0; i < prices.length; i++) {
			for (int j = i+1; j < prices.length; j++) {
				int profit=prices[j]-prices[i];
				if (profit>max) {
					max=profit;
				}
			}
		}
		return max;
    }
	/**
	 * dp
	 * 暴力破解是以i天作为买入的时间，dp是以i天作为卖出的时间，从0到i
	 * f(i)=Max{f(i-1),p[i]-minPrice}
	 * @param prices
	 * @return
	 */
	public int maxProfit12(int[] prices) {
		if (prices==null||prices.length==0) {
			return 0;
		}
		int[]dp=new int[prices.length+1];
		dp[0]=0;
		int min=prices[0];
		for (int i = 1; i < dp.length; i++) {
			//一次for循环找到最小的min
			min=Math.min(min, prices[i-1]);
			//判断dp[i-1]和这天的价格减去min，哪个更大
			dp[i]=Math.max(dp[i-1], prices[i-1]-min);
		}
		
		return dp[prices.length];
	}
	
	/**
	 * 122. 买卖股票的最佳时机 II
	 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

	注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

		
	 * @param prices
	 * @return
	 */
	
	
	/**
	 * 要想获得最大的股票收益，那么贪心算法，要买在低点，卖在高点
	 * 而且还要每次的低点购买，低点之后的高点卖出，那么比在最低点买，最高点卖要收益大
	 * @param prices
	 * @return
	 */
	public static int maxProfit22(int[] prices) {
		if (prices==null||prices.length<2) {
			return 0;
		}
		int max=0;
		int i=1;
		while(i<prices.length) {
			while(i<prices.length&&prices[i]<prices[i-1]) {
				i++;
			}
			int low=prices[i-1];
			while(i<prices.length&&prices[i]>prices[i-1]) {
				i++;
			}
			int high=prices[i-1];
			max+=high-low;
			i++;
		}
		
		return max;
	}
	
	/**
	 * 贪心算法，贪心的本质，就是做出当下认为最优的解，对于买卖股票，当下最优就是只要第二天的股票价格比今天 的高，就选择卖出
	 * 针对本题目，在股价上升的情况下，连续的买卖，最后得到的和就是从谷底到波峰的差
	 * 这里只是模拟连续买卖，实际上并不是这么操作的，只是因为数学上是相等的
	 * @param prices
	 * @return
	 */
	public static int maxProfit23(int[] prices) {
		int max=0;
		if (prices==null||prices.length<2) {
			return max;
		}
		
		for (int i = 1; i < prices.length; i++) {
			if (prices[i]>prices[i-1]) {
				max+=prices[i]-prices[i-1];
			}
		}
		return max;
	}
	
	
	/**
	 * dp中的状态，是今天买还是卖，具体到dp数组中就是持有股票还是现金
	 * 因为不限制交易次数，除了最后一天，每一天的状态可能不变化，也可能转移；
	 * dp[i][j] 代表i天时持有的最大收益
	 * i代表i天，j代表这天是持有股票0还是持有现金1
	 * @param prices
	 * @return
	 */
	public static int maxProfit24(int[] prices) {
		if (prices==null||prices.length<2) {
			return 0;
		}
		int[][]dp=new int[prices.length][2];
		
		//第一天持有了股票，就是买入了股票
		dp[0][0]=-prices[0];
		//第一天什么都不做
		dp[0][1]=0;
		
		
		for (int i = 1; i < dp.length; i++) {
			dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);
			dp[i][1]=Math.max(dp[i-1][1], dp[i-1][0]+prices[i]);
		}
		
		return dp[prices.length-1][1];
	}
	
	/**
	 * 123. 买卖股票的最佳时机 III
	 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {

    }
	
	/**
	 * 188. 买卖股票的最佳时机 IV
	 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
	 * @param k
	 * @param prices
	 * @return
	 */
	public int maxProfit(int k, int[] prices) {

    }
	
	
	/**
	 * 309. 最佳买卖股票时机含冷冻期
	 * 在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
		
	 * dp[i][j][k] 代表i天时持有的最大收益
	 * i代表i天，
	 * j代表是否为冷冻期，0代表是，1代表不是
	 * k代表这天是持有股票0还是持有现金1
	 * @param prices
	 * @return
	 */
	public static int maxProfit3(int[] prices) {
		if (prices==null||prices.length<2) {
			return 0;
		}
		int[][][]dp=new int[prices.length][2][2];
		
		//第一天持有了股票，就是买入了股票
		dp[0][1][0]=-prices[0];
		//第一天什么都不做
		dp[0][1][1]=0;
		//第一天没有冷冻期
		dp[0][0][0]=0;
		dp[0][0][1]=0;
		for (int i = 1; i < dp.length; i++) {
			
			//i天是冷冻期持有现金
			dp[i][0][1]=dp[i-1][1][1];
			//i天不是冷冻期持有股票
			dp[i][1][0]=Math.max(dp[i-1][1][0],dp[i-1][0][1]-prices[i]);
			//i天不是冷冻期持有现金
			dp[i][1][1]=Math.max(Math.max(dp[i-1][0][1],dp[i-1][1][1]),dp[i-1][1][0]+prices[i]);
		}
		
		return Math.max(dp[prices.length-1][0][1],dp[prices.length-1][1][1]);
    }
	
	/**
	 * dp[i][j] 代表i天时持有的最大收益
	 * i代表i天，
	 
	 * j代表这天是持有股票0还是持有现金1，还是冷冻期持有现金2
	 * 
	 * 本方法相比上边的，优化了空间复杂度
	 * @param prices
	 * @return
	 */
	public static int maxProfit32(int[] prices) {
		if (prices==null||prices.length<2) {
			return 0;
		}
		int[][]dp=new int[prices.length][3];
		
		//第一天持有了股票，就是买入了股票
		dp[0][0]=-prices[0];
		//第一天什么都不做
		dp[0][1]=0;
		dp[0][2]=0;
		for (int i = 1; i < dp.length; i++) {
			//由于有冷冻期，只能是冷冻期-prices[i]
			dp[i][0]=Math.max(dp[i-1][0], dp[i-1][2]-prices[i]);
			dp[i][1]=Math.max(Math.max(dp[i-1][1],dp[i-1][2]), dp[i-1][0]+prices[i]);
			dp[i][2]=dp[i-1][1];
			
		}
		
		return Math.max(dp[prices.length-1][1],dp[prices.length-1][2]);
	}
	
	/**
	 * 714. 买卖股票的最佳时机含手续费
	 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
	 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
	 * @param prices
	 * @param fee
	 * @return
	 */
	public static int maxProfit(int[] prices, int fee) {
		if (prices==null||prices.length<2) {
			return 0;
		}
		int[][]dp=new int[prices.length][2];
		
		//第一天持有了股票，就是买入了股票
		dp[0][0]=-prices[0];
		//第一天什么都不做
		dp[0][1]=0;
		
		
		for (int i = 1; i < dp.length; i++) {
			dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);
			dp[i][1]=Math.max(dp[i-1][1], dp[i-1][0]+prices[i]-fee);
		}
		
		return dp[prices.length-1][1];
    }
}
