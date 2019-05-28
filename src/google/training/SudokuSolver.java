package google.training;

public class SudokuSolver {

	static final int MAXN = 9; // 1-9
	static final int SQRT_MAXN = 3;

	static int stepCount = 0;

	static boolean solve(int[][] input, int freeCells) {

		stepCount++;

		if (freeCells == 0)
			return true;

		// pick x,y pair which has fewest possible values 
		int minX = -1, minY = -1, poss = MAXN + 1;
		for (int x = 0; x < MAXN; x++) {
			for (int y = 0; y < MAXN; y++) {
				if (input[x][y] == 0) {
					int possCount = 0;
					for (int v = 1; v <= MAXN; v++)
						if (possible(input, x, y, v))
							possCount++;
					if (possCount < poss) {
						minX = x;
						minY = y;
						poss = possCount;
					}
				}
			}
		}

		// just pick first non-occupied position
		// out:
		// for (int x = 0; x < MAXN; x++)
		// for (int y = 0; y < MAXN; y++)
		// if (input[x][y] == 0) {
		// minX = x;
		// minY = y;
		// break out;
		// }

		if (minX == -1 || minY == -1 || poss == 0)
			return false;

		int x = minX;
		int y = minY;

		// for each possible val fork
		for (int val = 1; val <= MAXN; val++) {
			if (possible(input, x, y, val)) {
				input[x][y] = val;
				if (solve(input, freeCells - 1))
					return true;
			}
		}
		input[x][y] = 0;
		return false;
	}

	static boolean possible(int[][] input, int x, int y, int val) {
		for (int i = 0; i < MAXN; i++)
			if (input[x][i] == val
					|| input[i][y] == val
					|| input[(x / SQRT_MAXN) * SQRT_MAXN + i % SQRT_MAXN][(y / SQRT_MAXN)
							* SQRT_MAXN + i / SQRT_MAXN] == val)
				return false;
		return true;
	}

	public static void main(String[] args) {

		// hardest on planet ;) 
		// {0, 0, 0, 0, 0, 0, 0, 1, 2},
		// {0, 0, 0, 0, 3, 5, 0, 0, 0},
		// {0, 0, 0, 6, 0, 0, 0, 7, 0},
		// {7, 0, 0, 0, 0, 0, 3, 0, 0},
		// {0, 0, 0, 4, 0, 0, 8, 0, 0},
		// {1, 0, 0, 0, 0, 0, 0, 0, 0},
		// {0, 0, 0, 1, 2, 0, 0, 0, 0},
		// {0, 8, 0, 0, 0, 0, 0, 4, 0},
		// {0, 5, 0, 0, 0, 0, 6, 0, 0},

		// easy:
		// {0, 6, 0, 3, 0, 0, 8, 0, 4},
		// {5, 3, 7, 0, 9, 0, 0, 0, 0},
		// {0, 4, 0, 0, 0, 6, 3, 0, 7},
		// {0, 9, 0, 0, 5, 1, 2, 3, 8},
		// {0, 0, 0, 0, 0, 0, 0, 0, 0},
		// {7, 1, 3, 6, 2, 0, 0, 4, 0},
		// {3, 0, 6, 4, 0, 0, 0, 1, 0},
		// {0, 0, 0, 0, 6, 0, 5, 2, 3},
		// {1, 0, 2, 0, 0, 9, 0, 8, 0},

		int[][] sudoku =

		{ {0, 0, 0, 0, 0, 0, 0, 1, 2},   
				{0, 0, 0, 0, 3, 5, 0, 0, 0},   
				{0, 0, 0, 6, 0, 0, 0, 7, 0},   
				{7, 0, 0, 0, 0, 0, 3, 0, 0},   
				{0, 0, 0, 4, 0, 0, 8, 0, 0},   
				{1, 0, 0, 0, 0, 0, 0, 0, 0},   
				{0, 0, 0, 1, 2, 0, 0, 0, 0},   
				{0, 8, 0, 0, 0, 0, 0, 4, 0},   
				{0, 5, 0, 0, 0, 0, 6, 0, 0},    };

		int freeCells = 0;
		for (int[] a : sudoku)
			for (int i : a)
				if (i == 0)
					freeCells++;

		System.out.println(freeCells);

		System.out.println(solve(sudoku, freeCells));

		System.out.println(stepCount);

		// print solution
		for (int[] a : sudoku) {
			System.out.println();
			for (int i : a)
				System.out.print(i + " ");
		}
	}
}
