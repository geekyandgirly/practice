package practice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LRUCacheTest {

	private LRUCache cache;
	
	@Before
	public void setUp() throws Exception {
		cache = new LRUCache(4);
		cache.set(1, 10);
		cache.set(2, 20);
		cache.set(3, 30);
		cache.set(4, 40);
	}

	@After
	public void tearDown() throws Exception {
		cache = null;
	}

	@Test
	public void test() {
		
		assertEquals("init head value should be 10", 10, cache.getHeadValueForTest());
		assertEquals("init tail value should be 40", 40, cache.getTailValueForTest());
		
		int v = cache.get(2);
		assertEquals("access 2", 20, v);
		assertEquals("last accessed 2 (middle), head value should be 10", 10, cache.getHeadValueForTest());
		assertEquals("last accessed 2 (middle), tail value should be 20", 20, cache.getTailValueForTest());
		
		cache.set(1, 11);
		assertEquals("last set 1 (head) to 11, head value should be 30", 30, cache.getHeadValueForTest());
		assertEquals("last set 1 (head) to 11, tail value should be 11", 11, cache.getTailValueForTest());
		
		int v1 = cache.get(1);
		assertEquals("access 1 (now tail)", 11, v1);
		assertEquals("last accessed 1 (tail), head value should be 30", 30, cache.getHeadValueForTest());
		assertEquals("last accessed 1 (tail), tail value should remain 11", 11, cache.getTailValueForTest());
		
		cache.set(5,  50);
		assertEquals("exceed capacity, head value should be 40", 40, cache.getHeadValueForTest());
		assertEquals("exceed capacity, tail value should remain 50", 50, cache.getTailValueForTest());
	
	}
}
