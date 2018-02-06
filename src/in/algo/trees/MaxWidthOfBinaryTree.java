package in.algo.trees;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MaxWidthOfBinaryTree {

	static class Node {
		int val;
		Node left;
		Node right;

		Node(int val) {
			this.val = val;
		}
	}

	public static int maxWidth(Node root) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		if (root == null)
			return 0;
		maxWidth_Aux(root, hm, 1);
		int retValMaxWidth = 0;
		for(Integer i : hm.values())
			retValMaxWidth = Math.max(retValMaxWidth, i);
		return retValMaxWidth;
	}

	public static void maxWidth_Aux(Node root, HashMap<Integer, Integer> hm, int level) {
		if (root == null)
			return;
		if (hm.containsKey(level))
			hm.put(level, hm.get(level) + 1);
		else
			hm.put(level, 1);
		maxWidth_Aux(root.left, hm, level + 1);
		maxWidth_Aux(root.right, hm, level + 1);
	}

	public static void main(String[] args) {
		Node n = new Node(1);
		n.left = new Node(2);
		n.right = new Node(3);
		n.left.left = new Node(4);
		n.left.right = new Node(5);
		n.right.right = new Node(8);
		n.right.right.left = new Node(6);
		n.right.right.right = new Node(7);
		System.out.println(maxWidth(n));
	}

}
