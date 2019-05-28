package crackingcoding;

/** Robot NxN moves only right or down, all possible paths */
public class Task8p2 {

	public static long fun(int x, int y) {
		if (x == 1 || y == 1)
			return 1;
		else
			return fun(x - 1, y) + fun(x, y - 1);
	}

	/** combinations */
	public static long C(int n, int k) {
		long numerator = 1;
		for (int i = n; i > (n - k); i--)
			numerator *= i;
		long denominator = 1;
		for (int i = 1; i <= k; i++)
			denominator *= i;
		return numerator / denominator;
	}

	public static void main(String[] args) {
		// for (int n = 1; n <= 5; n++)
		// for (int k = 1; k <= n; k++)
		// System.out.println("C(" + n + ", " + k + ") = " + C(n, k));

		for (int N = 1; N <= 10; N++) {
			System.out.println("FUN = " + fun(N, N) + "\t\tC(2N-2, N-1) = "
					+ C(2 * N - 2, N - 1));
		}
	}
}
