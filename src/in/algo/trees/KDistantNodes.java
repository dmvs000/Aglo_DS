package in.algo.trees;

public class KDistantNodes {

	static class Node {
		Node left;
		Node right;
		int val;

		Node(int v) {
			this.val = v;
		}
	}

	public static int findAllNodes(Node root, Node target, int k) {
		//-1 means. We didn't find it on left or right.
		if (root == null)
			return -1;
		//If found, get K Distance Nodes in subTree with target as subTree.
		if (root.equals(target)) {
			findKDistSubTree(target, k);
			//Return (k-1) So, that we can find the other distant nodes.
			return k - 1;
		}
		int left = findAllNodes(root.left, target, k);
		int right = findAllNodes(root.right, target, k);
		//If both don't have the target node or they might be more than K Distance from here. So, just bounce back.
		if (left == -1 && right == -1)
			return -1;
		//If zero, this is one of the node. Print it and return -1 as parents will exceed the K distance.
		if (left == 0 || right == 0) {
			System.out.print(root.val + "\t");
			return -1;
		}
		//Check if the target was found in left or right and accordinlgy find k Distance form the opposite side.
		if (left != -1 && right == -1) {
			findKDistSubTree(root.right, left - 1);
			return left - 1;
		}
		if (left == -1 && right != -1) {
			findKDistSubTree(root.left, right - 1);
			return right - 1;
		}
		return -1;
	}

	public static void findKDistSubTree(Node root, int k) {
		//Base.
		if (root == null)
			return;
		//If k is zero. This is K Distant Node.
		if (k == 0) {
			System.out.print(root.val + "\t");
			return;
		}
		//Recur for left and right subTree.
		findKDistSubTree(root.left, k - 1);
		findKDistSubTree(root.right, k - 1);
	}

	public static void main(String[] args) {
		Node root = new Node(20);
		root.left = new Node(8);
		root.right = new Node(22);
		root.left.left = new Node(4);
		root.left.right = new Node(12);
		root.left.right.left = new Node(10);
		root.left.right.right = new Node(14);
		Node target = root.left.right;
		int k = 2;
		findAllNodes(root, target, k);
	}

}
