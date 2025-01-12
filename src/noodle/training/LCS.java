package noodle.training;

/** Skiena 8-4 */
public class LCS {

	/** dynamic programming - O(mn) */
	public static String longestCommonSubsequence(char[] a, char[] b) {

		int[][] S = new int[a.length + 1][b.length + 1];

		for (int i = 0; i < S.length; i++)
			S[i][0] = 0;
		for (int i = 0; i < S[0].length; i++)
			S[0][i] = 0;

		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j <= b.length; j++) {
				if (a[i - 1] == b[j - 1]) {
					S[i][j] = S[i - 1][j - 1] + 1;
				} else {
					S[i][j] = Math.max(S[i - 1][j], S[i][j - 1]);
				}
			}
		}

		// reconstruct
		StringBuilder sb = new StringBuilder();
		int i = a.length, j = b.length;
		while (i > 0 && j > 0)
			if (a[i - 1] == b[j - 1]) {
				sb.append(a[i - 1]);
				i--;
				j--;
			} else {
				if (S[i][j - 1] < S[i - 1][j]) {
					i--;
				} else {
					j--;
				}
			}

		return sb.reverse().toString();
	}

	public static void main(String[] args) {

		System.out.println(longestCommonSubsequence(
				"photography".toCharArray(), "tomograxxphzz".toCharArray()));

		System.out.println(longestCommonSubsequence("0a1b2c3".toCharArray(),
				"zaybcwx".toCharArray()));

		System.out.println(longestCommonSubsequence("0a1b2c3".toCharArray(),
				"0123".toCharArray()));

		System.out.println(longestCommonSubsequence("1".toCharArray(),
				"1".toCharArray()));

		System.out.println(longestCommonSubsequence("x".toCharArray(),
				"y".toCharArray()));

		System.out.println(longestCommonSubsequence("xy".toCharArray(),
				"yx".toCharArray()));
	}
}
