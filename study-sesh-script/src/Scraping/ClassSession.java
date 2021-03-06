package Scraping;

public class ClassSession {
	private String code = "";
	private String location = "";
	private String startTime = "";
	private String endTime = "";
	private String day = "";
	private String room = "";
	private String building = "";
	
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public ClassSession(){
	}
	
	public ClassSession(String code, String location, String startTime, String endTime, String day){
		this.code = code;
		this.location = location;
		
		int splitIndex = getEndOfLetters(location);
		
 		this.building = location.substring(0, splitIndex);
 		this.room = location.substring(splitIndex);
 		
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getLocation(){
		return this.location;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	private int getEndOfLetters(String location){
 		char[] charLocation = location.toCharArray();
 		
 		for(int i = 0; i < charLocation.length; i++){
 			if(charLocation[i] <= 9 && charLocation[i] >= 0){
 				return i;
 			}
 		}
 		
 		return charLocation.length;
 	}
	
}
