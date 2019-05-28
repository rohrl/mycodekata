package google.trainingOnSite;

import java.util.Arrays;
import java.util.Random;

/**
 * There are fifty coins in a line---these could be pennies, nickels, dimes, or
 * quarters. Two players, F and S, take turns at choosing one coin each---they
 * can only choose from the two coins at the ends of the line. The game ends
 * when all the coins have been picked up. The player whose coins have the
 * higher total value wins. Each player must select a coin when it is his turn,
 * so the game ends in fifty turns.
 * 
 * If you want to ensure you do not lose, would you rather go first or second?
 * Design an efficient algorithm for computing the maximum amount of money the
 * first player can win.
 * 
 * 
 */
public class CoinGame {

	static enum Turn {
		MINE, OPPON;
		Turn opposite() {
			return this == MINE ? OPPON : MINE;
		}
	};

	static int score(int[] a, int l, int r, int sum, Turn turn, String[] moves,
			int move) {

		if (r == l) {
			moves[move] = turn + " LEFT WITH " + a[r];
			return a[r];
		}

		// TODO: moves are overwritten, need to clone
		int takeLeft = sum
				- score(a, l + 1, r, sum - a[l], turn.opposite(), moves,
						move + 1);
		int takeRight = sum
				- score(a, l, r - 1, sum - a[r], turn.opposite(), moves,
						move + 1);

		if (takeLeft < takeRight) {
			moves[move] = turn + "\t: R[" + r + "]\t" + a[r] + "\tscore: "
					+ takeRight;
			return takeRight;
		} else {
			moves[move] = turn + "\t: L[" + l + "]\t" + a[l] + "\tscore: "
					+ takeLeft;
			return takeLeft;
		}
	}

	public static void main(String[] args) {

		final Random r = new Random();
		final int[] DIMES = { 1, 2, 5, 10, 25 };
		final int N = 10; // MUST BE EVEN

		int[] a = new int[N];

		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			a[i] = DIMES[r.nextInt(DIMES.length)];
			sum += a[i];
		}

		System.out.println(Arrays.toString(a));

		String[] moves = new String[N];

		System.out.println(score(a, 0, a.length - 1, sum, Turn.MINE, moves, 0));

		for (String s : moves) {
			System.out.println(s);
		}
	}
}
