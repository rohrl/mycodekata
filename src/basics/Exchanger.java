package basics;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simple blocking queue with size = 1
 */
public class Exchanger<T> {

	private T item;
	private Lock lock = new ReentrantLock();
	private Condition emptyItem = lock.newCondition();

	static void say(String msg) {
		System.out.println(Thread.currentThread().getName() + "\t" + msg);
	}

	public void push(T t) {

		lock.lock();
		try {
			while (item != null) {
				say("waiting on push...");
				try {
					emptyItem.await();
				} catch (InterruptedException e) {
					say("interrupted!");
				}
			}

			say("...finished waiting. Pushing.");

			item = t;
			emptyItem.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public T pop() {
		lock.lock();
		try {
			while (item == null) {
				say("waiting on pop...");
				try {
					emptyItem.await();
				} catch (InterruptedException e) {
					say("interrupted!");
				}
			}

			say("...finished waiting. Poping.");

			T t = item;
			item = null;
			emptyItem.signalAll();
			return t;
		} finally {
			lock.unlock();
		}
	}

	static class Task implements Runnable {

		Exchanger<String> exchanger;
		boolean isProducer;
		int times;

		Task(Exchanger<String> exchanger, boolean produce, int times) {
			this.exchanger = exchanger;
			this.isProducer = produce;
			this.times = times;
		}

		@Override
		public void run() {
			while (--times > 0) {
				String item;
				if (isProducer) {
					item = "String " + Math.random() + " [" + times + "]";
					exchanger.push(item);
					say("pushed " + item);
				} else {
					item = exchanger.pop();
					say("popped " + item);
				}
			}
		}
	}

	public static void main(String[] args) {
		int times = 10;
		Exchanger<String> exchanger = new Exchanger<String>();

		new Thread(new Task(exchanger, false, times)).start();
		new Thread(new Task(exchanger, true, times)).start();
	}
}
