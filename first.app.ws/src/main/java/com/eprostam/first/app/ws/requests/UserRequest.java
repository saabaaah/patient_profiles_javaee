package com.eprostam.first.app.ws.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {

	// Attributes
	
	@NotBlank(message="the first name can't be empty!")
	private String firstName;
	
	@NotBlank(message="the last name can't be empty!")
	private String lastName;
	
	@NotBlank(message="the email can't be empty!")
	@Email(message="the email doesn't respect the right format!")
	private String email;
	
	@NotBlank(message="the last name can't be empty!")
	private String birthDate;
	
	@NotBlank(message="the birth date can't be empty!")
	private String profession;
	
	@NotBlank(message="the password can't be empty!")
	@Size(min=8, max=12, message="the password must be between 8 and 12 caracters!")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message="The password must contain at least one of the following : UpperCase, LowerCase and Number !")
	private String password;
	
	@NotNull(message="the gender can't be empty!")
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
