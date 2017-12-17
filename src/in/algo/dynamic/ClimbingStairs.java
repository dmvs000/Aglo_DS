package in.algo.dynamic;

public class ClimbingStairs {
	
	public static int countSteps(int s, int m)
	{
		if(m==0)
			return -1;
		if(s==0||s==1||m==1)
			return 1;
		int[] dp = new int[s+1];	//Stores the number of steps for ith position
		int[] sum = new int[s+1];	//Stores the total steps uptil ith position
		int cur_m = 2;
		dp[0] = dp[1] = 1;
		sum[0] = 1;
		sum[1] = 2;
		for(int i=2;i<=s;i++)
		{
			if(cur_m<=m)
			{
				dp[i] = sum[i-1];
				cur_m++;
			}
			else
			{
				dp[i] = sum[i-1] - sum[i-m-1];
			}
			sum[i] = dp[i] + sum[i-1];
		}
		for(int i=0;i<=s;i++)
			System.out.print(dp[i]+"\t");
		System.out.println();
		for(int i=0;i<=s;i++)
			System.out.print(sum[i]+"\t");
		System.out.println();
		return dp[s];
	}
	
	public static void main(String[] args)
	{
		System.out.println(countSteps(5,3));
	}
	
}
