package mine;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThrowNotNew {

	static class SomeException extends Exception {
	}

	/** Exception which does not create the stacktrace */
	static class SomeFasterException extends SomeException {
		@Override
		public synchronized Throwable fillInStackTrace() {
			return null;
		}
	}

	static final SomeFasterException SINGLE_INSTANCE = new SomeFasterException();

	final static int EXCEPTION_COUNT = 100000;
	final static int THREADS = 100;
	final static int DEPTH = 20;
	final static AtomicInteger counter = new AtomicInteger();

	// final static CountDownLatch ready = new CountDownLatch(1);
	// final static CountDownLatch done = new CountDownLatch(EXCEPTION_COUNT);

	static boolean recursion(SomeException toThrow, int lvl)
			throws SomeException {
		if (lvl == 0)
			if (toThrow != null)
				throw toThrow;
			else
				return false;
		return recursion(toThrow, lvl - 1);
	}

	final static Callable<Void> ifOnly = new Callable<Void>() {

		@Override
		public Void call() throws SomeException {
			int x = 2;
			if (recursion(null, DEPTH))
				x--;
			else
				x++;
			counter.incrementAndGet();
			return null;
		}
	};

	final static Callable<Void> throwNew = new Callable<Void>() {

		@Override
		public Void call() {
			try {
				recursion(new SomeException(), DEPTH);
			} catch (SomeException e) {
				counter.incrementAndGet();
			}
			return null;
		}
	};

	final static Callable<Void> throwNotNew = new Callable<Void>() {

		@Override
		public Void call() {
			try {
				recursion(SINGLE_INSTANCE, DEPTH);
			} catch (SomeException e) {
				counter.incrementAndGet();
			}
			return null;
		}
	};

	void test(Callable<Void> c) throws InterruptedException {
		System.out.println("START");
		ExecutorService ex = Executors.newFixedThreadPool(THREADS);
		counter.set(0);
		
		long start = System.nanoTime();
		ex.invokeAll(Collections.nCopies(EXCEPTION_COUNT, c));
		System.out.println(0.000001d * (System.nanoTime() - start));
		
		if (counter.get() != EXCEPTION_COUNT)
			throw new AssertionError("Incorrect number of iterations");
		ex.shutdown();
	}

	public static void main(String[] args) throws InterruptedException {
		ThrowNotNew test = new ThrowNotNew();
		System.out.println(" * Throw new");
		test.test(throwNew);
		System.out.println(" * Throw single instance");
		test.test(throwNotNew);
		System.out.println(" * Only if else");
		test.test(ifOnly);
	}
}
