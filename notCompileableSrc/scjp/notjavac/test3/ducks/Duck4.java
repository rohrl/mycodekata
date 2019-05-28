package scjp.notjavac.test3.ducks;

import scjp.notjavac.test3.Duck;
import bartczak.scjp.zad3duckpackage.Duck1;

public class Duck4 extends Duck {

	void doesntHaveAccessToProtectedVariablesFromOtherInstancesOfDifferentSubclasses() {
		Duck1 duck = new Duck1();
		duck.nFeathers = 5431;
	}
	
}
