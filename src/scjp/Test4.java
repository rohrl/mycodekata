package scjp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test4 {

	public void testCasting() {
		try {
		Number n;
		Integer i=5;
		Double d=5.5;
		// You cannot do:
		// d= (Double) i;
		// however you can:		
		d= (Double) (Number) i;
		System.out.println(d);
		} catch (ClassCastException cce) {
			System.out.println("'Hacking' casting mechanism will be found at runtime");
		}
	}
	
	public static void main (String [] a) {
		new Test4().testPrimitiveCasting();
	}
	
	public void testConversionAndCastingPrecise() {
		for (int i = 0; i < 20; i++) {
			int x = new Random().nextInt(Integer.MAX_VALUE);
			double d = x;
			int cc = (int) d;
			System.out.println("int > double >int difference from original " + x + " to " + cc + 
					" is " + Math.abs(cc-x));
		}
		for (int i = 0; i < 20; i++) {
			int x = new Random().nextInt(Integer.MAX_VALUE);
			float f = x;
			int cc = (int) f;
			System.out.println("int > float > int difference from original " + x + " to " + cc + 
					" is " + Math.abs(cc-x));
		}		
		for (int i = 0; i < 20; i++) {
			long x = new Random().nextLong();
			double f = x;
			long cc = (long) f;
			System.out.println("long > double > long difference from original " + x + " to " + cc + 
					" is " + Math.abs(cc-x));
		}
		for (int i = 0; i < 20; i++) {
			long x = new Random().nextLong();
			double f = x;
			long cc = (long) f;
			System.out.println("long > float > long difference from original " + x + " to " + cc + 
					" is " + Math.abs(cc-x));
		}
		
	}
	
	public void testArrayConversion() {
		ArrayList[] array1 = new ArrayList[32];
		List[] array2;
		array2 = array1;
		System.out.println("You can convert arrays when the arrays are of object references that can be casted");
	}
	
	public void testPrimitiveCasting() {
		short z = Short.MAX_VALUE - 1;
		byte b = (byte) z;
		System.out.println("When casting primitives to lowest precision You lose data!\n" +
				"original= " + z  + " casted= " + b);
		double d = Double.MAX_VALUE;
		float f = (float) d;
		System.out.println("original= " + d + " casted= " + f);
		d= 1.11111111;
		f = (float) d;
		System.out.println("Sometimes casting down can get quite normal results and You only lose precision.\n" +
				"original= " + d + " casted= " + f);
	}
	
	
}
