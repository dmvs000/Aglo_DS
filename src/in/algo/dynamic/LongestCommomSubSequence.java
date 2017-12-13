package in.algo.dynamic;

public class LongestCommomSubSequence {
	
	//Naive Recursive Implementation
	public static int LCS(String str1, String str2, int i1, int i2)
	{
		if(i1<0||i2<0)
			return 0;
		if(str1.charAt(i1)==str2.charAt(i2))
		{
			return LCS(str1,str2,i1-1,i2-1)+1;
		}
		else
		{
			return Math.max(LCS(str1,str2,i1-1,i2), LCS(str1,str2,i1,i2-1));
		}
	}
	
	//DP Implementation
	public static int LCS_DP(String str1, String str2)
	{
		if(str1.length()==0||str2.length()==0)
			return 0;
		int[][] dp = new int[str1.length()+1][str2.length()+1];
		for(int i=0;i<=str1.length();i++)
			dp[i][0] = 0;
		for(int i=0;i<=str2.length();i++)
			dp[0][i] = 0;
		for(int i=1;i<=str1.length();i++)
		{
			for(int j=1;j<=str2.length();j++)
			{
				if(str1.charAt(i-1)==str2.charAt(j-1))
					dp[i][j] = dp[i-1][j-1] + 1;
				else
				{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		//Printing the DP Array
		for(int i=0;i<=str1.length();i++)
		{
			for(int j=0;j<=str2.length();j++)
			{
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		return dp[str1.length()][str2.length()];
	}
	
	public static void main(String[] args)
	{
		//String str1 = "abcdgh";
		//String str2 = "aedfhr";
		String str1 = "aggtab";
		String str2 = "gxtxayb";
		System.out.println(LCS(str1,str2,str1.length()-1,str2.length()-1));
		System.out.println(LCS_DP(str1,str2));
	}
	
}
