package mine;

import java.util.Arrays;

public class KMP {

	public static int find(char[] txt, char[] pattern) {

		int[] jumps = calcJumps(pattern);

		System.out.println(Arrays.toString(jumps));

		int j = 0;
		for (int i = 0; i < txt.length - pattern.length; i++) {

			while (j > 0 && txt[i] != pattern[j])
				j = jumps[j];

			if (txt[i] == pattern[j])
				j++;

			if (j == pattern.length)
				return i - j + 1;
		}

		return -1;
	}

	private static int[] calcJumps(char[] pattern) {

		int[] array = new int[pattern.length];

		int j = 0;
		for (int i = 2; i < pattern.length; i++) {

			while (j > 0 && pattern[i] != pattern[j + 1])
				j = array[j];

			if (pattern[i] == pattern[j + 1])
				j++;

			array[i] = j;
		}

		return array;
	}

	public static void main(String[] args) {

		String txt = "jestem jestjesesmy lalala jestestwo jest ggg jestjesteniejestem jesterdey";

		String pattern = "jestjest";

		int foundAt = find(txt.toCharArray(), pattern.toCharArray());

		System.out.println(foundAt);
	}
}
