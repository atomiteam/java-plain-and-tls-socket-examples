package com.atomiteam;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CrossOverTwins {

	static boolean twins(String a, String b) {
		boolean result = false;
		if (a.length() == b.length()) {
			Set<Character> aEven = new HashSet<Character>();
			Set<Character> aOdd = new HashSet<Character>();
			Set<Character> bEven = new HashSet<Character>();
			Set<Character> bOdd = new HashSet<Character>();

			for (int i = 0; i < a.length(); i++) {
				// even
				if (i % 2 == 0) {
					aEven.add(a.charAt(i));
					bEven.add(b.charAt(i));
				} else {
				// odd
					aOdd.add(a.charAt(i));
					bOdd.add(b.charAt(i));
				}
			}
			
			return aEven.containsAll(bEven) && aOdd.containsAll(bOdd) & bEven.containsAll(aEven) && bOdd.containsAll(aOdd);
		} else {
			result = true;
		}
		return result;
	}


	/**
	 * Complete the function below. DO NOT MODIFY anything outside this method.
	 */
	static boolean[] twins(String[] a, String[] b) {
		boolean[] result = new boolean[a.length];
		for(int i = 0; i < a.length; i++) {
			result[i] = twins(a[i], b[i]);
		}
		return result;
	}


	/**
	 * DO NOT MODIFY THIS METHOD!
	 */
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		int n = Integer.parseInt(in.nextLine().trim());
		String[] a = new String[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextLine();
		}

		int m = Integer.parseInt(in.nextLine().trim());
		String[] b = new String[m];
		for (int i = 0; i < m; i++) {
			b[i] = in.nextLine();
		}

		// call twins function
		boolean[] results = twins(a, b);

		for (int i = 0; i < results.length; i++) {
			System.out.println(results[i] ? "Yes" : "No");
		}
	}
}