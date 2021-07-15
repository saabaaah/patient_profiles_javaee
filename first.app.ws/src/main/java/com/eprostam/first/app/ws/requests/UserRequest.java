package com.eprostam.first.app.ws.requests;


public class UserRequest {

	// Attributes
	private String name;
	private String birthDate;
	private String profession;
	private int gender;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "UserRequest [name=" + name + ", birthDate=" + birthDate + ", profession=" + profession + ", gender="
				+ gender + "]";
	}
	
	
	
}
