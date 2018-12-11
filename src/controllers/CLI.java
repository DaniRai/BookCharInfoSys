//----------Packages & Imports----------//
package controllers;

import models.*;
import models.Character;

import java.util.InputMismatchException;
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
	private HashedMap<Book, Character> map;
	
	//----------Constructor---------//
	public CLI() {
		input = new Scanner(System.in);
		books = new SortedLinkedList<Book>();
		characters = new SortedLinkedList<Character>();
		map = new HashedMap<Book,Character>();
	}
//----------Main Menu----------//
	private int adminMenu() {
		System.out.println("1. Add new Book");
		System.out.println("2. Add new Character");
		System.out.println("3. Remove Book");
		System.out.println("4. Remove Character");
		System.out.println("5. Update Book Details");
		System.out.println("6. Update Character Details");
		System.out.println("7. View Database");
		System.out.println("8. Save System Data");
		System.out.println("9. Return to Main Menu");
		int option = getUserInt();
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
          case 2:    addCharacter();
          			 break;
          case 3:    removeBook();
          			 break;
          case 4:    removeCharacter();
          			 break;
          case 5:    updateBook();
          			 break;
          case 6:    updateCharacter();
          			 break;
          case 7:	 console();
          			 break;
          default:   System.out.println("Invalid option entered: " + option);
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
//----------Main Menu----------//

	private int mainMenu(){
			System.out.println("1. Admin Menu");
			System.out.println("2. End User Menu");
			int option = getUserInt();
      return option;
	}
public void console(){
	int option = mainMenu();
    while (option != 0)
    {
       
    	switch (option)
        {
           	case 1:    	adminConsole();
        	          	break;
           	case 2:		option =endUserMenu();
           				break;
           	default:   	System.out.println("Invalid option entered: " + option);
          				break;
        }
        
        //pauses the program so that the user can read what was printed to the terminal window
        System.out.println("\nPress any key to continue...");
        input.nextLine(); 
        
        //returns to the main menu
        option = mainMenu();
    }
}	
//----------Admin Menu Functions----------//
	
	public void addBook() {
		System.out.println("Enter book title: ");
		String title = getUserString();
		System.out.println("Enter author: ");
		String author = getUserString();
		System.out.println("Enter plot: ");
		String plot = getUserString();
		System.out.println("Enter genre: ");
		String genre = getUserString();
		System.out.println("Enter ");
		String published = getUserString();
		System.out.println("Enter publisher: ");
		String publisher = getUserString();
		System.out.println("Enter year: ");
		int year = getUserInt();
		System.out.println("Enter number of pages:  ");
		int pages = getUserInt();
		
		//books.push(new Book(title, author, plot, genre, published, publisher, year, pages));
		System.out.println("Book added");
	}
	public void addCharacter() {
		System.out.println();
		String name = getUserString();
		System.out.println();
		int age = getUserInt();
		System.out.println();
		String gender = getUserString();
		System.out.println();
		String desc = getUserString();
		
		characters.push(new Character(name, age, gender.charAt(0), desc));
		System.out.println("Character added");	
	}
	
	public void removeBook() {
		
	}
	
	public void removeCharacter() {
		
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
	//----------Other Functions----------//
	
		// Loop until input is valid (integer)
		public int getUserInt() {
			int integer=0;
			boolean isNumeric=false;
			int trials=0;
			while(!isNumeric){
				try {
					if(trials>0) System.out.println("Enter an integer: ");
					trials++;
					integer=input.nextInt();
					input.nextLine();
					isNumeric=true;
				}catch(InputMismatchException e) {
					input.nextLine();
				}
			}
			return integer;
		}
		
		// Loop until input is valid (string)
		public String getUserString() {
			int trials=0;
			String string="";
			while(!string.matches("[a-zA-Z]+")){
				try {
					if(trials>0) System.out.println("Enter a word: ");
					trials++;
					string=input.next();
					input.nextLine();
				}catch(InputMismatchException e) {
					input.nextLine();
				}
			}
			return string;
		}
		
	
}
