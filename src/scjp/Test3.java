package scjp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test3 {
	
	public void testAccessors() {
		
	}
			
	protected class ProtectedInnerClassesAreAllowed {
	
	}
	
	private class SoAsPrivateOnes {
		
	}
	
	public final static int w(){
//		what is it for ? :D why to make final method that is only static, 
//		so no inhertance and overriding..?
		return 0;
	}
	
	static class XYZ {
		
	}
	
	
}
