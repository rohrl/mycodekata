package myscjp;

import myscjp.prot.Protected;

public class CanAccessProtectedNotIherited extends Protected {

	
	void method() throws RuntimeException {

		// can access INHERITED x:
		super.x++;

		// BUT NOT others' x:
		Protected s = new Protected();
		// s.x++; // wont compile...

	}

}
