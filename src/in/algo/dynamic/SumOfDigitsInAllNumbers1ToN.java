package in.algo.dynamic;

public class SumOfDigitsInAllNumbers1ToN {

	public static int SumDigitsInAllNumbers(int N)
	{
		int sum = 0;
		for(int i=1;i<=N;i++)
			sum = getSumOfDigits(i) + sum;
		return sum;
	}
	
	public static int getSumOfDigits(int N)
	{
		if(N==0)
			return 0;
		int sum = 0;
		while(N!=0)
		{
			sum += (N%10);
			N = N/10;
		}
		return sum;
	}
	
	public static void main(String[] args)
	{
		int N = 12;
		System.out.println(SumDigitsInAllNumbers(N));
	}
	
}
