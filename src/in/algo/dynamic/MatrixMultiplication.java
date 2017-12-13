package in.algo.dynamic;

public class MatrixMultiplication {
	
	public static int matrixMultiplicationRecur(int[] p, int start, int end)
	{
		//System.out.println(start+" "+end);
		//Base Case. If it same matrix then the multiplciation effort is zero.
		if(start==end)
			return 0;
		else
		{
			//Get the Min Value by placing bracket at every possible place
			//(ABCD) -> Min[ (A)(BCD), (AB)(CD), (ABC)(D) ] at every step.
			int minVal = Integer.MAX_VALUE;
			for(int i=start;i<end;i++)
			{
				minVal = Math.min(minVal, 
						matrixMultiplicationRecur(p,start,i)+
						matrixMultiplicationRecur(p,i+1,end)+
						(p[start-1]*p[i]*p[end]));
			}
			return minVal;
		}
	}
	
	public static int matrixMultiplicationDP(int[] p)
	{
		int[][] dp = new int[p.length-1][p.length-1];
		for(int i=p.length-2;i>=0;i--)
		{
			for(int j=i;j<p.length-1;j++)
			{
				if(i==j)
				{
					dp[i][j] = 0;
				}
				else
				{
					int val = Integer.MAX_VALUE;
					for(int k=i;k<j;k++)
					{
						val = Math.min(val, dp[i][k]+dp[k+1][j]+(p[i]*p[k+1]*p[j+1]));
					}
					dp[i][j] = val;
				}
			}
		}
		//Printing the DP Array
		for(int i=0;i<p.length-1;i++)
		{
			for(int j=0;j<p.length-1;j++)
			{
				System.out.print(dp[i][j]+"\t");
			}
			System.out.println();
		}
		
		return dp[0][p.length-2];
	}
	
	public static void main(String[] args)
	{
		//Total 4 Matrices are there.
		//Starting at index 1. Array 1 size is (20*10).
		//p[i-1]*p[i] is the size of a matrix where i ranges from 1 to p.length-1
		int[] p = {10,20,30,40,30};
		System.out.println(matrixMultiplicationRecur(p,1,p.length-1));
		System.out.println(matrixMultiplicationDP(p));
	}
	
}
