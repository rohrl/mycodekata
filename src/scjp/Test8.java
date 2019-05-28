package scjp;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Test8 {

	public void testScanner() {
		
		String test = "bbbanyText_b_anytext_b";
		System.out.println("we want to find somethign that starts with 'a' and finishes with 'b'");
		String regex = "a.+b";
		Scanner scanner = new Scanner(test);
		scanner.useDelimiter(regex);
		
		System.out.println("regex by default matches the longest possible string - greedy");
		
		while (scanner.hasNext()) {
			String string = (String) scanner.next();
			System.out.println(string);
		}
		
	}

	public void testCurrency() {
		Locale[] locales = { Locale.US, Locale.TAIWAN, Locale.CHINA, Locale.GERMANY };
		for (Locale loc:locales) {
		  NumberFormat f = NumberFormat.getCurrencyInstance(loc);
		  String formattedMoney = f.format(123.45);
		  System.out.format("%15s: %s\n",
		                    loc.getDisplayCountry(),
		                    formattedMoney);
		}

	}
	
	public class Apple {
	}
	
	public void testSet(){
	   
		try{
	         Set<Apple> set = new TreeSet<Apple>();
	         set.add(new Apple());
	         set.add(new Apple());
	         set.add(new Apple());
		} catch ( ClassCastException cce) {
			System.out.println("if You are adding something to the treeset it has to be comparable,\n");
		}

	}
	
}
