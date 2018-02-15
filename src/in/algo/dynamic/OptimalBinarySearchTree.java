package in.algo.dynamic;

/*
 * https://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
 */
public class OptimalBinarySearchTree {

	static int[] freq;
	static int[] sum;
	static int[] keys;
	static int n;
	static int[][] track;

	public static void OBSTree_Recur() {
		System.out.println(OBSTree_Recur_Aux(0, n - 1, 1));
	}

	public static int OBSTree_Recur_Aux(int i, int j, int level) {
		if (i > j)
			return 0;
		int minCost = Integer.MAX_VALUE;
		for (int k = i; k <= j; k++) {
			minCost = Math.min(minCost, OBSTree_Recur_Aux(i, k - 1, level + 1) + OBSTree_Recur_Aux(k + 1, j, level + 1)
					+ (level * freq[k]));
		}
		return minCost;
	}

	public static void OBSTree_DP() {
		calSum();
		int[][] dp = new int[n][n];
		track = new int[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				if (i == j) {
					dp[i][j] = freq[i];
				} else {
					int curMinCost = Integer.MAX_VALUE;
					int rootIs = 0;
					for (int k = i; k <= j; k++) {
						int cur_kAsRootCost = 0;
						if (k == j) {
							cur_kAsRootCost = freq[k] + dp[i][k - 1] + getSum(i, k - 1);
						} else if (k == i) {
							cur_kAsRootCost = freq[k] + dp[k + 1][j] + getSum(k + 1, j);
						} else {
							cur_kAsRootCost = freq[k] + dp[i][k - 1] + getSum(i, k - 1) + dp[k + 1][j]
									+ getSum(k + 1, j);
						}
						if (curMinCost > cur_kAsRootCost) {
							curMinCost = cur_kAsRootCost;
							rootIs = k;
						}
					}
					dp[i][j] = curMinCost;
					track[i][j] = rootIs;
				}
			}
		}
		// Printing the DP Matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}

		// Printing the solution.
		// If the trees are added in this way they should form the minimum cost.
		printOrder(0, n - 1);
		System.out.println();
	}

	public static void printOrder(int i, int j) {
		if (i > j || i < 0 || j < 0 || j > n - 1 || i > n - 1)
			return;
		if (i == j) {
			System.out.print(keys[i]+" ");
			return;
		}
		int index = track[i][j];
		System.out.print(keys[index] + " ");
		printOrder(i, index - 1);
		printOrder(index + 1, j);
	}

	public static void calSum() {
		for (int i = 0; i < n; i++) {
			if (i == 0)
				sum[i] = freq[i];
			else
				sum[i] = sum[i - 1] + freq[i];
		}
		// Print After Sum
		/*
		 * for(int i=0;i<n;i++) System.out.print(sum[i]+"\t");
		 * System.out.println();
		 */
	}

	public static int getSum(int from_Index, int to_Index) {
		if (from_Index == 0) {
			// System.out.println("Sum of "+from_Index+" to "+to_Index+" :
			// "+sum[to_Index]);
			return sum[to_Index];
		} else {
			// System.out.println("Sum of "+from_Index+" to "+to_Index+" :
			// "+(sum[to_Index]-sum[from_Index-1]));
			return (sum[to_Index] - sum[from_Index - 1]);
		}
	}

	public static void main(String[] args) {
		freq = new int[5];
		keys = new int[5];
		keys[0] = 1;
		keys[1] = 2;
		keys[2] = 3;
		keys[3] = 4;
		keys[4] = 5;
		sum = new int[5];
		n = 5;
		freq[0] = 3;
		freq[1] = 2;
		freq[2] = 5;
		freq[3] = 4;
		freq[4] = 1;
		OBSTree_DP();
		OBSTree_Recur();
		/*
		 * freq = new int[1]; sum = new int[1]; n = 1; freq[0] = 3;
		 * OBSTree_DP(); OBSTree_Recur();
		 */
	}

}
