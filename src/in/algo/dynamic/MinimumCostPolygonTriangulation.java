package in.algo.dynamic;

import java.util.ArrayList;

public class MinimumCostPolygonTriangulation {

	static class point
	{
		double x;
		double y;
		point(double x, double y)
		{
			this.x = (double)x;
			this.y = (double)y;
		}
	}
	
	public static double distance(point i, point j)
	{
		return Math.sqrt(((j.y-i.y)*(j.y-i.y))+((j.x-i.x)*(j.x-i.x)));
	}
	
	public static double Cost(ArrayList<point> al, int x, int y, int z)
	{
		return distance(al.get(x),al.get(y))+distance(al.get(y),al.get(z))+distance(al.get(z),al.get(x));
	}
	
	public static double calMinCost_Recur(ArrayList<point> al, int s, int e)
	{
		if(e-s<2)
			return 0;
		double minCost = Double.MAX_VALUE;
		for(int k=s+1;k<e;k++)
		{
			minCost = Math.min(minCost, calMinCost_Recur(al,s,k) + calMinCost_Recur(al,k,e) + Cost(al,s,k,e));
		}
		return minCost;
	}
	
	public static double calMinCost_DP(ArrayList<point> al)
	{
		//System.out.println(al.size());
		//If there are less than two values in al then no triangle or polygon can be formed.
		//So the cost would be zero.
		//IF there are more than or equal to 3 points. We should split it points by connecting first,k,end points. k varies from k+1 to end (exclusive)
		double[][] dp = new double[al.size()][al.size()];
		for(int i=al.size()-1;i>=0;i--)
		{
			for(int j=i;j<al.size();j++)
			{
				if(j-i<2)
					dp[i][j] = 0;
				else
				{
					double minCost = Double.MAX_VALUE;
					for(int k=i+1;k<j;k++)
					{
						minCost = Math.min(minCost, dp[i][k] + dp[k][j] + Cost(al,i,k,j));
					}
					dp[i][j] = minCost;
				}
			}
		}
		//Print the DP Table
		for(int i=0;i<al.size();i++)
		{
			for(int j=0;j<al.size();j++)
			{
				System.out.print(dp[i][j]+"\t");
			}
			System.out.println();
		}
		return dp[0][al.size()-1];
	}
	
	//The input should either be in clockwise or anti clockwise direction.
	public static void main(String[] args)
	{
		double[][] arr = {{0, 0}, {1, 0}, {2, 1}, {1, 2}, {0, 2}};
		ArrayList<point> al = new ArrayList<point>();
		for(int i=0;i<arr.length;i++)
		{
				al.add(new point(arr[i][0],arr[i][1]));
		}
		System.out.println(al.size());
		System.out.println(calMinCost_Recur(al,0,arr.length-1));
		System.out.println(calMinCost_DP(al));
	}
	
}
