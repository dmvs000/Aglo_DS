package in.algo.dynamic;

/*
 * https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 */
public class MinimumNumberOfCoins {
	
	public static int minCoins(int[] coins, int S)
	{
		int C = coins.length;
		int[][] dp = new int[C+1][S+1];
		for(int i=0;i<=C;i++)
		{
			for(int j=0;j<=S;j++)
			{
				if(i==0&&j==0)
				{
					dp[i][j] = 0;
					continue;
				}
				if(i==0)
				{
					dp[i][j] = Integer.MAX_VALUE;
					continue;
				}
				if(j==0)
				{
					dp[i][j] = 0;
					continue;
				}
				int pick = Integer.MAX_VALUE;
				if((i-coins[i-1]>=0)&&(dp[i][i-coins[i-1]]!=Integer.MAX_VALUE))
					pick = dp[i][i-coins[i-1]] + 1;
				int no_pick = dp[i-1][j];
				dp[i][j] = Math.min(pick, no_pick);
			}
		}
		return dp[C][S];
	}
	
	public static void main(String[] args)
	{
		int[] coins = {9,6,5,1};
		int Sum = 11;
		System.out.println(minCoins(coins,Sum));
	}
	
}
