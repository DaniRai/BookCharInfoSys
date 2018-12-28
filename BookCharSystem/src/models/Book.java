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
public class Book implements Comparable<Book>, java.io.Serializable {
	private String title;
	private String author;
	private String plot;
	private String genre;
	private String publisher;
	private String cover;
	private int published;
	private int pages;

//----------Constructors---------//
	public Book(String title, String author, String plot, String genre, String publisher, String cover, int published, int pages) {
		this.title = title;
		this.author = author;
		this.plot = plot;
		this.genre = genre;
		this.publisher = publisher;
		this.cover = cover;
		this.published = published;
		this.pages = pages;
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
	
//----------Comparators----------//
	
	/*
	 * Comparator to compare the titles of books
	 */
	public static final Comparator<Book> TitleComparator = new Comparator<Book>() {
		public int compare(Book b1, Book b2) {
			return b1.getTitle().compareTo(b2.getTitle());
		}
	};
	
	public static final Comparator<Book> GenreComparator = new Comparator<Book>() {
		public int compare(Book b1, Book b2) {
			return b1.getGenre().compareTo(b2.getGenre());
		}
	};
	
	public static final Comparator<Book> AuthorComparator = new Comparator<Book>() {
		public int compare(Book b1, Book b2) {
			return b1.getAuthor().compareTo(b2.getAuthor());
		}
	};
	public static final Comparator<Book> PublisherComparator = new Comparator<Book>() {
		public int compare(Book b1, Book b2) {
			return b1.getPublisher().compareTo(b2.getPublisher());
		}
	};
	public static final Comparator<Book> PublishedComparator = new Comparator<Book>() {
		public int compare(Book b1, Book b2) {
			return Integer.compare(b1.getPublished(), b2.getPublished());
		}
	};

	
	public int compareTo(Book o) {
		return 0;
	}
	
//----------To String----------//
	
	@Override
	public String toString() {
		return title + "\n" + "Synopsis: " + plot + "\nAuthor: " + author + "\nGenre: " + genre + "# Pages: " + pages + "\nYear: " + published + "\nPublisher :" + publisher + "\nCover URL" + cover;
	}
	
//----------Overrides----------//
	
	/*
	 * Method to create hashCode from fields used to check equality
	 */
	@Override
	public int hashCode() {
		//Create hashcodes from each object field	
		int c1 = this.author.hashCode();
		int c2 = this.cover.hashCode();
		int c3 = this.genre.hashCode();
		int c4 = this.plot.hashCode();
		int c5 = this.publisher.hashCode();
		int c6 = this.title.hashCode();
		
		//Create object hash by addition partials and modulating by prime number
		int hash = 13 * c1 + c2;
		hash = 13 * hash + c3;
		hash = 13 *  hash + c4;
		hash = 13 * hash + c5;
		hash = 13 * hash + c6;
		return hash;		
	}
	/**
	 * Method to override equals
	 * Checks if Book is equal to given Object
	 * 
	 * @param Object
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Book) 
			return this.title.equals(((Book) o).title) && 
		           this.author.equals(((Book) o).author) && 
		           this.cover.equals(((Book) o).cover) &&
		           this.genre.equals(((Book) o).genre) &&
		           this.plot.equals(((Book) o).plot) &&
		           this.publisher.equals(((Book) o).publisher);
		           else
		        	   return false;		           
 	}
		
}
	

