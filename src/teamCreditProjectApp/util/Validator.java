package teamCreditProjectApp.util;

/* Program Name	:	Validator.java
 * Purpose		:	This class contains static methods to
 *					validate that input parameter is correct.
 * Authjor		:	Phoon Lee Kien
 * Last Modified:	28-11-2014
 */
public class Validator {

	public static boolean isAllLetter(String str) {
		boolean result = true;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static boolean isPhoneNum(String str) {
		boolean result = true;
		try {
			Integer.parseInt(str);
			if ((str.charAt(0) != '9') && (str.charAt(0) != '6')
					&& (str.charAt(0) != '8'))
				result = false;
			else if (str.length() != 8)
				result = false;
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}

	public static void main(String[] args) {
		String s = "Phoon Lee Kien";
		if (isAllLetter(s))
			System.out.println("Valid!");
		else
			System.out.println("Invalid!");
	}

}
