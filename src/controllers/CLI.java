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
	private SortedLinkedList<Character> characters;
	private SortedMap<Book, Character> map;
	
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
			System.out.println("5. View Database");
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
	          case 6:    saveSysData();
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
	
	public void addBook() {
		System.out.println();
		String title = input.nextLine();
		System.out.println();
		String author = input.nextLine();
		System.out.println();
		String plot = input.nextLine();
		System.out.println();
		String genre = input.nextLine();
		System.out.println();
		String published = input.nextLine();
		System.out.println();
		String publisher = input.nextLine();
		System.out.println();
		int year = input.nextInt();
		System.out.println();
		int pages = input.nextInt();
		
		System.out.println();
		String name = input.nextLine();
		System.out.println();
		int age = input.nextInt();
		System.out.println();
		String gender = input.nextLine();
		System.out.println();
		String desc = input.nextLine();
		
		
		
		map.add(books.push(new Book(title, author, plot, genre, published, publisher, year, pages)), characters.push(new Character(name, age, gender.charAt(0), desc)));
		
	}
	
	public void removeBook() {
		
	}
	
	public void updateBook() {
		
	}
	
	public void updateCharacter() {
		
	}
	
	/**
	 * Clears all Lists to reset System
	 */
	public void resetSystem() {
		books.clear();
		characters.clear();		
	}
	
	public void saveSysData() {
		
	}
//----------End User Menu----------//	
	
	private int endUserMenu() {
		System.out.println("1. List all Titles");
		System.out.println("2. Search by Title");
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
			case 2:    searchTitle();
          			break;
			case 3:    searchAuthor();
          			break;
			case 4:    searchPublished();
          			break;
			case 5:    searchPublisher();
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
	
	public void searchTitle() {
		
	}
	
	public void searchAuthor() {
		
	}
	
	public void searchPublished() {
		
	}
	
	public void searchPublisher() {
		
	}
		
}
