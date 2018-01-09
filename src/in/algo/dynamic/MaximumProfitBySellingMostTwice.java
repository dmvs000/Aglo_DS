package in.algo.dynamic;

public class MaximumProfitBySellingMostTwice {

	public static int maxProfit(int[] arr) {
		int[] profit = new int[arr.length];
		int n = profit.length;
		int min = arr[n - 1];
		int minIndex = n - 1;
		int max = arr[n - 1];
		int maxIndex = n - 1;
		for (int i = n - 1; i >= 0; i--) {
			if (i == n - 1)
				profit[i] = 0;
			else {
				if (arr[i] >= max) {
					max = arr[i];
					maxIndex = i;
				}
				if (arr[i] <= min) {
					min = arr[i];
					minIndex = i;
				}
				// System.out.println(i+" : "+minIndex+" "+maxIndex);
				// System.out.println(i+" : "+arr[minIndex]+" "+arr[maxIndex]);
				if (minIndex <= maxIndex) {
					profit[i] = Math.max(profit[i + 1], arr[maxIndex] - arr[minIndex]);
				} else {
					profit[i] = profit[i + 1];
				}
			}
		}
		print(profit);
		min = arr[0];
		max = arr[0];
		minIndex = 0;
		maxIndex = 0;
		for (int i = 1; i < n; i++) {
			if (i == n - 1) {
				if (minIndex <= maxIndex)
					profit[i] = Math.max(0 + arr[maxIndex] - arr[minIndex], profit[i - 1]);
				else
					profit[i] = profit[i - 1];
			} else {
				if (arr[i] >= max) {
					max = arr[i];
					maxIndex = i;
				}
				if (arr[i] <= min) {
					min = arr[i];
					minIndex = i;
				}
				if (minIndex <= maxIndex) {
					profit[i] = Math.max(profit[i + 1] + arr[maxIndex] - arr[minIndex], profit[i - 1]);
				} else {
					profit[i] = profit[i - 1];
				}
			}
		}

		print(profit);
		return profit[n - 1];
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + "\t");
		System.out.println();
	}

	public static void main(String[] args) {
		//int[] arr = { 2, 30, 15, 10, 8, 25, 80 };
		int[] arr = { 100, 30, 15, 10, 8, 25, 80 };
		//int[] arr = { 10, 22, 5, 75, 65, 80 };
		//int[] arr = {90, 80, 70, 60, 50};
		System.out.println(maxProfit(arr));
	}

}
