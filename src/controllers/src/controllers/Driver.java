//----------Imports & Packages----------//
package controllers;

/**
 * @author Daniel Quinlan
 * @studentNumber 01133940
 * @course Entertainment Systems Yr 2
 * 
 * @param <T>
 */
public class Driver {
	private CLI cli = new CLI();
	
	public Driver() {
		cli.console(); //for Debug
	}

	public static void main(String[] args) {
		Driver app = new Driver();
	}

}
