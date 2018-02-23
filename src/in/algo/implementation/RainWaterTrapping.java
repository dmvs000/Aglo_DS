package in.algo.implementation;

import java.util.Arrays;

/*
 * https://www.geeksforgeeks.org/trapping-rain-water/
 */

public class RainWaterTrapping {

	public static void main(String[] args) {
		int[] arr = { 3, 0, 0, 2, 0, 4 };
		int n = arr.length;
		int[] left = new int[n];
		int[] right = new int[n];
		left[0] = arr[0];
		right[n - 1] = arr[n - 1];
		for (int i = 1; i < n; i++)
			left[i] = Math.max(left[i - 1], arr[i]);
		for (int i = n - 2; i >= 0; i--)
			right[i] = Math.max(right[i + 1], arr[i]);
		System.out.println("Left  : " + Arrays.toString(left));
		System.out.println("Right : " + Arrays.toString(right));
		int water = 0;
		for (int i = 0; i < n; i++)
			water = water + (Math.min(left[i], right[i]) - arr[i]);
		System.out.println(water);
	}

}
