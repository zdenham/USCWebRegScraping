package Scraping;

public class Classroom {
	String building = "";
	String roomNumber = "";
			
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Classroom(String building, String roomNumber){
		this.building = building;
		this.roomNumber = roomNumber;
	}
	
	
}
