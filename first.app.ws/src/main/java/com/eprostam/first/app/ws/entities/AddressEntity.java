package com.eprostam.first.app.ws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="addresses")
public class AddressEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2407018848438603816L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=30, nullable = false)
	private String addressId;
	
	@Column(length=20, nullable = false)
	private String city;
	
	@Column(length=10, nullable = false)
	private String zip;
	
	@Column(length=50, nullable = false)
	private String street;
	
	@Column(length=20, nullable = false)
	private String country;

	@Column(length=10, nullable = false)
	private String type;

	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity user;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAddressId() {
		return addressId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}
	

}
