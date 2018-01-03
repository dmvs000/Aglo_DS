package in.algo.greedy;

import java.util.PriorityQueue;

public class HuffmanCoding {

	static class Node implements Comparable<Node> {
		char c;
		int val;
		Node left;
		Node right;

		Node(char c, int v) {
			this.c = c;
			this.val = v;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (this.val < o.val)
				return -1;
			else if (this.val == o.val)
				return 0;
			else
				return 1;
		}
	}

	public static void HuffmanCoding(char[] cArr, int[] freq) {
		int n = cArr.length;
		// Take a PriortyQueue for getting two Mins Everytime.
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (int i = 0; i < n; i++)
			pq.add(new Node(cArr[i], freq[i]));
		while (pq.size() != 1) {
			// Take two min Nodes, create new One and Push it to PQ.
			Node t1 = pq.poll();
			Node t2 = pq.poll();
			Node inNode = new Node(' ', t1.val + t2.val);
			// Min of two will be left and the other to right.
			inNode.left = t1;
			inNode.right = t2;
			pq.add(inNode);
		}
		DFS(pq.peek(), "");
	}

	public static void DFS(Node cur, String curStr) {
		// If reached the leaf Node, Print the encoded String and return
		if (cur.c != ' ') {
			System.out.println(cur.c + " " + curStr);
			return;
		}
		// Add zero when going towards left
		DFS(cur.left, curStr + "0");
		// Add one when going towards right
		DFS(cur.right, curStr + "1");
	}

	public static void main(String[] args) {
		char[] charArr = { 'a', 'b', 'c', 'd', 'e', 'f' };
		int[] freq = { 5, 9, 12, 13, 16, 45 };
		HuffmanCoding(charArr, freq);
	}

}