package mine;

import java.util.Random;

import org.junit.Test;

/** Find integer which occurs at least half of the times in the array */
public class MostCommonElement {

	@Test
	public void testMostCommonElement() {

		int[] a = new int[] { 1, 2, 5, 5, 5 };
		int[] b = new int[] { 9, 1, 9, 2, 9 };
		int[] c = new int[] { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 };
		int[] d = new int[] { 1, 2, 1 };
		int[] e = new int[] { 1, 1, 1, 2 };
		int[] f = new int[] { 1, 2, 2, 2 };

		org.junit.Assert.assertEquals(findMostCommonElement(a), 5);
		org.junit.Assert.assertEquals(findMostCommonElement(b), 9);
		org.junit.Assert.assertEquals(findMostCommonElement(c), 0);
		org.junit.Assert.assertEquals(findMostCommonElement(d), 1);
		org.junit.Assert.assertEquals(findMostCommonElement(e), 1);
		org.junit.Assert.assertEquals(findMostCommonElement(f), 2);
	}

	public int findMostCommonElement(int[] a) {
		return medianOf(a);
	}

	/** in case of even length, returns "left" median */
	public int medianOf(int[] a) {

		int pivotIdx, b = 0, e = a.length - 1;
		double half = (a.length - 1) / 2.0d;

		while (true) {
			pivotIdx = partition(a, b, e, (int) (b + Math.random() * (e - b)));
			if (Math.floor(half) == pivotIdx || Math.ceil(half) == pivotIdx)
				return a[pivotIdx];
			if (pivotIdx < Math.floor(half)) {
				b = pivotIdx + 1;
			} else {
				e = pivotIdx - 1;
			}
		}
	}

	/** Quick sort partition algorithm, returns pivot idx */
	public int partition(int[] a, int b, int e, int pivotElIdx) {
		int pivotVal = a[pivotElIdx];
		swap(a, pivotElIdx, e);
		pivotElIdx = b;
		int i = b;
		while (i < e) {
			if (a[i] < pivotVal) {
				swap(a, i, pivotElIdx++);
			}
			i++;
		}
		swap(a, e, pivotElIdx);
		return pivotElIdx;
	}

	private void swap(int[] a, int x, int y) {
		if (x == y || a[x] == a[y])
			return;
		a[x] = a[x] ^ a[y];
		a[y] = a[x] ^ a[y];
		a[x] = a[x] ^ a[y];
	}

	@Test
	public void testPartition() {

		int[] a = new int[] { 2, 3, 1 };
		int[] b = new int[] { 1, 2, 3 };
		int[] c = new int[] { 3, 2, 1 };

		int[] d = new int[] { 1, 2, 3, 4 };
		int[] e = new int[] { 2, 1, 4, 3 };
		int[] f = new int[] { 4, 3, 2, 1 };

		int[] g = new int[] { 1, 2, 3, 3, 3, 4, 5, 5, 6, 6, 6, 6 };

		org.junit.Assert.assertEquals(partition(a, 0, 2, 0), 1);
		org.junit.Assert.assertEquals(partition(b, 0, 2, 1), 1);
		org.junit.Assert.assertEquals(partition(c, 0, 2, 1), 1);

		org.junit.Assert.assertEquals(partition(d, 0, 3, 2), 2);
		org.junit.Assert.assertEquals(partition(e, 0, 3, 0), 1);
		org.junit.Assert.assertEquals(partition(f, 0, 3, 2), 1);

		for (int i = 0; i < g.length; i++) {
			int[] arr = g.clone();
			int res = partition(arr, 0, arr.length - 1, i);
			org.junit.Assert.assertEquals(arr[res], g[res]);
			org.junit.Assert.assertEquals(arr[res], g[i]);

			arr = g.clone();
			shuffle(arr);
			res = partition(arr, 0, arr.length - 1, i);
			org.junit.Assert.assertEquals(arr[res], g[res]);
		}
	}

	private void shuffle(int[] a) {
		Random r = new Random();
		for (int i = 0; i < a.length; i++) {
			swap(a, i, i + r.nextInt(a.length - i));
		}
	}
}
