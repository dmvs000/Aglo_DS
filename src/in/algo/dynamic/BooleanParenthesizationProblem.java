package in.algo.dynamic;

public class BooleanParenthesizationProblem {

	static class stor_Class
	{
		int true_Count;
		int false_Count;
		int total_Count;
		stor_Class(int tc, int fc, int toc)
		{
			this.true_Count = tc;
			this.false_Count = fc;
			this.total_Count = toc;
		}
		void print()
		{
			System.out.print(this.true_Count+" "+this.false_Count+" "+this.total_Count);
		}
	}
	
	public static int BP_DP(boolean[] ops, char[] sym)
	{
		stor_Class[][] dp = new stor_Class[ops.length][ops.length];
		for(int i=ops.length-1;i>=0;i--)
		{
			for(int j=i;j<ops.length;j++)
			{
				if(i==j)
				{
					if(ops[i])
						dp[i][j] = new stor_Class(1,0,1);
					else
						dp[i][j] = new stor_Class(0,1,1);
				}
				else
				{
					int ttc,tfc, ttotalc;
					ttc = tfc = ttotalc = 0;
					for(int k=i;k<j;k++)
					{
						if(sym[k]=='|')
						{
							ttc = ttc + (dp[i][k].total_Count*dp[k+1][j].total_Count-dp[i][k].false_Count*dp[k+1][j].false_Count);
							tfc = tfc + (dp[i][k].false_Count*dp[k+1][j].false_Count);
						}
						if(sym[k]=='&')
						{
							ttc = ttc + (dp[i][k].true_Count*dp[k+1][j].true_Count);
							tfc = tfc + (dp[i][k].total_Count*dp[k+1][j].total_Count-dp[i][k].true_Count*dp[k+1][k].true_Count);
						}
						if(sym[k]=='^')
						{
							ttc = ttc + (dp[i][k].true_Count*dp[k+1][j].false_Count+dp[i][k].false_Count+dp[k+1][j].true_Count);
							tfc = tfc + (dp[i][k].true_Count*dp[k+1][j].true_Count+dp[i][k].false_Count+dp[k+1][j].false_Count);
						}
					}
					dp[i][j] = new stor_Class(ttc,tfc,ttc+tfc);
				}
			}
		}
		for(int i=0;i<ops.length;i++)
		{
			for(int j=0;j<ops.length;j++)
			{
				if(dp[i][j]==null)
				{
					System.out.print("null\t");
					continue;
				}
				dp[i][j].print();
				System.out.print("\t");
			}
			System.out.println();
		}
		return dp[0][ops.length-1].true_Count;
	}
	
	public static void main(String[] args)
	{
		boolean[] ops = {true,false,false};
		char[] sym = {'^','|'};
		System.out.println(BP_DP(ops,sym));
	}
	
}
