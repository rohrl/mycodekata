package mine;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class QuickSort<T extends Comparable<? super T>> {

	ExecutorService ex = Executors.newFixedThreadPool(8);
	ExecutorCompletionService<Void> ecs = new ExecutorCompletionService<Void>(
			ex);

	AtomicInteger counter = new AtomicInteger();

	class SortJob implements Callable<Void> {

		T[] array;
		int b, e;

		SortJob(T[] a, int b, int e) {
			this.array = a;
			this.b = b;
			this.e = e;
		}

		@Override
		public Void call() throws Exception {

			if (b >= e)
				return null;

			int i = b, j = e;
			T pivot = array[b];

			do {
				while (array[i].compareTo(pivot) < 0)
					i++;

				while (pivot.compareTo(array[j]) < 0)
					j--;

				if (i <= j) {
					T tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;

					i++;
					j--;
				}

			} while (i < j);

			counter.incrementAndGet();
			ecs.submit(new SortJob(array, b, j));
			counter.incrementAndGet();
			ecs.submit(new SortJob(array, i, e));

			return null;
		}

	}

	public void sort(T[] arr) throws InterruptedException, ExecutionException {

		counter.incrementAndGet();
		ecs.submit(new SortJob(arr, 0, arr.length - 1));

		do {
			ecs.take().get();
			counter.decrementAndGet();
		} while (counter.get() > 0);

		ex.shutdown();
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {

		final int SIZE = 15; // Integer.MAX_VALUE / 2048;

		Integer[] a = new Integer[SIZE];
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 100);// Integer.MAX_VALUE);
		}

		System.out.println("START");
		final long start = System.nanoTime();
		// new QuickSort<Integer>().sort(a);
		sort(a, 0, a.length - 1);
		System.out.println("SORTED " + (System.nanoTime() - start) / 1e9);
		
		System.out.println(Arrays.toString(a));

		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				throw new AssertionError(a[i] + " > " + a[i + 1]);
			}
		}

	}

	public static <U> void swap(U[] array, int a, int b) {
		U tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}

	public static <U extends Comparable<? super U>> void sort(U[] array, int b,
			int e) {

		if (b >= e)
			return;

		int pivotIdx = (int) (Math.random() * (e - b + 1)) + b;
		U pivotVal = array[pivotIdx];

		// swap pivotIdx and e
		swap(array, pivotIdx, e);

		pivotIdx = b;

		for (int i = b; i < e; i++) {
			if (array[i].compareTo(pivotVal) < 0) {
				swap(array, pivotIdx, i);
				pivotIdx++;
			}
		}

		// swap pivotIdx and e
		swap(array, pivotIdx, e);

		sort(array, b, pivotIdx - 1);
		sort(array, pivotIdx + 1, e);
	}
}
