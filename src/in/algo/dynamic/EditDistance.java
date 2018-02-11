package in.algo.dynamic;

public class EditDistance {

	// Given two Strings, find how many ops are required to convert from
	// one string to the other. Permitted ops are insertion, deletion or
	// replacement.

	// Naive Recursive Solution
	public static int editDistanceRecur(String str1, String str2, int index1, int index2) {
		if (index1 < 0 || index2 < 0)
			return 0;
		// If the chars Are Same
		if (str1.charAt(index1) == str2.charAt(index2))
			return editDistanceRecur(str1, str2, index1 - 1, index2 - 1);
		else {
			int insert = editDistanceRecur(str1, str2, index1, index2 - 1);
			int delete = editDistanceRecur(str1, str2, index1 - 1, index2);
			int replace = editDistanceRecur(str1, str2, index1 - 1, index2 - 1);
			return Math.max(Math.max(insert, delete), replace) + 1;
		}
	}

	// DP Solution
	public static int editDistanceDP(String str1, String str2) {
		int n1 = str1.length();
		int n2 = str2.length();
		int[][] dp = new int[n1 + 1][n2 + 1];
		for (int i = 0; i <= n1; i++) {
			for (int j = 0; j <= n2; j++) {
				if (i == 0 && j == 0)
					dp[i][j] = 0;
				else if (i == 0)
					dp[i][j] = j;
				else if (j == 0)
					dp[i][j] = i;
				else if (str1.charAt(i - 1) == str2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];
				else {
					// insert
					int insert = dp[i][j - 1];
					// delete
					int delete = dp[i - 1][j];
					// replace
					int replace = dp[i - 1][j - 1];
					dp[i][j] = Math.min(Math.min(insert, delete), replace) + 1;
				}
			}
		}
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++)
				System.out.print(dp[i][j] + " ");
			System.out.println();
		}
		return dp[n1][n2];
	}

	public static void main(String[] args) {
		String str1 = "saturday";
		String str2 = "sunday";
		System.out.println(editDistanceRecur(str1, str2, str1.length() - 1, str2.length() - 1));
		System.out.println(editDistanceDP(str1, str2));
	}

}
