package in.algo.dynamic;

import java.util.Arrays;

public class MaximumSum2DSubArray {

	
	static int cols = 0;
	static int rows = 0;
	static int maxSum = 0;
	static int maxTop = 0;
	static int maxBottom = 0;
	static int maxLeft = 0;
	static int maxRight = 0;
	
	public static void maxSum(int[][] arr)
	{
		rows = arr.length;
		cols = arr[0].length;
		int[] temp = new int[rows];
		for(int L=0;L<cols;L++)
		{
			temp = new int[rows];
			for(int R=L;R<cols;R++)
			{
				for(int i=0;i<rows;i++)
					temp[i] = arr[i][R] + temp[i];
				if(maxKadane(temp))
				{
					maxLeft = L;
					maxRight = R;
				}
			}
		}
		System.out.println("T : "+maxTop);
		System.out.println("B : "+maxBottom);
		System.out.println("L : "+maxLeft);
		System.out.println("R : "+maxRight);
		System.out.println("MaxSum : "+maxSum);
	}
	
	public static boolean maxKadane(int[] temp)
	{
		//System.out.println(Arrays.toString(temp));
		int tMaxSum = 0;
		int tstart = 0;
		int start = 0;
		int end = 0;
		int maxUptilHere = 0;
		int cur = 0;
		while(cur<temp.length)
		{
			maxUptilHere = maxUptilHere + temp[cur];
			if(maxUptilHere<=0)
			{
				maxUptilHere = 0;
				tstart = cur+1;
				cur++;
				continue;
			}
			if(maxUptilHere>tMaxSum)
			{
				end = cur;
				start = tstart;
				tMaxSum = maxUptilHere;
			}
			cur++;
		}
		if(maxSum<tMaxSum)
		{
			maxTop = start;
			maxBottom = end;
			maxSum = tMaxSum;
			//System.out.println("Start "+start+" End "+end);
			return true;
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		int[][] temp = {
				{2,1,-3,-4,5},
				{0,6,3,4,1},
				{2,-2,-1,4,-5},
				{-3,3,1,0,3}
		};
		maxSum(temp);
	}
	
}
