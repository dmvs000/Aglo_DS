package in.algo.greedy;

import java.util.ArrayList;
import java.util.Collections;

public class FractionalKnapSack {

	static class Item implements Comparable<Item> {
		double value;
		double weight;

		Item(int v, int w) {
			this.value = (double) v;
			this.weight = (double) w;
		}

		// Sort based on the ratio of value to weight. This gives the value of
		// product per unit weight.
		@Override
		public int compareTo(Item o) {
			if ((this.value / this.weight) > (o.value / o.weight))
				return -1;
			else if ((this.value / this.weight) == (o.value / o.weight))
				return 0;
			else
				return 1;
		}
	}

	public static double FracKnapSack(int[] value, int[] weight, int W) {
		double remSum = W;
		ArrayList<Item> al = new ArrayList<Item>();
		int n = value.length;
		for (int i = 0; i < n; i++)
			al.add(new Item(value[i], weight[i]));
		Collections.sort(al);
		double maxVal = 0;
		for (int i = 0; i < n && remSum > 0; i++) {
			Item temp = al.get(i);
			// Check if the items weight is more than remSum. Take partially.
			if (temp.weight > remSum) {
				maxVal = maxVal + ((temp.value / temp.weight) * remSum);
				System.out.println(temp.weight + " " + temp.value + " partial. " + remSum + " Units  "
						+ ((temp.value / temp.weight) * remSum) + " value");
				break;
			}
			// If Item can be picked completely. Pick completely.
			else {
				System.out.println(temp.weight + " " + temp.value + " completely.");
				maxVal = maxVal + temp.value;
				remSum = remSum - temp.weight;
			}
		}
		return maxVal;
	}

	public static void main(String[] args) {
		// Input
		int[] value = { 60, 100, 120 };
		int[] weight = { 10, 20, 30 };
		int W = 55;
		System.out.println(FracKnapSack(value, weight, W));
	}

}
