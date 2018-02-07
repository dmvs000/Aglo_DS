package in.algo.patternsearch;

import java.util.Arrays;

/*
 * https://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
 */
public class KMP {

	public static void match_KMP(String text, String pat) {
		int[] lps = new int[pat.length()];
		lps[0] = 0;
		int j = 0;
		int i = j + 1;
		while (i < pat.length()) {
			if (pat.charAt(i) == pat.charAt(j)) {
				lps[i] = j + 1;
				i++;
				j++;
			} else if (j == 0) {
				lps[i] = j;
				i++;
			} else {
				j = lps[j - 1];
			}
		}
		int text_i = 0;
		int pat_i = 0;
		while (text_i < text.length()) {
			if (text.charAt(text_i) == pat.charAt(pat_i)) {
				pat_i++;
				if(pat_i==pat.length())
				{
					pat_i=lps[pat_i-1];
					System.out.println("Starting index 	: "+(text_i-pat.length()+1));
					System.out.println("Ending index	: "+text_i);
				}
				text_i++;
			} else {
				if (pat_i != 0)
					pat_i = lps[pat_i - 1];
				else
					text_i++;
			}
		}
		System.out.println(Arrays.toString(lps));
	}

	public static void main(String[] args) {
		String text = "abxabcabcaby";
		String pat = "abcaby";
		// String pat = "acacabacacabacacac";
		match_KMP(text, pat);
	}

}
