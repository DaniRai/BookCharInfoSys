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
	private Scanner input = new Scanner(System.in);
	private SortedLinkedList<Book> books=new SortedLinkedList<Book>();
	private SortedLinkedList<Character> characters=new SortedLinkedList<Character>();
	private HashedMap<Book, Character> map=new HashedMap<Book,Character>();
		
//----------Main Menu----------//
	public CLI() {
		Book book = new Book("HarryPotter", "Rowling", "Wizards", "Fantasy", "HP Publisher", "NoCover", 1999, 300);
		Character character = new Character("HarryPotter", 20, 'M', "No one");
		books.push(book);
		characters.push(character);
		map.push(book, character);	
	}
	private int mainMenu(){
			System.out.println("1. Admin Menu");
			System.out.println("2. End User Menu");
			int option = input.nextInt();
        return option;
	}

	public void console(){
		//books= new SortedLinkedList<Book>();
		//map= new HashedMap<Book, Character>();
		
		int option = mainMenu();
        while (option != 0)
        {
           
        	switch (option)
            {
               	case 1:    	adminConsole();
            	          	break;
               	case 2:		endUserConsole();
               				break;
               	default:   	System.out.println("Invalid option entered: " + option);
              				break;
            }
            
            //pauses the program so that the user can read what was printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine(); 
            
            //returns to the main menu
            option = mainMenu();
        }
	}
	
//----------Admin Menu----------//
	
	private int adminMenu() {
			
			System.out.println("1. Add new Book");
			System.out.println("2. Remove Book");
			System.out.println("3. Update book details");
			System.out.println("4. Add new character");
			System.out.println("5. Remove character");
			System.out.println("6. Update character details");
			System.out.println("7. Reset System");
			System.out.println("8. Save System Data");
			System.out.println("9. Return to Main Menu");
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
	          case 3:    updateBookConsole();
	          			break;
	          case 4:	 addCharacter();
	          			break;
	          case 5:	 removeCharacter();
	          			break;
	          case 6:    updateCharacterConsole();
	          			break;
	          case 7:    resetSystem();
	          			break;
	          case 8:    //saveSysData();
	          			break;
	          case 9:	 console();
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
//----------Book methods-----------------//
	/**
	 * Method to find a book by its title
	 */
	public Book getBookByTitle() {
		System.out.println("Title of book: ");
		input.nextLine();
		String title = input.nextLine();
		
		SortedLinkedList<Book>.Node node = books.find(new Book(title, "a", "a", "a", "a", "a", 1, 1), Book.TitleComparator); 
		
		if(node == null)
			return null;
		else
			return node.getData();
	}
	/**
	 * Method to add book to database
	 */
	public void addBook() {
		
		input.nextLine();
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
		
		input.nextLine();
		System.out.println("Protagonists name: ");
		String name = input.nextLine();
		System.out.println("Age: ");
		int age = input.nextInt();
		input.nextLine();
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
		Book book = getBookByTitle();
		if(book!=null) {
			map.erase(book);
			books.remove(new Book(book.getTitle(), "a", "a", "a", "a", "a", 1, 1), Book.TitleComparator);		
		}
		else {
			System.out.println("Not found");
		}
	}
	
	/**
	 * Clears all Lists to reset System
	 */
	public void resetSystem() {
		books.clear();
		characters.clear();
	}
	
	/**
	 * Method to save System Data to a File
	 */
	//public void saveSysData() {
		
	//}
	
//----------Update Book Menu----------//
	
	private int updateBookMenu() {
		System.out.println("1. Update Title");
		System.out.println("2. Update Author");
		System.out.println("3. Update Genre");
		System.out.println("4. Update Publisher");
		System.out.println("5. Update Publishded Year");
		System.out.println("6. Update Page Count");
		System.out.println("7. Update Plot");
		System.out.println("8. Update Cover Url");
		System.out.println("9. Return to Admin Menu");
		int option = input.nextInt();
		return option;
	}
	
	public void updateBookConsole(){
		int option = updateBookMenu();
		while (option != 0)
		{
       
			switch (option)
			{
			case 1:    updateBook(1);
					break;
			case 2:    updateBook(2);
          			break;
			case 3:    updateBook(3);
          			break;
			case 4:    updateBook(4);
          			break;
			case 5:    updateBook(5);
          			break;
			case 6:    updateBook(6);
  					break;
			case 7:    updateBook(7);
  					break;
			case 8:    updateBook(8);
  					break;
			case 9:	 adminConsole();
          			break;
			default:    System.out.println("Invalid option entered: " + option);
                      break;
			}
            
			//pauses the program so that the user can read what was printed to the terminal window
			System.out.println("\nPress any key to continue...");
			input.nextLine();
			input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
            
			//returns to the main menu
			option = updateBookMenu();
		}
	}
	
//----------Update Book Functions----------//
	
	/**
	 * Method to update book within database
	 */
	public void updateBook(int option) {
		Book toFind= getBookByTitle();
		if(toFind!=null) {
			SortedLinkedList<Book>.Node node = books.find(toFind, Book.TitleComparator);
			if(node==null)
				System.out.println("0");
			Book book = node.getData();
			book.toString();
			if(option == 1) {
				System.out.println("New Title for Book: ");
				String newTitle = input.nextLine();
				Character character = map.get(book).get(0);
				map.erase(book);
				book.setTitle(newTitle);
				map.push(book, character);			
			}
			else if (option == 2) {
				System.out.println("New Author for Book: ");
				String newAuthor = input.nextLine();
				Character character = map.get(book).get(0);
				map.erase(book);
				book.setAuthor(newAuthor);
				map.push(book, character);			
			}
			else if(option ==3) {
				System.out.println("Updated Genre for Book: ");
				String newGenre = input.nextLine();
				Character character = map.get(book).get(0);
				map.erase(book);
				book.setGenre(newGenre);
				map.push(book, character);			
			}
			else if(option == 4) {
				System.out.println("New Publisher for Book: ");
				String newPublisher = input.nextLine();
				Character character = map.get(book).get(0);
				map.erase(book);
				book.setPublisher(newPublisher);
				map.push(book, character);			
			}
			else if(option == 5) {
				System.out.println("Updated Year of Publishing for Book: ");
				int newYear = input.nextInt();
				Character character = map.get(book).get(0);
				map.erase(book);
				book.setPublished(newYear);
				map.push(book, character);			
			}
			else if(option == 6) {
				System.out.println("Updated Page Count for Book: ");
				int newPages = input.nextInt();
				Character character = map.get(book).get(0);
				map.erase(book);
				book.setPages(newPages);
				map.push(book, character);			
			}
			else if(option == 7) {
				System.out.println("Updated Plot for Book: ");
				String newPlot = input.nextLine();
				Character character = map.get(book).get(0);
				map.erase(book);
				book.setPlot(newPlot);
				map.push(book, character);			
			}
			else if(option == 8) {
				System.out.println("New Cover Url for Book: ");
				String newUrl = input.nextLine();
				Character character = map.get(book).get(0);
				map.erase(book);
				book.setCover(newUrl);
				map.push(book, character);			
			}
		}else {
			System.out.println("Not found");
		}
	}
//----------Character methods--------------//
	public Character getCharacterByName() {
		System.out.println("Name of character: ");
		input.nextLine();
		String name = input.nextLine();
		
		SortedLinkedList<Character>.Node node = characters.find(new Character(name, 1, 'a', "a"), Character.NameComparator); 
		
		if(node == null)
			return null;
		else
			return node.getData();
	}
	/**
	 * Method to add book to database
	 */
	public void addCharacter() {
		
		input.nextLine();
		System.out.println("Character name: ");
		String name = input.nextLine();
		System.out.println("Age: ");
		int age = input.nextInt();
		input.nextLine();
		System.out.println("Gender: ");
		String gender = input.nextLine();
		System.out.println("Character description: ");
		String desc = input.nextLine();
		Character character = new Character(name, age, gender.charAt(0), desc);
		characters.push(character);
	}
	
	/**
	 * Method to remove book from database
	 */
	public void removeCharacter() {
		Character c = getCharacterByName();
		if(c!=null) {
			//map.erase(c);
			characters.remove(new Character(c.getName(), 1, 'a', "a"), Character.NameComparator);		
		}
		else {
			System.out.println("Not found");
		}
	}
	
//----------Update Character Menu----------//
	
	private int updateCharacterMenu() {
		System.out.println("1. Update Name");
		System.out.println("2. Update Age");
		System.out.println("3. Update Gender");
		System.out.println("4. Update Description");
		System.out.println("5. Return to Admin Menu");
		int option = input.nextInt();
		return option;
	}
	
	public void updateCharacterConsole(){
		int option = updateCharacterMenu();
		while (option != 0)
		{
       
			switch (option)
			{
			case 1:    updateCharacter(1);
					break;
			case 2:    updateCharacter(2);
          			break;
			case 3:    updateCharacter(3);
          			break;
			case 4:    updateCharacter(4);
          			break;
			case 5:	   adminConsole();
          			break;
			default:    System.out.println("Invalid option entered: " + option);
                      break;
			}
            
			//pauses the program so that the user can read what was printed to the terminal window
			System.out.println("\nPress any key to continue...");
			input.nextLine();
			input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
            
			//returns to the main menu
			option = updateCharacterMenu();
		}
	}
	
//----------Update Character Functions----------//
	
	/**
	 * Method to update Character within database
	 */
	public void updateCharacter(int option) {
		Book book = getBookByTitle();
		if(book != null) {
			Character character = map.get(book).get(0);

			if(option == 1) {
				System.out.println("New Name for Character: ");
				String newName = input.nextLine();
				character.setName(newName);
				map.push(book, character);			
			}
			else if (option == 2) {
				System.out.println("Updated Age for Character: ");
				int newAge = input.nextInt();
				character.setAge(newAge);
				map.push(book, character);			
			}
			else if(option ==3) {
				System.out.println("Updated Gender for Character: ");
				String newGender = input.nextLine();
				character.setGender(newGender.charAt(0));
				map.push(book, character);			
			}
			else if(option == 4) {
				System.out.println("Updated Description for Character: ");
				String newDesc = input.nextLine();
				character.setDesc(newDesc);
				map.push(book, character);			
			}
		}
		else {
			System.out.println("Not found");
		}
	}
	
//----------End User Menu----------//	
	
	private int endUserMenu() {
		System.out.println("1. List all books");
		System.out.println("2. Search book by title");
		System.out.println("3. Search by Author");
		System.out.println("4. Search by Genre");
		System.out.println("5. Search by Year Published");
		System.out.println("6. Search by Publisher");
		System.out.println("7. Search character by name");
		System.out.println("8. Return to Main Menu");
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
			case 6:	   searchPublisher();
					break;
			case 7:	   viewCharacter();
					break;
			case 8:	 console();
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
		Book book = getBookByTitle();
		if(book != null) {
			System.out.println(book.toString());
			System.out.println(map.get(book).toString());
		}
	}
	
	/**
	 * Method to list all works by specified author
	 */
	public void searchAuthor() {
		System.out.println("Authors name: ");
		input.nextLine();
		String author = input.nextLine();
		
		books.listBy(new Book("a", author, "a", "a", "a", "a", 1, 1), Book.AuthorComparator);
	}
	
	/**
	 * Method to list all works within a specific genre
	 */
	public void searchGenre() {
		System.out.println("Genre: ");
		input.nextLine();
		String genre = input.nextLine();
		
		books.listBy(new Book("a", "a", "a", genre, "a", "a", 1, 1), Book.GenreComparator);	
	}
	
	/**
	 * Method to list all works published in a certain year
	 */
	public void searchPublished() {
		System.out.println("Year: ");
		input.nextLine();
		int published = input.nextInt();
		
		books.listBy(new Book("a", "a", "a", "a", "a", "a", published, 1), Book.PublishedComparator);
	}
	public void searchPublisher() {
		System.out.println("Publisher: ");
		input.nextLine();
		String publisher = input.nextLine();
		
		books.listBy(new Book("a", "a", "a", "a", publisher, "a", 1, 1), Book.PublisherComparator);
	}	
	
	public void viewCharacter() {
		Character c = getCharacterByName();
		if(c!= null) {
			System.out.println(c.toString());
			//System.out.println(map.get(c).toString());
		}
	}
}
