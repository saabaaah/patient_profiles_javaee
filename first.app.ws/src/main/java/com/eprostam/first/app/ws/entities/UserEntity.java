package com.eprostam.first.app.ws.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity(name="users")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8549558072993179417L;
	
	// identifiant de l'enregistremen
	@Id
	@GeneratedValue
	private long id;
	
	private String userId;
	
	@Column(nullable=false, length = 50)
	private String firstName;

	@Column(nullable=false, length = 50)
	private String lastName;

	@Column(nullable=false, length = 120, unique=true)
	private String email;
	
	@Column(nullable=true, length = 255)
	private String profession;

	@Column(nullable=true)
	private int gender;
	
	@Column(nullable=true)
	private String birthDate;
	
	@Column(nullable=false)
	private String encryptedPassword;

	@Column(nullable=true)
	private String emailVerificationToken;
	
	@Column(nullable = false)
	private boolean emailVerificationStatus = false;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;
	
	// contact of the user
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private ContactEntity contact;

	
	// the groups of this user
	// @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
	
	// private Set<GroupEntity> groups = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", profession=" + profession + ", gender=" + gender + ", encryptedPassword="
				+ encryptedPassword + ", emailVerificationToken=" + emailVerificationToken
				+ ", emailVerificationStatus=" + emailVerificationStatus + "]";
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public ContactEntity getContact() {
		return contact;
	}

	public void setContact(ContactEntity contact) {
		this.contact = contact;
	}

	/*
	 * public Set<GroupEntity> getGroups() { return groups; }
	 * 
	 * public void setGroups(Set<GroupEntity> groups) { this.groups = groups; }
	 */
	
	
	

	
}
