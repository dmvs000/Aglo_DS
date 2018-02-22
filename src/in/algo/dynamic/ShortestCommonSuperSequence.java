package in.algo.dynamic;

/*
 * https://www.geeksforgeeks.org/shortest-common-supersequence/
 */
public class ShortestCommonSuperSequence {

	public static String SCSRecur(String s1, String s2, int s1Index, int s2Index) {
		if (s1Index < 0)
			return s2.substring(0, s2Index + 1);
		if (s2Index < 0)
			return s1.substring(0, s1Index + 1);
		if (s1.charAt(s1Index) == s2.charAt(s2Index))
			return SCSRecur(s1, s2, s1Index - 1, s2Index - 1) + s1.charAt(s1Index);
		else {
			String a = SCSRecur(s1, s2, s1Index - 1, s2Index);
			String b = SCSRecur(s1, s2, s1Index, s2Index - 1);
			if (a.length() < b.length()) {
				return a + s1.charAt(s1Index);
			} else {
				return b + s2.charAt(s2Index);
			}
		}
	}

	public static String SCSDP(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m + 1; i++)
			dp[i][0] = i;
		for (int i = 0; i < n + 1; i++)
			dp[0][i] = i;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
				}
			}
		}
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		// constructing the String
		int i = m;
		int j = n;
		StringBuilder sb = new StringBuilder();
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				sb.append(s1.charAt(i - 1));
				i--;
				j--;
				continue;
			} else {
				if (dp[i - 1][j] <= dp[i][j - 1]) {
					sb.append(s1.charAt(i - 1));
					i--;
				} else {
					sb.append(s2.charAt(j - 1));
					j--;
				}
			}
		}
		while (i > 0) {
			sb.append(s1.charAt(i - 1));
			i--;
		}
		while (j > 0) {
			sb.append(s2.charAt(j - 1));
			j--;
		}
		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		String str1 = "gxtxayb";
		String str2 = "aggtab";
		System.out.println(SCSRecur(str1, str2, str1.length() - 1, str2.length() - 1));
		System.out.println(SCSDP(str1, str2));
	}

}
