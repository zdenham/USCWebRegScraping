package Scraping;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args){
		Departments myDepartments = new Departments();
		
		try{
			myDepartments.findDepartmentsFromWeb();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		System.out.println("Number of Departments: " + myDepartments.getSize());
		System.out.println("Here are a list of USC's department codes:");
		myDepartments.printDepartments();
		
		System.out.println();
		
		SectionSessions myCourses = new SectionSessions();
		try{
			myCourses.findCoursesFromWeb(myDepartments);
		} catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println("The following are ALL the sections offered at USC spring 2017: ");
		
		myCourses.printClasses();
		
		myCourses.saveClassesToFile("courses-usc.txt");
		
		System.out.println("There are " + myCourses.getSize() + " sections listed.");
		
		
	}
}
