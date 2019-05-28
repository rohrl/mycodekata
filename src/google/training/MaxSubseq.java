package google.training;

import java.util.Arrays;

public class MaxSubseq {

	/** encodes result as: highbyte = left bound, lowbyte = right bound, O(N) */
	public static long findSubseqWithMaxSum(long[] input) {

		long beg = 0;
		long sum = 0;

		long maxSum = 0;
		long maxB = 0, maxE = 0;

		for (int i = 0; i < input.length; i++) {
			if (sum < 0) {
				sum = 0;
				beg = i;
			}
			sum += input[i];
			if (sum > maxSum) {
				maxSum = sum;
				maxB = beg;
				maxE = i;
			}
		}

		return (maxB << Integer.SIZE | maxE);
	}

	public static long brute(long[] input) {

		long p = 0, q = 0, max = Integer.MIN_VALUE;
		for (int i = 0; i < input.length; i++) {
			int sum = 0;
			for (int j = i; j < input.length; j++) {
				sum += input[j];
				if (sum > max) {
					max = sum;
					p = i;
					q = j;
				}
			}
		}

		return (p << Integer.SIZE | q);
	}

	static void decodeAndPrint(long indexes) {
		// decode left and right index
		System.out.println("from " + (int) (indexes >> Integer.SIZE) + " to "
				+ (int) (indexes & ((1L << Integer.SIZE) - 1)));
	}

	public static void main(String[] args) {

		int N = 10;
		long[] input = new long[N];
		for (int i = 0; i < input.length; i++) {
			input[i] = Math.round(Math.random() * 20 - 10);
		}

		System.out.println(Arrays.toString(input));

		decodeAndPrint(brute(input));
		decodeAndPrint(findSubseqWithMaxSum(input));
	}
}
