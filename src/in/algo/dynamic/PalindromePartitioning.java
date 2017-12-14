package in.algo.dynamic;

public class PalindromePartitioning {
	
	//Naive recursive implementation
	//Similar to Matrix Multiplication Problem, Try to partition for every possible index.
	public static int palindromePartitionRecur(String str, int index1, int index2)
	{
		//No need to partition if it is a single char.
		//Zero partitions required for a single char.
		if(index1==index2)
			return 0;
		//If the sub-string passed is a palindrome, then zero partitions.
		if(isPalidrome(str.substring(index1,index2+1)))
			return 0;
		//Take a hihger Value,
		//Partition the problem into subproblems by keeping a partition at every place. Recursively solve them, 
		//Get the results, use them to construct the mininum of partitions for this substring.
		int val = Integer.MAX_VALUE;
		for(int i=index1;i<index2;i++)
		{
			//Recursively get the values for left substring and right substring and add ONE ( one is current partition ).
			val = Math.min(val, palindromePartitionRecur(str,index1,i) + palindromePartitionRecur(str,i+1,index2) + 1);
		}
		return val;
	}
	
	public static boolean isPalidrome(String str)
	{
		if(str.length()==1)
			return true;
		int i = 0;
		int j = str.length()-1;
		while(i<j)
		{
			if(str.charAt(i)==str.charAt(j))
			{
				i++;
				j--;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	public static void printBool(boolean[][] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[0].length;j++)
			{
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	//DP Solution
	public static int palindromPartition_DP(String str)
	{
		//Calculate all the substring and store if they are palindromes or not.
		boolean[][] isPalindrome = new boolean[str.length()][str.length()];
		//If it only one char it is palindrome.
		for(int i=0;i<str.length();i++)
			isPalindrome[i][i] = true;
		
		//Bottom Up Approach for the SubString Palindrome check.
		for(int i=str.length()-1;i>=0;i--)
		{
			for(int j=i+1;j<str.length();j++)
			{
				if(i+1==j&&(str.charAt(i)==str.charAt(j)))
					isPalindrome[i][j] = true;
				else if(str.charAt(i)==str.charAt(j)&&(isPalindrome[i+1][j-1]==true))
					isPalindrome[i][j] = true;
			}
		}
		
		//Store the Cuts in a 1-D Array.
		//Similar to LIS Problem.
		int[] cuts = new int[str.length()];
		for(int i=0;i<str.length();i++)
		{
			//If the subString is Palindrome. Then the cuts[i] -> subStirng[0..i] will be 0; Period
			//If not, check from subString[1..i], subString[2..i] so on uptill subString[i..i]
			if(isPalindrome[0][i])
				cuts[i] = 0;
			else
			{
				cuts[i] = Integer.MAX_VALUE;
				//
				for(int j=0;j<i;j++)
				{
					//j will cut the string from 0 till i-1
					//if right part is palindrome, then check the left cuts + 1
					//at last right part is palindrome for [i][i] So, cuts[i] = cuts[j]+1;
					if(isPalindrome[j+1][i]==true&&1+cuts[j]<cuts[i])
						cuts[i] = cuts[j] + 1;
				}
			}
		}
		printBool(isPalindrome);
		return cuts[str.length()-1];
	}
	
	public static void main(String[] args)
	{
		String str = "ababbbabbababa";
		System.out.println(palindromePartitionRecur(str, 0, str.length()-1));
		System.out.println(palindromPartition_DP(str));
	}
	
}
