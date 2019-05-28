package scjp.notjavac;

public class Test2 {

	void a() {
		final int a = 1;
		final int b;
		b = 2;
		int x = 0;
		switch (x) {
		case a: // ok
		case b: // compiler error
		}
	}
	
}
