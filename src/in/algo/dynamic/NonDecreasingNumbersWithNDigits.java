package in.algo.dynamic;

import java.util.ArrayList;

public class NonDecreasingNumbersWithNDigits {
	
	public static int NDN_Recur(int N)
	{
		int cur_count = 0;
		for(int i=0;i<=9;i++)
		{
			cur_count += NDN_Recur_Aux(i,N-1);
		}
		return cur_count;
	}
	
	public static int NDN_Recur_Aux(int prev, int N)
	{
		if(N==0)
			return 1;
		int cur_count = 0;
		for(int i=prev;i<=9;i++)
			cur_count += NDN_Recur_Aux(i, N-1);
		return cur_count;
	}
	
	public static int NDN_DP(int N)
	{
		if(N==0)
			return 0;
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0;i<=9;i++)
			al.add(0);
		for(int i=1;i<=N;i++)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int j=0;j<=9;j++)
			{
				if(j==0)
					temp.add(1);
				else
				{
					temp.add(temp.get(j-1)+al.get(j));
				}
			}
			al = temp;
		}
		int retVal = 0;
		for(int i=0;i<al.size();i++)
			retVal += al.get(i);
		return retVal;
	}
	
	public static void main(String[] args)
	{
		int N = 5;
		System.out.println(NDN_Recur(N));
		System.out.println(NDN_DP(N));
	}
	
}
