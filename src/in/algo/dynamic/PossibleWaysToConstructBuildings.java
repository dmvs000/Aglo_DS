package in.algo.dynamic;

public class PossibleWaysToConstructBuildings {
	
	public static int getCount(int N)
	{
		if(N==1)
			return 2*2;
		int prev_Pick = 1;
		int prev_NoPick = 1;
		for(int i=2;i<=N;i++)
		{
			int cur_Pick = prev_NoPick;
			int cur_NoPick = prev_Pick+prev_NoPick;
			prev_Pick = cur_Pick;
			prev_NoPick = cur_NoPick;
		}
		return (prev_Pick+prev_NoPick)*(prev_Pick+prev_NoPick);
	}
	
	public static void main(String[] args)
	{
		int N = 4;
		System.out.println(getCount(N));
	}
	
}
