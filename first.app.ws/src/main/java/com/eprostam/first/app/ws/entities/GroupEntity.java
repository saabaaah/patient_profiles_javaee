package com.eprostam.first.app.ws.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity(name="groups")
public class GroupEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2191991017649850141L;

	
	@GeneratedValue
	@Id
	private long id;
	
	@Column(length = 30, name="name")
	@NotBlank
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable( name = "users_groups", 
				joinColumns = {@JoinColumn(name="group_id")},
				inverseJoinColumns = {@JoinColumn(name="user_id")})
	private Set<UserEntity> users = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
	
	
	
	
	
	
}


