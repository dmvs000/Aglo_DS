package in.algo.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class TopologicalSort {

	static class Graph {

		private int V;
		private LinkedList<Integer> adj[];

		Graph(int v) {
			this.V = v;
			adj = new LinkedList[V];
			for (int i = 0; i < V; i++)
				adj[i] = new LinkedList<Integer>();
		}

		void addEdges(int u, int v) {
			adj[u].add(v);
		}

		void topologicalSort() {
			Stack<Integer> stack = new Stack<Integer>();
			boolean[] traversed = new boolean[V];
			for(int i=0;i<V;i++)
			{
				if(traversed[i]==false)
					topologicalSortAux(i,stack,traversed);
			}
			while(!stack.isEmpty())
				System.out.print(stack.pop()+"\t");
		}

		void topologicalSortAux(int curVertex, Stack<Integer> stack, boolean[] traversed) {
			traversed[curVertex] = true;
			LinkedList<Integer> adjListOfCurVertex = adj[curVertex];
			Iterator<Integer> i = adjListOfCurVertex.iterator();
			while(i.hasNext())
			{
				int temp = i.next();
				if(!traversed[temp])
					topologicalSortAux(temp,stack,traversed);
			}
			stack.push(curVertex);
		}

	}

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdges(5, 2);
		g.addEdges(5, 0);
		g.addEdges(4, 0);
		g.addEdges(4, 1);
		g.addEdges(2, 3);
		g.addEdges(3, 1);
		g.topologicalSort();
	}

}
