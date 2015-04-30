package practice;

/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 *  (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
 */
public class EditDistance {
  public static int editDistance(String w1, String w2) {
	  int m = w1.length(), n = w2.length();
	  if (m == 0) {
		  return n;
	  }
	  if (n == 0) {
		  return m;
	  }
	  
	  int[][] table = new int[m+1][n+1];
	  
	  for (int i = 0; i <= m; i++) {
	  	table[i][0] = i;
	  }
	  for (int j = 0; j <= n; j++) {
  		table[0][j] = j;
	  }
	  
	  for (int i = 1; i <= m; i++) {
		for(int j = 1; j <= n; j++) {
	    	int cost = (w1.charAt(i) == w2.charAt(j)) ? 0 : 1;
		    table[i][j] = Math.min(
			      Math.min(table[i-1][j] + 1 /* insert */, table[i][j-1] +1 /* delete */),
				    table[i-1][j-1] + cost /* replace */);
			}
	  }
	  
	  return table[m][n];
  }
}
