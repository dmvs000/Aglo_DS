package in.algo.dynamic;

public class WordWrapProblem {
	
	//Cost is the extra spaces in each line ^ 3
	public static void wordWrap(int[] arr, int width)
	{
		//Calculate the cost for every subString if it can be fit in line
		//else give MAX value so as not to consider it.
		int[][] lc = new int[arr.length][arr.length];
		for(int i=lc.length-1;i>=0;i--)
		{
			for(int j=i;j<lc.length;j++)
			{
				if(i==j)
				{
					lc[i][j] = (int) Math.pow(width-arr[i],3);
				}
				else
				{
					int totalSpace = 0;
					for(int k=i;k<=j;k++)
						totalSpace += arr[k]+1;
					totalSpace--;
					//System.out.println(" i : "+i+" j : "+j+" "+totalSpace);
					if(totalSpace>width)
						lc[i][j] = Integer.MAX_VALUE;
					else
						lc[i][j] = (int) Math.pow(width-totalSpace, 3);
				}
			}
		}
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr.length;j++)
				System.out.print(lc[i][j]+"\t");
			System.out.println();
		}
		System.out.println(minCostWordWrap(arr,0,arr.length-1,lc));
		minCostWordWrapDP(arr,lc);
	}
	
	public static int minCostWordWrap(int[] arr, int start, int end, int[][] lc)
	{
		if(start==end)
			return lc[start][start];
		int retVal = lc[start][end];
		for(int j=start;j<end;j++)
			retVal = Math.min(retVal,minCostWordWrap(arr,start,j,lc)+minCostWordWrap(arr,j+1,end,lc));
		return retVal;
	}
	
	public static int minCostWordWrapDP(int[] arr, int[][] lc)
	{
		int[] c = new int[arr.length];
		int[] p = new int[arr.length];
		for(int i=lc.length-1;i>=0;i--)
		{
			c[i] = lc[i][lc.length-1];
			for(int j=i;j<lc.length-1;j++)
			{
				if(lc[i][j]!=Integer.MAX_VALUE&&(c[i]>lc[i][j]+c[j+1]))
				{
					c[i] = lc[i][j] + c[j+1];
					p[i] = j+1;
				}
			}
		}
		System.out.print("C[i] DP Array : 	");
		for(int i=0;i<arr.length;i++)
			System.out.print(c[i]+"\t");
		System.out.println();
		System.out.print("Tracking Array : 	");
		for(int i=0;i<arr.length;i++)
			System.out.print(p[i]+"\t");
		System.out.println();
		int temp = 0;
		p[c.length-1] = c.length;
		while(temp<=c.length-1)
		{
			int next = p[temp];
			System.out.println("From "+temp+" to "+next);
			temp = next;
		}
		return 0;
	}
	
	public static void main(String[] args)
	{
		int[] arr = {3,2,2,5};
		//String "aaa bb cc ddddd"
		wordWrap(arr,6);
	}
	
}
