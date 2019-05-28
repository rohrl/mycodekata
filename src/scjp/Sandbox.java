package scjp;

import java.util.ArrayList;

/**
 * <pre>
 *     Two top-level public classes cannot be in the same source file.
 *     main() cannot call an instance (non-static) method.
 *     Methods can have the same name as the constructor(s).
 *     Watch for thread initiation with classes that don't have a run() method.
 *     Local classes cannot access non-final variables.
 *     Case statements must have values within permissible range.
 *     Watch for Math class being an option for immutable classes.
 *     instanceOf is not the same as instanceof.
 *     Constructors can be private.
 *     Assignment statements can be mistaken for a comparison; e.g., if(a=true)...
 *     Watch for System.exit() in try-catch-finally blocks.
 *     Watch for uninitialized variable references with no path of proper initialization.
 *     Order of try-catch-finally blocks matters.
 *     main() can be declared final.
 *     -0.0 == 0.0 is true.
 *     A class without abstract methods can still be declared abstract.
 *     Map does not implement Collection.
 *     Dictionary is a class, not an interface.
 *     Collection (singular) is an Interface, but Collections (plural) is a helper class.
 *     Class declarations can come in any order (e.g., derived first, base next, etc.).
 *     Forward references to variables gives a compiler error.
 *     Multi-dimensional arrays can be "sparse" -- i.e., if you imagine the array as a matrix, every row need not have the same number of columns.
 *     Arrays, whether local or class-level, are always initialized
 *     Strings are initialized to null, not empty string.
 *     An empty string is not the same as a null reference.
 *     A declaration cannot be labelled.
 *     continue must be in a loop (e.g., for, do, while). It cannot appear in case constructs.
 *     Primitive array types can never be assigned to each other, even though the primitives themselves can be assigned. For example, ArrayofLongPrimitives = ArrayofIntegerPrimitives gives compiler error even though longvar = intvar is perfectly valid.
 *     A constructor can throw any exception.
 *     Initializer blocks are executed in the order of declaration.
 *     Instance initializers are executed only if an object is constructed.
 *     All comparisons involving NaN and a non-NaN always result in false.
 *     Default type of a numeric literal with a decimal point is double.
 *     int and long operations / and % can throw an ArithmeticException, while float and double / and % never will (even in case of division by zero).
 *     == gives compiler error if the operands are cast-incompatible.
 *     You can never cast objects of sibling classes (sharing the same parent).
 *     equals() returns false if the object types are different. It does not raise a compiler error.
 *     No inner class (non-static inner class) can have a static member.
 *     File class has no methods to deal with the contents of the file.
 *     InputStream and OutputStream are abstract classes
 *     
 * </pre>
 * 
 * @author kpajak
 * 
 */
interface Foo {
	void foo();

	void bar();
}

abstract class Foobar implements Foo {
	// abstract class don't have to explicitly declare interface methods as
	// abstract
	// so the body can be empty and foo and bar become abstract now
}

class A {

	public A() {
		System.out.println("2. THEN PARENT CONSTRUCTOR");
	}

	private void method() {
		System.out.println("a");
	}

	public void foo() {
		method();
	}

	public static void staticFoo() {
		System.out.println("A's static foo");
	}

	public static void staticBar() {
		System.out.println("A's static bar");
	}

	protected int var = 10;

	public void printVar() {
		System.out.println(var);
	}

	void $dollarWorksInNames() {

	}
}

class B extends A {
	final int x;
	static final int y;

	static {
		System.out.println("1. FIRST IS EXECUTED STATIC BLOCK");
		// final fields can be initialized in initialization blocks
		y = 2;
	}

	{
		System.out.println("3. THEN NORMAL BLOCK");
	}

	public B() {
		System.out.println("4. THEN CONSTRUCTORS");
		// final fields can be initialized in constructor
		x = 1;
	}

	protected void method() {
		// super.method(); - if its not visible then we can't access
		System.out.println("b");
	}

	// static methods are not overriden but hidden
	public static void staticFoo() {
		System.out.println("B's static foo");
	}

	// fields are not overriden like methods!
	protected int var = 20;
}

public class Sandbox {

	public static void main(String[] args) {
		B b = new B();
		// note that result depends on whether A.method is
		// private/package-private
		b.foo();

		System.out.println(true & false);

		System.out.println(4 | 2);
		System.out.println(4L | 2L);
		System.out.println(4L | 2);

		// >>> pads left with 1's if negative
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE >> 10));
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE >>> 10));

		// we can use ^ & | also for booleans
		System.out.println(false ^ false);

		// you can declare array of interfaces/enums
		Foo[] x;
		Enum[] enums;

		int i = 1;
		long l = 1;
		System.out.println(i == l); // long is casted to int (?)
		short s = 1;
		float f = 1.0f;
		System.out.println(f == s);

		A a = b;
		// we can invoke statics from A:
		B.staticBar();
		// invokes A.staticFoo regardless of actual class of object being
		// referenced
		a.staticFoo();

		// fields are not overriden like methods!
		b.printVar();

		byte bb = 1;
		bb++; // the result of any arithmetic is always AT LEAST int

		ArrayList list = new ArrayList();
		// casting an obj to generic does not generate compile error...
		ArrayList<String> listStr = list;
		// ... contrary to incompatible generic parameters:
		// ArrayList<Integer> listBuf = listStr;

		Integer intObj = 34;
		int intPrim = 34;
		// obj will be unboxed, not primitive boxed
		System.out.println(intObj == intPrim);

		char c = 'a';
		switch (c) {
		default:
			System.out.println("default can be anywhere");
		case 65:
			System.out.println("65");
			break;
		case 'a':
			System.out.println("a");
		case 3:
			System.out.println("3");
		}

		// floats and doubles don;t throw Arithmetic Exception
		double willBeNan = 4.0 / 0;
		double willThrowEx = 4 / 0;
	}
}