package in.algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KruskalsMST {

	//Contains ParrentARR for Union and Find
	static int[] parentArr;
	static int V;
	//Contains Source, Destination and Weight.
	static class Edge implements Comparable<Edge>
	{
		int src;
		int dest;
		int weight;
		Edge(int s, int d, int w)
		{
			this.src = s;
			this.dest = d;
			this.weight = w;
		}
		@Override
		public int compareTo(Edge o) {
			//Sorting based on weight. Low weight edges comes before.
			// TODO Auto-generated method stub
			if(this.weight<o.weight)
				return -1;
			else if(this.weight==o.weight)
				return 0;
			else
				return 1;
		}
		
	}
	
	public static void findMST(ArrayList<Edge> al)
	{
		Collections.sort(al);
		int totalWeight = 0;
		int count = 0;
		int i = 0;
		while(count<=V-2)
		{
			int tempSrc = al.get(i).src;
			int tempDest = al.get(i).dest;
			int tempWeight = al.get(i).weight;
			if(find(tempSrc)!=find(tempDest))
			{
				System.out.println(tempSrc+" "+tempDest+" "+tempWeight);
				union(tempSrc,tempDest);
				totalWeight+=tempWeight;
				count++;
			}
			i++;
		}
	}
	
	public static void union(int src, int dest)
	{
		int src_Parent = find(src);
		int dest_Parent = find(dest);
		parentArr[dest_Parent]=src_Parent;
	}
	
	public static int find(int vertex)
	{
		int temp = vertex;
		while(parentArr[vertex]!=-1)
			vertex = parentArr[vertex];
		if(temp!=vertex)
			parentArr[temp] = vertex;
		return vertex;
	}
	
	public static void main(String[] args)
	{
		/*
		        10
		   0--------1
		   |  \     |
		  6|   5\   |15
		   |      \ |
		   2--------3
       			4      */
		V = 4;
		int[] srcArr = 		{0,0,0,1,2};
		int[] destArr = 	{1,2,3,3,3};
		int[] weightArr = 	{10,6,5,15,4};
		ArrayList<Edge> al = new ArrayList<Edge>();
		parentArr = new int[V];
		Arrays.fill(parentArr, -1);
		for(int i=0;i<srcArr.length;i++)
			al.add(new Edge(srcArr[i],destArr[i],weightArr[i]));
		findMST(al);
		for(int i=0;i<parentArr.length;i++)
			System.out.print(parentArr[i]+" ");
	}
	
}
