package in.algo.dynamic;

/*
 * https://www.geeksforgeeks.org/find-length-of-the-longest-consecutive-path-in-a-character-matrix/
 */
public class LongestConsecutivePathInCharMatrix {

	static int rows = 0;
	static int cols = 0;
	static int[][] dp;
	static boolean[][] traversed;
	
	public static int LCP_Recur(char[][] arr, int i, int j)
	{
		dp = new int[rows][cols];
		//Make all DP array to -1 initially
		for(int x=0;x<rows;x++)
			for(int y=0;y<cols;y++)
				dp[x][y] = -1;
		traversed = new boolean[rows][cols];
		int curVal = 0;
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i-1,j-1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i-1,j,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i-1,j+1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i,j-1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i,j+1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i+1,j-1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i+1,j,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i+1,j+1,arr[i][j]));
		dp[i][j] = curVal+1;
		//DP Print
		for(int x=0;x<rows;x++)
		{
			for(int y=0;y<cols;y++)
				System.out.print(dp[x][y]+"\t");
			System.out.println();
		}
		//printPath(dp,arr,i,j);
		return curVal+1;
	}
	
	public static int LCP_Recur_Aux(char[][] arr, int i, int j, char prev)
	{
		//System.out.println("Prev : "+prev);
		if(i<0||i>=rows||j<0||j>=cols)
			return 0;
		if(!validate(arr[i][j],prev))
			return 0;
		if(dp[i][j]!=-1)
			return dp[i][j];
		int curVal = 0;
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i-1,j-1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i-1,j,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i-1,j+1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i,j-1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i,j+1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i+1,j-1,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i+1,j,arr[i][j]));
		curVal=Math.max(curVal, LCP_Recur_Aux(arr,i+1,j+1,arr[i][j]));
		dp[i][j] = curVal+1;
		return curVal+1;
	}
	
	public static boolean validate(char cur, char prev)
	{
		if(cur==prev+1)
			return true;
		return false;
	}
	
	public static void main(String[] args)
	{
		char[][] arr = {
				{'a','c','d'},
				{'h','g','e'},
				{'i','g','f'}
		};
		char[][] arr1 = {
				{'b','e','f'},
				{'h','d','a'},
				{'i','c','a'}
		};
		rows = arr.length;
		cols = arr[0].length;
		System.out.println(LCP_Recur(arr,1,2));
	}
	
}
