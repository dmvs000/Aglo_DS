package in.algo.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/*
 * https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 */
public class DetectCycleInDirectedGraph {

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
		private boolean[] isAncestor;
		private boolean formsCycle = false;

		void DFS_DetectCycle() {
			traversed = new boolean[V];
			isAncestor = new boolean[V];
			for (int i = 0; i < V; i++) {
				if (!traversed[i]) {
					traversed[i] = true;
					isAncestor[i] = true;
					DFS_Aux(i);
					isAncestor[i] = false;
				}
			}
			System.out.println("Cycle : " + formsCycle);
		}

		void DFS_Aux(int curVertex) {
			Iterator<Integer> i = adj[curVertex].iterator();
			while (i.hasNext()) {
				int adjVertex = i.next();
				if (isAncestor[adjVertex])
					formsCycle = true;
				if (!traversed[adjVertex]) {
					traversed[adjVertex] = true;
					isAncestor[adjVertex] = true;
					DFS_Aux(adjVertex);
					isAncestor[adjVertex] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		// g.addEdge(2, 0);
		g.addEdge(2, 3);
		// g.addEdge(3, 3);
		g.DFS_DetectCycle();
	}

}
