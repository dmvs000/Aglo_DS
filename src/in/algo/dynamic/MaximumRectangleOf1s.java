package in.algo.dynamic;

import java.util.Stack;

public class MaximumRectangleOf1s {

	public static int findMaxRect(int[][] arr) {
		int rows = arr.length;
		int cols = arr[0].length;
		int[] temp = new int[cols];
		int maxSize = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (arr[i][j] == 1)
					temp[j] = temp[j] + 1;
				else
					temp[j] = 0;
			}
			maxSize = Math.max(maxSize, findMaxRectHistogram(temp));
		}
		return maxSize;
	}

	public static int findMaxRectHistogram(int[] temp) {
		Stack<Integer> heights = new Stack<Integer>();
		Stack<Integer> positions = new Stack<Integer>();
		int maxSize = 0;
		int cur = 0;
		int n = temp.length;
		while (cur < n) {
			if (heights.isEmpty()) {
				heights.push(temp[cur]);
				positions.push(cur);
			} else if (heights.peek() >= temp[cur]) {
				positions.push(0);
				while (!heights.isEmpty() && (heights.peek() >= temp[cur])) {
					int popped_height = heights.pop();
					int peeked_pos = positions.peek();
					maxSize = Math.max(maxSize, popped_height * (cur - peeked_pos));
				}
				heights.push(temp[cur]);
			}
			cur++;
		}
		while (!heights.isEmpty())
			maxSize = Math.max(maxSize, heights.pop() * (cur - positions.pop()));
		return maxSize;
	}

	public static void main(String[] args) {
		int[][] arr = { { 0, 1, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 } };
		System.out.println(findMaxRect(arr));
	}

}
