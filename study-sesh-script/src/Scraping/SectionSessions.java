package Scraping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.PrintWriter;

//This class uses a department list to find all meeting times and locations for every class offered at USC
public class SectionSessions {
	private Vector<ClassSession> classesUSC = new Vector<ClassSession>();
	
	public SectionSessions (){
		
	}
	
	public Vector<ClassSession> getClasses(){
		return this.classesUSC;
	}
	
	public int getSize(){
		return this.classesUSC.size();
	}
	
	public void setClasses(Vector<ClassSession> classesUSC){
		this.classesUSC = classesUSC;
	}
	
	public void findCoursesFromWeb(Departments departmentsUSC) throws IOException{
		
		Vector<String> myDepartments = departmentsUSC.getDepartments();
		
		for(int i = 0; i < myDepartments.size(); i++){
			parseDepartment(myDepartments.get(i));
		}
		
	}
	
	private void parseDepartment(String departmentName) throws IOException{
		System.out.println("Scraping department " + departmentName);
		
		String urlString = "http://web-app.usc.edu/web/soc/api/classes/" + departmentName + "/20171";
		
		URL url = new URL(urlString);
		URLConnection uc = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		
		String inputLine = "";
		
		inputLine = in.readLine();
		
		JSONArray offeredCourses = null;
		
		try{	
			JSONObject json = new JSONObject(inputLine);
			JSONObject courseObject = json.getJSONObject("OfferedCourses");
			offeredCourses = courseObject.getJSONArray("course");
		} catch (JSONException e){
			System.out.println("department " + departmentName + " did not have any courses");
		}
		
		if(offeredCourses == null){
			return;
		}
		
		for(int i = 0; i < offeredCourses.length(); i++){
			parseCourse(offeredCourses.getJSONObject(i));
		}
	}
	
	private void parseCourse(JSONObject course){
		JSONArray sectionArray = null;
		JSONObject sectionObject = null;
		Object sectionData = null;
		String code = course.getString("PublishedCourseID");
		
		try{
			JSONObject courseData = course.getJSONObject("CourseData");
			sectionData = courseData.get("SectionData");
			
			if(sectionData instanceof JSONObject){
				sectionObject = (JSONObject)sectionData;
			} else if(sectionData instanceof JSONArray){
				sectionArray = (JSONArray)sectionData;
			}
			
		} catch(JSONException e){
			System.out.println("A course didn't have any section data");
		}
		
		if(sectionArray == null){
			parseSection(sectionObject, code);
			return;
		}
		
		for(int i = 0; i < sectionArray.length(); i++){
			parseSection(sectionArray.getJSONObject(i), code);
		}
	}
	
	private void parseSection(JSONObject section, String code){
		String location = "";
		String startTime = "";
		String endTime = "";
		String day = "";
		
		try {
			location = section.getString("location");
			startTime = section.getString("start_time");
			endTime = section.getString("end_time");
			day = section.getString("day");
			
			ClassSession myCourse = new ClassSession(code,location,startTime,endTime,day);
			
			if(myCourse.getLocation().compareTo("OFFICE") != 0){
				classesUSC.add(myCourse);
			}
			
		} catch (JSONException e){
			System.out.println("No location was given for a section"); 
		}
			
	}
	
	public void printClasses(){
		
		for(int i = 0; i < classesUSC.size(); i++){
			ClassSession myClass = classesUSC.get(i);
			System.out.print("Class code: " + myClass.getCode());
			System.out.print(" Location: " + myClass.getLocation());
			System.out.print(" section meeting time: ");
			
			System.out.print(" Start: " + myClass.getStartTime() + " End: " + myClass.getEndTime() + " Day: " + myClass.getDay());
			
			System.out.println();
		}
	}
	
	public void saveClassesToFile(String fileName){
		try{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    for(int i = 0; i < classesUSC.size(); i++){
				ClassSession myClass = classesUSC.get(i);
				writer.print(" Class title: " + myClass.getCode());
				writer.print(" Location: " + myClass.getLocation());
				writer.print(" section meeting time: ");
				writer.print(" Start: " + myClass.getStartTime() + " End: " + myClass.getEndTime() + " Day: " + myClass.getDay());
				writer.println();
			}
		    writer.close();
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
	
}
