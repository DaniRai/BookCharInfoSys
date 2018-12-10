//----------Packages & Imports----------//
package controllers;

import models.*;
import models.Character;

import java.util.Scanner;

/**
 * @author Daniel Quinlan
 * @studentNumber 01133940
 * @course Entertainment Systems Yr 2
 * 
 * @param <T>
 */
public class CLI {
	private Scanner input;
	private SortedLinkedList<Book> books;
	private HashedMap<Book, Character> map;
	
//----------Main Menu----------//
	
	private int mainMenu(){
			System.out.println("1. Admin Menu");
			System.out.println("2. End User Menu");
			int option = input.nextInt();
        return option;
	}

	public void console(){
		int option = mainMenu();
        while (option != 0)
        {
           
        	switch (option)
            {
               	case 1:    	adminMenu();
            	          	break;
               	case 2:		endUserMenu();
               				break;
               	default:   	System.out.println("Invalid option entered: " + option);
              				break;
            }
            
            //pauses the program so that the user can read what was printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
            
            //returns to the main menu
            option = mainMenu();
        }
	}
	
//----------Admin Menu----------//
	
	private int adminMenu() {
			System.out.println("1. Add new Book & Character");
			System.out.println("2. Remove Book & Character");
			System.out.println("3. Update Book Details");
			System.out.println("4. Update Character Details");
			System.out.println("5. Reset System");
			System.out.println("6. Save System Data");
			System.out.println("7. Return to Main Menu");
			int option = input.nextInt();
			return option;
	}
		
	public void adminConsole(){
		int option = adminMenu();
        while (option != 0)
        {
           
        	switch (option)
            {
	          case 1:    addBook();
	          			break;
	          case 2:    removeBook();
	          			break;
	          case 3:    updateBook();
	          			break;
	          case 4:    updateCharacter();
	          			break;
	          case 5:    resetSystem();
	          			break;
	          case 6:    //saveSysData();
	          			break;
	          case 7:	 console();
	          			break;
              default:    System.out.println("Invalid option entered: " + option);
                          break;
            }
	            
            //pauses the program so that the user can read what was printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
	            
            //returns to the main menu
            option = adminMenu();
        }
	}
		
//----------Admin Menu Functions----------//
	
	/**
	 * Method to add book to database
	 */
	public void addBook() {
		System.out.println("Title of book to add: ");
		String title = input.nextLine();
		System.out.println("Author: ");
		String author = input.nextLine();
		System.out.println("Please give a synopsis of the title: ");
		String plot = input.nextLine();
		System.out.println("Genre of title: ");
		String genre = input.nextLine();
		System.out.println("Publisher: ");
		String publisher = input.nextLine();
		System.out.println("Cover URL: ");
		String cover = input.nextLine();
		System.out.println("Year published: ");
		int year = input.nextInt();
		System.out.println("Number of Pages: ");
		int pages = input.nextInt();
		
		System.out.println("Protagonists name: ");
		String name = input.nextLine();
		System.out.println("Age: ");
		int age = input.nextInt();
		System.out.println("Gender: ");
		String gender = input.nextLine();
		System.out.println("Character description: ");
		String desc = input.nextLine();
		
		Book book = new Book(title, author, plot, genre, publisher, cover, year, pages);
		Character character = new Character(name, age, gender.charAt(0), desc);
		
		books.push(book);
				
		map.push(book, character);	
		
		books.delDupe();
	}
	
	/**
	 * Method to remove book from database
	 */
	public void removeBook() {
		System.out.println("Title of book to remove: ");
		String title = input.nextLine();
		
		Book toDelete = books.find(new Book(title, "a", "a", "a", "a", "a", 1, 1), Book.TitleComparator).getData();
		
		map.erase(toDelete);
		
		books.remove(new Book(title, "a", "a", "a", "a", "a", 1, 1), Book.TitleComparator);		
	}
	
	/**
	 * Method to update book within database
	 */
	public void updateBook() {
		System.out.println("Title of book to update: ");;
		String title = input.nextLine();
		
		Book book = books.find(new Book(title, "a", "a", "a", "a", "a", 1, 1), Book.TitleComparator).getData();
		book.toString();
				
	}
	
	/**
	 * Method to update character within database
	 */
	public void updateCharacter() {
		System.out.println("Title of book which contains character: ");;
		String title = input.nextLine();
		
		Book book = books.find(new Book(title, "a", "a", "a", "a", "a", 1, 1), Book.TitleComparator).getData();
		map.get(book).toString();
		
		
	}
	
	/**
	 * Clears all Lists to reset System
	 */
	public void resetSystem() {
		books.clear();
			}
	
	/**
	 * Method to save System Data to a File
	 */
	//public void saveSysData() {
		
	//}
//----------End User Menu----------//	
	
	private int endUserMenu() {
		System.out.println("1. List all Titles");
		System.out.println("2. View Title");
		System.out.println("3. Search by Author");
		System.out.println("4. Search by Year Published");
		System.out.println("5. Search by Publisher");
		System.out.println("6. Return to Main Menu");
		int option = input.nextInt();
		return option;
	}
	
	public void endUserConsole(){
		int option = endUserMenu();
		while (option != 0)
		{
       
			switch (option)
			{
			case 1:    books.printDescend();
					break;
			case 2:    viewTitle();
          			break;
			case 3:    searchAuthor();
          			break;
			case 4:    searchGenre();
          			break;
			case 5:    searchPublished();
          			break;
			case 6:	 console();
          			break;
			default:    System.out.println("Invalid option entered: " + option);
                      break;
			}
            
			//pauses the program so that the user can read what was printed to the terminal window
			System.out.println("\nPress any key to continue...");
			input.nextLine();
			input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
            
			//returns to the main menu
			option = endUserMenu();
		}
	}
	
//----------End User Functions----------//
	
	/**
	 * Method to Search for book by Title and print book details
	 */
	public void viewTitle() {
		System.out.println("Title of book to view: ");
		String title = input.nextLine();
		
		Book book = books.find(new Book(title, "a", "a", "a", "a", "a", 1, 1), Book.TitleComparator).getData();
		
		book.toString();
		map.get(book).toString();
	}
	
	/**
	 * Method to list all works by specified author
	 */
	public void searchAuthor() {
		System.out.println("Authors name: ");
		String author = input.nextLine();
		
		books.listBy(new Book("a", author, "a", "a", "a", "a", 1, 1), Book.AuthorComparator);
		
		viewTitle();
	}
	
	/**
	 * Method to list all works within a specific genre
	 */
	public void searchGenre() {
		System.out.println("Genre: ");
		String genre = input.nextLine();
		
		books.listBy(new Book("a", "a", "a", genre, "a", "a", 1, 1), Book.GenreComparator);	
		
		viewTitle();
	}
	
	/**
	 * Method to list all works published in a certain year
	 */
	public void searchPublished() {
		System.out.println("Year: ");
		int published = input.nextInt();
		
		books.listBy(new Book("a", "a", "a", "a", "a", "a", published, 1), Book.PublishedComparator);
		
		viewTitle();
	}
		
}
