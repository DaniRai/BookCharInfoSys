package application;
import controllers.*;
import controllers.SortedLinkedList.Node;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.Serializable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import models.Book;
import models.Character;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GUI extends Application {
	private SortedLinkedList<Book> books;
	private SortedLinkedList<Character> characters;
	private HashedMap<Book, Character> bookCharMap;
	private HashedMap<Character, Book> charBookMap;
	private Stage primaryStage;
	private Scene scene;
	private Book selectedBook;
	private Character selectedCharacter;
	private BorderPane root;
	private BorderPane root1;
	private BorderPane root2;
	private BorderPane root3;
	private BorderPane root4;
	private BorderPane root5;
	private BorderPane root6;
	private BorderPane root7;
	private BorderPane root8;
	
	@Override
	public void start(Stage stage) {
		try {
			setup(); //initialize linked lists and map 
			
			root = new BorderPane();
			root.setId("root");
			scene= new Scene(root,1900,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage=stage;
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//Example books and characters
			Book book = new Book("Harry Potter", "Rowling", "Wizards", "Fantasy", "HP Publisher", "NoCover", 1999, 300);
			books.push(book);
			Character character = new Character("Ron", 20, 'M', "Poor wizard");
			bookCharMap.push(book, character);	
			characters.push(character);
			charBookMap.push(character, book);	
			Character character4 = new Character("Harry", 20, 'M', "Skilled wizard");
			bookCharMap.push(book, character4);	
			characters.push(character4);
			charBookMap.push(character4, book);	
			Book book3 = new Book("A Game of Thrones", "Martin", "Complex conflicts", "Fantasy", "HP Publisher", "NoCover", 1990, 300);
			Character character3 = new Character("Jon", 20, 'M', "Night's watch");
			books.push(book3);
			bookCharMap.push(book3, character3);
			characters.push(character3);
			charBookMap.push(character3, book3);	
			Book book1 = new Book("Foundation", "Asimov", "Story of the future", "SF", "SF Publisher", "NoCover", 1950, 300);
			Character character1 = new Character("Hari Seldon", 70, 'M', "Scientist");
			books.push(book1);
			bookCharMap.push(book1, character1);	
			characters.push(character1);
			charBookMap.push(character1, book1);
			Book book5 = new Book("A Storm of swords", "Martin", "other conflicts", "Fantasy", "HP Publisher", "NoCover", 1990, 300);
			Character character5 = new Character("Daenerys", 20, 'F', "");
			books.push(book5);
			bookCharMap.push(book5, character5);
			characters.push(character5);
			charBookMap.push(character5, book5);	
			//*********
			
			//Entry point
			generalMenuScreen();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 	Two parts :
		1- Screens
		2- Utility Methods 
	 */	

	//*****Views*****
	public void generalMenuScreen(){
		Button listAllBooks = listAllBooksScreen();
		Button listAllCharacters = listAllCharactersScreen();
		Button search = new Button("Search");
		Button admin = new Button("Administration");
		Button quit = new Button("Quit");

		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(listAllBooks,listAllCharacters,search,admin,quit);
		vbox.setAlignment(Pos.CENTER);
		root.setCenter(vbox);

		//To other screens
		search.setOnAction(e->{
			searchMenuScreen();
		});
		admin.setOnAction(e->{
			adminMenuScreen();
		});
		quit.setOnAction(e->{
			primaryStage.close();
		});
	}
	public Button listAllBooksScreen() { //returns a button triggering new screen
		BorderPane pane = new BorderPane();
		Button button = new Button("List all books");
		button.setOnAction(e->{
			Button back=new Button("Back");
			back.setOnAction(g->{
				listAllBooksScreen();
				scene.setRoot(root);
			});
			sortByTitle(pane, back, books.getAll()); //list all books, sorted by title
			scene.setRoot(pane);
		});

		return button; 
	}
	public Button listAllCharactersScreen() {
		BorderPane pane = new BorderPane();
		Button button = new Button("List all characters");
		button.setOnAction(e->{
			Button back=new Button("Back");
			back.setOnAction(g->{
				generalMenuScreen();
				scene.setRoot(root);
			});
			sortByName(pane, back, characters.getAll()); 
			scene.setRoot(pane);
		});

		return button; 
	}
	public void adminMenuScreen() {
		root1 = new BorderPane();
		root1.setId("root");

		Button addBookB = new Button("Add a book & character");
		Button resetB = new Button("Reset System");
		Button saveB = new Button("Save System Data");
		Button loadB = new Button("Load previous session");
		Button mainMenuB = new Button("Back");

		addBookB.setOnAction(e->{
			addBookCharScreen();
			scene.setRoot(root2);
		});
		resetB.setOnAction(e->{
			reset();
			scene.setRoot(root);
		});
		saveB.setOnAction(e->{
			save();
			scene.setRoot(root);
		});
		loadB.setOnAction(e->{
			load();
			scene.setRoot(root);
		});
		mainMenuB.setOnAction(e->{
			scene.setRoot(root);
		});
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(addBookB,saveB,loadB,resetB,mainMenuB);
		vbox.setAlignment(Pos.CENTER);
		root1.setCenter(vbox);

		scene.setRoot(root1);
	}
	public void addBookCharScreen() {
		root2 = new BorderPane();
		root2.setId("root");

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);

		root2.setCenter(gp);
		Button mainMenu=new Button("Back");
		Label setTitleL = new Label("Title");
		Label setAuthorL = new Label("Author");
		Label setPublisherL = new Label("Publisher");
		Label setYearL = new Label("Year of publication");
		Label setGenreL = new Label("Genre");
		Label setPlotL = new Label("Plot");
		Label setPagesL = new Label("Number of pages");
		Label setCoverL = new Label("Cover URL");
		Button addBookBtn = new Button("Add");
		gp.addColumn(0, setTitleL, setAuthorL, setPublisherL, setYearL,
				setGenreL, setPlotL, setPagesL,setCoverL);

		TextField setTitleTF = new TextField();
		TextField setAuthorTF = new TextField();
		TextField setPublisherTF = new TextField();
		TextField setYearTF = new TextField();
		TextField setGenreTF = new TextField();
		TextField setPagesTF = new TextField();
		TextField setPlotTF = new TextField();
		TextField setCoverTF = new TextField();
		gp.addColumn(1, setTitleTF, setAuthorTF, setPublisherTF, setYearTF, 
				setGenreTF,setPlotTF, setPagesTF,setCoverTF);
		Label charLabel = new Label("Character (optional)");
		Label setNameL = new Label("Name");
		Label setAgeL = new Label("Age");
		Label setGenderL = new Label("Gender");
		Label setDescriptionL = new Label("Description");
		gp.addColumn(0, setNameL, setAgeL, setGenderL, setDescriptionL);

		TextField setNameTF = new TextField();
		TextField setAgeTF = new TextField();
		TextField setGenderTF = new TextField();
		TextField setDescriptionTF = new TextField();
		Label success = new Label();
		Button clear= new Button("Clear");
		gp.addColumn(1,charLabel, setNameTF, setAgeTF, setGenderTF, setDescriptionTF,success,addBookBtn,clear,mainMenu);
		addBookBtn.setOnAction(e->{
			Book book = new Book(setTitleTF.getText(),setAuthorTF.getText(),setPlotTF.getText(),
					setPublisherTF.getText(), setGenreTF.getText(),setCoverTF.getText(),
					Integer.parseInt(setYearTF.getText()),Integer.parseInt(setPagesTF.getText()));
			books.push(book);
			//if(!setNameTF.getText().trim().isEmpty() || !setAgeTF.getText().trim().isEmpty() 
			//		|| !setGenderTF.getText().trim().isEmpty() || !setDescriptionTF.getText().trim().isEmpty()) {
			Character character =new Character(setNameTF.getText(),Integer.parseInt(setAgeTF.getText()),
					setGenderTF.getText().charAt(0),setDescriptionTF.getText()); 
			characters.push(character);
			characters.delDupe();
			bookCharMap.push(book, character);	
			charBookMap.push(character, book);	

			books.delDupe();
			
			success.setText("Added "+setTitleTF.getText());
			
			clear.setOnAction(f->{
				setTitleTF.clear();
				setAuthorTF.clear();
				setPublisherTF.clear();
				setYearTF.clear();
				setGenreTF.clear();
				setPagesTF.clear();
				setPlotTF.clear();
				setCoverTF.clear();
				setNameTF.clear();
				setAgeTF.clear();
				setGenderTF.clear();
				setDescriptionTF.clear();
			});
		});

		mainMenu.setOnAction(e->{
			scene.setRoot(root1);
		});
		scene.setRoot(root2);
	}
	public Button addCharScreen() {
		Button button = new Button();
		root8 = new BorderPane();
		root8.setId("root");

		button.setOnAction(g->{
			GridPane gp = new GridPane();
			gp.setAlignment(Pos.CENTER);

			root8.setCenter(gp);
			Button mainMenu=new Button("Back");

			Label setNameL = new Label("Name");
			Label setAgeL = new Label("Age");
			Label setGenderL = new Label("Gender");
			Label setDescriptionL = new Label("Description");
			gp.addColumn(0, setNameL, setAgeL, setGenderL, setDescriptionL);

			TextField setNameTF = new TextField();
			TextField setAgeTF = new TextField();
			TextField setGenderTF = new TextField();
			TextField setDescriptionTF = new TextField();
			Label success = new Label();
			Button clear= new Button("Clear");
			Button addCharBtn = new Button("Add");

			gp.addColumn(1, setNameTF, setAgeTF, setGenderTF, setDescriptionTF,success,addCharBtn,clear,mainMenu);
			addCharBtn.setOnAction(e->{
				Character character =new Character(setNameTF.getText(),Integer.parseInt(setAgeTF.getText()),
						setGenderTF.getText().charAt(0),setDescriptionTF.getText()); 
				characters.push(character);
				bookCharMap.push(selectedBook, character);	
				charBookMap.push(character, selectedBook);	
				System.out.println(selectedBook);
				characters.delDupe();

				success.setText("Added "+setNameTF.getText());

				clear.setOnAction(f->{
					setNameTF.clear();
					setAgeTF.clear();
					setGenderTF.clear();
					setDescriptionTF.clear();
				});
			});

			mainMenu.setOnAction(e->{
				adminMenuScreen();
				scene.setRoot(root1);
			});
			scene.setRoot(root8);
		});
		return button;
	}
	
	public void editBookScreen(Book book) {
		root3 = new BorderPane();
		root3.setId("root");

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);

		root3.setCenter(gp);
		Button back=new Button("Back");
		Label setTitleL = new Label("Title");
		Label setAuthorL = new Label("Author");
		Label setPublisherL = new Label("Publisher");
		Label setYearL = new Label("Year of publication");
		Label setGenreL = new Label("Genre");
		Label setPlotL = new Label("Plot");
		Label setPagesL = new Label("Number of pages");
		Label setCoverL = new Label("Cover URL");
		Button editBookBtn = new Button("Edit");
		gp.addColumn(0, setTitleL, setAuthorL, setPublisherL, setYearL,
				setGenreL, setPlotL, setPagesL,setCoverL);

		TextField setTitleTF = new TextField();
		TextField setAuthorTF = new TextField();
		TextField setPublisherTF = new TextField();
		TextField setYearTF = new TextField();
		TextField setGenreTF = new TextField();
		TextField setPagesTF = new TextField();
		TextField setPlotTF = new TextField();
		TextField setCoverTF = new TextField();
		gp.addColumn(1, setTitleTF, setAuthorTF, setPublisherTF, setYearTF, 
				setGenreTF,setPlotTF, setPagesTF,setCoverTF);

		Button clear= new Button("Clear");
		gp.addColumn(1,editBookBtn,clear,back);
		editBookBtn.setOnAction(e->{
			clear.setOnAction(f->{
				setAuthorTF.clear();
				setPublisherTF.clear();
				setYearTF.clear();
				setGenreTF.clear();
				setPagesTF.clear();
				setPlotTF.clear();
				setCoverTF.clear();
			});
			SortedLinkedList<Book>.Node current = books.head;
			//while list is not finished compare input data to Node's data 
			while (current != null)
			{
				Book b = (Book)current.getData();
				//If matching node is found edit data
				if(Book.TitleComparator.compare(b, book) == 0){
					if(!setTitleTF.getText().trim().isEmpty()) {
						b.setTitle(setTitleTF.getText());
					}
					if(!setAuthorTF.getText().trim().isEmpty()) {
						b.setAuthor(setAuthorTF.getText());
					}
					if(!setYearTF.getText().trim().isEmpty()) {
						//b.setPublished(Integer.parseInt(setYearTF.getText()));
					}
					if(!setPublisherTF.getText().trim().isEmpty()) {
						b.setPublisher(setPublisherTF.getText());
					}
					if(!setGenreTF.getText().trim().isEmpty()) {
						b.setGenre(setGenreTF.getText());
					}
					if(!setPlotTF.getText().trim().isEmpty()) {
						b.setPlot(setPlotTF.getText());
					}
					if(!setPagesTF.getText().trim().isEmpty()) {
						b.setPages(Integer.parseInt(setPagesTF.getText()));
					}
					if(!setCoverTF.getText().trim().isEmpty()) {
						b.setCover(setCoverTF.getText());
					}
				}
				current = current.getNext();
			}
		});

		back.setOnAction(e->{
			generalMenuScreen();
			scene.setRoot(root);
		});
		scene.setRoot(root3);
	}
	public void editCharacterScreen(Character character) {
		root7 = new BorderPane();
		root7.setId("root");

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);

		root7.setCenter(gp);
		Button back=new Button("Back");
		Label setNameL = new Label("Name");
		Label setAgeL = new Label("Age");
		Label setGenderL = new Label("Gender");
		Label setDescriptionL = new Label("Description");
		gp.addColumn(0, setNameL, setAgeL, setGenderL, setDescriptionL);

		TextField setNameTF = new TextField();
		TextField setAgeTF = new TextField();
		TextField setGenderTF = new TextField();
		TextField setDescriptionTF = new TextField();
		Label success = new Label();
		Button clear= new Button("Clear");
		Button editCharacterBtn= new Button("Edit");
		gp.addColumn(1, setNameTF, setAgeTF, setGenderTF, setDescriptionTF,success,editCharacterBtn,clear,back);
		

		editCharacterBtn.setOnAction(e->{
			clear.setOnAction(f->{
				setNameTF.clear();
				setAgeTF.clear();
				setGenderTF.clear();
				setDescriptionTF.clear();
			});
			SortedLinkedList<Character>.Node current = characters.head;
			//while list is not finished compare input data to Node's data 
			while (current != null)
			{
				Character c = (Character)current.getData();
				//If matching node is found edit data
				if(Character.NameComparator.compare(c, character) == 0){
					if(!setNameTF.getText().trim().isEmpty()) {
						c.setName(setNameTF.getText());
					}
					if(!setAgeTF.getText().trim().isEmpty()) {
						c.setAge(Integer.parseInt(setAgeTF.getText()));
					}
					if(!setGenderTF.getText().trim().isEmpty()) {
						c.setGender((setGenderTF.getText()).charAt(0));
					}
					if(!setDescriptionTF.getText().trim().isEmpty()) {
						c.setDesc(setDescriptionTF.getText());
					}
				}
				current = current.getNext();
			}
		});

		back.setOnAction(e->{
			generalMenuScreen();
			scene.setRoot(root);
		});
		scene.setRoot(root7);
	}
	public void searchMenuScreen() {
		//##########
		//Search menu
		root4 = new BorderPane();
		root4.setId("root");

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);

		Button mainMenu4=new Button("Back");
		Button searchBook=new Button("Search a book");
		Button searchCharacter=new Button("Search a character");

		vbox.getChildren().addAll(searchBook, searchCharacter, mainMenu4);
		root4.setCenter(vbox);

		searchBook.setOnAction(e->{
			searchBookScreen();
			scene.setRoot(root5);
		});
		searchCharacter.setOnAction(e->{
			searchCharacterScreen();
			scene.setRoot(root6);
		});
		mainMenu4.setOnAction(e->{
			scene.setRoot(root);
		});
		scene.setRoot(root4);
	}
	public void searchBookScreen() {
		//Search book
		root5=new  BorderPane();
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		root5.setId("root");
		root5.setCenter(gp);

		Button mainMenu=new Button("Back");
		Button searchB=new Button("Search");
		Label searchTitleL = new Label("Title");
		Label searchAuthorL = new Label("Author");
		Label searchYearL = new Label("Year");
		Label searchPublisherL = new Label("Publisher");
		Label searchGenreL = new Label("Genre");
		gp.addColumn(0, searchTitleL, searchAuthorL,searchYearL,searchPublisherL,searchGenreL);

		TextField searchTitleTF = new TextField();
		TextField searchAuthorTF = new TextField();
		TextField searchYearTF = new TextField();
		TextField searchPublisherTF = new TextField();
		TextField searchGenreTF = new TextField();
		gp.addColumn(1, searchTitleTF, searchAuthorTF,searchYearTF,searchPublisherTF,searchGenreTF,searchB,mainMenu);
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.setAlignment(Pos.CENTER);

		mainMenu.setOnAction(e->{
			scene.setRoot(root);
		});
		searchB.setOnAction(e->{
			vbox.getChildren().clear();

			if(!searchTitleTF.getText().trim().isEmpty()) {
				getBy(new Book(searchTitleTF.getText() , "a", "a", "a", "a", "a", 1, 1), Book.TitleComparator, root5, vbox); 
			}
			if(!searchAuthorTF.getText().trim().isEmpty()) {
				getBy(new Book("a" ,searchAuthorTF.getText(), "a", "a", "a", "a", 1, 1), Book.AuthorComparator, root5, vbox); 
			}
			if(!searchGenreTF.getText().trim().isEmpty()) {
				getBy(new Book("a" ,"a", searchGenreTF.getText(), "a", "a", "a", 1, 1), Book.GenreComparator, root5, vbox); 
			}
			if(!searchYearTF.getText().trim().isEmpty()) {
				getBy(new Book("a" ,"a", "a","a", "a", "a",Integer.parseInt(searchYearTF.getText()), 1), Book.PublishedComparator, root5, vbox); 
			}
			if(!searchPublisherTF.getText().trim().isEmpty()) {
				getBy(new Book("a" ,"a", "a", "a", searchPublisherTF.getAccessibleText(), "a", 1, 1), Book.PublisherComparator, root5, vbox); 
			}
			scene.setRoot(root5);
			Button back = new Button("Back");
			back.setOnAction(f->{
				searchBookScreen();
				scene.setRoot(root5);
			});
			vbox.getChildren().add(back);
		});
	}
	public void searchCharacterScreen() {
		root6=new  BorderPane();
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		root6.setId("root");
		root6.setCenter(gp);
		
		Button searchB=new Button("Search");
		Button back = new Button("Back");
		back.setOnAction(f->{
			searchBookScreen();
			scene.setRoot(root4);
		});
		Label searchNameL = new Label("Name");
		Label searchAgeL = new Label("Age");
		gp.addColumn(0, searchNameL, searchAgeL);

		TextField searchNameTF = new TextField();
		TextField searchAgeTF = new TextField();
		gp.addColumn(1, searchNameTF, searchAgeTF, searchB, back);

		searchB.setOnAction(e->{
			if(!searchNameTF.getText().trim().isEmpty()) {
				getCharBy(new Character(searchNameTF.getText(), 1, 'a', "a"),root6);
			}
			if(!searchAgeTF.getText().trim().isEmpty()) {
				//getBy(new Book("a" ,searchAgeTF.getText(), "a", "a", "a", "a", 1, 1), Character.AgeComparator, root5, vbox5); 
			}
		});
	}

	//****Utility Methods*******

	public TableView<Book> bookTable(ObservableList<Book> tableData, Button back) {
		TableView<Book> table = new TableView<Book>();
		table.setEditable(true);
		table.setId("tableview");
		// set detection of click on a book row to display its character, edit or remove
		table.setRowFactory(tv -> {
			TableRow<Book> row = new TableRow<>();
			row.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(event.getClickCount() == 2 && (!row.isEmpty())) {
						Book book = row.getItem();
						final ObservableList<Character> tableData = FXCollections.observableArrayList();
						tableData.addAll(bookCharMap.get(book));
						back.setText("Back");
						
						Label bookTitle = new Label();
						bookTitle.setText(book.getTitle()+" by "+book.getAuthor());
						bookTitle.setAlignment(Pos.CENTER);
						
						BorderPane pane = new BorderPane();
						VBox vbox = new VBox();
						vbox.setSpacing(5);
						vbox.setPadding(new Insets(10, 0, 0, 10));
						vbox.getChildren().addAll(charTable(tableData, back),bookTitle, back);
						vbox.setAlignment(Pos.CENTER);
						pane.setId("root");
						pane.setCenter(vbox);
						
						scene.setRoot(pane);
					}
					if(event.getClickCount() == 1 && (!row.isEmpty())) { //edit or remove
						selectedBook = (Book) row.getItem();
					}
				}
			});
			return row;
		});

		TableColumn TitleCol = new TableColumn("Title");
		TitleCol.setSortable(false);
		TableColumn AuthorCol = new TableColumn("Author");
		AuthorCol.setSortable(false);
		TableColumn PublisherCol = new TableColumn("Publisher");
		PublisherCol.setSortable(false);
		TableColumn GenreCol = new TableColumn("Genre");
		GenreCol.setSortable(false);
		TableColumn YearCol = new TableColumn("Year");
		YearCol.setSortable(false);
		TableColumn PlotCol = new TableColumn("Plot");
		PlotCol.setSortable(false);
		TableColumn CoverURLCol = new TableColumn("Cover URL");
		CoverURLCol.setSortable(false);
		TableColumn PagesCol = new TableColumn("Pages");
		PagesCol.setSortable(false);

		TitleCol.setCellValueFactory(
				new PropertyValueFactory<Book,String>("Title")
				);
		AuthorCol.setCellValueFactory(
				new PropertyValueFactory<Book,String>("Author")
				);
		PublisherCol.setCellValueFactory(
				new PropertyValueFactory<Book,String>("Publisher")
				);
		GenreCol.setCellValueFactory(
				new PropertyValueFactory<Book,String>("Genre")
				);
		YearCol.setCellValueFactory(
				new PropertyValueFactory<Book,String>("Published")
				);
		PlotCol.setCellValueFactory(
				new PropertyValueFactory<Book,String>("Plot")
				);
		CoverURLCol.setCellValueFactory(
				new PropertyValueFactory<Book,String>("Cover")
				);
		PagesCol.setCellValueFactory(
				new PropertyValueFactory<Book,String>("Pages") 
				);

		table.setItems(tableData);
		table.getColumns().addAll(TitleCol, AuthorCol, PublisherCol,
				GenreCol, YearCol, PlotCol,
				CoverURLCol, PagesCol);

		return table;
	}
	public TableView<Character> charTable(ObservableList<Character> tableData, Button back){
		TableView<Character> table = new TableView<Character>();
		table.setEditable(true);

		TableColumn NameCol = new TableColumn("Name");
		TableColumn GenderCol = new TableColumn("Gender");
		TableColumn AgeCol = new TableColumn("Age");
		TableColumn DescCol = new TableColumn("Description");

		NameCol.setCellValueFactory(
				new PropertyValueFactory<Character,String>("Name")
				);
		GenderCol.setCellValueFactory(
				new PropertyValueFactory<Character,String>("Gender")
				);
		AgeCol.setCellValueFactory(
				new PropertyValueFactory<Character,Integer>("Age")
				);
		DescCol.setCellValueFactory(
				new PropertyValueFactory<Character,String>("Desc")
				);

		// set detection of click on a character row to display its book
				table.setRowFactory(tv -> {
					TableRow<Character> row = new TableRow<>();
					row.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							if(event.getClickCount() == 2 && (!row.isEmpty())) {
								Character character = row.getItem();
								final ObservableList<Book> tableData = FXCollections.observableArrayList();
								tableData.addAll(charBookMap.get(character));
								back.setText("Back");
								System.out.println("Boo");
								Label characterName = new Label();
								characterName.setText("Books featuring "+character.getName());
								characterName.setAlignment(Pos.CENTER);
								
								BorderPane pane = new BorderPane();
								VBox vbox = new VBox();
								vbox.setSpacing(5);
								vbox.setPadding(new Insets(10, 0, 0, 10));
								vbox.getChildren().addAll(bookTable(tableData, back),characterName,back);
								vbox.setAlignment(Pos.CENTER);
								pane.setId("root");
								pane.setCenter(vbox);
								
								scene.setRoot(pane);
							}
							if(event.getClickCount() == 1 && (!row.isEmpty())) { //edit or remove
								selectedCharacter = (Character) row.getItem();
							}
						}
					});
					return row;
				});

		table.setItems(tableData);
		table.getColumns().addAll(NameCol, GenderCol, AgeCol,DescCol);

		return table;
	}
	public void listBooks(BorderPane pane, Button back, ArrayList<Book> titles) {
		pane.setId("root");
		final ObservableList<Book> tableData = FXCollections.observableArrayList();
		tableData.addAll(titles); //store the sorted books in the array
		Button addChar = addCharScreen();
		addChar.setText("Add character");
		Button sortTitle= new Button("Sort by title");
		Button sortGenre= new Button("Sort by genre");
		Button sortYear= new Button("Sort by year");
		
		sortTitle.setOnAction(g->{
			sortByTitle(pane, back, titles);
			scene.setRoot(pane);
		});
		sortYear.setOnAction(g->{
			sortByYear(pane, back, titles);
			scene.setRoot(pane);
		});
		sortGenre.setOnAction(g->{
			sortByGenre(pane, back, titles);
			scene.setRoot(pane);
		});
		
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		//give array to table, add table to the vbox then add vbox to pane
		Button edit= editBookButton();
		Button remove= removeBookButton();
		vbox.getChildren().addAll(bookTable(tableData, back), addChar,sortTitle, sortYear, sortGenre, edit,remove, back);  
		vbox.setAlignment(Pos.CENTER);
		pane.setCenter(vbox); //add box to panel
		scene.setRoot(pane);
	}

	public void listCharacters(BorderPane pane, Button back, ArrayList<Character> characters) {
		pane.setId("root");
		final ObservableList<Character> tableData = FXCollections.observableArrayList();
		tableData.addAll(characters); //store the sorted books in the array
		
		Button sortName= new Button("Sort by name");
		Button sortAge= new Button("Sort by age");
		
		sortName.setOnAction(g->{
			sortByName(pane, back,characters);
			scene.setRoot(pane);
		});
		sortAge.setOnAction(g->{
			sortByAge(pane, back, characters);
			scene.setRoot(pane);
		});
		
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		//give array to table then add table to the vbox then add vbox to pane
		Button edit= editCharacterButton();
		Button remove= removeCharacterButton();
		vbox.getChildren().addAll(charTable(tableData, back), sortName, sortAge,edit, remove, back);  
		vbox.setAlignment(Pos.CENTER);
		pane.setCenter(vbox); //add box to panel
		scene.setRoot(pane);
	}
	public void getBy(Book book,Comparator<Book> c, BorderPane pane, VBox vbox) {
		//create table
		final ObservableList<Book> tableData = FXCollections.observableArrayList();
		pane.setCenter(vbox);
		
		SortedLinkedList<Book>.Node current = books.head;
		
		//while list is not finished compare input data to Node's data 
		while (current != null)
		{
			//If matching node is found add to table data
			if(c.compare((Book)current.getData(), book) == 0) {
				tableData.add((Book)current.getData());
			}

			current = current.getNext();
		}
		Button editB = new Button("Edit selected");
		
		vbox.getChildren().addAll(bookTable(tableData,null), editB);
	}
	public void getCharBy(Character character, BorderPane pane) {
		//create table
		final ObservableList<Character> tableData = FXCollections.observableArrayList();

		//Search by name
		SortedLinkedList<Character>.Node node = characters.find(new Character(character.getName(), 1, 'a', "a"), Character.NameComparator); 
		if(node!=null) {
			tableData.add((Character)node.getData());
		}
		
		Button back=new Button("Back");
		
		back.setOnAction(e->{
			scene.setRoot(root4);
		});

		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(charTable(tableData, back), back);
		vbox.setAlignment(Pos.CENTER);
		pane.setCenter(vbox);
		
		scene.setRoot(pane);
	}

	//****Quicksorts*****
	//Sort and List
	public void sortByTitle(BorderPane pane,Button back, ArrayList<Book> bks) {
		if(!bks.isEmpty())
			quickSortTitles(bks,0,bks.size()-1);
		listBooks(pane,back,bks);
	}
	public void sortByYear(BorderPane pane,Button back, ArrayList<Book> bks) {
		quickSortYears(bks,0,books.size()-1);
		listBooks(pane,back,bks);
	}
	public void sortByGenre(BorderPane pane, Button back, ArrayList<Book> bks) {
		quickSortGenres(bks,0,books.size()-1);
		listBooks(pane,back,bks);
	}
	public void sortByName(BorderPane pane,Button back, ArrayList<Character> crs) {
		if(!crs.isEmpty())
			quickSortNames(crs,0,crs.size()-1);
		listCharacters(pane,back,crs);
	}
	public void sortByAge(BorderPane pane,Button back, ArrayList<Character> crs) {
		if(!crs.isEmpty())
			quickSortAge(crs,0,crs.size()-1);
		listCharacters(pane,back,crs);
	}
	//store books in array then sort array 
	public static void quickSortTitles(ArrayList<Book> a, int lowerIndex, int higherIndex) {
		int leftIndex = lowerIndex;
		int rightIndex = higherIndex;

		String pivot = a.get(lowerIndex+(higherIndex-lowerIndex)/2).getTitle();
		while(leftIndex<=rightIndex) {
			while(a.get(leftIndex).getTitle().compareTo(pivot)<0) leftIndex++;
			while(a.get(rightIndex).getTitle().compareTo(pivot)>0) rightIndex--;

			if(leftIndex<=rightIndex) {
				Book swap=a.get(leftIndex);
				a.set(leftIndex,a.get(rightIndex));
				a.set(rightIndex,swap);

				leftIndex++;
				rightIndex--;
			}
		}
		if(lowerIndex<rightIndex) quickSortTitles(a,lowerIndex,rightIndex);
		if(lowerIndex<higherIndex) quickSortTitles(a,leftIndex,higherIndex);

	}
	public static void quickSortYears(ArrayList<Book> a, int lowerIndex, int higherIndex) {
		int leftIndex = lowerIndex;
		int rightIndex = higherIndex;

		int pivot = a.get(lowerIndex+(higherIndex-lowerIndex)/2).getPublished();
		while(leftIndex<=rightIndex) {
			while(a.get(leftIndex).getPublished()<pivot) leftIndex++;
			while(a.get(rightIndex).getPublished()>pivot) rightIndex--;

			if(leftIndex<=rightIndex) {
				Book swap=a.get(leftIndex);
				a.set(leftIndex,a.get(rightIndex));
				a.set(rightIndex,swap);

				leftIndex++;
				rightIndex--;
			}
		}
		if(lowerIndex<rightIndex) quickSortYears(a,lowerIndex,rightIndex);
		if(lowerIndex<higherIndex) quickSortYears(a,leftIndex,higherIndex);

	}
	public static void quickSortGenres(ArrayList<Book> a, int lowerIndex, int higherIndex) {
		int leftIndex = lowerIndex;
		int rightIndex = higherIndex;

		String pivot = a.get(lowerIndex+(higherIndex-lowerIndex)/2).getGenre();
		while(leftIndex<=rightIndex) {
			while(a.get(leftIndex).getGenre().compareTo(pivot)<0) leftIndex++;
			while(a.get(rightIndex).getGenre().compareTo(pivot)>0) rightIndex--;

			if(leftIndex<=rightIndex) {
				Book swap=a.get(leftIndex);
				a.set(leftIndex,a.get(rightIndex));
				a.set(rightIndex,swap);

				leftIndex++;
				rightIndex--;
			}
		}
		if(lowerIndex<rightIndex) quickSortTitles(a,lowerIndex,rightIndex);
		if(lowerIndex<higherIndex) quickSortTitles(a,leftIndex,higherIndex);
	}
	public static void quickSortNames(ArrayList<Character> a, int lowerIndex, int higherIndex) {
		int leftIndex = lowerIndex;
		int rightIndex = higherIndex;

		String pivot = a.get(lowerIndex+(higherIndex-lowerIndex)/2).getName();
		while(leftIndex<=rightIndex) {
			while(a.get(leftIndex).getName().compareTo(pivot)<0) leftIndex++;
			while(a.get(rightIndex).getName().compareTo(pivot)>0) rightIndex--;

			if(leftIndex<=rightIndex) {
				Character swap=a.get(leftIndex);
				a.set(leftIndex,a.get(rightIndex));
				a.set(rightIndex,swap);

				leftIndex++;
				rightIndex--;
			}
		}
		if(lowerIndex<rightIndex) quickSortNames(a,lowerIndex,rightIndex);
		if(lowerIndex<higherIndex) quickSortNames(a,leftIndex,higherIndex);

	}
	public static void quickSortAge(ArrayList<Character> a, int lowerIndex, int higherIndex) {
		int leftIndex = lowerIndex;
		int rightIndex = higherIndex;

		int pivot = a.get(lowerIndex+(higherIndex-lowerIndex)/2).getAge();
		while(leftIndex<=rightIndex) {
			while(a.get(leftIndex).getAge()<pivot) leftIndex++;
			while(a.get(rightIndex).getAge()>pivot) rightIndex--;

			if(leftIndex<=rightIndex) {
				Character swap=a.get(leftIndex);
				a.set(leftIndex,a.get(rightIndex));
				a.set(rightIndex,swap);

				leftIndex++;
				rightIndex--;
			}
		}
		if(lowerIndex<rightIndex) quickSortAge(a,lowerIndex,rightIndex);
		if(lowerIndex<higherIndex) quickSortAge(a,leftIndex,higherIndex);
	}
	public void setup() { //made independent for resetting
		books = new SortedLinkedList<Book>();
		characters = new SortedLinkedList<Character>();
		bookCharMap = new HashedMap<Book,Character>();
		charBookMap = new HashedMap<Character,Book>();
	}
	public void reset() { 
		setup();
	}
	public void save() {
		//SAVE
		//Serialize Book and Character
		//Write to file all objects in lists
		//Write to file all names of objects in maps
		
		//LOAD
		//Deserialize
		//Put in lists
		//Put in maps
		
		try {
			FileOutputStream fileOut = new FileOutputStream("save.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(books);
			out.writeObject(characters);
			out.writeObject(bookCharMap);
			out.writeObject(charBookMap);
			out.close();
			fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void load(){
		try {
			FileInputStream fileIn = new FileInputStream("save.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				books=(SortedLinkedList<Book>)in.readObject();
				characters=(SortedLinkedList<Character>)in.readObject();
				bookCharMap=(HashedMap<Book,Character>)in.readObject();
				charBookMap=(HashedMap<Character,Book>)in.readObject();
			}
			catch(ClassNotFoundException e) {
				System.out.println("Class not found");
			}
			in.close();
			fileIn.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
	public Button editBookButton() {
		Button button = new Button("Edit selected");
		button.setOnAction(e->{
			if(selectedBook!=null) {
				editBookScreen(selectedBook);
				scene.setRoot(root3);
			}
		});
		return button;
	}
	public Button removeBookButton() {
		Button button = new Button("Remove selected");
		button.setOnAction(e->{
			if(selectedBook!=null) {
				books.remove(selectedBook, Book.TitleComparator);
				generalMenuScreen();
				scene.setRoot(root);
			}
		});
		return button;
	}
	public Button editCharacterButton() {
		Button button = new Button("Edit selected");
		button.setOnAction(e->{
			if(selectedCharacter!=null) {
				editCharacterScreen(selectedCharacter);
				scene.setRoot(root7);
			}
		});
		return button;
	}
	public Button removeCharacterButton() {
		Button button = new Button("Remove selected");
		button.setOnAction(e->{
			if(selectedCharacter!=null) {
				characters.remove(selectedCharacter, Character.NameComparator);
				generalMenuScreen();
				scene.setRoot(root);
			}
		});
		return button;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
