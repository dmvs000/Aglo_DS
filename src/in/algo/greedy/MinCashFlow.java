package in.algo.greedy;

import java.util.Arrays;

public class MinCashFlow {

	public static void minCashFlow(int[][] flow) {
		int n = flow.length;
		/*
		 * // ith Person owes this total to all/few int[] outFlow = new int[n];
		 * // ith Person gets this total amount from all/few int[] inFlow = new
		 * int[n]; for (int i = 0; i < n; i++) { for (int j = 0; j < n; j++) {
		 * outFlow[i] = outFlow[i] + flow[i][j]; inFlow[j] = inFlow[j] +
		 * flow[i][j]; } } System.out.println(Arrays.toString(inFlow));
		 * System.out.println(Arrays.toString(outFlow));
		 */

		int[] netVal = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				netVal[i] = netVal[i] - flow[i][j];
				netVal[j] = netVal[j] + flow[i][j];
			}
		}

		// int[] netVal = { -8, -7, -6, 2, 2, 2, 2, 6, 7 };
		// int n = 9;
		// System.out.println(Arrays.toString(netVal));
		int f = 0;
		int e = n - 1;
		while (f < e) {
			if (netVal[f] + netVal[e] > 0) {
				System.out.println(f + " -> " + e + " : " + (-1 * netVal[f]));
				netVal[e] = netVal[e] + netVal[f];
				netVal[f] = 0;
				f++;
			} else if (netVal[f] + netVal[e] < 0) {
				System.out.println(f + " -> " + e + " : " + netVal[e]);
				netVal[f] = netVal[f] + netVal[e];
				netVal[e] = 0;
				e--;
			} else {
				System.out.println(f + " -> " + e + " : " + netVal[e]);
				f++;
				e--;
			}
			// System.out.println(Arrays.toString(netVal));
		}
	}

	public static void main(String[] args) {
		int[][] flow = { { 0, 1000, 2000 }, { 0, 0, 5000 }, { 0, 0, 0 } };
		minCashFlow(flow);
	}

}
