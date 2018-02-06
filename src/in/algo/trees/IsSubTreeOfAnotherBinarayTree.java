package in.algo.trees;

public class IsSubTreeOfAnotherBinarayTree {

	static class Node {
		int val;
		Node left;
		Node right;

		Node(int val) {
			this.val = val;
		}
	}

	public static boolean isSubTree = false;

	public static void findIfSubTree(Node root1, Node root2) {
		if (root1 == null)
			return;
		if (root1.val == root2.val) {
			if (findIfSubTreeAux(root1, root2))
				isSubTree = true;
		}
		findIfSubTree(root1.left, root2);
		findIfSubTree(root1.right, root2);
	}

	public static boolean findIfSubTreeAux(Node root1, Node root2) {
		if (root1 == null && root2 == null)
			return true;
		if ((root1 != null && root2 == null) || (root1 == null && root2 != null))
			return false;
		if (root1.val != root2.val)
			return false;
		else {
			return (findIfSubTreeAux(root1.left, root2.left) && findIfSubTreeAux(root1.right, root2.right));
		}
	}

	public static void main(String[] args) {
		Node n = new Node(26);
		n.left = new Node(10);
		n.left.left = new Node(4);
		n.left.right = new Node(6);
		n.left.left.right = new Node(30);
		n.right = new Node(3);
		n.right.right = new Node(3);
		Node sn = new Node(10);
		sn.left = new Node(4);
		sn.right = new Node(6);
		sn.left.right = new Node(30);
		findIfSubTree(n, sn);
		System.out.println(isSubTree);
	}

}
