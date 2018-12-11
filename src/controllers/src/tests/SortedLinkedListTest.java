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
		
		book.push(new Book("Ender's Game", "Orson Scott Card", "Plot here", "Sci-Fi", "Orbit", null, 1985, 326));
		book.push(new Book("The Hitchhikers Guide to the Galaxy", "Douglas Adams", "Plot Here", "Comedy", "Pan Books", null, 1979, 180));
		book.push(new Book("Jonathan Strange & Mr Norrell", "Susanna Clarke", "Plot here", "Fantasy", "Bloomsbury", null, 2004, 261));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		book = null;
	}

	/**
	 * Test method for {adding to list
	 */
	@Test
	void testPush() {
		int size = book.size();
		book.push(new Book("Ender's Game", "Orson Scott Card", "Plot here", "Sci-Fi", "Orbit", null, 1985, 326));
		assertEquals(book.size(), size+1);
	}

	/**
	 * Test method for removing from list
	 */
	@Test
	void testRemove() {
		int size = book.size();
		book.remove(new Book("Ender's Game", "Orson Scott Card", "Plot here", "Sci-Fi", "Orbit", null, 1985, 326), Book.TitleComparator);
		assertEquals(book.size(), size);
		book.remove(new Book("The Hitchhikers Guide to the Galaxy", "Douglas Adams", "Plot Here", "Comedy", "Pan Books", null, 1979, 180), Book.TitleComparator);
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
		String tdata = "Ender's Game";
		String fdata = book.find(new Book("Ender's Game", "a", "a", "a", "a", null, 0, 0), Book.TitleComparator).getData().getTitle();
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
		String tdata = book.head.getData().getTitle();
		String fdata = book.getListHead().getTitle();
		assertEquals(tdata, fdata);
	}

	/**
	 * Test method for getting the tail of the list
	 */
	@Test
	void testGetListTail() {
		String tdata = book.tail.getData().getTitle();
		String fdata = book.getListTail().getTitle();
		assertEquals(tdata, fdata);
	}
	
	/**
	 * Test method for sorting of list
	 */
	@Test
	void testSort() {
		String tTitle = "Ender's Game";
		//String tAuthor = "Douglas Adams";
		//String tGenre = "Comedy";
		//int tYear = 1979;
		book.sort(book.head, Book.TitleComparator);
		assertEquals(tTitle, book.getListHead().getTitle());
		//book.sort(book.head, Book.AuthorComparator);
		//assertEquals(tAuthor, book.getListHead().getAuthor());
		//book.sort(book.head, Book.GenreComparator);
		//assertEquals(tGenre, book.getListHead().getGenre());
		//book.sort(book.head, Book.PublishedComparator);
		//assertEquals(tYear, book.getListHead().getPublished());
	}
	
	/**
	 * Test method for remove duplicates
	 */
	@Test
	void testDelDupe() {
		book.push(new Book("Ender's Game", "Orson Scott Card", "Plot here", "Sci-Fi", "Orbit", null, 1985, 326));
		assertTrue(book.size()==4);
		book.sort(book.head, Book.TitleComparator);
		book.delDupe();
		assertTrue(book.size()==3);
	}

}
