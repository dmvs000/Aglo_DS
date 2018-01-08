package in.algo.greedy;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PrimsMST {

	static class Graph {
		class Vertex {
			int src;
			int dest;
			int weight;

			Vertex(int dest, int weight) {
				this.dest = dest;
				this.weight = weight;
			}
		}

		LinkedList<Vertex>[] adjList;
		int V; // No of vertices

		Graph(int V) {
			this.V = V;
			adjList = new LinkedList[V];
			for (int i = 0; i < V; i++)
				adjList[i] = new LinkedList<Vertex>();
		}

		void addEdge(int vertex, int dest, int weight) {
			adjList[vertex].add(new Vertex(dest, weight));
			adjList[dest].add(new Vertex(vertex, weight));
		}

		class PrimsKeyVertex implements Comparable<PrimsKeyVertex> {
			int key;
			int vertex;

			PrimsKeyVertex(int key, int vertex) {
				this.key = key;
				this.vertex = vertex;
			}

			@Override
			public int compareTo(PrimsKeyVertex kv) {
				if (this.key < kv.key)
					return -1;
				else if (this.key > kv.key)
					return 1;
				else
					return 0;
			}

			@Override
			public boolean equals(Object o) {
				if (!(o instanceof PrimsKeyVertex))
					return false;
				PrimsKeyVertex temp = (PrimsKeyVertex) o;
				if (temp.vertex == this.vertex)
					return true;
				else
					return false;
			}
		}

		void PrimsMST() {
			int[] key = new int[V];	//Store the Key Values
			Arrays.fill(key, -1);	//Initially all -1
			boolean[] MST = new boolean[V];	//MST Set
			int[] parent = new int[V];	//From which vertex is this coming from
			PriorityQueue<PrimsKeyVertex> pq = new PriorityQueue<PrimsKeyVertex>();
			pq.add(new PrimsKeyVertex(0, 0));
			key[0] = 0;
			int MSTSum = 0;
			while (!pq.isEmpty()) {
				PrimsKeyVertex temp = pq.poll();
				int Vertex = temp.vertex;
				int Key = temp.key;
				MSTSum+=Key;
				MST[Vertex] = true;
				//System.out.println("curVertex : "+Vertex+" curKey : "+Key);
				System.out.println(parent[Vertex]+"->"+Vertex);
				Iterator<Vertex> i = adjList[Vertex].iterator();
				while (i.hasNext()) {
					Vertex tempVertex = i.next();
					if (MST[tempVertex.dest] == false) {
						if (key[tempVertex.dest] == -1) {
							pq.add(new PrimsKeyVertex(tempVertex.weight, tempVertex.dest));
							key[tempVertex.dest] = tempVertex.weight;
							parent[tempVertex.dest] = Vertex;
						} else if (key[tempVertex.dest] > tempVertex.weight) {
							//System.out.println("Vertex 	: "+tempVertex.dest);
							//System.out.println("Old Key : "+key[tempVertex.dest]);
							//System.out.println("New Key : "+tempVertex.weight);
							pq.remove(new PrimsKeyVertex(0, tempVertex.dest));
							pq.add(new PrimsKeyVertex(tempVertex.weight, tempVertex.dest));
							key[tempVertex.dest] = tempVertex.weight;
							parent[tempVertex.dest] = Vertex;
						}
					}
				}
			}
			System.out.println("Minimum Spanning Tree : "+MSTSum);
			//System.out.println(Arrays.toString(parent));
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(9);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 7, 8);
		g.addEdge(1, 2, 8);
		g.addEdge(1, 7, 11);
		g.addEdge(2, 3, 7);
		g.addEdge(2, 8, 2);
		g.addEdge(2, 5, 4);
		g.addEdge(3, 4, 9);
		g.addEdge(3, 5, 14);
		g.addEdge(4, 5, 10);
		g.addEdge(5, 6, 2);
		g.addEdge(6, 7, 1);
		g.addEdge(6, 8, 6);
		g.addEdge(7, 8, 7);
		g.PrimsMST();
	}

}