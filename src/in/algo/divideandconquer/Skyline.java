package in.algo.divideandconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/*
 * https://www.youtube.com/watch?v=Cv0ft2dFz80
 */
public class Skyline {

	static class Building {
		int x1;
		int x2;
		int h;

		Building(int x1, int x2, int h) {
			this.x1 = x1;
			this.x2 = x2;
			this.h = h;
		}
	}

	static class criticalPoint implements Comparable<criticalPoint> {
		int x;
		int y;

		criticalPoint(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(criticalPoint o) {
			if (this.x < o.x)
				return -1;
			else if (this.x > o.x)
				return 1;
			else
				return 0;
		}
	}

	public static int getMaxHeight(criticalPoint cp, ArrayList<Building> al) {
		int retH = cp.y;
		for (int i = 0; i < al.size(); i++) {
			Building curB = al.get(i);
			if (cp.x >= curB.x1 && cp.x < curB.x2)
				retH = Math.max(retH, curB.h);
		}
		return retH;
	}

	public static void solve(ArrayList<Building> al) {
		// Generate Critical Points
		ArrayList<criticalPoint> alCP = new ArrayList<criticalPoint>();
		for (int i = 0; i < 5; i++) {
			alCP.add(new criticalPoint(al.get(i).x1, al.get(i).h));
			alCP.add(new criticalPoint(al.get(i).x2, 0));
		}
		// Edit the Critical Points heights if it's inside any building
		for (int i = 0; i < alCP.size(); i++) {
			alCP.get(i).y = getMaxHeight(alCP.get(i), al);
		}
		Collections.sort(alCP);
		// Remove the redundant critical points
		for (int i = 1; i < alCP.size(); i++) {
			criticalPoint temp = alCP.get(i);
			criticalPoint tempPrev = alCP.get(i - 1);
			if (temp.y == tempPrev.y)
				alCP.remove(i);
		}
		// Print the remaining critical points
		for (int i = 0; i < alCP.size(); i++) {
			criticalPoint cp = alCP.get(i);
			System.out.println(cp.x + " " + cp.y);
		}
	}

	public static void main(String[] args) {
		int n = 5;
		int[] x1 = { 1, 2, 5, 6, 8 };
		int[] x2 = { 3, 4, 8, 7, 9 };
		int[] h = { 3, 4, 2, 4, 4 };
		ArrayList<Building> al = new ArrayList<Building>();
		for (int i = 0; i < n; i++)
			al.add(new Building(x1[i], x2[i], h[i]));
		solve(al);
	}

}
