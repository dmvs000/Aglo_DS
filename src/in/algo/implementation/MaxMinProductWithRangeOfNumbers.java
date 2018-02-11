package in.algo.implementation;

import java.util.HashMap;

/*
 * Given an Array Of Number that lie with the range of -10 to 10.
 * Find the Max Product and Min Product that could be attained of that array.
 */
public class MaxMinProductWithRangeOfNumbers {

	public static void main(String[] args) {
		int[] arr = { 0, -2, -10, -10 };
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (hm.containsKey(arr[i]))
				hm.put(arr[i], hm.get(arr[i]) + 1);
			else
				hm.put(arr[i], 1);
		}
		// Check if this only has Zero and no other element.
		// Or It has nothing. No Zero and No other element.
		boolean containsOtherThanZero = false;
		for (int i = -10; i <= 10; i++) {
			if (i == 0)
				continue;
			if (hm.containsKey(i))
				containsOtherThanZero = true;
		}
		double maxProduct = 0;
		double minProduct = 0;
		//If no other elements other than zero. Then MaxProduct and MinProduct is zero
		if (!containsOtherThanZero) {
			System.out.println("MaxProduct" + maxProduct);
			System.out.println("MinProduct" + minProduct);
			return;
		}
		double tempProduct = 1;
		double firstNegNumber = 0;
		double firstPosNumber = 0;
		for (int i = -1; i >= -10; i--) {
			if (hm.containsKey(i)) {
				firstNegNumber = i;
				break;
			}
		}
		for (int i = 1; i <= 10; i++) {
			if (hm.containsKey(i)) {
				firstPosNumber = i;
				break;
			}
		}
		for (int i = -10; i <= 10; i++) {
			if (i == 0)
				continue;
			if (hm.containsKey(i)) {
				tempProduct = tempProduct * Math.pow(i, hm.get(i));
			}
		}
		//tempProduct will either be a positive or a negative number but not zero
		if (tempProduct > 0) {
			maxProduct = tempProduct;
			if (firstNegNumber == 0) {
				if (hm.containsKey(0)) {
					minProduct = 0;
				} else {
					minProduct = firstPosNumber;
				}
			} else {
				minProduct = tempProduct / firstNegNumber;
			}
		} else {
			minProduct = tempProduct;
			maxProduct = tempProduct / firstNegNumber;
		}
		System.out.println("MaxProduct" + maxProduct);
		System.out.println("MinProduct" + minProduct);
	}

}
