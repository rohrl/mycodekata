package scjp.notjavac;

public class Test6 {

	public String testMethod() {
		return "test";
	}

	// You cannot overload methods by changing only return type.
	 public void testMethod() {
			
	 }
	
	 
	 class ThisSuper {

			 ThisSuper(){
				 this(getString());
			 }
//			 that was not ok - think why?

			public ThisSuper(String string) {

			}

			String getString() {
				return "a";
			}

		}
}
