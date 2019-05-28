package google.training;

public class Semaphore {
	static final int MAX_VAL = 3;
	int counter;

	public synchronized void inc() {
		System.out.print(" < inc:" + counter);
		while (counter == MAX_VAL)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		counter++;
		notifyAll();
		System.out.println(" > inc:" + counter);
	}

	public synchronized void dec() {
		System.out.print(" < dec:" + counter);
		while (counter == 0)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		counter--;
		notifyAll();
		System.out.println(" > dec:" + counter);
	}

	public static void main(String[] argv) {
		final Semaphore s = new Semaphore();
		new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++)
					s.inc();
			}
		}.start();
		new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++)
					s.dec();
			}
		}.start();
	}
}