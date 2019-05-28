package scjp;

import java.io.File;
import java.io.IOException;

public class Test5 {
	
	public void testTryWithoutCatch() {
		
		try {
			innerTest();
		} catch (IOException e) {
			System.out.println("exception caught");
		}
		
	}

	private void innerTest() throws IOException {
		System.out.println("You can use try-finally blocks without catching the exception");
		File file = new File("sadsad/asdas/dsadas/asd");
		try {
			file.createNewFile();
		} finally {
			System.out.println("after getting the exception - You have this code run");
		}
		
	}
	
}
