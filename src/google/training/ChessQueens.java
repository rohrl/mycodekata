package google.training;

public class ChessQueens {
	static final int SIZE = 8;
	// number of row where queen is for given column
	static Integer[] board = new Integer[SIZE];

	static int queens(int col) {

		if (col == SIZE) {
			return 1;
		}

		int ways = 0;
		for (int r = 0; r < SIZE; r++) {

			if (possible(col, r)) {
				board[col] = r;
				ways += queens(col + 1);
			}
		}
		// step back
		// board[col] = null; // no need actually :)
		return ways;
	}

	static boolean possible(int c, int r) {
		for (int i = 0; i < c; i++) {
			if (board[i] == r)
				return false;
			if (board[i] == r - (c - i))
				return false;
			if (board[i] == r + (c - i))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(queens(0));
	}
}
