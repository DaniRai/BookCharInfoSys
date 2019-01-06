/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.*;
import controllers.*;

/**
 * @author Daniel Quinlan
 * @studentNumber 01133940
 * @course Entertainment Systems Yr 2
 * 
 * @param <T>
 */
class SortedLinkedListTest {
	private SortedLinkedList<Book> book;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		book = new SortedLinkedList<Book>();
		
		book.push(new Book(null, null, null, null, null, null, 0, 0));
		book.push(new Book(null, null, null, null, null, null, 0, 0));
		book.push(new Book(null, null, null, null, null, null, 0, 0));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		book = null;
	}

	/**
	 * Test method for adding to list
	 */
	@Test
	void testPush() {
		int size = book.size();
		book.push(new Book(null, null, null, null, null, null, size, size));
		assertEquals(book.size(), size+1);
	}

	/**
	 * Test method for removing from list
	 */
	@Test
	void testRemove() {
		int size = book.size();
		book.remove(new Book(null, null, null, null, null, null, size, size), Book.TitleComparator);
		assertEquals(book.size(), size-1);
	}

	/**
	 * Test method for clearing list
	 */
	@Test
	void testClear() {
		assertTrue(book.size()>0);
		book.clear();
		assertEquals(book.size(), 0);
	}

	/**
	 * Test method for searching list
	 */
	@Test
	void testFind() {
		String tdata = "";
		String fdata = book.find(new Book(tdata, "a", "a", "a", "a", "a", 0, 0), Book.TitleComparator).getData().getTitle();
		assertEquals(tdata, fdata);
	}

	/**
	 * Test method for getting size of list
	 */
	@Test
	void testSize() {
		assertEquals(book.size(),3);
	}

	/**
	 * Test method for getting head of the list
	 */
	@Test
	void testGetListHead() {
		String tdata = "";
		String fdata = book.getListHead().getData().getTitle();
		assertEquals(tdata, fdata);
	}

	/**
	 * Test method for getting the tail of the list
	 */
	@Test
	void testGetListTail() {
		String tdata = "";
		String fdata = book.getListTail().getData().getTitle();
		assertEquals(tdata, fdata);
	}
	
	/**
	 * Test method for sorting of list
	 */
	@Test
	void testSort() {
		String tTitle = "";
		String tAuthor = "";
		String tGenre = "";
		int tYear = 0;
		book.sort(book.head, Book.TitleComparator);
		assertEquals(tTitle, book.getListHead().getData().getTitle());
		book.sort(book.head, Book.AuthorComparator);
		assertEquals(tAuthor, book.getListHead().getData().getAuthor());
		book.sort(book.head, Book.GenreComparator);
		assertEquals(tGenre, book.getListHead().getData().getGenre());
		book.sort(book.head, Book.PublishedComparator);
		assertEquals(tYear, book.getListHead().getData().getPublished());
	}
	
	/**
	 * Test method for remove duplicates
	 */
	@Test
	void testDelDupe() {
		book.push(new Book(null, null, null, null, null, null, 0, 0));
		assertTrue(book.size()==4);
		book.sort(book.head, Book.TitleComparator);
		book.delDupe();
		assertTrue(book.size()==3);
	}

}
