package mine.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import junit.framework.Assert;

import org.junit.Test;

public class HeapTest {

	@Test
	public void emptyConstructorTest() {

		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();

		Assert.assertTrue(heap.isEmpty());
		Assert.assertTrue(heap.size() == 0);
	}

	@Test
	public void constructFromCollectionTest() {

		BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Collections.nCopies(
				5, 10));

		Assert.assertFalse(heap.isEmpty());
		Assert.assertTrue(heap.size() == 5);
		Assert.assertTrue(heap.peek() == 10);
	}

	@Test
	public void constructFromCollectionTest2() {

		BinaryHeap<Integer> heap = new BinaryHeap<Integer>(
				Arrays.asList(new Integer[] { 5, 2, 2934, 34, -10, 0, 999, -1,
						1 }));

		Assert.assertFalse(heap.isEmpty());
		Assert.assertTrue(heap.size() == 9);
		Assert.assertTrue(heap.peek() == -10);
	}

	@Test
	public void addAndPeekTestNotOrdered() {

		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		PriorityQueue<Integer> que = new PriorityQueue<Integer>();

		for (int i : new int[] { 9, 0, 10, -1, 2, 3, -9, 123456, -99 }) {
			heap.add(i);
			que.add(i);

			Assert.assertTrue(heap.size() == que.size());
			Assert.assertTrue(heap.peek() == que.peek());
			Assert.assertFalse(heap.isEmpty());
		}
	}

	@Test
	public void addAndPeekTestDesc() {

		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		PriorityQueue<Integer> que = new PriorityQueue<Integer>();

		Integer[] arr = new Integer[] { 9, 0, 10, -1, 2, 3, -9, 123456, -99 };
		Arrays.sort(arr);
		for (int i : Arrays.asList(arr)) {
			heap.add(i);
			que.add(i);

			Assert.assertTrue(heap.size() == que.size());
			Assert.assertTrue((int) heap.peek() == (int) que.peek());
			Assert.assertFalse(heap.isEmpty());
		}
	}

	@Test
	public void addAndPeekTestAsc() {

		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		PriorityQueue<Integer> que = new PriorityQueue<Integer>();

		Integer[] arr = new Integer[] { 9, 0, 10, -1, 2, 3, -9, 123456, -99 };
		List<Integer> l = new ArrayList<Integer>(Arrays.asList(arr));
		Collections.sort(l);
		Collections.reverse(l);
		for (int i : l) {
			heap.add(i);
			que.add(i);

			Assert.assertTrue(heap.size() == que.size());
			Assert.assertTrue((int) heap.peek() == (int) que.peek());
			Assert.assertFalse(heap.isEmpty());
		}
	}

	@Test
	public void addAndPopTest() {

		Integer[] initial = { 3, 5, 0, -1 };

		BinaryHeap<Integer> heap = new BinaryHeap<Integer>(
				Arrays.asList(initial));
		PriorityQueue<Integer> que = new PriorityQueue<Integer>(
				Arrays.asList(initial));

		for (int i : new int[] { 2, 3, 5, 8, 1, -3, -43, 123, -34, 0, -22, 89,
				3, -312 }) {
			heap.add(i);
			que.add(i);
		}

		while (!heap.isEmpty()) {
			Assert.assertTrue(heap.size() == que.size());
			Assert.assertTrue((int) que.poll() == (int) heap.pop());
		}
		Assert.assertTrue(que.isEmpty());
	}

	@Test
	public void addAndRemoveTest() {

		Integer[] arr = new Integer[] { 2, 3, 5, 8, 1, -3, -43, 123, -34, 0,
				-22, 89, 3, -312 };

		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		PriorityQueue<Integer> que = new PriorityQueue<Integer>();

		for (int i : arr) {
			heap.add(i);
			que.add(i);
		}

		for (int i : arr) {
			Assert.assertTrue((int) heap.peek() == (int) que.peek());
			Assert.assertTrue(heap.size() == que.size());
			Assert.assertTrue(i == (int) heap.remove(i));
			que.remove(i);		
		}
		Assert.assertTrue(que.isEmpty());
		Assert.assertTrue(heap.isEmpty());
	}
}
