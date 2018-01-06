package in.algo.greedy;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class mColoringGreedy {

	static class Graph {
		int V;
		LinkedList<Integer>[] adjList;

		Graph(int V) {
			this.V = V;
			adjList = new LinkedList[V];
			for (int i = 0; i < V; i++)
				adjList[i] = new LinkedList<Integer>();
		}

		void addEdge(int u, int v) {
			adjList[u].add(v);
			adjList[v].add(u);
		}

		int getFirstAvailable(boolean[] arr) {
			int n = arr.length;
			for (int i = 0; i < n; i++)
				if (arr[i])
					return i;
			return -1; // This ideally shouldn't execute.
		}

		void mColoring() {
			// traversed Array for keeping track of traversed vertices
			boolean[] traversed = new boolean[V];
			Arrays.fill(traversed, false);
			// Used at each vertex to mark the color not available
			boolean[] available = new boolean[V];
			// Result will store the ith vertex color
			int[] result = new int[V];
			// Initially all are uncolored
			Arrays.fill(result, -1);
			// Do a BFS and color all the vertices.
			LinkedList<Integer> q = new LinkedList<Integer>();
			q.addLast(0);
			traversed[0] = true;
			while (!q.isEmpty()) {
				int cur_vertex = q.removeFirst();
				Arrays.fill(available, true);
				// Get all the adjacent vertices
				Iterator<Integer> i = adjList[cur_vertex].iterator();
				while (i.hasNext()) {
					int cur_adj_vertex = i.next();
					// If -1 not yet colored. If not -1, that is already used,
					// hence can't be used now.
					if (result[cur_adj_vertex] != -1)
						available[result[cur_adj_vertex]] = false;
					// If not traversed add it the BFS Queue
					if (traversed[cur_adj_vertex] == false) {
						q.addLast(cur_adj_vertex);
						traversed[cur_adj_vertex] = true;
					}
				}
				// Get the first available color.
				int color = getFirstAvailable(available);
				result[cur_vertex] = color;
			}
			System.out.println(Arrays.toString(result));
		}

	}

	public static void main(String[] args) {
		Graph g1 = new Graph(5);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(1, 2);
		g1.addEdge(1, 3);
		g1.addEdge(2, 3);
		g1.addEdge(3, 4);
		System.out.println("Coloring of graph 1");
		g1.mColoring();

		System.out.println();
		Graph g2 = new Graph(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(1, 2);
		g2.addEdge(1, 4);
		g2.addEdge(2, 4);
		g2.addEdge(4, 3);
		System.out.println("Coloring of graph 2 ");
		g2.mColoring();
	}

}
