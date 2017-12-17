package in.algo.dynamic;

public class OptimalBinarySearchTree {

	static int[] freq;
	static int[] sum;
	static int n;
	public static void OBSTree_Recur()
	{
		
	}
	
	public static void OBSTree_DP()
	{
		calSum();
		int[][] dp = new int[n][n];
		for(int i=n-1;i>=0;i--)
		{
			for(int j=i;j<n;j++)
			{
				if(i==j)
				{
					dp[i][j] = freq[i];
				}
				else
				{
					int minVal = Integer.MAX_VALUE;
					for(int k=i;k<=j;k++)
					{
						if(k==j)
						{
							minVal = Math.min(minVal, freq[k] + dp[i][k-1] + getSum(i,k-1));
						}
						else if(k==i)
						{
							minVal = Math.min(minVal, freq[k] + dp[k+1][j] + getSum(k+1,j));
						}
						else
						{
							minVal = Math.min(minVal, freq[k] + dp[i][k-1] + getSum(i,k-1) + dp[k+1][j] + getSum(k+1,j));
						}
						//System.out.println("i "+i+" j "+j+" k "+k+" MV :"+minVal);
					}
					dp[i][j] = minVal;
				}
			}
		}
		//Printing the DP Matrix
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				System.out.print(dp[i][j]+"\t");
			}
			System.out.println();
		}
		
		//Printing the solution.
		//If the trees are added in this way they should form the minimum cost.
		
	}
	
	public static void calSum()
	{
		for(int i=0;i<n;i++)
		{
			if(i==0)
				sum[i] = freq[i];
			else
				sum[i] = sum[i-1] + freq[i];
		}
		//Print After Sum
		/*for(int i=0;i<n;i++)
			System.out.print(sum[i]+"\t");
		System.out.println();*/
	}
	
	public static int getSum(int from_Index, int to_Index)
	{
		if(from_Index==0)
		{
			//System.out.println("Sum of "+from_Index+" to "+to_Index+" : "+sum[to_Index]);
			return sum[to_Index];
		}
		else
		{
			//System.out.println("Sum of "+from_Index+" to "+to_Index+" : "+(sum[to_Index]-sum[from_Index-1]));
			return (sum[to_Index]-sum[from_Index-1]);
		}
	}
	
	public static void main(String[] args)
	{
		freq = new int[5];
		sum = new int[5];
		n = 5;
		freq[0] = 3;
		freq[1] = 2;
		freq[2] = 5;
		freq[3] = 4;
		freq[4] = 1;
		OBSTree_DP();
	}
	
}
