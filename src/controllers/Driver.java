//----------Imports & Packages----------//
package controllers;
import application.*;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Daniel Quinlan
 * @studentNumber 01133940
 * @course Entertainment Systems Yr 2
 * 
 * @param <T>
 */

public class Driver {

	public Driver() {
		CLI cli = new CLI();
		cli.console(); //for Debug
	}
	public static void main(String[] args) {
		Driver app = new Driver();
		//Application.launch(GUI.class, args);
	}
	
}
