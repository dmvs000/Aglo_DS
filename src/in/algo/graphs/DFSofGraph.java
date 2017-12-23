package in.algo.graphs;

import java.util.Iterator;
import java.util.LinkedList;

public class DFSofGraph {

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
			adj[u].add(v);
		}

		private boolean[] traversed;

		void DFS(int v) {
			traversed = new boolean[V];
			DFSAux(v);
		}

		void DFSAux(int v) {
			System.out.print(v+"\t");
			int curVertex = v;
			traversed[curVertex] = true;
			Iterator<Integer> i = adj[curVertex].iterator();
			while(i.hasNext())
			{
				int adjVertex = i.next();
				if(traversed[adjVertex]==false)
					DFSAux(adjVertex);
			}
		}
	}

	public static void main(String[] args) {
		//Directed Graph
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		g.DFS(0);
	}

}
