package nanosoft.interview;

import static crackingcoding.Task8p4.factorial;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public class CheckDuplicates {

	static boolean hasDups(int[] a) {

		int i = 0;

		while (i < a.length) {

			if (a[i] != i + 1) {

				int s = i;
				i = a[i] - 1;
				while (s != i && a[i] != i + 1) {
					int j = a[i] - 1;
					a[i] = i + 1;
					i = j;
				}
				if (s != i)
					return true;
			}

			i++;
		}

		return false;
	}

	public static void main(String[] args) {

		int[] array = new int[] { 1, 2, 3 };

		assert permutations(array).length == factorial(array.length);
		for(int [] a : permutations(array))
			System.out.println(Arrays.toString(a));

		System.out.println(hasDups(array));

	}

	public static int[][] permutations(int[] a) {
		return permutations(a, 0);
	}

	private static int[][] permutations(int[] a, int from) {

		if (from >= a.length - 1) {
			return new int[][] { new int[] { a[a.length - 1] } };
		}

		int head = a[from];
		int[][] result = new int[(int) factorial(a.length - from)][];
		int idx = 0;
		for (int[] perm : permutations(a, from + 1)) {

			for (int i = 0; i <= perm.length; i++)
				result[idx++] = insert(perm, i, head);
		}

		return result;
	}

	private static int[] insert(int[] a, int idx, int val) {
		int[] result = new int[a.length + 1];
		for (int i = 0, j = 0; i < result.length; i++) {
			if (i == idx) {
				result[i] = val;
			} else
				result[i] = a[j++];
		}
		return result;
	}

	@Test
	public void testAllPermutationsWithoutDups() {

		int[] array = new int[] { 1, 2, 3, 4, 5 };

		for (int[] a : permutations(array))
			Assert.assertFalse(hasDups(a));
	}

	@Test
	public void testAllPermutationsWithDups1() {

		int[] array = new int[] { 1, 1, 3, 4, 5 };

		for (int[] a : permutations(array))
			Assert.assertTrue(hasDups(a));
	}

	@Test
	public void testAllPermutationsWithDups2() {

		int[] array = new int[] { 5, 2, 3, 4, 5 };

		for (int[] a : permutations(array))
			Assert.assertTrue(hasDups(a));
	}

	@Test
	public void testAllPermutationsWithDups3() {

		int[] array = new int[] { 1, 2, 3, 4, 1 };

		for (int[] a : permutations(array))
			Assert.assertTrue(hasDups(a));
	}

	@Test
	public void testAllPermutationsWithDups4() {

		int[] array = new int[] { 1, 2, 3, 5, 5 };

		for (int[] a : permutations(array))
			Assert.assertTrue(hasDups(a));
	}

	@Test
	public void testAllPermutationsWithDups5() {

		int[] array = new int[] { 1, 2, 3, 1, 2, 3 };

		for (int[] a : permutations(array))
			Assert.assertTrue(hasDups(a));
	}

	@Test
	public void testBoundaries() {
		Assert.assertTrue(hasDups(new int[] { 1, 1 }));
		Assert.assertTrue(hasDups(new int[] { 4, 4, 4, 4 }));
		Assert.assertTrue(hasDups(new int[] { 1, 2, 1, 2 }));

		Assert.assertFalse(hasDups(new int[] { 1 }));
		Assert.assertFalse(hasDups(new int[] { 1, 2 }));
		Assert.assertFalse(hasDups(new int[] { 2, 1 }));
		Assert.assertFalse(hasDups(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
				11, 12, 13, 14 }));
	}

}
