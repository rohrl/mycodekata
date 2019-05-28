package scjp.notjavac;

public class Test4 {

	public void testOperatorsHiddenPromotion() {
		byte b1 = 10;
		byte b2 = 2;
		//this will not compile:
		byte result = b1 * b2;
		// this will be an int
		int r = b1 * b2;
		
	}
	
	
}
