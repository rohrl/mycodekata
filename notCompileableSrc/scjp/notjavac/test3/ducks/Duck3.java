package scjp.notjavac.test3.ducks;

import scjp.notjavac.test3.Duck;

public class Duck3 extends Duck {

	void doesntHaveAccessToProtectedVariableOfAParentClassInOtherInstance() {
		Duck duck = new Duck();
		duck.nFeathers = 543;
	}
	
}
