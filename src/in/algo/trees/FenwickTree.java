package in.algo.trees;

import java.util.Arrays;

/*
 * https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 */
public class FenwickTree {

	static int[] fenwickTree;
	static int n;

	static int getParent(int index) {
		/*
		 * 2's complement -> AND with original number -> subtract result from
		 * original
		 */
		return index - (index & (-index));
	}

	static int getNext(int index) {
		/*
		 * 2's complement -> AND with original number -> add result to original
		 */
		return index + (index & (-index));
	}

	static void update(int index, int val) {
		index = index + 1;
		while (index <= n) {
			fenwickTree[index] += val;
			index = getNext(index);
		}
	}

	static int getPrefixSum(int index) {
		index = index + 1;
		int sum = 0;
		while (index > 0) {
			sum = sum + fenwickTree[index];
			index = getParent(index);
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3 };
		n = arr.length;
		fenwickTree = new int[n + 1];
		for (int i = 0; i < n; i++) {
			update(i, arr[i]);
			System.out.println(Arrays.toString(fenwickTree));
		}
		for (int i = 0; i < n; i++)
			System.out.print(getPrefixSum(i)+" ");
		System.out.println();
		System.out.println(Arrays.toString(fenwickTree));
	}

}
