package practice;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexMatchingTest {

	@Test
	public void test() {
		  String[] test1 = new String[] { "aa", "a" };
		  String[] test2 = new String[] { "abxc", "ab.c" };
		  String[] test3 = new String[] { "axb", "c*a.b" };
		  String[] test4 = new String[] { "axb", "c*a.b" };
		  String[] test5 = new String[] { "cax", "c*a.b*" };
		  
		  assertTrue("test1", RegexMatching.isMatch(test1[0], test1[1]));
		  assertTrue("test2", RegexMatching.isMatch(test2[0], test2[1]));
		  assertTrue("test3", RegexMatching.isMatch(test3[0], test3[1]));
		  assertTrue("test4", RegexMatching.isMatch(test4[0], test4[1]));
		  assertTrue("test5", RegexMatching.isMatch(test5[0], test5[1]));
	}
}
