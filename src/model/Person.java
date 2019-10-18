package model;

public class Person {

	public int personid;
	public  String firstName;



	public String lastName;
	public String room;
	public String startHour;
	public String finishHour;
	
	public Person(){
		
	}
	
	public Person(int personid,String firstName,String lastName,String room,String startHour,String finishHour){
		this.personid = personid;
		this.firstName=firstName;
		this.lastName=lastName;
		this.room =room;
		this.startHour=startHour;
		this.finishHour=finishHour;
	}
	public Person(String firstName){
		this.firstName= firstName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	public String getRoom(){
		return room;
	}
	public String getStartHour(){
		return startHour;
	}
	public String getfinishHour(){
		return finishHour;
	}


	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}
	
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	
	public void setRoom(String room){
		this.room=room;
	}
	public void setStartHour(String startHour){
		this.startHour=startHour;
	}
	public void setFinishHour(String finishHour){
		this.finishHour=finishHour;
	}

	public String toString() {
		return firstName +" "+ lastName +" "+ room +" "+ startHour +" "+ finishHour +" ";
	}
	
	
	
	
	
	
}
