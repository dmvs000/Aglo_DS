package in.algo.dynamic;

/*
 * https://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
 */
public class FloydWarshallAllPairsShortestPath {
	static int INF = Integer.MAX_VALUE;

	public static void Func(int[][] graph) {
		int V = graph.length;
		// Initially the dist Matrix is same as adjMatrix
		int[][] dist = graph.clone();
		// Start from k = 0 and go uptil k = V-1 last vertex
		// When we will be solving when k is intermediate vertex, we'd already
		// have solved for 0 to k-1 as intermediate vertex
		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					// If there exists no path from i to k (OR) no path from k
					// to j
					// Then, we cannot go from i to j via k.
					if (dist[i][k] == INF || dist[k][j] == INF)
						continue;
					// If there exists and path from k and it is less than what
					// we have till now, update it dist matrix
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		// Print the Shortest Path Matrix
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (dist[i][j] == INF)
					System.out.print("INF" + "\t");
				else
					System.out.print(dist[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// Adjacency Matrix Representation
		int[][] graph = { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 }, { INF, INF, INF, 0 } };
		Func(graph);
	}
}
