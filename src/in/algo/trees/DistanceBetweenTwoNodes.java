package in.algo.trees;
public class DistanceBetweenTwoNodes {
	
	static class Node {
		Node left;
		Node right;
		int val;

		Node(int v) {
			this.val = v;
		}
	}
	
	//Way to get the LCA.
	//If one lies beneath another. Returns the first found Node and hence that is LCA
	//If they lie on different subTrees, return the LCA
	//This assumes that the N1 and N2 are present in the array. If not, the answer is not accurate.
	public static Node LCA(Node root, Node n1, Node n2)
	{
		if(root==null)
			return null;
		if(n1.equals(root))
			return n1;
		if(n2.equals(root))
			return n2;
		Node ret_Left = LCA(root.left,n1,n2);
		Node ret_Right = LCA(root.right,n1,n2);
		if(ret_Left!=null&&ret_Right!=null)
			return root;
		if(ret_Left!=null&&ret_Right==null)
			return ret_Left;
		if(ret_Right!=null&&ret_Left==null)
			return ret_Right;
		return null;
	}
	
	//Find the distance between two Nodes.
	//Get the LCA first.
	public static int findNodesDistance(Node root, Node n1, Node n2)
	{
		if(root==null||n1==null||n2==null)
			return 0;
		Node LCA = LCA(root,n1,n2);
		return findDistance(LCA,n1,0)+findDistance(LCA,n2,0);
	}
	
	//Find Distance between a Node root and another node which could be beneath it.
	public static int findDistance(Node root, Node n, int dist)
	{
		if(root==null)
			return 0;
		if(root.equals(n))
			return dist;
		return findDistance(root.left,n,dist+1)+findDistance(root.right,n,dist+1);
	}
	
	//Given two nodes. Find the distance between them.
	//Find LCA of the two nodes and then find the distance between Node1 & LCA and Node2 & LCA
	public static void main(String[] args)
	{
		Node  root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);
        //4,5
        System.out.println(findNodesDistance(root,root.left.left,root.left.right));
        //4,6
        System.out.println(findNodesDistance(root,root.left.left,root.right.left));
        //3,4
        System.out.println(findNodesDistance(root,root.right,root.left.left));
        //2,4
        System.out.println(findNodesDistance(root,root.left,root.left.left));
        //8,5
        System.out.println(findNodesDistance(root,root.right.left.right,root.left.right));
	}
}

