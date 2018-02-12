package in.algo.trees;

import in.algo.trees.DeleteInBST.BinarySearchTree.Node;

public class AVLTrees {

	class AVLTree {
		class Node {
			Node left;
			Node right;
			int val;
			int height;

			Node(int v) {
				this.val = v;
			}
		}

		Node root = null;

		public void insert(int value) {
			System.out.println("Inserting : " + value);
			if (root == null) {
				root = new Node(value);
				root.height = 1;
				return;
			}
			root = insert_Aux(root, value);
		}

		public Node insert_Aux(Node curNode, int val) {
			if (curNode == null) {
				curNode = new Node(val);
				curNode.height = 1;
				return curNode;
			}
			if (val < curNode.val) {
				curNode.left = insert_Aux(curNode.left, val);
				updateHeight(curNode);

			} else if (val > curNode.val) {
				curNode.right = insert_Aux(curNode.right, val);
				updateHeight(curNode);
			}
			curNode = checkAndReBalance(curNode);
			return curNode;
		}

		public void delete(int val) {
			if (root == null)
				return;
			root = deleteAux(root, val);
			//updateHeight(root);
			//root = checkAndReBalance(root);
		}

		public Node deleteAux(Node curNode, int val) {
			if (curNode == null) {
				return null;
			}
			if (curNode.val == val) {
				if (curNode.left == null && curNode.right == null) {
					curNode = null;
					return curNode;
				} else if (curNode.left != null && curNode.right != null) {
					curNode.val = findInOrderSuc(curNode).val;
					curNode.right = deleteAux(curNode.right, curNode.val);
				} else {
					if (curNode.left == null) {
						return curNode.right;
					} else if (curNode.right == null) {
						return curNode.left;
					}
				}
			}
			curNode.left = deleteAux(curNode.left, val);
			updateHeight(curNode.left);
			curNode.right = deleteAux(curNode.right, val);
			updateHeight(curNode.right);
			curNode = checkAndReBalance(curNode);
			return curNode;
		}

		public Node findInOrderSuc(Node curNode) {
			if (curNode == null)
				return null;
			if (curNode.right.left == null)
				return curNode.right;
			else {
				Node temp = curNode.right;
				while (temp.left != null) {
					temp = temp.left;
				}
				return temp;
			}
		}

		public void updateHeight(Node curNode) {
			if (curNode == null)
				return;
			int height = 1;
			int leftHeight = 0;
			int rightHeight = 0;
			if (curNode.left != null)
				leftHeight = curNode.left.height;
			if (curNode.right != null)
				rightHeight = curNode.right.height;
			height = Math.max(leftHeight, rightHeight) + height;
			curNode.height = height;
			// System.out.println("Height of "+curNode.val+" is
			// "+curNode.height);
		}

		public int getDiff(Node curNode) {
			if (curNode == null)
				return 0;
			int leftH = 0;
			int rightH = 0;
			if (curNode.left != null)
				leftH = curNode.left.height;
			if (curNode.right != null)
				rightH = curNode.right.height;
			return rightH - leftH;
		}

		public Node checkAndReBalance(Node curNode) {
			int diff = getDiff(curNode);
			System.out.println("Diff : " + curNode.val + " " + diff);
			if (diff > 1) // right
			{
				if (getDiff(curNode.right) > 0) // right-right case
				{
					System.out.println("right-right case");
					curNode = leftRotate(curNode);
				} else if (getDiff(curNode.right) <= 0) // right-left case
				{
					System.out.println("right-left case");
					curNode.right = rightRotate(curNode.right);
					curNode = leftRotate(curNode);
				}
			} else if (diff < -1) // left
			{
				if (getDiff(curNode.left) <= 0) // left -left case
				{
					System.out.println("left-left case");
					curNode = rightRotate(curNode);
				} else if (getDiff(curNode.left) > 0) // left-right case
				{
					System.out.println("left-right case");
					curNode.left = leftRotate(curNode.left);
					curNode = rightRotate(curNode);
				}
			}
			updateHeights(curNode);
			return curNode;
		}

		public int updateHeights(Node curNode) {
			if (curNode == null)
				return 0;
			curNode.height = Math.max(updateHeights(curNode.left), updateHeights(curNode.right)) + 1;
			return curNode.height;
		}

		public Node leftRotate(Node curNode) {
			Node z = curNode;
			Node y = z.right;
			Node yLeft = y.left;
			y.left = z;
			z.right = yLeft;
			return y;
		}

		public Node rightRotate(Node curNode) {
			Node z = curNode;
			Node y = z.left;
			Node yRight = y.right;
			y.right = z;
			z.left = yRight;
			return y;
		}

		public void preOrder(Node curNode) {
			if (curNode == null)
				return;
			// System.out.print(curNode.val + " " + curNode.height + " - ");
			System.out.print(curNode.val + " ");
			preOrder(curNode.left);
			preOrder(curNode.right);
		}
	}

	public static void main(String[] args) {
		AVLTrees trees = new AVLTrees();
		AVLTree tree = trees.new AVLTree();
		// Insert test
		/*
		 * tree.insert(10); tree.preOrder(tree.root); System.out.println();
		 * tree.insert(20); tree.preOrder(tree.root); System.out.println();
		 * tree.insert(30); tree.preOrder(tree.root); System.out.println();
		 * tree.insert(40); tree.preOrder(tree.root); System.out.println();
		 * tree.insert(50); tree.preOrder(tree.root); System.out.println();
		 * tree.insert(25); tree.preOrder(tree.root); System.out.println();
		 */

		// Deletion test
		/* Constructing tree given in the above figure */
		tree.insert(9);
		tree.insert(5);
		tree.insert(10);
		tree.insert(0);
		tree.insert(6);
		tree.insert(11);
		tree.insert(-1);
		tree.insert(1);
		tree.insert(2);

		/*
		 * The constructed AVL Tree would be 9 / \ 1 10 / \ \ 0 5 11 / / \ -1 2
		 * 6
		 */
		System.out.println("Preorder traversal of " + "constructed tree is : ");
		tree.preOrder(tree.root);

		tree.delete(10);

		/*
		 * The AVL Tree after deletion of 10 1 / \ 0 9 / / \ -1 5 11 / \ 2 6
		 */
		System.out.println("");
		System.out.println("Preorder traversal after " + "deletion of 10 :");
		tree.preOrder(tree.root);
	}

}
