package in.algo.dynamic;

import java.util.ArrayList;
import java.util.Collections;

/*
 * https://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
 */
public class BoxStackingProblem {
	
	//Box Object. Implements Comparable
	static class Box implements Comparable<Box>
	{
		int l,w,h;
		//Initialize length, width and height.
		Box(int l, int w, int h)
		{
			this.l=l;this.w=w;this.h=h;
		}
		
		@Override
		public int compareTo(Box o) {
			//If length is same, check the width.
			if(this.l==o.l)
			{
				if(this.w<o.w)
					return 1;
				else if(this.w==o.w)
					return 0;
				else
					return -1;
			}
			//Check length. And sort in descending order.
			else
			{
				if(this.l<o.l)
					return 1;
				else if(this.l==o.l)
					return 0;
				else
					return -1;
			}
		}
		//This checks if the 'this' box can be above the passed box.
		public boolean canBeAbove(Box b)
		{
			if(b.l<this.l&&b.w<this.w)
				return true;
			return false;
		}
	}
	
	//Create the box of different configuration for a give lwd and add to the passed list.
	public static void addBoxes(int l, int w, int d,ArrayList<Box> al)
	{
		Box b1 = new Box(l,w,d);
		Box b2 = new Box(w,d,l);
		Box b3 = new Box(d,l,w);
		al.add(b1);
		al.add(b2);
		al.add(b3);
	}
	
	
	public static void solve(int[] l, int[] w, int[] d)
	{
		//This list will consist of all the different combination of BOX objects possible
		ArrayList<Box> al = new ArrayList<Box>();
		for(int i=0;i<l.length;i++)
			addBoxes(l[i],w[i],d[i],al);
		//Sort them first by L and then by W.
		Collections.sort(al);
		
		//Maximum Boxes that can be stacked uptil ith point.
		//MaxUptilHere[i]
		int[] maxUptilHere = new int[al.size()];
		for(int i=0;i<al.size();i++)
		{
			System.out.print(al.get(i).l+" ");
			System.out.print(al.get(i).w+" ");
			System.out.print(al.get(i).h+" ");
			System.out.println();
		}
		
		//Vairation of LIS problem
		for(int i=0;i<al.size();i++)
		{
			//Get the height. Atleast the stack will be uptil this height if this is the only Box that can be placed.
			maxUptilHere[i] = al.get(i).h;
			//Check every Box before this and update the Max Stack Height value
			for(int j=i-1;j>0;j--)
			{
				if(al.get(j).canBeAbove(al.get(i)))
					maxUptilHere[i] = Math.max(maxUptilHere[i], maxUptilHere[j]+al.get(i).h);
			}
		}
		for(int i=0;i<maxUptilHere.length;i++)
			System.out.print(maxUptilHere[i]+" ");
		System.out.println();
		int maxBoxStacked = 0;
		for(int i=0;i<maxUptilHere.length;i++)
			maxBoxStacked = Math.max(maxBoxStacked, maxUptilHere[i]);
		System.out.println(maxBoxStacked);
	}
	
	public static void main(String[] args)
	{
		int[] l = {4,1,4,10};
		int[] w = {6,2,5,12};
		int[] d = {7,3,6,32};
		solve(l,w,d);
	}
	
}
