package controllers;
	
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import models.Book;
import models.Character;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Main extends Application {
	private SortedLinkedList<Book> books;
	private SortedLinkedList<Character> characters;
	private HashedMap<Book, Character> map;
	private TableView table = new TableView();
	
	//----------Constructor---------//
	public Main() {
		books = new SortedLinkedList<Book>();
		characters = new SortedLinkedList<Character>();
		map = new HashedMap<Book,Character>();
	}
	public void listAll(Scene scene) {
    	}
	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = new BorderPane();
			root.setId("root");
			Scene scene = new Scene(root,1900,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//1st menu
			Button listAll = new Button("List all books");
			Button search = new Button("Search");
			Button admin = new Button("Administration");
			Button quit = new Button("Quit");
			
			VBox vbox = new VBox(10);
			
			vbox.getChildren().addAll(listAll,search,admin,quit);
			vbox.setAlignment(Pos.CENTER);
			
			root.setCenter(vbox);
			
			//the different screens/menus
			BorderPane root2 = new BorderPane();
			BorderPane root3 = new BorderPane();
			BorderPane root4 = new BorderPane();
			BorderPane root5 = new BorderPane();
			BorderPane root6 = new BorderPane();
			BorderPane root7 = new BorderPane();
			BorderPane root8 = new BorderPane();
			BorderPane root9 = new BorderPane();
			BorderPane root10 = new BorderPane();
			BorderPane root11 = new BorderPane();
			BorderPane root12 = new BorderPane();
			
			listAll.setOnAction(e->{
				//listAll();
				scene.setRoot(root8);
			});
			admin.setOnAction(e->{
				scene.setRoot(root2);
			});
			VBox vbox3 = new VBox(10);
			search.setOnAction(e->{
				scene.setRoot(root5);
			});
			quit.setOnAction(e->{
				primaryStage.close();
			});
			//#################
			//Admin menu
			root2.setId("root");
			
			Button addBook = new Button("Add a book");
			Button addCharacter = new Button("Add a character");
			Button mainMenu = new Button("Back");
			
			addBook.setOnAction(e->{
				scene.setRoot(root3);
			});
			addCharacter.setOnAction(e->{
				scene.setRoot(root4);
			});
			mainMenu.setOnAction(e->{
				scene.setRoot(root);
			});
			
			VBox vbox2 = new VBox(10);
			vbox2.getChildren().addAll(addBook,addCharacter,mainMenu);
			vbox2.setAlignment(Pos.CENTER);
			root2.setCenter(vbox2);
			
			//#################
			//Add book menu
			root3.setId("root");
			GridPane gp = new GridPane();
			gp.setAlignment(Pos.CENTER);
			
			root3.setCenter(gp);
			Button mainMenu2=new Button("Back");
			Label setTitleL = new Label("Title");
			Label setAuthorL = new Label("Author");
			Label setPublisherL = new Label("Publisher");
			Label setYearL = new Label("Year of publication");
			Label setGenreL = new Label("Genre");
			Label setPlotL = new Label("Plot");
			Label setPagesL = new Label("Number of pages");
			Label setCoverL = new Label("Cover URL");
			Label setISBNL = new Label("ISBN");
			Button addBookBtn = new Button("Add");
			gp.addColumn(0, setTitleL, setAuthorL, setPublisherL, setYearL,
					setGenreL, setPlotL, setPagesL,setCoverL,setISBNL,addBookBtn);
			
			TextField setTitleTF = new TextField();
			TextField setAuthorTF = new TextField();
			TextField setPublisherTF = new TextField();
			TextField setYearTF = new TextField();
			TextField setGenreTF = new TextField();
			TextField setPagesTF = new TextField();
			TextField setPlotTF = new TextField();
			TextField setCoverTF = new TextField();
			TextField setISBNTF = new TextField();
			gp.addColumn(1, setTitleTF, setAuthorTF, setPublisherTF, setYearTF, 
					setGenreTF,setPlotTF, setPagesTF,setCoverTF,setISBNTF,mainMenu2);
			
			addBookBtn.setOnAction(e->{
				books.push(new Book(setTitleTF.getText(),setAuthorTF.getText(),setPlotTF.getText(),
						setPublisherTF.getText(), setGenreTF.getText(),setCoverTF.getText(),
						Integer.parseInt(setYearTF.getText()),Integer.parseInt(setPagesTF.getText()),
						Integer.parseInt(setISBNTF.getText())));
			});
			
			mainMenu2.setOnAction(e->{
				scene.setRoot(root2);
			});
			//#################
			//Add character menu
			root4.setId("root");
			GridPane gp2 = new GridPane();
			gp2.setAlignment(Pos.CENTER);
			
			root4.setCenter(gp2);
			Button mainMenu3=new Button("Back");
			Label setNameL = new Label("Name");
			Label setAgeL = new Label("Age");
			Label setGenderL = new Label("Gender");
			Label setDescriptionL = new Label("Description");
			gp2.addColumn(0, setNameL, setAgeL, setGenderL, setDescriptionL);
			
			TextField setNameTF = new TextField();
			TextField setAgeTF = new TextField();
			TextField setGenderTF = new TextField();
			TextField setDescriptionTF = new TextField();
			gp2.addColumn(1, setNameTF, setAgeTF, setGenderTF, setDescriptionTF,mainMenu3);
			
			
			mainMenu3.setOnAction(e->{
				scene.setRoot(root2);
			});
			//#################
			//Search menu
			root5.setId("root");
			vbox3.setAlignment(Pos.CENTER);
			
			Button mainMenu4=new Button("Back");
			Button searchBook=new Button("Search a book");
			Button searchCharacter=new Button("Search a character");
			
			vbox3.getChildren().addAll(searchBook, searchCharacter, mainMenu4);
			root5.setCenter(vbox3);
			
			searchBook.setOnAction(e->{
				scene.setRoot(root6);
			});
			searchCharacter.setOnAction(e->{
				scene.setRoot(root7);
			});
			mainMenu4.setOnAction(e->{
				scene.setRoot(root);
			});
			
			//#################
			//Search book
			GridPane gp4 = new GridPane();
			gp4.setAlignment(Pos.CENTER);
			root6.setId("root");
			root6.setCenter(gp4);
			
			Button mainMenu5=new Button("Back");
			Label searchTitleL = new Label("Title");
			Label searchAuthorL = new Label("Author");
			Label searchYearL = new Label("Year");
			Label searchPublisherL = new Label("Publisher");
			Label searchGenreL = new Label("Genre");
			gp4.addColumn(0, searchTitleL, searchAuthorL,searchYearL,searchPublisherL,searchGenreL);
			
			TextField searchTitleTF = new TextField();
			TextField searchAuthorTF = new TextField();
			TextField searchYearTF = new TextField();
			TextField searchPublisherTF = new TextField();
			TextField searchGenreTF = new TextField();
			gp4.addColumn(1, searchTitleTF, searchAuthorTF,searchYearTF,searchPublisherTF,searchGenreTF,mainMenu5);
			
			
			mainMenu5.setOnAction(e->{
				scene.setRoot(root5);
			});
			//#################
			//Search character menu
			GridPane gp5 = new GridPane();
			gp5.setAlignment(Pos.CENTER);
			root7.setId("root");
			root7.setCenter(gp5);
			
			Button mainMenu6=new Button("Back");
			Label searchNameL = new Label("Name");
			Label searchAgeL = new Label("Age");
			gp5.addColumn(0, searchNameL, searchAgeL);
			
			TextField searchNameTF = new TextField();
			TextField searchAgeTF = new TextField();
			gp5.addColumn(1, searchNameTF, searchAgeTF,mainMenu6);
			
			
			mainMenu6.setOnAction(e->{
				scene.setRoot(root5);
			});
			//#################
			//#################
			//Search character menu
			VBox vbox4 = new VBox();
	        root8.setId("root");
			root8.setCenter(vbox4);
			
		    table.setEditable(true);
		    
	        TableColumn TitleCol = new TableColumn("Title");
	        TableColumn AuthorCol = new TableColumn("Author");
	        TableColumn PublisherCol = new TableColumn("Publisher");
	        TableColumn GenreCol = new TableColumn("Genre");
	        TableColumn YearCol = new TableColumn("Year");
	        TableColumn PlotCol = new TableColumn("Plot");
	        TableColumn CoverURLCol = new TableColumn("Cover URL");
	        TableColumn PagesCol = new TableColumn("Pages");
	        TableColumn ISBNCol = new TableColumn("ISBN");
	        
	        table.getColumns().addAll(TitleCol, AuthorCol, PublisherCol,
	        		GenreCol, YearCol, PlotCol,
	        		CoverURLCol, PagesCol, ISBNCol);
	 
	        vbox4 = new VBox();
	        vbox4.setSpacing(5);
	        vbox4.setPadding(new Insets(10, 0, 0, 10));
	        vbox4.getChildren().addAll(table);
	 
	        root8.setCenter(vbox4);

			
			mainMenu6.setOnAction(e->{
				scene.setRoot(root);
			});
			//#################
		} catch(Exception e) {
			e.printStackTrace();
		}
	 }
	
	public static void main(String[] args) {
		launch(args);
	}
}
