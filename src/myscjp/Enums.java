package myscjp;

public class Enums {
	static Animals a;

	public static void main(String[] args) {
		System.out.println(a.DOG.sound + " " + a.FISH.sound);// works :)
	}
}

enum Animals {
	DOG("woof"), CAT("meow"), FISH("burble");
	String sound;

	Animals(String s) {
		sound = s;
	}
}
