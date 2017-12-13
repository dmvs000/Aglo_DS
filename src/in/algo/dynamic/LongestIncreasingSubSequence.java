package in.algo.dynamic;

public class LongestIncreasingSubSequence {
	
	//Naive Implementation
	public static int LIS_Recur(int[] arr, int index)
	{
		if(index<0)
			return 0;
		else 
		{
			int max_uptil_here = 1;
			for(int i=0;i<index;i++)
			{
				if(arr[index]>arr[i])
				{
					max_uptil_here = Math.max(max_uptil_here, LIS_Recur(arr,i)+1);
				}
			}
			return max_uptil_here;
		}
	}
	
	
	//DP Implementation
	public static int LIS(int[] arr)
	{
		int[] dp = new int[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			int max = 1;
			for(int j=i-1;j>=0;j--)
			{
				if(arr[i]>arr[j])
				{
					max = Math.max(dp[j]+1, max);
				}
			}
			dp[i] = max;
		}
		return dp[arr.length-1];
	}
	
	public static void main(String[] args)
	{
		int[] arr = {10,22,9,33,21,50,41,60,80};
		System.out.println(LIS_Recur(arr,arr.length-1));
		System.out.println(LIS(arr));
	}
	
}
