package noodle.training;

import java.util.Arrays;

public class Sorting {

	public static void quicksort(int[] a, int from, int to) {

		// if size <= 1 return
		if (to - from <= 1)
			return;

		// pick pivot
		int pivIdx = to - 1;

		// partition
		int piv = a[pivIdx];
		swap(a, pivIdx, to - 1);
		int firstLarger = from;
		for (int i = from; i < to - 1; i++) {
			if (a[i] < piv) {
				swap(a, firstLarger, i);
				firstLarger++;
			}
		}
		swap(a, firstLarger, to - 1);

		// branch on left and right
		quicksort(a, from, firstLarger);
		quicksort(a, firstLarger + 1, to);
	}

	public static void swap(int[] a, int x, int y) {
		int tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}

	public static void main(String[] args) {
		
		int[] a = { 5, -4, 1, 2, -6, 1, 56, 34, 1, 34, 9, -8, -4, 9, 0 };
		quicksort(a, 0, a.length);
		System.out.println(Arrays.toString(a));
	}
}
