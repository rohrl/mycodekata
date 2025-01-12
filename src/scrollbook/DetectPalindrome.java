package scrollbook;

public class DetectPalindrome {

	public static boolean isPalindrome(String string) {
		if(string.isEmpty())
			return false;
		final int slength = string.length();
		for(int i = 0; i*2 < slength; i++)
			if(string.charAt(i) != string.charAt(slength - 1 -  i))
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		
		System.out.println(isPalindrome("abc"));
		System.out.println(isPalindrome("abcba"));
		System.out.println(isPalindrome("a"));
		System.out.println(isPalindrome("aa"));
		System.out.println(isPalindrome("aba"));
		System.out.println(isPalindrome("abba"));
		System.out.println(isPalindrome("abbb"));
		System.out.println(isPalindrome("abcbc"));
		System.out.println(isPalindrome(""));
	}
}
