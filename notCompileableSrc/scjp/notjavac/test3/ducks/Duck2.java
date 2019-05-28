package scjp.notjavac.test3.ducks;

import scjp.notjavac.test3.Duck;
import bartczak.scjp.zad3duckpackage.Duck2;

public class Duck2 extends Duck {

	void haveAccessToProtectedVariablesOfParentClassThroughOtherInstancesOfItself(){
		Duck2 duck = new Duck2();
		duck.nFeathers = 546;
	}
	
}
