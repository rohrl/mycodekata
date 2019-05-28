package scjp.notjavac.test3;

public class PublicClassWithPrivateVariables {

	private int p;
	
	private void toOverride(){
		
	}
	
	class ChildClass extends PublicClassWithPrivateVariables {
		
		int x = this.p; // you cannot do like this
		int y = p;		// however You can do like that :-)
						// but only if the class and the field
						// has no static or both have static modifier
		
	}
	
}
