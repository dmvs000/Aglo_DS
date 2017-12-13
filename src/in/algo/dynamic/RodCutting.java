package in.algo.dynamic;

public class RodCutting {

	//Naive Recursive Code
	public static int RodCuttingRecur(int[] price, int n)
	{
		if(n==0)
			return 0;
		int val = price[n-1];
		int end = n/2;
		for(int i=1;i<=end;i++)
			val = Math.max(val, RodCuttingRecur(price,i)+RodCuttingRecur(price,n-i));
		return val;
	}
	
	//DP Implementation
	public static int RodCuttingDP(int[] price, int n)
	{
		int[] dp = new int[n];
		for(int i=1;i<=n;i++)
		{
			dp[i-1] = price[i-1];
			for(int j=1;j<=(i/2);j++)
			{
				dp[i-1] = Math.max(dp[i-1], dp[j-1] + dp[i-j-1]);
				//System.out.println(j+" "+(n-j));
			}
			//Printing the dp Array after every Iteration
			for(int k=0;k<n;k++)
				System.out.print(dp[k]+"\t");
			System.out.println();
		}
		return dp[n-1];
	}
	
	public static void main(String[] args)
	{
		int[] arr = {1,5,8,9,10,17,17,20};
		System.out.println(RodCuttingRecur(arr,8));
		System.out.println(RodCuttingDP(arr,8));
	}
	
}
