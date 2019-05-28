package mine;

public class UnboundKnapsack {

	public static int count = 0;

	public static int mineSolver(int C, int[] weights, int[] values) {

		return mineSolver(C, weights, values, new Integer[C + 1]);
	}

	private static int mineSolver(int C, int[] weights, int[] values,
			Integer[] solutions) {

		count++;

		if (C == 0)
			return solutions[0] = 0;

		solutions[C] = 0;
		for (int i = 0; i < weights.length; i++) {

			int w = weights[i];
			if (C - w < 0)
				continue;

			int v = values[i];

			Integer k = solutions[C - w];
			if (k == null)
				k = mineSolver(C - w, weights, values, solutions);

			if (k + v > solutions[C])
				solutions[C] = k + v;
		}

		return solutions[C];
	}

	public static int wikiSolver(int C, int[] weights, int[] values) {

		// Optimization: dividate C and weights by their GCD
		int gcd = GCD.gcd(weights);
		gcd = GCD.gcd(gcd, C);
		int[] weightsDivided = new int[weights.length];
		for (int i = 0; i < weights.length; i++)
			weightsDivided[i] = weights[i] / gcd;
		C /= gcd;

		int[] solutions = new int[C + 1];

		for (int i = 0; i <= C; i++)
			for (int j = 0; j < weightsDivided.length; j++)
				if (weightsDivided[j] <= i)
					solutions[i] = Math.max(solutions[i], solutions[i
							- weightsDivided[j]]
							+ values[j]);

		return solutions[C];
	}

	public static void main(String[] args) {

		int C = 15;
		int[] W = new int[] { 12, 2, 1, 4, 1 };
		int[] V = new int[] { 4, 2, 2, 10, 1 };

		count = 0;
		System.out.println(mineSolver(C, W, V));
		System.out.println("operations: " + count);

		System.out.println(wikiSolver(C, W, V));
		System.out.println("operations: " + C * V.length);
	}
}
