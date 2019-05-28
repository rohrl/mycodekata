package scjp;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestInvoker {

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException, InstantiationException {
		for (int i = 1; i <= 10; ++i) {
			try {
				String className = "scjp.Test" + i;
				Class clazz = Class.forName(className);
				Annotation[] anns = clazz.getAnnotations();
				for (Annotation a : anns) {
					System.out.println("ANNOTATION " + a.toString());
					if(a instanceof MyAnnotation)
						System.out.println("\tNAME = "+((MyAnnotation) a).name());
				}
				Method[] methods = clazz.getMethods();
				Object test = clazz.newInstance();
				for (Method method : methods) {
					if (method.getName().startsWith("test")) {
						method.invoke(test, new Object[0]);
					}
				}
			} catch (ClassNotFoundException ignore) {
				// skip testcase;
				continue;
			}
		}
	}
}
