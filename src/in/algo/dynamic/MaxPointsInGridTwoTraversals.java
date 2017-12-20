package in.algo.dynamic;

public class MaxPointsInGridTwoTraversals {
	
	
	static int rows = 0;
	static int cols = 0;
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;
	static int[][][] dp;
	
	public static int MaxPoints_Recur(int[][] arr, int x, int y1, int y2)
	{
		if(x<0||x>=rows||y1<0||y1>=cols||y2<0||y2>=cols)
			return MIN;
		if(x==rows-1&&y1==0&&y2==cols-1)
		{
			dp[x][y1][y2] =  arr[x][y1] + arr[x][y2];
			return dp[x][y1][y2];
		}
		if(dp[x][y1][y2]!=-1)
			return dp[x][y1][y2];
		int curScore = MIN;
		curScore = Math.max(curScore,MaxPoints_Recur(arr,x+1,y1-1,y2));
		curScore = Math.max(curScore,MaxPoints_Recur(arr,x+1,y1-1,y2-1));
		curScore = Math.max(curScore,MaxPoints_Recur(arr,x+1,y1-1,y2+1));
		curScore = Math.max(curScore,MaxPoints_Recur(arr,x+1,y1,y2));  
		curScore = Math.max(curScore,MaxPoints_Recur(arr,x+1,y1,y2-1));
		curScore = Math.max(curScore,MaxPoints_Recur(arr,x+1,y1,y2+1));
		curScore = Math.max(curScore,MaxPoints_Recur(arr,x+1,y1+1,y2));  
		curScore = Math.max(curScore,MaxPoints_Recur(arr,x+1,y1+1,y2-1));
		curScore = Math.max(curScore,MaxPoints_Recur(arr,x+1,y1+1,y2+1));
		if(y1==y2)
			dp[x][y1][y2] = curScore + arr[x][y1];
		else
			dp[x][y1][y2] = curScore + arr[x][y1] + arr[x][y2];
		return dp[x][y1][y2];
	}
	
	public static void main(String[] args)
	{
		int[][] arr = {
				{3,6,8,2},
				{5,2,4,3},
				{1,1,20,10},
				{1,1,20,10},
				{1,1,20,10}
		};
		rows = arr.length;
		cols = arr[0].length;
		dp = new int[rows][cols][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				for(int k=0;k<cols;k++)
					dp[i][j][k] = -1;
		System.out.println(MaxPoints_Recur(arr, 0, 0, cols-1));
	}
	
}
