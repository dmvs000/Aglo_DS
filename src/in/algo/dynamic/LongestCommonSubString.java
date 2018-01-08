package in.algo.dynamic;

/*
 * Given Two Strings. Find the MaxLen Common SubString among those.
 */
public class LongestCommonSubString {

	static int maxLenRecur = 0;
	static int startIndexRecur = 0;
	static void LongestCommonSubString_Recur(String str1, String str2)
	{
		LCSS_Recur_Aux(str1.length()-1,str2.length()-1,str1,str2,0);
		System.out.println(maxLenRecur);
		System.out.println(startIndexRecur);
		System.out.println(str1.substring(startIndexRecur,startIndexRecur+maxLenRecur));
	}
	
	static void LCSS_Recur_Aux(int index1, int index2, String str1, String str2, int val)
	{
		if(index1<0||index2<0)
			return;
		if(str1.charAt(index1)==str2.charAt(index2))
		{
			LCSS_Recur_Aux(index1-1,index2-1,str1,str2,val+1);
			//maxLenRecur = Math.max(maxLenRecur, val+1);
			if(maxLenRecur<val+1)
			{
				maxLenRecur = val + 1;
				startIndexRecur = index1;
			}
		}
		else
		{
			LCSS_Recur_Aux(index1,index2-1,str1,str2,0);
			LCSS_Recur_Aux(index1-1,index2,str1,str2,0);
		}
	}
	
	static void LongestCommonSubString(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		int[][] dp = new int[m][n];
		int maxLen = 0;
		int commonStringStartIndex = 0;
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (str1.charAt(i) == str2.charAt(j)) {
					if (i == m - 1 || j == n - 1)
						dp[i][j] = 1;
					else
						dp[i][j] = dp[i + 1][j + 1] + 1;
				} else {
					dp[i][j] = 0;
				}
				//maxLen = Math.max(dp[i][j], maxLen);
				if(maxLen<dp[i][j])
				{
					maxLen = dp[i][j];
					commonStringStartIndex = i;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(str1.substring(commonStringStartIndex,commonStringStartIndex+maxLen));
		System.out.println(maxLen);
	}

	public static void main(String[] args) {
		String str1 = "geeksforgeeks";
		String str2 = "geeks";
		LongestCommonSubString(str1, str2);
		LongestCommonSubString_Recur(str1,str2);
	}

}
