package myscjp;

class NonPublicCanHaveDiffNameThanFile {

	protected int x = 1;

}

class Subclass extends NonPublicCanHaveDiffNameThanFile {
	public int x = 2; // compiles! hides the super.x

	@Override
	public String toString() {
		return "Super: " + super.x + " my: " + x;
	}

	// wont be able to run because class is nonpublic :)
	public static void main(String[] args) {
		NonPublicCanHaveDiffNameThanFile c = new Subclass();
		System.out.println(c.x + " " + c);

		int[][][] x = new int[3][][];

		for (int __x = 0; __x < 3; __x++)
			;

		// compiles!
		Boolean[] ba[];
		Short myGold = 7;

		Integer[][][] multi = new Integer[1][2][3];
		Integer z = multi[0][0][0];
		Integer[][][] multi2 = new Integer[5][][];
		System.out.println(multi2[0] == null);

		// dont compile:
		// int #lb = 7;
		// long [] x [5];
		// enum Traffic { RED, YELLOW, GREEN };
	}
}

interface FileCanHaveAtMostOnePublicClassOrInterfaceButCanHaveNoneInParticular {
	// cant have static methods
	// all variables are public static final
	// all methods are public abstract
}