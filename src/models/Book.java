//----------Packages & Imports----------//
package models;

import java.util.Comparator;

/**
 * @author Daniel Quinlan
 * @studentNumber 01133940
 * @course Entertainment Systems Yr 2
 * 
 * @param <T>
 */
public class Book implements Comparable<Book> {
	private String title;
	private String author;
	private String plot;
	private String genre;
	private String publisher;
	private String cover;
	private int published;
	private int pages;
	private int ISBN;

//----------Constructor---------//
	
	public Book(String title, String author, String plot, String genre, String publisher, String cover, int published, int pages, int ISBN) {
		this.title = title;
		this.author = author;
		this.plot = plot;
		this.genre = genre;
		this.publisher = publisher;
		this.cover = cover;
		this.published = published;
		this.pages = pages;
		this.ISBN=ISBN;
	}
	
//----------Getters----------//	

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the plot
	 */
	public String getPlot() {
		return plot;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @return cover URL
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @return year published
	 */
	public int getPublished() {
		return published;
	}

	/**
	 * @return number of pages
	 */
	public int getPages() {
		return pages;
	}
	/**
	 * @return ISBN
	 */
	public int getISBN() {
		return ISBN;
	}
	
//---------Setters----------//	

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @param plot the plot to set
	 */
	public void setPlot(String plot) {
		this.plot = plot;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @param cover the cover to set
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}

	/**
	 * @param published the year published to set
	 */
	public void setPublished(int published) {
		this.published = published;
	}

	/**
	 * @param pages the number pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}
	/**
	 * @param ISBN the ISBN to set
	 */
	public void setISBN(int isbn) {
		this.ISBN = isbn;
	}
//----------Comparators----------//
	
	/*
	 * Comparator to compare the titles of books
	 */
	public static final Comparator<Book> TitleComparator = new Comparator<Book>() {
		public int compare(Book b1, Book b2) {
			return b1.getTitle().compareTo(b2.getTitle());
		}
	};

	
	public int compareTo(Book o) {
		return 0;
	}
	
//----------To String----------//
	
}
