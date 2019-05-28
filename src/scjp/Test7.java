package scjp;

public class Test7 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test7().testNormalThreads();
	}
	
	
	class MyThread implements Runnable {
		
		int id;
		
		public MyThread(int id) {
			this.id = id;

		}
		
		public void run() {
			for (int i = 0; i < 55; i++) {
				System.out.println("thread " + id  + " : " + i);
				
			}
		}
		
	}
	
	public void testNormalThreads(){
		
		Thread thread = new Thread(new MyThread(10));
		
		thread.start();
		
		try {
		thread.start();
		} catch ( IllegalThreadStateException e) {
			System.out.println("You cannot start one thread twice! ;-)");
		}
		
		thread.run();
		
		
	}

}
