package in.algo.dynamic;

import java.util.ArrayList;

public class LongestBitonicSubSequence {

	public static void longestBitonicSubSequence(int[] arr) {
		// LIS DP Array
		int[] lis = new int[arr.length];
		// To track back the elements that are considered in LIS
		int[] lis_track = new int[arr.length];
		// LDS DP Array
		int[] lds = new int[arr.length];
		// To track back the elements that are considered in LDS
		int[] lds_track = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j >= 0; j--) {
				if (i == j) {
					lis[i] = 1;
					lis_track[i] = i;
				} else {
					if (arr[j] < arr[i]) {
						if (lis[i] < lis[j] + 1) {
							lis[i] = Math.max(lis[i], lis[j] + 1);
							lis_track[i] = j;
						}
					}
				}

			}
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = i; j < arr.length; j++) {
				if (i == j) {
					lds[i] = 1;
					lds_track[i] = i;
				} else {
					if (arr[j] < arr[i]) {
						if (lds[i] < lds[j] + 1) {
							lds[i] = Math.max(lds[i], lds[j] + 1);
							lds_track[i] = j;
						}
					}
				}

			}
		}
		System.out.print("LIS		: ");
		printArr(lis);
		System.out.print("LIS Track	: ");
		printArr(lis_track);
		System.out.print("LDS		: ");
		printArr(lds);
		System.out.print("LDS Track	: ");
		printArr(lds_track);
		int maxBiTonicLen = lds[0];
		int breakPoint = 0;
		for (int i = 1; i < arr.length; i++) {
			if (maxBiTonicLen < lis[i - 1] + lds[i]) {
				maxBiTonicLen = lis[i - 1] + lds[i];
				breakPoint = i;
			}
		}
		if (maxBiTonicLen < lis[arr.length - 1]) {
			maxBiTonicLen = lis[arr.length - 1];
			breakPoint = arr.length - 1;
		}
		System.out.println("Max Bitonic Length 	: " + maxBiTonicLen);
		System.out.println("Break Point Index	: " + breakPoint);
		int tempBP = breakPoint - 1;
		StringBuilder sb = new StringBuilder();
		while (tempBP >= 0) {
			sb.append(" " + arr[tempBP]);
			if (tempBP == lis_track[tempBP])
				break;
			tempBP = lis_track[tempBP];
		}
		sb.reverse();
		tempBP = breakPoint;
		while (tempBP < arr.length) {
			sb.append(arr[tempBP] + " ");
			if (tempBP == lis_track[tempBP])
				break;
			tempBP = lds_track[tempBP];
		}
		System.out.println(sb.toString());
	}

	public static void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + "\t");
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 1, 11, 2, 10, 4, 5, 2, 1 };
		longestBitonicSubSequence(arr);
	}

}
