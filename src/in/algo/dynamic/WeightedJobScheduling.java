package in.algo.dynamic;

import java.util.ArrayList;
import java.util.Collections;

public class WeightedJobScheduling {
	
	static class Job implements Comparable<Job>
	{
		int profit, start, finish;
		Job(int p, int s, int f)
		{
			this.profit = p;
			this.start = s;
			this.finish = f;
		}
		@Override
		public int compareTo(Job j)
		{
			if(this.finish<j.finish)
				return -1;
			else if(this.finish==j.finish)
				return 0;
			else
				return 1;
		}
	}
	
	public static void findMaxProfit(ArrayList<Job> al)
	{
		Collections.sort(al);
		int[] dp = new int[al.size()];
		for(int i=0;i<al.size();i++)
		{
			dp[i] = al.get(i).profit;
			for(int j=i-1;j>=0;j--)
			{
				if(al.get(j).finish<=al.get(i).start)
					dp[i] = Math.max(dp[i], dp[j]+al.get(i).profit);
			}
		}
		System.out.println("Maximum Profit is : "+dp[al.size()-1]);
	}
	
	public static void main(String[] args)
	{
		int[] start = {1,3,6,2};
		int[] finish = {2,5,19,200};
		int[] profit = {50,20,100,200};
		ArrayList<Job> al = new ArrayList<Job>();
		for(int i=0;i<start.length;i++)
		{
			Job t_job = new Job(profit[i],start[i],finish[i]);
			al.add(t_job);
		}
		findMaxProfit(al);
	}
	
}
