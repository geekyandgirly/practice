package practice;

public class RegexMatching {
  public static boolean isMatch(String s, String p) {
	  // initial checks for null and empty string
	  int offset = 0;
	  char currentChar = s.charAt(0);
	  char previousChar = currentChar;
	  
	  int i = 0;
	  while (i < p.length() - 1) {
		  char nextChar = p.charAt(i+1);
		  if (nextChar == '*') {
			  // can have 0 or more current char, so don't care about current char
			  // and the next char '*'
			  System.out.println("nextChar is *, currentChar=" + currentChar);
			  i += 2;
			  previousChar = nextChar;
		  } else if (currentChar == '*') {
			  System.out.println("currentChar is star, currentChar" + currentChar);
			  i++;
			  previousChar = currentChar;
		  } else if (currentChar == '.') {
			  System.out.println("currentChar is ., currentChar" + currentChar);
			  if (offset >= p.length()) {
				  System.out.println("currentChar is ., and offset over length offset=" + offset);
				  // the dot needs to match at least one character, but we run over end of s.
				  return false;
			  }
			  i++;
			  previousChar = currentChar;
			  offset++;
		  } else {
			  System.out.println("currentChar is " + currentChar);

			  if (offset >= p.length()) {
				  // we have a char that is not '.' or '*', and next char is not '*',
				  // so we need to match the exact char, but there's no mare chars in s to match it.
				  return false;
			  }
			  
			  int foundIndex = -1;
			  if (previousChar == '*') {
				System.out.println("previous char is *, just find next char anywhere starting at offset");
    		    foundIndex = s.indexOf(currentChar, offset);
			  } else if (s.charAt(offset) == currentChar) {
				System.out.println("we must match this char");
				foundIndex = offset;
			  }
			  if (foundIndex < 0) {
				  System.out.println("didn't find it, we are done");
				  return false;
			  }
			  
			  offset = foundIndex + 1;
			  i++;
			  System.out.println("foundIndex for " + currentChar + ": " + foundIndex
					  + " offset=" + offset + " i=" + i);
		  }
		  
		  if (i < p.length()) {
			  currentChar = p.charAt(i);
		  }
	  }
	  
	  // The last character if we didn't skip it already
	  if (i == (p.length() - 1)) {
		  if (currentChar == '*') {
			  System.out.println("Last char is *, currentChar=" + currentChar);
			  return true;
		  }
		  if (currentChar == '.') {
			  System.out.println("Last char is ., currentChar=" + currentChar + " offset=" + offset);
			  return (offset < s.length());
		  }

		  System.out.println("Last char is " + currentChar + " offset=" + offset);
          return s.indexOf(currentChar, offset) >= 0;
	  }
	  
	  // this is the case where the last char in p is '*', so we skipped 2 indices and got to length
	  System.out.println("Last char should be * since we i out of bound, currentChar=" + currentChar);
	  return true;
  }
}
