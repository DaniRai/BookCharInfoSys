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
public class Character implements Comparable<Character>, java.io.Serializable {
	private String name;
	private int age;
	private char gender;
	private String desc;
	
	public Character(String name, int age, char gender, String desc) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.desc = desc;
	}
	
//----------Getters----------//	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * @return the description
	 */
	public String getDesc() {
		return desc;
	}
	
//----------Setters----------//

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * @param desc the description to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
//----------Comparators----------//
	
	/**
	 * Comparator to compare the names of character
	 */
	public static final Comparator<Character> NameComparator = new Comparator<Character>() {
		public int compare(Character c1, Character c2) {
			return c1.getName().compareTo(c2.getName());
		}
	};
	
	public int compareTo(Character o) {
		return 0;
	}
		
//----------To String----------//
	
	@Override
	public String toString() {
		return "Name: " + name + "\nAge: " + age + "\nGender" + gender + "\nDescription: " + desc;				
	}
	//----------Overrides----------//
	
	/*
	 * Method to create hashCode from fields used to check equality
	 */
	@Override
	public int hashCode() {
		//Create hashcodes from each object field	
		int c1 = this.name.hashCode();
		int c2 = this.desc.hashCode();

		//Create object hash by addition partials and modulating by prime number
		int hash = 13 * c1 + c2;
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
			return this.name.equals(((Character) o).name) && 
					this.desc.equals(((Character) o).desc);
		else
			return false;		           
	}

}
