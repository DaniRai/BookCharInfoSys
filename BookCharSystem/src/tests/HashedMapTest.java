/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.HashedMap;
import models.Book;
import models.Character;

/**
 * @author DaniR
 *
 */
class HashedMapTest {
	private HashedMap<Book,Character> map;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		map = new HashedMap<Book,Character>();
		
		map.push(new Book(null, null, null, null, null, null, 0, 0),new Character(null,0,'a',null));
		map.push(new Book(null, null, null, null, null, null, 0, 0),new Character(null,0,'a',null));
		map.push(new Book(null, null, null, null, null, null, 0, 0),new Character(null,0,'a',null));
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		map = null;
	}

	/**
	 * Test method for {@link controllers.HashedMap#get(java.lang.Object)}.
	 */
	@Test
	void testGet() {
		Book b = new Book(null, null, null, null, null, null, 0, 0);
		Character c = new Character(null,0,'a',null); 
		map.push(b,c);
		Character d = map.get(b).get(0);
		assertEquals(c,d);
	}

	/**
	 * Test method for {@link controllers.HashedMap#erase(java.lang.Object)}.
	 */
	@Test
	void testErase() {
		Book b = new Book(null, null, null, null, null, null, 0, 0);
		Character c = new Character(null,0,'a',null); 
		map.push(b,c);
		map.erase(b);
		assertNull(map.get(b));
	}

}
