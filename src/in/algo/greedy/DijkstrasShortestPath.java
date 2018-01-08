package in.algo.greedy;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

class DijkstraShortestPath {

	static class Graph {

		class Vertex {
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

		class DSPKeyVertex implements Comparable<DSPKeyVertex> {
			int key;
			int vertex;

			DSPKeyVertex(int key, int vertex) {
				this.key = key;
				this.vertex = vertex;
			}

			@Override
			public int compareTo(DSPKeyVertex kv) {
				if (this.key < kv.key)
					return -1;
				else if (this.key > kv.key)
					return 1;
				else
					return 0;
			}

			@Override
			public boolean equals(Object o) {
				if (!(o instanceof DSPKeyVertex))
					return false;
				DSPKeyVertex temp = (DSPKeyVertex) o;
				if (temp.vertex == this.vertex)
					return true;
				else
					return false;
			}
		}

		void dijkstrasShortestPath(int src) {
			int[] key = new int[V]; // Array that stores the Key Values
			Arrays.fill(key, -1); // Initially All Key Values are '-1'
			boolean[] sptSet = new boolean[V]; // True indicates that the
												// verticies are added to SPT
												// set
			int[] shortestPath = new int[V]; // Store the shortestPaths from
												// source to all other vertices.
			PriorityQueue<DSPKeyVertex> pq = new PriorityQueue<DSPKeyVertex>();
			pq.add(new DSPKeyVertex(0, 0));
			while (!pq.isEmpty()) {
				//System.out.println(Arrays.toString(shortestPath));
				//System.out.println(pq.size());
				DSPKeyVertex curKeyVertex = pq.poll();
				int curKey = curKeyVertex.key;
				int curVertex = curKeyVertex.vertex;
				shortestPath[curVertex] = curKey;
				sptSet[curVertex] = true;
				Iterator<Vertex> i = adjList[curVertex].iterator();
				while (i.hasNext()) {
					Vertex temp = i.next();
					if (sptSet[temp.dest] == false) {
						if (key[temp.dest] == -1) {
							pq.add(new DSPKeyVertex(temp.weight + curKey, temp.dest));
							key[temp.dest] = temp.weight + curKey;
						} else {
							if (key[temp.dest] > temp.weight + curKey) {
								pq.remove(new DSPKeyVertex(0, temp.dest));
								pq.add(new DSPKeyVertex(temp.weight + curKey, temp.dest));
								key[temp.dest] = temp.weight + curKey;
							}
						}
					}
				}
			}
			System.out.println(Arrays.toString(shortestPath));
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
		g.dijkstrasShortestPath(0);
	}
}
