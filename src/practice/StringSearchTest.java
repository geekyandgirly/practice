package practice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringSearchTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDecodeFind() {
		assertEquals("jaEkB", 123, StringSearch.findDecode("jaEkB").intValue());
		assertEquals("jAEkB", 123, StringSearch.findDecode("jAEkB").intValue());
		assertEquals("JaekB", 123, StringSearch.findDecode("JaekB").intValue());
		assertNull("jaEkBk", StringSearch.findDecode("jaEkBk"));
		assertNull("kd", StringSearch.findDecode("kd"));
	}
	
	@Test
	public void testSearchNeedle() {
	    assertEquals("abc", StringSearch.findFirst("abc", "abc"));
	    assertEquals("abc", StringSearch.findFirst("abc", "abcabc"));
	    assertEquals("aaaabc", StringSearch.findFirst("abc", "aaaabc"));
	    assertNull(StringSearch.findFirst("abc", "bcbcbca"));

	    assertEquals("abc", StringSearch.findFirst("*bc", "abc"));
	    assertEquals("aaabc", StringSearch.findFirst("*bc", "aaabc"));
	    assertEquals("aaabc", StringSearch.findFirst("a*bc", "aaabc"));

	    assertEquals("aaabc", StringSearch.findFirst("****", "aaabc"));
	    assertEquals("aa", StringSearch.findFirst("a***a", "aa"));
	    assertEquals("abc", StringSearch.findFirst("*", "abc"));
	    assertEquals("", StringSearch.findFirst("*", ""));
	}
}
