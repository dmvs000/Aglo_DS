package in.algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class JobSequencingProblem {

	static class Job implements Comparable<Job> {
		char name;
		int deadline;
		int profit;

		Job(char name, int deadline, int profit) {
			this.name = name;
			this.deadline = deadline;
			this.profit = profit;
		}

		@Override
		public int compareTo(Job o) {
			if (this.profit > o.profit)
				return -1;
			else if (this.profit == o.profit)
				return 0;
			else
				return 1;
		}

	}

	public static void MaxProfit(int[] deadline, int[] profit, char[] name) {
		int n = deadline.length;
		ArrayList<Job> al = new ArrayList<Job>();
		for (int i = 0; i < n; i++)
			al.add(new Job(name[i], deadline[i], profit[i]));
		Collections.sort(al);
		int maxDeadLine = deadline[0];
		for (int i = 1; i < n; i++)
			maxDeadLine = Math.max(maxDeadLine, deadline[i]);
		int[] time = new int[maxDeadLine + 1];
		Arrays.fill(time, -1);
		for (int i = 0; i < al.size(); i++) {
			int j = al.get(i).deadline;
			for (; j > 0; j--) {
				if (time[j] != -1)
					continue;
				else
					break;
			}
			if (j != 0)
			{
				time[j] = i;
				//System.out.println(al.get(i).name + " " + j);
			}
		}
		int maxProfit = 0;
		for (int i = 1; i <= maxDeadLine; i++) {
			//System.out.print(time[i]+" ");
			System.out.print(al.get(time[i]).name + " ");
			maxProfit += al.get(time[i]).profit;
		}
		System.out.println("\nMax Profit : " + maxProfit);
	}

	public static void main(String[] args) {
		int[] deadline = { 2, 1, 2, 1, 3 };
		int[] profit = { 100, 19, 27, 25, 15 };
		char[] names = { 'a', 'b', 'c', 'd', 'e' };
		MaxProfit(deadline, profit, names);
	}

}
