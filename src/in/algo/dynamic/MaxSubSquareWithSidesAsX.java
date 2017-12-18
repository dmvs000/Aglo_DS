package in.algo.dynamic;

public class MaxSubSquareWithSidesAsX {

	static class obj {
		int v;
		int h;

		obj(int v, int h) {
			this.v = v;
			this.h = h;
		}
	}

	public static int maxSize(char[][] arr) {
		// Construct DP Array
		obj[][] dp = new obj[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == '0') {
					dp[i][j] = new obj(0, 0);
					continue;
				}
				// arr[i][j] is not zero. Check for other conditions.
				if (i == '0' && j == '0') {
					dp[i][j] = new obj(1, 1);
				} else if (i == 0) {
					dp[i][j] = new obj(1, dp[i][j - 1].h + 1);
				} else if (j == 0) {
					dp[i][j] = new obj(dp[i - 1][j].v + 1, 1);
				} else {
					dp[i][j] = new obj(dp[i - 1][j].v + 1, dp[i][j - 1].h + 1);
				}
			}
		}
		// Find the MaxSize
		int maxSize = 0;
		int endIndex_i = 0;
		int endIndex_j = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = arr.length - 1; j >= 0; j--) {
				if (dp[i][j].v != 0 && dp[i][j].h != 0 && maxSize == 0)
				{
					maxSize = 1;
					endIndex_i = i;
					endIndex_j = j;
				}
				if (dp[i][j].v == 0 && dp[i][j].h == 0)
					continue;
				int t_min = Math.min(dp[i][j].v, dp[i][j].h);
				while (t_min > maxSize) {
					int top = i - t_min + 1;
					int left = j - t_min + 1;
					if (dp[top][j].h < t_min) {
						t_min--;
						continue;
					}
					if (dp[i][left].v < t_min) {
						t_min--;
						continue;
					}
					maxSize = t_min;
					t_min--;
					endIndex_i = i;
					endIndex_j = j;
				}
			}
		}
		System.out.println("The Ending Index is : "+endIndex_i+" "+endIndex_j);
		return maxSize;
	}

	public static void main(String[] args) {
		char[][] arr = { { '0', '0', '0', '0', 'X' }, { 'X', '0', 'X', 'X', 'X' }, { 'X', '0', 'X', '0', 'X' },
				{ 'X', 'X', 'X', 'X', 'X' }, { '0', '0', 'X', 'X', 'X' } };
		System.out.println(maxSize(arr));
	}

}
