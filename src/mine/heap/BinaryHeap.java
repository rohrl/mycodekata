package mine.heap;

import java.util.ArrayList;
import java.util.Collection;

public class BinaryHeap<T extends Comparable<? super T>> {

	private ArrayList<T> arr;

	public BinaryHeap() {
		arr = new ArrayList<T>();
	}

	public BinaryHeap(Collection<? extends T> c) {

		arr = new ArrayList<T>(c.size());

		int i = 0;
		for (T t : c) {
			arr.add(t);
			heapify(i);
			i++;
		}
	}

	private void heapify(int i) {

		int parent = (i - 1) >> 1;
		while (i > 0 && arr.get(parent).compareTo(arr.get(i)) > 0) {
			swap(parent, i);
			i = parent;
			parent = (i - 1) >> 1;
		}
	}

	private int getSmallerChild(int i) {
		int firstChild = (i << 1) + 1;
		int secondChild = (i << 1) + 2;
		if (firstChild >= arr.size())
			return -1;
		int smaller = firstChild;
		if (secondChild < arr.size()
				&& arr.get(firstChild).compareTo(arr.get(secondChild)) > 0) {
			smaller = secondChild;
		}
		return smaller;
	}

	private void backHeapify(int i) {

		int smaller = getSmallerChild(i);

		while (smaller > 0 && arr.get(i).compareTo(arr.get(smaller)) > 0) {
			swap(smaller, i);
			i = smaller;
			smaller = getSmallerChild(i);
		}
	}

	private void swap(int a, int b) {
		T t = arr.get(a);
		arr.set(a, arr.get(b));
		arr.set(b, t);
	}

	public void add(T t) {
		arr.add(t);
		heapify(arr.size() - 1);
	}

	public T peek() {
		if (arr.isEmpty())
			return null;
		return arr.get(0);
	}

	public T pop() {
		if (arr.isEmpty())
			return null;

		T toReturn = arr.get(0);
		T last = arr.remove(arr.size() - 1);
		if (arr.isEmpty())
			return toReturn;
		arr.set(0, last);
		backHeapify(0);
		return toReturn;
	}

	private int find(T t, int i) {
		if (i >= arr.size())
			return -1;
		if (arr.get(i).compareTo(t) == 0)
			return i;
		int idx = find(t, 2 * i + 1);
		if (idx > 0)
			return idx;
		return find(t, 2 * i + 2);
	}

	public T remove(Object o) {
		@SuppressWarnings("unchecked")
		int i = find((T)o, 0);
		if (i < 0)
			return null;

		if (i == arr.size() - 1) {
			return arr.remove(i);
		}

		T toReturn = arr.get(i);
		arr.set(i, arr.remove(arr.size() - 1));
		heapify(i);
		backHeapify(i);
		return toReturn;
	}

	public int size() {
		return arr.size();
	}

	public boolean isEmpty() {
		return arr.isEmpty();
	}

	@Override
	public String toString() {
		return arr.toString();
	}
}
