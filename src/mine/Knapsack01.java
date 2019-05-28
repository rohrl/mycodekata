package mine;

public class Knapsack01 {

	public static int count = 0;

	public static int mineSolver(int C, int[] weights, int[] values) {

		return mineSolver(C, weights.length - 1, weights, values,
				new Integer[C + 1][weights.length]);
	}

	private static int mineSolver(int C, int n, int[] weights, int[] values,
			Integer[][] solutions) {

		count++;

		if (C == 0)
			return solutions[0][n] = 0;

		solutions[C][0] = 0;
		for (int i = 1; i < weights.length; i++) {

			int w = weights[i];
			int v = values[i];

			if (C - w < 0) {
				solutions[C][i] = solutions[C][i - 1];
			} else {
				Integer s1 = solutions[C - w][i - 1];
				if (s1 == null)
					s1 = mineSolver(C - w, i - 1, weights, values, solutions);
				solutions[C][i] = Math.max(s1 + v, solutions[C][i - 1]);

			}
		}

		return solutions[C][n];
	}

	public static int wikiSolver(int C, int[] weights, int[] values) {

		int[][] s = new int[weights.length][C + 1];

		for (int i = 1; i < weights.length; i++)
			for (int j = 0; j <= C; j++)
				if (j >= weights[i])
					s[i][j] = Math.max(s[i - 1][j], s[i - 1][j - weights[i]]
							+ values[i]);
				else
					s[i][j] = s[i - 1][j];

		return s[weights.length - 1][C];
	}

	public static void main(String[] args) {

		int C = 15;
		int[] W = new int[] { 12, 2, 1, 4, 4, 4, 1 };
		int[] V = new int[] { 4, 2, 2, 10, 10, 10, 1 };

		count = 0;
		System.out.println(mineSolver(C, W, V));
		System.out.println("operations: " + count);

		System.out.println(wikiSolver(C, W, V));
		System.out.println("operations: " + C * V.length);
	}
}
