package in.algo.dynamic;

public class MinimumSquaresSumEqualsAnotherNumber {
	
	public static int getMinSquares(int n)
	{
		if(n==1)
			return 1;
		if(Math.sqrt(n)%2==0)
			return 1;
		int min = Integer.MAX_VALUE;
		for(int i=1;i<=n;i++)
		{
			if(i*i>n)
				break;
			min = Math.min(getMinSquares(n-(i*i)),min);
		}
		return min+1;
	}
	
	public static int getMinSquares_DP(int n)
	{
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		for(int i=2;i<=n;i++)
		{
			dp[i] = Integer.MAX_VALUE;
			for(int j=1;j<=i;j++)
			{
				if(j*j>i)
					break;
				dp[i] = Math.min(dp[i], 1 + dp[i-(j*j)]);
			}
		}
		//Print the DP
		for(int i=0;i<=n;i++)
			System.out.print(dp[i]+"\t");
		System.out.println();
		return dp[n];
	}
	
	public static void main(String[] args)
	{
		//System.out.println(getMinSquares(600));
		System.out.println(getMinSquares_DP(625));
	}
	
}
