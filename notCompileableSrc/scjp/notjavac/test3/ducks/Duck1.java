package scjp.notjavac.test3.ducks;

import scjp.notjavac.test3.Duck;

public class Duck1 extends Duck {

	void haveAccessToProtectedVariablesFromASubclassInCurrentInstance(){
		nFeathers = 432;
	}
	
}
