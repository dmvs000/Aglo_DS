package in.algo.dynamic;

import java.util.Arrays;

/*
 * Can perform only one transaction at a time
 * Buy-Sell Then Buy-Sell
 * Can transact at most K times
 * Find maxProfit by transacting at most K times
 */
public class MaximumProfitBuySellKTransaction {

	/*
	 * dp[i][k] denotes the MaxProfit attained after ith day and performing k
	 * transaction 1 transaction is Buy - Sell If we just Buy, transaction is
	 * still zero If we Sell (that means we performed Buy) before, now
	 * transaction is one Action Performed on the ith day either would be BUY,
	 * SELL, REST According to the given constraint, we could only buy and sell
	 * and then buy and sell. So, at any given time, we can only hold one stock
	 * or zero stock. nothing more and nothing less
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-
	 * transaction-fee/discuss/108870/
	 */
	public static int findMaxProfit(int[] prices, int k) {
		int n = prices.length;
		int[][][] dp = new int[n + 1][k + 1][2];
		/*
		 * Initially, the days are zero, no stock data given, holding 0 stock
		 * -MaxProfit = 0 and holding 1 is impossilbe and hence -INF
		 */
		for (int i = 0; i <= k; i++) {
			dp[0][i][0] = 0;
			dp[0][i][1] = Integer.MIN_VALUE;
		}
		/*
		 * Initially, the transactions are zero, that mean, holding 0 stock
		 * -MaxProfit = 0 and holding 1 is impossilbe and hence -INF
		 */
		for (int i = 0; i <= n; i++) {
			dp[i][0][0] = 0;
			dp[i][0][1] = Integer.MIN_VALUE;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				/*
				 * For holding zero now, we may either REST or SELL what we
				 * have. If we sell, the current would be added to what we have
				 * before
				 */
				dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i - 1]);
				/*
				 * For holding 1 now, we may BUY or REST. If we Buy, the current
				 * price would be subtracted from what we have before
				 */
				dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i - 1]);
			}
		}
		// Print the DP Array
		/*
		 * for (int i = 0; i <= n; i++) { for (int j = 0; j <= k; j++) {
		 * System.out.print("[" + dp[i][j][0] + "," + dp[i][j][1] + "]\t\t"); }
		 * System.out.println(); }
		 */
		return Math.max(dp[n][k][0], dp[n][k][1]);
	}

	// Space Optimized Version
	public static int findMaxProfit_SO(int[] prices, int k) {
		int n = prices.length;
		int[][] prev_dp = new int[k + 1][2];
		for (int i = 0; i <= k; i++) {
			prev_dp[i][0] = 0;
			prev_dp[i][1] = Integer.MIN_VALUE;
		}
		int[][] cur_dp = new int[k + 1][2];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				if (j == 0) {
					cur_dp[j][0] = 0;
					cur_dp[j][1] = Integer.MIN_VALUE;
				} else {
					cur_dp[j][0] = Math.max(prev_dp[j][0], prev_dp[j][1] + prices[i - 1]);
					cur_dp[j][1] = Math.max(prev_dp[j][1], prev_dp[j - 1][0] - prices[i - 1]);
				}
			}
			// prev_dp = Arrays.copyOf(cur_dp, cur_dp.length);
			for (int j = 0; j <= k; j++) {
				prev_dp[j][0] = cur_dp[j][0];
				prev_dp[j][1] = cur_dp[j][1];
			}
		}
		int retVal = Math.max(prev_dp[k][0], prev_dp[k][1]);
		if (retVal == Integer.MIN_VALUE)
			return 0;
		return retVal;
	}

	public static void main(String[] args) {
		int[] arr = { 100, 30, 15, 10, 8, 25, 80 };
		int k = 3;
		System.out.println(findMaxProfit_SO(arr, k));
	}

}
