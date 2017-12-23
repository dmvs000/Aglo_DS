package in.algo.graphs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class StronglyConnectedComponents {

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
			
			public void findSCC()
			{
				boolean[] traversed = new boolean[V];
				Stack<Integer> stack = new Stack<Integer>();
				for(int i=0;i<V;i++)
				{
					if(traversed[i]==false)
						DFS(i,stack,traversed);
				}
				System.out.println(stack.toString());
				transposeGraph();
				Arrays.fill(traversed, false);
				int compCount = 0;
				while(!stack.isEmpty())
				{
					int temp = stack.pop();
					if(traversed[temp]==false)
					{
						System.out.println("Component : "+(++compCount));
						DFSAux(temp,traversed);
						System.out.println();
					}
				}
			}
			
			public void DFSAux(int curVertex, boolean[] traversed)
			{
				System.out.print(curVertex+"\t");
				LinkedList<Integer> adjVertexList = adj[curVertex];
				traversed[curVertex] = true;
				Iterator<Integer> i = adjVertexList.iterator();
				while(i.hasNext())
				{
					int temp = i.next();
					if(traversed[temp]==false)
						DFSAux(temp,traversed);
				}
			}
			
			void transposeGraph()
			{
				LinkedList<Integer>[] adjTemp = new LinkedList[V];
				for(int i=0;i<V;i++)
					adjTemp[i] = new LinkedList<Integer>();
				for(int i=0;i<V;i++)
				{
					LinkedList<Integer> tempList = adj[i];
					Iterator<Integer> iter = tempList.iterator();
					while(iter.hasNext())
					{
						int t = iter.next();
						adjTemp[t].add(i);
					}
				}
				adj = adjTemp;
				/*for(int i=0;i<V;i++)
				{
					System.out.print(i+" : ");
					Iterator iii = adj[i].iterator();
					while(iii.hasNext())
						System.out.print(iii.next()+" ");
					System.out.println();
				}*/
			}
			
			public void DFS(int curVertex, Stack<Integer> stack, boolean[] traversed)
			{
				LinkedList<Integer> adjVertexList = adj[curVertex];
				traversed[curVertex] = true;
				Iterator<Integer> i = adjVertexList.iterator();
				while(i.hasNext())
				{
					int temp = i.next();
					if(traversed[temp]==false)
						DFS(temp,stack,traversed);
				}
				stack.push(curVertex);
			}
			
		}
		
		public static void main(String[] args)
		{
			Graph g = new Graph(5);
			g.addEdge(1, 0);
		    g.addEdge(0, 2);
		    g.addEdge(2, 1);
		    g.addEdge(0, 3);
		    g.addEdge(3, 4);
		    g.findSCC();
		}

}
