package in.algo.dynamic;

public class PartitionProblem {
	
	//Either an element can be picked or cannot be picked.
	//True should be returned if there is a subset of required sum.
	public static boolean partitionRecur(int[] arr, int index, int sum)
	{
		//No elements are there beyond this point and hence cannot picked.
		if(index<0)
			return false;
		//If and element is greater than a sum. It cannot be picked.
		if(arr[index]>sum)
			return false;
		//If the element is same, then pick it and it becomes the only subset.
		if(arr[index]==sum)
			return true;
		else
			// Either picked or not picked. Return the || of them.
			return partitionRecur(arr, index-1, sum)||partitionRecur(arr, index-1, sum-arr[index]);
	}
	
	public static boolean partitionDP(int[] arr, int sum)
	{
		boolean[][] dp = new boolean[arr.length+1][sum+1];
		//If the sum is zero and no items, we can form zero sum by picking empty subset.
		dp[0][0] = true;
		//Remaing all cannot be formed with empty subset.
		for(int i=1;i<=sum;i++)
			dp[0][i] = false;
		for(int i=1;i<=arr.length;i++)
		{
			for(int j=0;j<=sum;j++)
			{
				//If the sum is less than the element, then element cannot be picked
				//If picked, exceeds the sum.
				if(j-arr[i-1]<0)
				{
					dp[i][j] = dp[i-1][j];
				}
				//Check if picking this element or not picking returns true
				else
				{
					dp[i][j] = dp[i-1][j]||dp[i-1][j-arr[i-1]];
				}
			}
		}
		for(int i=0;i<=arr.length;i++)
		{
			for(int j=0;j<=sum;j++)
			{
				System.out.print(dp[i][j]+"\t");
			}
			System.out.println();
		}
		
		//If it can be partitioned. Check what elements should be picked.
		if(dp[arr.length][sum])
		{
			
		}
		
		return dp[arr.length][sum];
	}
	
	public static void main(String[] args)
	{
		//Given a Set {1,5,11,5}.
		//Check if it can be divided into subSets 
		//with each subset sum being equal
		int[] arr = {1,5,11,5};
		int n = arr.length;
		int sum = 0;
		for(int i=0;i<n;i++)
			sum = sum + arr[i];
		//If odd can't be divided.
		if(sum%2==1)
		{
			System.out.println("false");
			return;
		}
		sum = sum/2;
		//Get the subset for sum/2 if only this is even
		System.out.println(partitionRecur(arr,arr.length-1,sum));
		System.out.println(partitionDP(arr,sum));
	}
	
}
