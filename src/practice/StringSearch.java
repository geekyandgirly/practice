package practice;

public class StringSearch {
	
	/** 
	 * Find first occurrence of a needle string in a haystack. The needle string
	 *  can contain "*" which match 0 to any number of characters.
	 */
	public static String findFirst(String bigNeedle, String haystack) {
		checkNotNull(bigNeedle);
		checkNotNull(haystack);
		
		String[] needles = bigNeedle.split("\\*");
		int start = 0, end = 0;
		if (needles.length == 1) {
			if (needles[0].length() == 0) {
				return haystack;
			}
			end = search(needles[0], haystack, start);
			if (end > start) {
				return haystack.substring(start, end);
			} else {
				return null;
			}
		}
		
		int firstFoundIndex = -1;
		for (String needle : needles) {
			if (needle.length() == 0) {
				// empty string, this happens when there are multiple ***.
				continue;
			}
			end = search(needle, haystack, start);
			if (end > start) {
				start = end;
				if (firstFoundIndex < 0) {
					firstFoundIndex = end - needle.length();
				}
			} else {
				return null;
			}			
		}
		
		if (start == end) {
			// needle is all ***
			return haystack;
		}
		return haystack.substring(firstFoundIndex, end);
	}

	private static void checkNotNull(String str) {
		if (str == null) {
			throw new IllegalArgumentException("input should not be null");
		}
	}
	
	private static int search(String needle, String haystack, int startIndex) {
		if (haystack.length() - startIndex < needle.length()) {
			return -1;
		}
		int foundIndex = haystack.indexOf(needle, startIndex);
		if (foundIndex >= startIndex) {
			return foundIndex + needle.length();
		}
		return -1;
	}
	
	/**********  match string with all capitalization cases **********/
	public static Integer findDecode(String str) {
		String surfix = (str.length() == 1) ? "" : str.substring(1);
		return (doDecode("", str.charAt(0), surfix));
	}
	
	private static Integer doDecode(String prefix, char c, String surfix) {
		Integer ret = decode(prefix + c + surfix);
		if (ret != null) {
			return ret;
		}
				
		if (surfix.length() == 0) {
			return null;
		}
		
		String newSurfix = (surfix.length() == 1) ? "" : surfix.substring(1);
		
		Integer lower = doDecode(prefix + Character.toLowerCase(c), surfix.charAt(0),
				newSurfix);
		if (lower != null) {
			return lower;
		}
		
		Integer upper = doDecode(prefix + Character.toUpperCase(c), surfix.charAt(0),
				newSurfix);
		return upper;
	}
	
	private static Integer decode(String encoded) {
		if (encoded.equals("jaEkB")) {
			return new Integer(123);
		}
		return null;
	}
	
}
