package in.algo.dynamic;

public class NDigitNumberWithTheirSumS {
	
	public static int ND_Recur(int N, int S)
	{
		//This is first function. Only modification is that first digits should start from 0 to 9 inclusive
		if(S<0)
			return 0;
		if(N==0)
			return 0;
		int cur = 0;
		//Start from 1 to 9. As first digits cannot be 0.
		for(int i=1;i<=9;i++)
			//Start the digit with i, recursively search for N-1 digits and S-i Sum.
			cur += ND_Recur_Aux(N-1,S-i);
		return cur;
	}
	
	public static int ND_Recur_Aux(int N, int S)
	{
		//System.out.println("N : "+N+" S : "+S);
		//If SUM become 0. don't count.
		if(S<0)
			return 0;
		//If sum is zero and digits are all used up. count this. so, return 1.
		if(N==0&&S==0)
			return 1;
		//if n==0, no digits so zero.
		if(N==0)
			return 0;
		int cur = 0;
		//From here numbers can start from 0. So, 0 to 9 inclusive.
		for(int i=0;i<=9;i++)
			cur += ND_Recur_Aux(N-1,S-i);
		return cur;
	}
	
	//O(n^3). Should be further optimized.
	//Same as recursive. Bottom Up Approach.
	public static int ND_DP(int N, int S)
	{
		int[][] dp = new int[N+1][S+1];
		dp[0][0] = 1;
		for(int i=1;i<=N;i++)
		{
			for(int j=0;j<=S;j++)
			{
				int cur = 0;
				int start = 1;
				if(i==1)
					start = 0;
				for(int k=start;k<=9;k++)
				{
					if(j-k>=0)
						cur += dp[i-1][j-k];
					else
						break;
				}
				dp[i][j] = cur;
			}
		}
		//Print the DP table
		for(int i=0;i<=N;i++)
		{
			for(int j=0;j<=S;j++)
			{
				System.out.print(dp[i][j]+"\t");
			}
			System.out.println();
		}
		return dp[N][S];
	}
	
	public static void main(String[] args)
	{
		//Number of digits and SUM
		System.out.println(ND_Recur(2,2));
		System.out.println(ND_DP(2,5));
	}
	
}
