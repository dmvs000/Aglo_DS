package in.algo.implementation;

import java.util.Arrays;

public class SieveOfEratosthenes {

	public static void main(String[] args) {
		findAllPrimes(50);
	}

	public static void findAllPrimes(int N) {
		boolean[] isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		for (int i = 2; i <= N; i++) {
			for (int j = 2 * i; j <= N; j = j + i) {
				isPrime[j] = false;
			}
		}
		// Print All Primes
		for (int i = 1; i <= N; i++) {
			if (isPrime[i])
				System.out.println(i + " " + isPrime[i]);
		}
	}

}
