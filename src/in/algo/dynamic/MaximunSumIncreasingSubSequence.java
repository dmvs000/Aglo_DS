package in.algo.dynamic;

public class MaximunSumIncreasingSubSequence {
	
	//Direct DP
	public static int MSISubSeqDP(int[] arr)
	{
		int[] dp_Max = new int[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			for(int j=i;j>=0;j--)
			{
				if(i==j)
				{
					dp_Max[i] = arr[i];
				}
				else
				{
					if(arr[j]<arr[i])
					{
						dp_Max[i] = Math.max(dp_Max[i], dp_Max[j] + arr[i]);
					}
				}
			}
		}
		//Print the DP Array
		for(int i=0;i<arr.length;i++)
			System.out.print(dp_Max[i]+"\t");
		System.out.println();
		int retMaxVal = dp_Max[0];
		for(int i=1;i<arr.length;i++)
			retMaxVal = Math.max(retMaxVal, dp_Max[i]);
		return retMaxVal;
	}
	
	public static void main(String[] args)
	{
		int[] arr = {1,101,2,3,100,4,5};
		System.out.print(MSISubSeqDP(arr));
	}
	
}
