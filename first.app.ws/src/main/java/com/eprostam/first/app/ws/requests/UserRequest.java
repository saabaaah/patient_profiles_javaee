package com.eprostam.first.app.ws.requests;


public class UserRequest {

	// Attributes
	private String firstName;
	private String lastName;
	private String email;
	private String birthDate;
	private String profession;
	private String password;
	private int gender;
	
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
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserRequest [name=" + firstName + lastName.toUpperCase() + ", birthDate=" + birthDate + ", profession=" + profession + ", gender="
				+ gender + "]";
	}
	
	
	
	
	
}
