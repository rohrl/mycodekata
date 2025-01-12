package noodle.trainingOnSite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoName {

	static int lis(int[] a) {

		int lis = 1, s = 0, e = 0;

		int currentLis = 1, currentStart = 0;

		for (int i = 1; i < a.length; i++) {

			if (a[i - 1] < a[i]) {
				currentLis++;
			} else {
				if (currentLis > lis) {
					s = currentStart;
					e = i - 1;
					lis = currentLis;
				}
				currentLis = 1;
				currentStart = i;
			}

		}

		System.out.println(s + " " + e);
		return lis;
	}

	public static void main(String[] args) {

		System.out.println(lis(new int[] { 1, 32, 56, 8, 9, 4, 2, 5, 87, 88, 9,
				4, 7, 9, 5, 9, 21 }));
	}

}
