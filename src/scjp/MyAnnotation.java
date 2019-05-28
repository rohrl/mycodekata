package scjp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	String name() default "default name";

	int number() default 1;

	String[] array = { "A", "B" };
}
