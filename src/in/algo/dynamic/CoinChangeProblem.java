package in.algo.dynamic;

/*
 * https://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 */
public class CoinChangeProblem {
	
	public static int CCP_Recur(int[] arr, int index, int N)
	{
		//Either, we can pick or don't pick
		if(index<0)
			return 0;
		if(N==0)
			return 1;
		if(N<0)
			return 0;
		int no_pick = CCP_Recur(arr,index-1,N);
		int pick = CCP_Recur(arr,index,N-arr[index]);
		return no_pick + pick;
	}
	
	public static int CCP_DP(int[] arr, int N)
	{
		if(N==0)
			return 1;
		if(arr.length==0)
			return 0;
		int[][] dp = new int[arr.length+1][N+1];
		dp[0][0] = 1;
		for(int i=1;i<=N;i++)
			dp[0][i] = 0;
		for(int i=1;i<=arr.length;i++)
		{
			for(int j=0;j<=N;j++)
			{
				int pick = 0;
				int not_pick = 0;
				if(j-arr[i-1]>=0)
				{
					pick = dp[i][j-arr[i-1]];
				}
				not_pick = dp[i-1][j];
				dp[i][j] = pick + not_pick;
			}
		}
		//DP Table
		for(int i=0;i<=arr.length;i++)
		{
			for(int j=0;j<=N;j++)
			{
				System.out.print(dp[i][j]+"\t");
			}
			System.out.println();
		}
		return dp[arr.length][N];
	}
	
	public static void main(String[] args)
	{
		int[] arr = {2,5,3,6};
		int N = 10;
		System.out.println(CCP_Recur(arr,arr.length-1,N));
		System.out.println(CCP_DP(arr,N));
	}
	
}
