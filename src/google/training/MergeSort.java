package google.training;

import java.util.Arrays;

public class MergeSort {

	static void mergesort(int[] a, int b, int e) {

		// assertions
		if (b > e || a.length < e || b < 0)
			throw new IllegalArgumentException();

		if (e - b <= 2) {
			if (e - b == 2 && a[b] > a[e - 1]) {
				int tmp = a[b];
				a[b] = a[e - 1];
				a[e - 1] = tmp;
			}
			return;
		}

		int half = (b + e) / 2;

		mergesort(a, b, half);
		mergesort(a, half, e);

		// merge two halves
		int[] tmp = Arrays.copyOfRange(a, b, e);
		int j = b, k = half;
		for (int i = b; i < e; i++) {
			if (k == e || (j < half && tmp[j - b] <= tmp[k - b])) {
				a[i] = tmp[j++ - b];
			} else {
				a[i] = tmp[k++ - b];
			}
		}
	}

	public static void main(String[] args) {

		int[] a = { 2, 0, 5, -10, 1000, 1, -2, 2, 3, 1 };
		mergesort(a, 0, a.length);
		System.out.println(Arrays.toString(a));
	}

}
