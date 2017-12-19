package in.algo.dynamic;

public class MinimumPositivePointsToReachDest {

	static int rows = 0;	//Number of Rows
	static int cols = 0;	//Number of Cols
	
	//Naive Recursive
	public static int MPP_Recur(int[][] arr, int i, int j)
	{
		//If out of bounds, return INF as it can't go.
		if(i<0||i>=rows||j<0||j>=cols)
			return Integer.MAX_VALUE;
		//If reached dest, check if value is more than 0. We need just mimimum of 1 to get out of that cell.
		//Else, we need to compensate for any negative value.
		if(i==rows-1&&j==cols-1)
		{
			if(arr[i][j]>0)
				return 1;
			return Math.abs(arr[i][j])+1;
		}
		int down = Math.max(MPP_Recur(arr,i+1,j)-arr[i][j],1);		//Min Value required in (i,j) here if we take down path.
		int right = Math.max(MPP_Recur(arr,i,j+1)-arr[i][j],1);		//Min value required in (i,j) here if we take the right path.
 		return Math.min(down, right);			//Return the minimum of two.
	}
	
	//DP Implementation
	public static int MPP_DP(int[][] arr)
	{
		int[][] dp = new int[rows][cols];
		//Set the last one based on its value.
		dp[rows-1][cols-1] = 1;
		if(arr[rows-1][cols-1]<=0)
			dp[rows-1][cols-1] = Math.abs(arr[rows-1][cols-1])+1;
		//Traverse the rows and cols so as to make the remaining computation easy.
		//Cal every row of last col based on down index.
		for(int i=rows-2;i>=0;i--)
			dp[i][cols-1] = Math.max(dp[i+1][cols-1]-arr[i][cols-1],1);
		//Cal every col of last row based on right index.
		for(int j=cols-2;j>=0;j--)
			dp[rows-1][j] = Math.max(dp[rows-1][j+1]-arr[rows-1][j], 1);
		//Cal all by taking the value of right and down index and check the min required to get out of the every (i,j) index
		//excluding the last row and col as they are already calculated.
		for(int i=rows-2;i>=0;i--)
		{
			for(int j=cols-2;j>=0;j--)
			{
				int Min = Math.min(dp[i+1][j], dp[i][j+1]);
				dp[i][j] = Math.max(Min - arr[i][j], 1);
			}
		}
		//Print DP table
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				System.out.print(dp[i][j]+"\t");
			}
			System.out.println();
		}
		//Return min value required to reach destination starting at 0,0;
		return dp[0][0];
	}
	
	public static void main(String[] args)
	{
		//The matrix
		int[][] arr = {
				{-2,-3,3},
				{-5,-10,1},
				{10,30,-5}
		};
		rows = arr.length;
		cols = arr[0].length;
		System.out.println(MPP_Recur(arr,0,0));
		System.out.println(MPP_DP(arr));
	}
	
}
