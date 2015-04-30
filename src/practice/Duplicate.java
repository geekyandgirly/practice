package practice;

import java.util.HashSet;

public class Duplicate {
	private Duplicate() {};
	
	public static int removeDuplicateInPlace(String[] lines) {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		
		// To keep track of how many lines have been removed.
		int numRemoved = 0;
		int i = 0;  // Running index in the array.
		
		while (i < (lines.length - 1)) {
			String line = lines[i];
			int hash = quickHash(line);
			if (line == null || hashSet.contains(hash)) {
				if (line != null) numRemoved ++;
				if ((i + numRemoved) == lines.length) {
					// reach the end, every line after i are null.
					trimArray(lines, numRemoved);
					return numRemoved;
				}
				lines[i] = lines[i + numRemoved];
				lines[i + numRemoved] = null;
			} else {
				hashSet.add(hash);
				i++;
			}
		}
		
		// Last line
		if (i == lines.length - 1) {
			String line = lines[i];
			int hash = quickHash(line);
			if ((line != null) && hashSet.contains(hash)) {
				lines[i] = null;
				numRemoved ++;
			}
		}
		return numRemoved;
	}
	
	private static void trimArray(String[] array, int numLinesToRemove) {
	  // get rid of numLinesToRemove at the end of the array.
	}
	
	private static int quickHash(String line) {
		if (line == null) {
			return -1;
		}
		// Is this method efficient when the line is long?
		return line.hashCode();
	}
	
	public static void main(String args[]) {
		String lines[] = new String[] {"bar", "foo", "foo", "tic", "tac", "bar", "tic"};
		int linesRemoved = Duplicate.removeDuplicateInPlace(lines);
		for (int i = 0; i < lines.length - linesRemoved; i++) {
			System.out.println(lines[i]);
		}
		System.out.println("done");
		
		String lines1[] = new String[] {"bar", "foo", "foo", "bar", "tac", "bar", "tok", "tok"};
		int linesRemoved1 = Duplicate.removeDuplicateInPlace(lines1);
		for (int i = 0; i < lines1.length - linesRemoved1; i++) {
			System.out.println(lines1[i]);
		}

	}
}
