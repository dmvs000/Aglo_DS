package in.algo.graphs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class LongestPathDAG {

	static class Graph {

		static class Edge {
			int src;
			int dest;
			int weight;

			Edge(int s, int d, int w) {
				this.src = s;
				this.dest = d;
				this.weight = w;
			}
		}

		private int V;
		private LinkedList<Edge> adj[];

		Graph(int v) {
			this.V = v;
			adj = new LinkedList[V];
			for (int i = 0; i < V; i++)
				adj[i] = new LinkedList<Edge>();
		}

		void addEdges(int u, int v, int w) {
			adj[u].add(new Edge(u,v,w));
		}

		Stack<Integer> topologicalSort() {
			Stack<Integer> stack = new Stack<Integer>();
			boolean[] traversed = new boolean[V];
			for (int i = 0; i < V; i++) {
				if (traversed[i] == false)
					topologicalSortAux(i, stack, traversed);
			}
			//while (!stack.isEmpty())
			//	System.out.print(stack.pop() + "\t");
			return stack;
		}

		void topologicalSortAux(int curVertex, Stack<Integer> stack, boolean[] traversed) {
			traversed[curVertex] = true;
			LinkedList<Edge> adjListOfCurVertex = adj[curVertex];
			Iterator<Edge> i = adjListOfCurVertex.iterator();
			while (i.hasNext()) {
				int temp = i.next().dest;
				if (!traversed[temp])
					topologicalSortAux(temp, stack, traversed);
			}
			stack.push(curVertex);
		}

		public void LongestPath(int start_vertex) {
			Stack<Integer> stack = topologicalSort();
			int[] longestPathArr = new int[V];
			Arrays.fill(longestPathArr, Integer.MIN_VALUE);
			longestPathArr[start_vertex] = 0;
			while(!stack.isEmpty())
			{
				int cur_vertex = stack.pop();
				Iterator<Edge> i = adj[cur_vertex].iterator();
				while(longestPathArr[cur_vertex]!=Integer.MAX_VALUE&&i.hasNext())
				{
					Edge temp = i.next();
					if(longestPathArr[temp.dest]<longestPathArr[cur_vertex]+temp.weight)
						longestPathArr[temp.dest] = longestPathArr[cur_vertex]+temp.weight;
				}
			}
			for(int i=0;i<V;i++)
				System.out.print(longestPathArr[i]+"\t");
			System.out.println();
		}
	}

	public static void main(String[] args) {
			Graph g = new Graph(6);
			g.addEdges(0, 1, 5);
			g.addEdges(0, 2, 3);
			g.addEdges(1, 3, 6);
			g.addEdges(1, 2, 2);
			g.addEdges(2, 4, 4);
			g.addEdges(2, 5, 2);
			g.addEdges(2, 3, 7);
			g.addEdges(3, 5, 1);
			g.addEdges(3, 4, -1);
			g.addEdges(4, 5, -2);
			g.LongestPath(1);
	}

}
