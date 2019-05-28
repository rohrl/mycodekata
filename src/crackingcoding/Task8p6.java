package crackingcoding;

/**
 * how many possible representations of some amount of money N in given set of
 * coins
 */
public class Task8p6 {

	/** available coins */
	public static int[] denoms = { 1, 5, 10, 25 };

	/** my solution */
	public static int makeChange(int n, int denomIdx) {

		if (n < 0)
			return 0;
		if (n == 0)
			return 1;

		int ways = 0;
		for (int d = denomIdx; d < denoms.length; d++) {
			ways += makeChange(n - denoms[d], d);
		}
		return ways;
	}

	public static void main(String[] args) {

		int n = 100;

		System.out.println(makeChange(n, 0));

		System.out.println(makeChange2(n, 25));
	}

	/** book solution */
	public static int makeChange2(int n, int denom) {
		int next_denom = 0;
		switch (denom) {
		case 25:
			next_denom = 10;
			break;
		case 10:
			next_denom = 5;
			break;
		case 5:
			next_denom = 1;
			break;
		case 1:
			return 1;
		}
		int ways = 0;
		for (int i = 0; i * denom <= n; i++) {
			ways += makeChange2(n - i * denom, next_denom);
		}
		return ways;
	}

}
