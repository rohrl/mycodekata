package scjp.various;

import java.lang.reflect.Field;

public class Strings {

	public void testEquals() {

		String a = "all literals ( specified at compile time ) can be compared using equality operator '==', however, whenever we use new Strings() - this method cannot be used.";
		String b = "all literals ( specified at compile time ) can be compared using equality operator '==', however, whenever we use new Strings() - this method cannot be used.";

		System.out.println(b);

		if (a == b) {
			System.out.println("yes, thats true");
		} else {
			System.out.println("nope, man");
		}

		String c = new String(a);

		if (a == c) {
			System.out.println("like that?");
		} else {
			System.out
					.println("however after some initialization, the hack will not work...");
		}

		String d = c.intern();

		if (a == d) {
			System.out
					.println("If you want to do it once more, You can intern the string, which will dereference one object and use some objects from static Strings cache");
		} else {
			System.out.println("intern isn't a good way");
		}

	}

	public void naiveModificator(String str) {
		str = "Strings changed";
	}

	public void testStringChangeabilityOnSunVM() {
			
			String str = "Strings original";
			System.out.println(str); 
			naiveModificator(str);
			System.out.println(str); 
			try {
				modificator(str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(str);
		
	}
	
	
	
	public void modificator(String str) throws Exception {  
		 Field valueField = str.getClass().getDeclaredField("value");
		 valueField.setAccessible(true);
		 char[] value = (char[])valueField.get(str); 
		 char[] reversed = new char[value.length];
		 for (int idx=0; idx<value.length; idx++) {
		  reversed[idx]=value[value.length-idx-1];
		 }
		 for (int idx=0; idx<value.length; idx++) {
		  value[idx]=reversed[idx];
		 }  
		}
}
