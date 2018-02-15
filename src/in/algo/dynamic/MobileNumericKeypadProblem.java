package in.algo.dynamic;

/*
 * https://www.geeksforgeeks.org/mobile-numeric-keypad-problem/
 */
public class MobileNumericKeypadProblem {

	static int rows = 4;
	static int cols = 3;

	// Plain Recursive
	public static int MNK_Recur(int N) {
		if (N == 0)
			return 0;
		int cur = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				cur += MNK_Recur_Aux(N - 1, i, j);
			}
		}
		return cur;
	}

	public static int MNK_Recur_Aux(int N, int i, int j) {
		if (i < 0 || i >= rows || j < 0 || j >= cols)
			return 0;
		if ((i == 3 && j == 0) || (i == 3 && j == 2))
			return 0;
		if (N == 0)
			return 1;
		int cur = 0;
		cur = cur + MNK_Recur_Aux(N - 1, i, j);
		cur = cur + MNK_Recur_Aux(N - 1, i - 1, j);
		cur = cur + MNK_Recur_Aux(N - 1, i + 1, j);
		cur = cur + MNK_Recur_Aux(N - 1, i, j + 1);
		cur = cur + MNK_Recur_Aux(N - 1, i, j - 1);
		return cur;
	}

	// DP Approach
	public static int MNK_DP(int N) {
		int[][][] dp = new int[N][4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i == 3 && j == 0) || (i == 3 && j == 2))
					continue;
				dp[0][i][j] = 1;
			}
		}
		System.out.println("0 :");
		printArr(dp[0]);
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 3; k++) {
					if ((j == 3 && k == 0) || (j == 3 && k == 2))
						continue;
					dp[i][j][k] = getValue(dp[i - 1], j, k) + getValue(dp[i - 1], j - 1, k)
							+ getValue(dp[i - 1], j, k - 1) + getValue(dp[i - 1], j, k + 1)
							+ getValue(dp[i - 1], j + 1, k);
				}
			}
			System.out.println(i + ": ");
			printArr(dp[i]);
		}
		int totalCount = 0;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++)
				totalCount += dp[N - 1][i][j];
		// System.out.println(totalCount);
		return totalCount;
	}

	// DP with Space Optimization
	public static int MNK_DP_SpaceOpt(int N) {
		int[][] prev_dp = new int[4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i == 3 && j == 0) || (i == 3 && j == 2))
					continue;
				prev_dp[i][j] = 1;
			}
		}
		for (int m = 1; m < N; m++) {
			int[][] dp = new int[4][3];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					if ((i == 3 && j == 0) || (i == 3 && j == 2))
						continue;
					dp[i][j] = getValue(prev_dp, i, j) + getValue(prev_dp, i, j + 1) + getValue(prev_dp, i, j - 1)
							+ getValue(prev_dp, i - 1, j) + getValue(prev_dp, i + 1, j);
				}
			}
			prev_dp = dp;
		}
		int totalCount = 0;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++)
				totalCount += prev_dp[i][j];
		// System.out.println(totalCount);
		return totalCount;
	}

	public static void printArr(int[][] arr) {
		int m = arr.length;
		int n = arr[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int getValue(int[][] subDP, int i, int j) {
		if ((i == 3 && j == 0) || (i == 3 && j == 2))
			return 0;
		if (i < 0 || i > 3 || j < 0 || j > 2)
			return 0;
		return subDP[i][j];
	}

	public static void main(String[] args) {
		System.out.println(MNK_Recur(5));
		System.out.println(MNK_DP(5));
		System.out.println(MNK_DP_SpaceOpt(5));
	}

}
