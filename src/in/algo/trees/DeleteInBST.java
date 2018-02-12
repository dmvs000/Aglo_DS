package in.algo.trees;

public class DeleteInBST {

	class BinarySearchTree {
		class Node {
			Node left;
			Node right;
			int val;

			Node(int val) {
				this.val = val;
			}
		}

		public Node root = null;

		public void insert(int value) {
			if (root == null) {
				root = new Node(value);
				return;
			}
			insertAux(root, value);
		}

		public Node insertAux(Node curNode, int val) {
			if (curNode == null)
			{
				return new Node(val);
			}
			if (val > curNode.val)
				curNode.right = insertAux(curNode.right, val);
			else if (val < curNode.val)
				curNode.left = insertAux(curNode.left, val);
			return curNode;
		}

		public void deleteKey(int val) {
			if (root == null)
				return;
			root = delete_Aux(root,val);
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

		public Node delete_Aux(Node curNode, int val) {
			if (curNode == null)
				return null;
			if (curNode.val == val) {
				if (curNode.left == null && curNode.right == null) {
					curNode = null;
					return curNode;
				}
				if (curNode.left != null && curNode.right != null) {
					Node InOrdSuc = findInOrderSuc(curNode);
					curNode.val = InOrdSuc.val;
					curNode.right = delete_Aux(curNode.right, curNode.val);
					return curNode;
				} else {
					if (curNode.left == null) {
						return curNode.right;
					} else {
						return curNode.left;
					}
				}
			}
			curNode.left = delete_Aux(curNode.left, val);
			curNode.right = delete_Aux(curNode.right, val);
			return curNode;
		}
		
		public void inorder()
		{
			inOrderAux(root);
		}
		
		public void inOrderAux(Node cur)
		{
			if(cur==null)
				return;
			inOrderAux(cur.left);
			System.out.print(cur.val+" ");
			inOrderAux(cur.right);
		}
	}

	public static void main(String[] args) {
		DeleteInBST dbst = new DeleteInBST();
		BinarySearchTree tree = dbst.new BinarySearchTree();
		tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        System.out.println("Inorder traversal of the given tree");
        tree.inorder();
 
        System.out.println("\nDelete 20");
        tree.deleteKey(20);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();
 
        System.out.println("\nDelete 30");
        tree.deleteKey(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();
 
        System.out.println("\nDelete 50");
        tree.deleteKey(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();
	}

}
