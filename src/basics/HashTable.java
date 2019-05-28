package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.Test;

public class HashTable<T> {

	private static final double MAX_LOAD_FACTOR = 0.6;
	private static final int INITIAL_CAPACITY = 8;

	private ArrayList<LinkedList<T>> set;
	private int size;

	public HashTable() {
		createNewSet(INITIAL_CAPACITY);
	}

	public boolean add(T t) {

		if (this.contains(t))
			return false;

		if (loadFactor() > MAX_LOAD_FACTOR) {
			rehash();
		}

		LinkedList<T> bucket = set.get(idxFor(t));
		if (bucket == null) {
			bucket = new LinkedList<T>();
			set.set(idxFor(t), bucket);
		}

		bucket.add(t);
		size++;
		return true;
	}

	public boolean remove(T t) {
		LinkedList<T> bucket = set.get(idxFor(t));
		if (bucket == null)
			return false;
		if (bucket.remove(t)) {
			size--;
			return true;
		} else
			return false;
	}

	public boolean contains(T t) {
		LinkedList<T> bucket = set.get(idxFor(t));
		return bucket != null && bucket.contains(t);
	}

	public int size() {
		return size;
	}

	private void rehash() {
		System.out.println("rehash at " + size() + "/" + capacity());
		ArrayList<LinkedList<T>> oldSet = set;
		createNewSet(2 * capacity());
		for (LinkedList<T> bucket : oldSet) {
			if (bucket != null)
				for (T t : bucket) {
					this.add(t);
				}
		}
	}

	private void createNewSet(int capacity) {
		set = new ArrayList<LinkedList<T>>(capacity);
		set.addAll(Collections.nCopies(capacity, (LinkedList<T>) null));
		assert set.size() == capacity;
		size = 0;
	}

	private int idxFor(T t) {
		int idx = (t == null ? 0 : t.hashCode() % capacity());
		return idx >= 0 ? idx : idx + capacity();
	}

	public int capacity() {
		return set.size();
	}

	public double loadFactor() {
		return (double) size() / (double) capacity();
	}

	@Test
	public void dontFailPlease() {

		Integer[] data = { 1, 3, 5, 8, 9, null, 10, 16, 32, 64, 15, 2, -100, 7,
				7, 7, 9, 7, 25, 300, 100000, 9999999, 100001, 0, -1,
				Integer.MAX_VALUE, Integer.MIN_VALUE, null };

		HashSet<Integer> desired = new HashSet<Integer>(Arrays.asList(data));
		HashTable<Integer> subject = new HashTable<Integer>();
		for (Integer x : data)
			subject.add(x);

		for (Integer x : data) {
			Assert.assertTrue(subject.contains(x));
		}

		for (Integer x : data)
			Assert.assertTrue(subject.add(x) == desired.add(x));

		Assert.assertTrue(subject.size() == desired.size());

		for (Integer i = -100001; i <= 100001; i++) {
			Assert.assertTrue(subject.contains(i) == desired.contains(i));
		}

		for (Integer i = -100001; i <= 100001; i++) {
			Assert.assertTrue(subject.remove(i) == desired.remove(i));
		}

		for (Integer x : data)
			Assert.assertTrue(subject.remove(x) == desired.remove(x));

		Assert.assertTrue(subject.size() == desired.size());
	}

}
