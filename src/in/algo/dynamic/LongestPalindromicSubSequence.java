package in.algo.dynamic;

public class LongestPalindromicSubSequence {
	
	//Naive Implementation.
	public static int LPS_Recur(String str, int index1, int index2)
	{
		//If first and last index equal, only one char so a palindrome.
		if(index1==index2)
			return 1;
		//If index1 > index2, no string possible and the LPS for that would be zero.
		if((index1>index2))
			return 0;
		//If the first and last chars match and indexes are not same, then this is a part of palindromic subsequence.
		//So, check for index+1, index2-1. Shrink and check recursively.
		if(str.charAt(index1)==str.charAt(index2))
			return LPS_Recur(str,index1+1,index2-1)+2;
		else
		//If the first and last don't match, check recursively for different combinations.
			return Math.max(LPS_Recur(str,index1,index2-1), LPS_Recur(str,index1+1,index2));
	}
	
	public static int LPS_DP(String str)
	{
		int[][] dp = new int[str.length()][str.length()];
		for(int i=str.length()-1;i>=0;i--)
		{
			for(int j=i;j<str.length();j++)
			{
				//If only one char is present, then it is palindromic subsequence of 1.
				if(i==j)
				{
					dp[i][j]=1;
				}
				else
				{
					//If chars are same get the value from shrinked substring.
					if(str.charAt(i)==str.charAt(j))
						dp[i][j] = dp[i+1][j-1] + 2;
					else
					//Check for different subString combinations.
						dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
				}
			}
		}
		for(int i=0;i<str.length();i++)
		{
			for(int j=0;j<str.length();j++)
			{
				System.out.print(dp[i][j]+"\t");
			}
			System.out.println();
		}
		return dp[0][str.length()-1];
	}
	
	public static void main(String[] args)
	{
		String str = "geeksforgeeks";
		System.out.println(LPS_Recur(str,0,str.length()-1));
		System.out.println(LPS_DP(str));
	}
	
}
