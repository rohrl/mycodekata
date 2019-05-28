package scjp;


//@MyAnnotation
public class Test2 {
//test for topics in chapter 2 of sybex guide
	
	public static void main (String [] a) {
		new Test2().testThreeArgumentOperatorAndConversion();
	}
	
	
	public void testResolvingComplexExpression() {
		System.out.println("testResolvingComplexExpression");
		int[] a = { 4,4};
		System.out.println(a[0] + ":" + a[1]);
		int b = 0;
		a[b] = b = 1;   /// this line is something interesting - it is resolving
						// where to put value first - from left to right, and then
						// resolving values from right to left
		System.out.println(a[0] + ":" + a[1]);
	}
	
	public void testUnaryOperator() {
		System.out.println("testUnaryOperator");
		int x = +1;
		int y = -x;
		System.out.println(y);
		boolean hehe = !!!!!!!!!true;	//correct :)
		int ohMyGod = ~~~~~~~~x; 		//correct :)
	}

	public void testArithmeticBehavior() {
		System.out.println("testArithmeticBehavior");
		double x = Double.NaN;
		if ( x == Double.NaN ) {
			System.out.println("such a behavior is expected, but is not gonna happen");
		} else {
			System.out.println("such a behavior wasn't expected, but is true");
			if (Double.isNaN(x)) {
				System.out.println("This is the only way of checking NaN value");
			}
		}
		x = Double.POSITIVE_INFINITY;
		if ( x == Double.POSITIVE_INFINITY) {
			System.out.println("however for other global values of floating-point types it's ok");
		}
	}

	public void testAddition() {
		int a = Integer.MAX_VALUE;
		int b = Integer.MAX_VALUE;
		int intResult = a + b;
		long longResult = (long) a + (long) b;
		System.out.println("value = " + a);
		System.out.println("intResult = " + intResult);
		System.out.println("longResult = " + longResult);
		short s = Short.MAX_VALUE;
		short r = Short.MAX_VALUE;
		//this is not ok :
		//short shortResult = s + r;
		int promotedResult = s + r;
		System.out.println("automatic promotion of operands give value = " + promotedResult);
		
		
	}
	
	public void testThreeArgumentOperatorAndConversion() {
		double d = 99.99;
		int i = 9;
		int x = 4;
		System.out.println("Strange behaviour of promoting operands before deciding " +
				"about the condition in three argument operator");
		System.out.println((x > 4 ) ? d : i);
	}
	
	

}
