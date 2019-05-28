package crackingcoding;

import java.util.Arrays;

/** put queens on chess board without conflict */
public class Task8p7 {
	static int columnForRow[] = new int[8];

	static boolean check(int row) {
		for (int i = 0; i < row; i++) {
			int diff = Math.abs(columnForRow[i] - columnForRow[row]);
			if (diff == 0 || diff == row - i)
				return false;
		}
		return true;
	}

	static int PlaceQueen(int row) {
		if (row == 8) {
			printBoard();
			return 1;
		}
		int ways = 0;
		for (int i = 0; i < 8; i++) {
			columnForRow[row] = i;
			if (check(row)) {
				ways += PlaceQueen(row + 1);
			}
		}
		return ways;
	}

	static void printBoard() {
		System.out.println(Arrays.toString(columnForRow));
	}

	public static void main(String[] args) {
		System.out.println(PlaceQueen(0));
	}
}
