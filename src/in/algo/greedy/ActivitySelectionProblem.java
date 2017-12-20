package in.algo.greedy;

import java.util.ArrayList;
import java.util.Collections;

public class ActivitySelectionProblem {
	
	static class Job implements Comparable<Job>
	{
		int start;
		int finish;
		Job(int s, int f)
		{
			this.start = s;
			this.finish = f;
		}
		@Override
		public int compareTo(Job o) {
			if(this.finish<o.finish)
				return -1;
			else if(this.finish==o.finish)
				return 0;
			else
				return 1;
		}
	}
	
	public static int findMaxActivities(ArrayList<Job> al)
	{
		Collections.sort(al);
		int count = 1;
		int cur = 1;
		int prev_finish = al.get(0).finish;
		System.out.print("0\t");
		while(cur<al.size())
		{
			if(al.get(cur).start>=prev_finish)
			{
				prev_finish = al.get(cur).finish;
				System.out.print(cur+"\t");
				count++;
			}
			cur++;
		}
		System.out.println();
		return count;
	}
	
	public static void main(String[] args)
	{
		//int[] start = {1,3,0,5,8,5};
		//int[] finish = {2,4,6,7,9,9};
		int[] start  =  {10, 12, 20};
	    int[] finish =  {20, 25, 30};
		ArrayList<Job> al = new ArrayList<Job>();
		for(int i=0;i<start.length;i++)
			al.add(new Job(start[i],finish[i]));
		System.out.print(findMaxActivities(al));
	}
	
}
