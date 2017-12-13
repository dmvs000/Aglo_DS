package in.algo.dynamic;

import java.util.Arrays;

public class MinCostPath {
	
	static int m = 0; //Rows
	static int n = 0; //Columns
	
	
	//Naive Implementation.
	public static int minCostPathRecur(int[][] arr, int i, int j, int sum)
	{
		if(i>=m||j>=n)
			return Integer.MAX_VALUE;
		if(i==m-1&&j==n-1)
			return sum+arr[m-1][n-1];
		int min = Integer.MAX_VALUE;
		min = Math.min(min, minCostPathRecur(arr,i+1,j,sum+arr[i][j]));
		min = Math.min(min, minCostPathRecur(arr,i,j+1,sum+arr[i][j]));
		min = Math.min(min, minCostPathRecur(arr,i+1,j+1,sum+arr[i][j]));
		return min;
	}
	
	//Dynamic Programming. Storing the results of the sub Problem
	public static int minCostPath_DP(int[][] arr)
	{
		int[][] dp = new int[m][n];
		for(int i=0;i<m;i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		dp[m-1][n-1] = arr[m-1][n-1];
		for(int i=n-1;i>=0;i--)
		{
			for(int j=m-1;j>=0;j--)
			{
				if(i-1>=0&&j-1>=0)	//Check for Bounds
				{
					dp[i-1][j-1] = Math.min(dp[i-1][j-1], dp[i][j]+arr[i-1][j-1]);
					dp[i-1][j] = Math.min(dp[i-1][j], dp[i][j]+arr[i-1][j]);
					dp[i][j-1] = Math.min(dp[i][j-1], dp[i][j]+arr[i][j-1]);
				}
				else if(i-1>=0)		//Check if only i-1 is valid
				{
					dp[i-1][j] = Math.min(dp[i-1][j], dp[i][j]+arr[i-1][j]);
				}
				else if(j-1>=0)     //Check if only j-1 is valid
				{
					dp[i][j-1] = Math.min(dp[i][j-1], dp[i][j]+arr[i][j-1]);
				}
			}
		}
		
		//Printing the 2 D DP Array
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		return dp[0][0];
	}
	
	public static void main(String[] args)
	{
		int[][] arr = {	{1,2,3},
						{4,8,2},
						{1,5,3} };
		m = 3;
		n = 3;
		System.out.println(minCostPathRecur(arr,0,0,0));
		System.out.println(minCostPath_DP(arr));
	}
	
}
