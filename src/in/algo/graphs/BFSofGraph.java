package in.algo.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFSofGraph {

	static class Graph {

		private int V;
		private LinkedList<Integer> adj[];

		Graph(int v) {
			this.V = v;
			adj = new LinkedList[V];
			for (int i = 0; i < V; i++)
				adj[i] = new LinkedList<Integer>();
		}

		public void addEdge(int u, int v) {
			adj[u-1].add(v-1);
		}

		private boolean[] traversed;

		void BFS(int v) {
			traversed = new boolean[V];
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(v);
			traversed[v] = true;
			while(!queue.isEmpty())
			{
				int curVertex = queue.remove();
				System.out.print(curVertex+"\t");
				LinkedList<Integer> adjVertexList = adj[curVertex];
				Iterator<Integer> i = adjVertexList.iterator();
				while(i.hasNext())
				{
					int temp = i.next();
					if(!traversed[temp])
					{
						traversed[temp] = true;
						queue.add(temp);
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 5);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 5);
		g.addEdge(4, 6);
		g.addEdge(5, 6);
		g.BFS(1-1);
	}

}
