package in.algo.misc;

/*
 * https://www.mathsisfun.com/games/towerofhanoi.html
 * https://en.wikipedia.org/wiki/Tower_of_Hanoi
 */

/*
 * Minimum moves required - (2^n)-1
 */

/*
 * Optimal Solution (Iterative)
 * For Odd Number of disks, A->C, A->B, B->C
 * For Even Number of disks, A->B, A->C, B->C
 */

/*
 *Recursive
 *Move the top (N-1) Disks to Intermediate peg
 *Move the Last Disk present to target peg
 *Move the N-1 disks on Intermediate peg to target peg 
 */

public class TowersOfHanoi {

	public static void recurSolve(int N, char src, char aux, char dest) {
		if (N == 1) {
			System.out.println("Move " + src + " and " + dest);
			return;
		}
		recurSolve(N - 1, src, dest, aux);
		System.out.println("Move " + src + " and " + dest);
		recurSolve(N - 1, aux, src, dest);
	}

	public static void main(String[] args) {
		recurSolve(64, 'A', 'B', 'C');
	}

}
