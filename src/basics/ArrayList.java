package basics;

import java.util.Arrays;

import org.junit.Test;

import junit.framework.Assert;

public class ArrayList<T> {

	private static final int INITIAL_SIZE = 4;

	private Object[] array;
	private int size;

	public ArrayList() {
		array = new Object[INITIAL_SIZE];
	}

	public void set(T t, int idx) {
		if (idx > size() || idx < 0)
			throw new ArrayIndexOutOfBoundsException(idx);

		if (idx >= array.length) {
			array = Arrays.copyOf(array, 2 * array.length);
		}

		array[idx] = t;

		if (idx == size())
			size++;
	}

	public void add(T t) {
		set(t, size());
	}

	@SuppressWarnings("unchecked")
	public T get(int idx) {
		if (idx >= size() || idx < 0)
			throw new ArrayIndexOutOfBoundsException(idx);
		return (T) array[idx];
	}

	public int size() {
		return size;
	}

	@Test
	public void simpleTest() {

		Integer[] arr = new Integer[] { 1, 3, 5, 3, 4, 10, Integer.MAX_VALUE,
				Integer.MIN_VALUE, 0, -1, 2, 4, 8, 16 };
		ArrayList<Integer> al = new ArrayList<Integer>();

		for (int i : arr)
			al.add(i);

		Assert.assertTrue(al.size() == arr.length);

		for (int i = 0; i < arr.length; i++)
			Assert.assertTrue(al.get(i).equals(arr[i]));
	}
}
